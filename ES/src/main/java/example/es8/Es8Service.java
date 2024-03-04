package example.es8;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.SourceConfig;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.es8.dto.MatchAllResponse;
import example.model.ContentDoc;
import example.model.FileDoc;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
public class Es8Service {

    private final ElasticsearchClient client;
    private static int max = 1000;
//    private static ObjectMapper objectMapper = new ObjectMapper();

    public void createIndex(String indexName) throws IOException {
        ElasticsearchIndicesClient indexClient = client.indices();

        InputStream createIndexJson = this.getClass().getClassLoader().getResourceAsStream("elasticsearch/" + indexName + ".json");

        CreateIndexResponse createIndexResponse = indexClient.create(
                new CreateIndexRequest.Builder()
                        .index(indexName)
                        .withJson(createIndexJson)
                        .build()
        );
    }

    public void singleIndexing(String indexName, Object document) throws IOException {
        IndexRequest<Object> singleIndexingReq = IndexRequest.of(i -> i
                .index(indexName)
                .document(document));
        IndexResponse singleIndexingRes = client.index(singleIndexingReq);

    }

    public void bulk(String indexName, List<Object> param) throws IOException {
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

        SearchResponse<ContentDoc> response = client.search(s -> s
                        .index(indexName)
                        .query(q -> q
                                .bool(b -> b
                                        .must(term1)
                                        .must(term2)
                                        .must(term3))
                        ),
                ContentDoc.class
        );

        List<Hit<ContentDoc>> hits = response.hits().hits();
        for (Hit<ContentDoc> hit : hits) {
            ContentDoc contentDoc = hit.source();
            System.out.println("hit content title: " + contentDoc.title);
        }
    }

    public void deleteByTermQuery(String indexName, String groupName, String jobType, String pkSeq) throws IOException {
        Query term1 = TermQuery.of(t -> t.field("groupName").value(groupName))._toQuery();
        Query term2 = TermQuery.of(t -> t.field("jobType").value(jobType))._toQuery();
        Query term3 = TermQuery.of(t -> t.field("pkSeq").value(pkSeq))._toQuery();

        DeleteByQueryResponse deleteByQueryResponse = client.deleteByQuery(d -> d.index(indexName)
                                                                                 .query(q -> q.bool(b -> b.must(term1)
                                                                                                          .must(term2)
                                                                                                          .must(term3))));
        if (deleteByQueryResponse.deleted() > 0) {
            System.out.println("delete by query is success");
        }
    }

    public void matchAllQuery(String indexName) throws IOException {
        SearchResponse<Object> search = client.search(s -> s
                .index(indexName)
                .query(q -> q
                        .matchAll(m -> m)), Object.class);
        System.out.println(search.hits().hits().size());
    }

    public void dateSort(String indexName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SortOptions sortOptions = new SortOptions.Builder()
                .field(f -> f
                        .field("date")
                        .order(SortOrder.Asc))
                .build();
        SearchResponse<Object> search = client.search(s -> s
                        .index(indexName)
                        .query(q -> q
                                .matchAll(m -> m))
                        .sort(sortOptions)
                        .size(100),
                Object.class);

        for (Hit<Object> hit : search.hits().hits()) {
            ContentDoc content = objectMapper.readValue(objectMapper.writeValueAsString(hit.source()), ContentDoc.class);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateToSTr = format.format(content.date);

            System.out.println("ID : " + hit.id() + ", pkSeq : " + content.pkSeq + ", Date type: " + dateToSTr + ", str Date : " + content.strDate);
        }
    }

    public void strDateSort(String indexName) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        SortOptions sortOptions = new SortOptions.Builder()
                .field(f -> f
                        .field("strDate")
                        .order(SortOrder.Asc))
                .build();
        SearchResponse<Object> search = client.search(s -> s
                        .index(indexName)
                        .query(q -> q
                                .matchAll(m -> m))
                        .sort(sortOptions)
                        .size(100),
                Object.class);

        for (Hit<Object> hit : search.hits().hits()) {
            ContentDoc content = objectMapper.readValue(objectMapper.writeValueAsString(hit.source()), ContentDoc.class);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateToSTr = format.format(content.date);

            System.out.println("ID : " + hit.id() + ", pkSeq : " + content.pkSeq + ", Date type: " + dateToSTr + ", str Date : " + content.strDate);
        }
    }

    public void searchAfter(String indexName) throws IOException {

        List<FieldValue> searchAfter = new ArrayList<>();
        int count = 0;
        while (true) {
            SearchRequest searchRequest = getSearchRequest(searchAfter, "content");
            SearchResponse<MatchAllResponse> searchResponse = client.search(searchRequest, MatchAllResponse.class);
            List<Hit<MatchAllResponse>> hits = searchResponse.hits().hits();
            count += hits.size();
//            Set<String> fileIds = fileIdsForDel(hits);
//            bulkDelete("file", fileIds);

            if (hits.size() < max) {
                break;
            }

            if (!hits.isEmpty()) { // 다음 페이지 검색을 위해 마지막 문서 정보 저장
                Hit<MatchAllResponse> last = hits.get(hits.size() - 1);
                searchAfter = last.sort();
            }

        }

        System.out.println(count);

    }

    public SearchRequest getSearchRequest(List<FieldValue> searchAfter, String indexName) {
        SourceConfig fetchSource = new SourceConfig.Builder().filter(f -> f.includes("groupName", "jobType", "pkSeq", "fileList")).build();
        SortOptions build = new SortOptions.Builder().doc(d -> d.order(SortOrder.Asc)).build();

        SearchRequest.Builder searchRequest = new SearchRequest.Builder();
        searchRequest.index(indexName)
                     .query(q -> q.matchAll(ma -> ma))
                     .size(max)
                     .sort(build)
                     .source(fetchSource);

        if (!searchAfter.isEmpty()) {
            searchRequest.searchAfter(searchAfter);
        }

        return searchRequest.build();
    }

    public Set<String> fileIdsForDel(List<Hit<MatchAllResponse>> hits) throws JsonProcessingException {
        Set<String> fileIds = new HashSet<>();

        for (Hit<MatchAllResponse> hit : hits) {
            MatchAllResponse source = hit.source();
            if (source == null) continue;


            //info index에 MatchAllResponse의 groupSeq, jobType, pkSeq로 검색

            // hit이 0이면 삭제할 파일

            // hit이 1 이상일 때 info 검색을 통해 얻은 fileList의 filePath와 file hit의 filePath를 비교하여 삭제 대상 정함
        }
        return fileIds;
    }

    public void bulkDelete(String indexName, Set<String> ids) {

    }

    public static ContentDoc makeContentDocument(String groupName, String jobType, String pkSeq, String title, List<Map<String, Object>> fileList) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = format.format(date);
        return ContentDoc.builder()
                         .groupName(groupName)
                         .jobType(jobType)
                         .pkSeq(pkSeq)
                         .title(title)
                         .fileList(fileList)
                         .strDate(strDate)
                         .date(date)
                         .build();
    }

    public static FileDoc makeFileDocument(String groupName, String jobType, String pkSeq, String fileName) {
        return FileDoc.builder()
                      .groupName(groupName)
                      .jobType(jobType)
                      .pkSeq(pkSeq)
                      .fileName(fileName)
                      .build();
    }
}
