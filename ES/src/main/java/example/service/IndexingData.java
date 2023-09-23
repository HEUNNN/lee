package example.service;

import example.Data;
import example.constant.ElasticSearchConstant;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class IndexingData {

    private final RestHighLevelClient client;

    public void insertData() throws Exception {
        List<Data> data = createDataList();
        List<IndexRequest> contentIndexRequests = data.stream()
                                                      .map(i -> createContentIndexRequest(i))
                                                      .collect(Collectors.toList());
        List<IndexRequest> fileIndexRequests = data.stream()
                                                   .map(i -> createFileIndexRequest(i))
                                                   .collect(Collectors.toList());
        BulkRequest bulkRequest = new BulkRequest();
        for (IndexRequest contentIndexRequest : contentIndexRequests) {
            bulkRequest.add(contentIndexRequest);
        }
        for (IndexRequest indexRequest : fileIndexRequests) {
            bulkRequest.add(indexRequest);
        }
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    public void insertFileDataOnly(Data fileData) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("fileName", fileData.getFileName());
        map.put("groupName", fileData.getGroupName());
        map.put("jobType", fileData.getJobType());
        map.put("pkSeq", fileData.getPkSeq());

        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index(ElasticSearchConstant.FILE_INDEX_NAME)
                    .source(map);
        client.index(indexRequest, RequestOptions.DEFAULT);
    }

    public void insertContentDataOnly(Data contentData) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("title", contentData.getTitle());
        map.put("groupName", contentData.getGroupName());
        map.put("jobType", contentData.getJobType());
        map.put("pkSeq", contentData.getPkSeq());

        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index(ElasticSearchConstant.CONTENT_INDEX_NAME)
                    .source(map);
        client.index(indexRequest, RequestOptions.DEFAULT);
    }

    private IndexRequest createContentIndexRequest(Data data) {
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index(ElasticSearchConstant.CONTENT_INDEX_NAME);
        Map<String, String> map = new HashMap<>();
        map.put("title", data.getTitle());
        map.put("groupName", data.getGroupName());
        map.put("jobType", data.getJobType());
        map.put("pkSeq", data.getPkSeq());
        indexRequest.source(map);
        return indexRequest;
    }

    private IndexRequest createFileIndexRequest(Data data) {
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index(ElasticSearchConstant.FILE_INDEX_NAME);
        Map<String, String> map = new HashMap<>();
        map.put("fileName", data.getFileName());
        map.put("groupName", data.getGroupName());
        map.put("jobType", data.getJobType());
        map.put("pkSeq", data.getPkSeq());
        indexRequest.source(map);
        return indexRequest;
    }

    private List<Data> createDataList() {

        List<Data> dataList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Data data = Data.builder()
                            .fileName("file name " + (i + 1))
                            .title("title " + (i + 1))
                            .groupName("local")
                            .jobType("board")
                            .pkSeq(String.valueOf(i + 1))
                            .build();

            dataList.add(data);
        }
        return dataList;
    }

}
