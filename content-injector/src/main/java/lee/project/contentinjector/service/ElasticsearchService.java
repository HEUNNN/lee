package lee.project.contentinjector.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.SourceConfig;
import co.elastic.clients.elasticsearch.indices.*;
import lee.project.contentinjector.constant.ElasticSearchConstant;
import lee.project.contentinjector.constant.RedisConstant;
import lee.project.contentinjector.dto.FileSource;
import lee.project.contentinjector.util.ElasticsearchUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn("RedisService")
public class ElasticsearchService {
    @Autowired
    ElasticsearchClient client;
    @Autowired
    RedisService redisService;

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchService.class);
    private static final String CONTENT = "content";
    private static final int SEARCH_SIZE = 100;

    private static final String TARGET_SEQ = "targetSeq";

    public void createFileIndex() throws IOException {
        try {
            ElasticsearchIndicesClient indexClient = client.indices();
            InputStream createIndexJson = this.getClass().getClassLoader().getResourceAsStream(ElasticSearchConstant.FILE_INDEX_JSON_PATH);

            CreateIndexResponse createIndexResponse = indexClient.create(
                    new CreateIndexRequest.Builder()
                            .index(ElasticSearchConstant.FILE_INDEX_NAME)
                            .aliases(ElasticSearchConstant.FILE_INDEX_ALIAS, new Alias.Builder().build())
                            .withJson(createIndexJson)
                            .build()
            );
        } catch (Exception e) {
            logger.error("create file index error, e mgs = " + e.getMessage());
            throw e;
        }
    }

    public boolean isExistsIndex(String indexName) throws IOException {
        try {
            ElasticsearchIndicesClient indexClient = client.indices();
            ExistsRequest existsRequest = new ExistsRequest.Builder()
                    .index(indexName)
                    .build();
            return indexClient.exists(existsRequest).value();
        } catch (Exception e) {
            logger.error("is exists index error, e mgs = " + e.getMessage());
            throw e;
        }
    }

    public boolean bulkIndex(String indexName, List<FileSource> fileSources) throws IOException {
        try {
            boolean isSuccess = true;
            BulkRequest.Builder bulkReqBuilder = new BulkRequest.Builder();
            bulkReqBuilder.index(indexName);

            for (FileSource fileSource : fileSources) {

                String id = ElasticsearchUtil.getDocumentId(fileSource);

                bulkReqBuilder.operations(op -> op
                        .index(i -> i
                                .id(id)
                                .document(fileSource)));
            }
            BulkResponse response = client.bulk(bulkReqBuilder.build());

            if (response.errors()) {
                isSuccess = false;
                for (BulkResponseItem item : response.items()) {
                    logger.error("bulk failed item index name = " + item.index() + ", id = " + item.id());
                }
            }

            return isSuccess;
        } catch (Exception e) {
            logger.error("bulk index error, e mgs = " + e.getMessage());
            throw e;
        }
    }

    public void contentInject(String indexName, List<String> targetSeqList) { // targetSeqList == groupSeqList
        for (String targetSeq : targetSeqList) {
            if (redisService.isExistsContentInjectStatus(targetSeq)) {
                continue;
            }
            try {
                redisService.setContentInjectStatus(targetSeq, RedisConstant.STATUS_START);
                // empty content 필드 total count 검색
                SourceConfig fetchSourceConfig = new SourceConfig.Builder()
                        .filter(f -> f.excludes("uniqueSeq")).build();

                SearchRequest searchRequest = new SearchRequest.Builder()
                        .index(indexName)
                        .query(getQueryForEmptyContentField(indexName, targetSeq)._toQuery())
                        .source(fetchSourceConfig) // total hit 만 얻을 목적이라 fetchsource를 최대한 적게
                        .build();


                SearchResponse<FileSource> hits = client.search(searchRequest, FileSource.class);

                long emptyContentTCnt = 0;
                long emptyContentCnt = 0;

                if (hits.hits().total() != null) {
                    emptyContentTCnt = hits.hits().total().value();
                }

                List<FieldValue> searchAfterValues = new ArrayList<>();

                while (true) {
                    SearchRequest.Builder searchReqBuilder = new SearchRequest.Builder();

                    if (!searchAfterValues.isEmpty()) {
                        searchReqBuilder.searchAfter(searchAfterValues);
                    }

                    searchReqBuilder.index(indexName)
                                    .size(SEARCH_SIZE)
                                    .query(getQueryForEmptyContentField(indexName, targetSeq)._toQuery());

                    SearchResponse<FileSource> emptyContentHit = client.search(searchReqBuilder.build(), FileSource.class);
                    List<Hit<FileSource>> hitFileSourceList = emptyContentHit.hits().hits();
                    long hitFileSourceCnt = hitFileSourceList.size();
                    emptyContentCnt += hitFileSourceCnt;

                    // TODO content inject logic -> parsing and bulk indexing

                    if (!hitFileSourceList.isEmpty()) {
                        Hit<FileSource> last = hitFileSourceList.get(hitFileSourceList.size() - 1);
                        searchAfterValues = last.sort();
                    }

                    if (hitFileSourceCnt < SEARCH_SIZE || emptyContentTCnt <= emptyContentCnt) {
                        break;
                    }
                }

                System.out.println("targetSeq = " + targetSeq + ", total cnt = " + emptyContentTCnt + ", cnt = " + emptyContentCnt + ", fetch size = " + SEARCH_SIZE);
                redisService.setContentInjectStatus(targetSeq, RedisConstant.STATUS_END);
            } catch (Exception e) {
                redisService.setContentInjectStatus(targetSeq, RedisConstant.STATUS_EXCEPTION);
            }
        }
    }

    public BoolQuery getQueryForEmptyContentField(String indexName, String targetSeq) {
        try {
            Query exitsFieldQuery = new Query.Builder()
                    .exists(e -> e.field(CONTENT)).build();

            Query hasTextQuery = new Query.Builder()
                    .regexp(r -> r.field(CONTENT)
                                  .value(".+")).build();

            Query findTargetSeqQuery = new Query.Builder()
                    .term(t -> t.field(TARGET_SEQ)
                                .value(targetSeq)).build();

            return new BoolQuery.Builder()
                    .must(exitsFieldQuery)
                    .mustNot(hasTextQuery)
                    .must(findTargetSeqQuery).build();
        } catch (Exception e) {
            logger.error("make for search content field is empty error, e mgs = " + e.getMessage());
            throw e;
        }
    }
}
