package example.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDataRefactoring {

    private static final String GROUP_SEQ = "groupSeq";
    private static final String JOB_TYPE = "jobType";
    private static final String PK_SEQ = "pkSeq";
    private static final List<String> TARGET_JOB_LIST = Arrays.asList("board", "note", "schedule");
    private static final int FETCH_MAX_SIZE = 3;

    @Autowired
    private RestHighLevelClient client;


    public void getFileIdWithoutInfo() throws IOException {
        Object[] searchAfterValues = null;
        CountResponse fileDocCnt = client.count(new CountRequest("file"), RequestOptions.DEFAULT);
        long cnt = 0L;

        while (true) {

            SearchRequest fileSearchReq = createFileSearchRequest(searchAfterValues);
            SearchResponse fileSearchRes = client.search(fileSearchReq, RequestOptions.DEFAULT);
            SearchHit[] fileSearchHits = fileSearchRes.getHits().getHits();
            cnt += (long) fileSearchHits.length;

            List<String> fileIdsForDel = findFileIdsForDel(fileSearchHits);
            deleteBulk(fileIdsForDel);

            if (fileSearchHits.length < FETCH_MAX_SIZE || (cnt >= fileDocCnt.getCount())) {
                break;
            }

            if (fileSearchHits.length > 0) { // 다음 페이지 검색을 위해 마지막 문서 정보 저장
                SearchHit lastHit = fileSearchHits[fileSearchHits.length - 1];
                searchAfterValues = lastHit.getSortValues();
            } else {
                break;
            }
        }
    }

    private SearchRequest createFileSearchRequest(Object[] searchAfterValues) {
        SearchSourceBuilder fileSearchSource = new SearchSourceBuilder();

        if (searchAfterValues != null) {
            fileSearchSource.searchAfter(searchAfterValues);
        }

        SearchSourceBuilder sourceBuilder =
                fileSearchSource.query(QueryBuilders.matchAllQuery())
                                .size(FETCH_MAX_SIZE)
                                .sort("_id", SortOrder.ASC)
                                .fetchSource(new String[]{GROUP_SEQ, JOB_TYPE, PK_SEQ}, null);

        return new SearchRequest("file").source(sourceBuilder);
    }

    private List<String> findFileIdsForDel(SearchHit[] fileSearchHits) throws IOException {
        List<String> fileIdForDel = new ArrayList<>();
        for (SearchHit fileSearchHit : fileSearchHits) {
            Map<String, Object> sourceAsMap = fileSearchHit.getSourceAsMap();
            String groupName = getString(sourceAsMap, GROUP_SEQ);
            String jobType = getString(sourceAsMap, JOB_TYPE);
            String pkSeq = getString(sourceAsMap, PK_SEQ);

            if (StringUtils.isEmpty(groupName) || StringUtils.isEmpty(jobType) || StringUtils.isEmpty(pkSeq)) {
                continue;
            }

            if (!TARGET_JOB_LIST.contains(jobType)) {
                continue;
            }

            SearchRequest contentSearchReq = createContentSearchRequest(groupName, jobType, pkSeq);
            SearchResponse contentSearchRes = client.search(contentSearchReq, RequestOptions.DEFAULT);

            if (contentSearchRes.getHits().getTotalHits().value == 0L) {
                fileIdForDel.add(fileSearchHit.getId());
            }
        }
        return fileIdForDel;
    }

    private String getString(Map<String, Object> sourceAsMap, String fieldName) {
        if (sourceAsMap == null) {
            return null;
        }
        Object value = sourceAsMap.get(fieldName);
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    private SearchRequest createContentSearchRequest(String groupName, String jobType, String pkSeq) {
        BoolQueryBuilder bq = new BoolQueryBuilder()
                .must(new TermQueryBuilder(GROUP_SEQ, groupName))
                .must(new TermQueryBuilder(JOB_TYPE, jobType))
                .must(new TermQueryBuilder(PK_SEQ, pkSeq));

        SearchSourceBuilder sourceBuilder =
                new SearchSourceBuilder()
                        .query(bq)
                        .fetchSource(new String[0], new String[0]);

        return new SearchRequest("content").source(sourceBuilder);
    }

    public void deleteBulk(List<String> ids) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (String id : ids) {
            bulkRequest.add(new DeleteRequest("file").id(id));
        }

        if (bulkRequest.numberOfActions() > 0) {
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
        }
    }

    public void deleteByBoolQuery(String indexName, String groupName, String jobType) throws IOException {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
                .must(new TermQueryBuilder("groupName", groupName))
                .must(new TermQueryBuilder("jobType", jobType));

        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(indexName);
        deleteByQueryRequest.setQuery(boolQueryBuilder);
        client.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
    }
}