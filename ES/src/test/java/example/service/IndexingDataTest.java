package example.service;

import example.Data;
import example.constant.ElasticSearchConstant;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class IndexingDataTest {

    @Autowired
    private IndexingData indexingData;
    @Autowired
    private RestHighLevelClient client;

    @Test
    public void insertFileDataOnly() throws Exception {
        Data fileData = new Data("test2 file alone2", "", "test2", "board", null);
        indexingData.insertFileDataOnly(fileData);
    }

    @Test
    public void find() throws Exception {
        CountResponse fileDocCnt = client.count(new CountRequest("file"), RequestOptions.DEFAULT);
        System.out.println(fileDocCnt.getCount());
    }

    @Test
    public void bulkInsert() throws Exception {
        for (int i = 0; i < 900; i++) {
            BulkRequest bulkRequest = new BulkRequest();
            for (int j = 0; j < 1000; j++) {
                bulkRequest.add(indexRequest(new Data("tx", "", "test2", "board", null)));
            }
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
            System.out.println((i + 1) * 100);
            Thread.sleep(100);
        }
    }

    public IndexRequest indexRequest(Data fileData) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("fileName", fileData.getFileName());
        map.put("groupName", fileData.getGroupName());
        map.put("jobType", fileData.getJobType());
        map.put("pkSeq", fileData.getPkSeq());

        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index(ElasticSearchConstant.FILE_INDEX_NAME)
                    .source(map);
        return indexRequest;
    }

    @Test
    public void insertContentDataOnly() throws Exception {
        Data contentData = new Data("", "3", "gn", "jt", null);
        indexingData.insertContentDataOnly(contentData);
    }
}