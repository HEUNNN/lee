package example.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MyFetchTest {

    private static final String GROUP_SEQ = "groupSeq";
    private static final String JOB_TYPE = "jobType";
    private static final String PK_SEQ = "pkSeq";
    private static final List<String> TARGET_JOB_LIST = Arrays.asList("board", "note", "schedule");
    private static final int FETCH_MAX_SIZE = 1000;

    private static RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

    public static void main(String[] args) throws Exception {
        afterValueTest();
        System.out.println("done");
    }


    public static void afterValueTest() throws IOException, InterruptedException {
        Object[] searchAfterValues = null;
        CountResponse fileDocCnt = client.count(new CountRequest("file"), RequestOptions.DEFAULT);
        long cnt = 0L;

        while (true) {

            SearchRequest fileSearchReq = createFileSearchRequest(searchAfterValues);
            SearchResponse fileSearchRes = client.search(fileSearchReq, RequestOptions.DEFAULT);
            SearchHit[] fileSearchHits = fileSearchRes.getHits().getHits();
            cnt += (long) fileSearchHits.length;
            System.out.println(cnt);

            if (fileSearchHits.length < FETCH_MAX_SIZE || (cnt >= fileDocCnt.getCount())) {
                break;
            }

            Thread.sleep(500);

            if (fileSearchHits.length > 0) { // 다음 페이지 검색을 위해 마지막 문서 정보 저장
                SearchHit lastHit = fileSearchHits[fileSearchHits.length - 1];
                searchAfterValues = lastHit.getSortValues();
            } else {
                break;
            }
        }
    }

    private static SearchRequest createFileSearchRequest(Object[] searchAfterValues) {
        SearchSourceBuilder fileSearchSource = new SearchSourceBuilder();

        if (searchAfterValues != null) {
            fileSearchSource.searchAfter(searchAfterValues);
        }

        SearchSourceBuilder sourceBuilder =
                fileSearchSource.query(QueryBuilders.matchAllQuery())
                                .size(FETCH_MAX_SIZE)
                                .sort("_id", SortOrder.ASC)
                                .fetchSource((String) null, null);

        return new SearchRequest("file").source(sourceBuilder);
    }

}