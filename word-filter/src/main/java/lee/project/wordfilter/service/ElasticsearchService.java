package lee.project.wordfilter.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.InlineScript;
import co.elastic.clients.elasticsearch._types.Script;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import co.elastic.clients.json.JsonData;
import lee.project.wordfilter.dto.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ElasticsearchService {

    @Autowired
    ElasticsearchClient client;
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchService.class);
    private static final String WORD_INDEX_NAME = "word";
    private static final String WORD_INDEX_FILE_PATH = "elasticsearch/word.json";

    public void createWordIndex() throws IOException { // create word index for test
        ElasticsearchIndicesClient indexClient = client.indices();

        InputStream createIndexJson = this.getClass().getClassLoader().getResourceAsStream(WORD_INDEX_FILE_PATH);

        CreateIndexResponse createIndexResponse = indexClient.create(
                new CreateIndexRequest.Builder()
                        .index(WORD_INDEX_NAME)
                        .withJson(createIndexJson)
                        .build()
        );
    }

    public void scripting(String indexName) throws IOException {
        try {
            List<String> ids = new ArrayList<>();
            SearchRequest searchRequest = new SearchRequest.Builder()
                    .index(indexName)
                    .query(q -> q.matchAll(ma -> ma))
                    .size(100)
                    .build();
            SearchResponse<Word> search = client.search(searchRequest, Word.class);
            for (Hit<Word> hit : search.hits().hits()) {
                ids.add(hit.id());
            }

            String strScript = "if(ctx._source.desc.length()==0){ctx._source.desc=params.customDesc;}else{ctx.op='none';}";
            InlineScript inlineScript = new InlineScript.Builder().lang("painless").source(strScript).params("customDesc", JsonData.of("hello")).build();
            Script script = new Script.Builder().inline(inlineScript).build();

            for (String id : ids) {
                UpdateRequest<Object, Object> build = new UpdateRequest.Builder<>()
                        .index(indexName)
                        .script(script)
                        .id(id).build();
                client.update(build, Object.class);
            }


        } catch (Exception e) {
            throw e;
        }
    }

    public void usingRegex(String indexName) {
        try {
            Query exitsFieldQuery = new Query.Builder()
                    .exists(e -> e.field("desc")).build();

            Query hasTextQuery = new Query.Builder()
                    .regexp(r -> r.field("desc")
                                  .value(".+")).build();

            Query boolQuery = new BoolQuery.Builder()
                    .must(exitsFieldQuery)
                    .mustNot(hasTextQuery).build()._toQuery();

            SearchRequest searchRequest = new SearchRequest.Builder()
                    .query(boolQuery)
                    .index(indexName).build();

            // 검색 실행
            SearchResponse<Word> searchResponse = client.search(searchRequest, Word.class);

            List<String> documentIds = new ArrayList<>();
            // 결과 처리
            for (Hit<Word> hit : searchResponse.hits().hits()) {
                String id = hit.id();
                System.out.println("Found document with ID: " + id);
                documentIds.add(id);
            }

            String strScript = "if(ctx._source.desc.length()==0){ctx._source.desc=params.customDesc;}else{ctx.op='none';}";
            InlineScript inlineScript = new InlineScript.Builder().lang("painless").source(strScript).params("customDesc", JsonData.of(UUID.randomUUID().toString())).build();
            Script script = new Script.Builder().inline(inlineScript).build();

            for (String id : documentIds) {
                UpdateRequest<Object, Object> build = new UpdateRequest.Builder<>()
                        .index(indexName)
                        .script(script)
                        .id(id).build();
                client.update(build, Object.class);
            }

            BulkRequest.Builder br = new BulkRequest.Builder();
            br.operations(op ->
                    op.update(u ->
                            u.index(indexName)
                             .id("id")
                             .action(a ->
                                     a.script(script)))).build();
        } catch (Exception e) {

        }
    }

    public void bulkUpdateWithScript(List<String> ids, String indexName) throws IOException {
        BulkRequest.Builder br = new BulkRequest.Builder();
        for (String id : ids) {
            String strScript = "if(ctx._id==params.docId){ctx._source.desc=params.customDesc;}else{ctx.op='none';}";
            InlineScript inlineScript = new InlineScript.Builder().lang("painless").source(strScript).params("customDesc", JsonData.of(UUID.randomUUID().toString())).params("docId", JsonData.of(id)).build();
            Script script = new Script.Builder().inline(inlineScript).build();
            br.operations(op ->
                    op.update(u ->
                            u.index(indexName)
                             .id(id)
                             .action(a ->
                                     a.script(script))));
        }
        BulkRequest bulkRequest = br.build();
        client.bulk(bulkRequest);
    }

    // 업데이트할 필드만 포함하는 부분 업데이트 클래스
    public static class DocumentPartialUpdate {
        private String desc;

        public DocumentPartialUpdate(String desc) {
            this.desc = desc;
        }

        // getter 및 setter
    }

    public BulkResponse bulkWords(List<Word> words) throws IOException { // bulk indexing document
        BulkRequest.Builder br = new BulkRequest.Builder();

        for (Word word : words) {
            br.operations(op ->
                    op.index((idx) ->
                            idx.index(WORD_INDEX_NAME)
                               .document(word)
                               .id(String.valueOf(word.getNumber())))
            );
        }
        BulkResponse bulkRes = client.bulk(br.build());
        if (bulkRes.errors()) {
            logger.error("bulk insert fail");
        }
        return bulkRes;
    }

    public void index(String indexName, Object document) throws IOException { // indexing document
        IndexRequest<Object> singleIndexingReq = IndexRequest.of(i -> i
                .index(indexName)
                .document(document));
        IndexResponse singleIndexingRes = client.index(singleIndexingReq);

    }

    public void bulk(String indexName, List<Object> param) throws IOException { // bulk indexing document
        BulkRequest.Builder br = new BulkRequest.Builder();

        for (Object doc : param) {
            br.operations(op ->
                    op.index((idx) ->
                            idx.index(indexName)
                               .document(doc)
                    )
            );
        }
        BulkResponse bulkRes = client.bulk(br.build());
        if (bulkRes.errors()) {
            System.out.println("bulk insert fail");
        }
    }

    public void searchByTermQuery(String indexName, String groupName, String jobType, String pkSeq) throws IOException {
        Query term1 = TermQuery.of(t -> t.field("groupName").value(groupName))._toQuery();
        Query term2 = TermQuery.of(t -> t.field("jobType").value(jobType))._toQuery();
        Query term3 = TermQuery.of(t -> t.field("pkSeq").value(pkSeq))._toQuery();

    }
}
