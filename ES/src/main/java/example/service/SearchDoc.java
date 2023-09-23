package example.service;

import example.constant.ElasticSearchConstant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class SearchDoc {

    @Autowired
    private RestHighLevelClient client;

    public void matchAll() {
        try {
            // query
            MatchAllQueryBuilder matchAll = QueryBuilders.matchAllQuery();

            // source builder
            SearchSourceBuilder searchSource = new SearchSourceBuilder().query(matchAll);

            // request
            SearchRequest searchReq = new SearchRequest(ElasticSearchConstant.CONTENT_INDEX_NAME, ElasticSearchConstant.FILE_INDEX_NAME);

            // execute
            SearchResponse matchAllRes = client.search(searchReq, RequestOptions.DEFAULT);

            // response verify
            if (matchAllRes.getHits().getTotalHits().value == 0L) {
                System.out.println("인덱싱된 도큐먼트가 존재하지 않음");
            } else {
                SearchHit[] hitDocs = matchAllRes.getHits().getHits(); // n개의 도큐먼트 검색 결과
                for (SearchHit doc : hitDocs) {
                    Map<String, Object> resourceMap = doc.getSourceAsMap(); // 검색된 1개의 도큐먼트의 필드와 값 즉, source를 map 형태로 매핑
                    System.out.println("--------------------------------------------------");
                    for (String key : resourceMap.keySet()) {
                        System.out.println(key + ":" + resourceMap.get(key).toString());
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("match all query 실행 시 예외 발생, msg = " + e.getMessage());
        }
    }

    public void fetchSourceSearch(String indexName, String groupName, String jobType) { // fetchSource를 사용하여 원하는 검색 결과 중 원하는 필드의 값만 조회
        long start = System.currentTimeMillis();
        try {
            BoolQueryBuilder bq = new BoolQueryBuilder()
                    .must(new TermQueryBuilder("groupName", groupName))
                    .must(new TermQueryBuilder("jobType", jobType));

            SearchSourceBuilder searchSource = new SearchSourceBuilder();

            searchSource.fetchSource("pkSeq", "")
                        .query(bq);

            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.source(searchSource);
            SearchResponse respone = client.search(searchRequest, RequestOptions.DEFAULT);

            if (respone.getHits().getTotalHits().value == 0L) {
                System.out.println("검색된 도큐먼트가 존재하지 않음");
            }

            for (SearchHit hitDoc : respone.getHits().getHits()) {
                System.out.println(hitDoc.getSourceAsString());
            }


        } catch (Exception e) {
            System.out.println("fetch source 적용하여 검색 시 예외 발생, msg = " + e.getMessage());
        }
        System.out.println((System.currentTimeMillis() - start));
    }


    public void boolMustQuerySearch(String indexName, String groupName, String jobType) { // fetchSource를 사용하여 원하는 검색 결과 중 원하는 필드의 값만 조회
        long start = System.currentTimeMillis();
        try {
            BoolQueryBuilder bq = new BoolQueryBuilder()
                    .must(new TermQueryBuilder("groupName", groupName))
                    .must(new TermQueryBuilder("jobType", jobType));

            SearchSourceBuilder searchSource = new SearchSourceBuilder();

            searchSource.fetchSource("title", "")
                        .query(bq);

            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.source(searchSource);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

            if (response.getHits().getTotalHits().value == 0L) {
                System.out.println("검색된 도큐먼트가 존재하지 않음");
            }
            SearchHit[] hits = response.getHits().getHits();

            for (SearchHit hitDoc : response.getHits().getHits()) {
                System.out.println(hitDoc.getSourceAsMap().toString());
            }


        } catch (Exception e) {
            System.out.println("fetch source 적용하여 검색 시 예외 발생, msg = " + e.getMessage());
        }
        System.out.println((System.currentTimeMillis() - start));
    }
}
