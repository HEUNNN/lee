package example.service;

import example.constant.ElasticSearchConstant;
import lombok.AllArgsConstructor;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;


@AllArgsConstructor
public class CreateIndex {

    private final RestHighLevelClient client;

    public void createIndex() throws Exception {
        // content index create
        CreateIndexRequest contentIndexRequest = settingContentIndex();
        client.indices().create(contentIndexRequest, RequestOptions.DEFAULT);

        // file index create
        CreateIndexRequest fileIndexRequest = settingFileIndex();
        client.indices().create(fileIndexRequest, RequestOptions.DEFAULT);
    }

    public CreateIndexRequest settingContentIndex() throws IOException {

        // index request setting
        XContentBuilder indexSetting = XContentFactory.jsonBuilder();
        indexSetting.startObject();
        {
            indexSetting.field("number_of_shards", 5);
            indexSetting.field("number_of_replicas", 1);
        }
        indexSetting.endObject();

        // mapping settings
        XContentBuilder contentMapping = XContentFactory.jsonBuilder();
        contentMapping
                .startObject()
                .startObject("properties")
                .startObject("title")
                .field("type", "text")
                .endObject()
                .startObject("groupName")
                .field("type", "keyword")
                .endObject()
                .startObject("jobType")
                .field("type", "keyword")
                .endObject()
                .startObject("pkSeq")
                .field("type", "keyword")
                .endObject()
                .endObject()
                .endObject();


        CreateIndexRequest contentIndexRequest = new CreateIndexRequest(ElasticSearchConstant.CONTENT_INDEX_NAME);
        contentIndexRequest.settings(indexSetting)
                           .mapping(contentMapping);

        return contentIndexRequest;
    }

    public CreateIndexRequest settingFileIndex() throws Exception {

        XContentBuilder indexSetting = XContentFactory.jsonBuilder();
        indexSetting.startObject();
        {
            indexSetting.field("number_of_shards", 5);
            indexSetting.field("number_of_replicas", 1);
        }
        indexSetting.endObject();

        // mapping request settings
        XContentBuilder fileMapping = XContentFactory.jsonBuilder();
        fileMapping
                .startObject()
                .startObject("properties")
                .startObject("fileName")
                .field("type", "text")
                .endObject()
                .startObject("groupName")
                .field("type", "keyword")
                .endObject()
                .startObject("jobType")
                .field("type", "keyword")
                .endObject()
                .startObject("pkSeq")
                .field("type", "keyword")
                .endObject()
                .endObject()
                .endObject();


        CreateIndexRequest fileIndexRequest = new CreateIndexRequest(ElasticSearchConstant.FILE_INDEX_NAME);
        fileIndexRequest.settings(indexSetting)
                        .mapping(fileMapping);

        return fileIndexRequest;
    }

}
