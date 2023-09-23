package example.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import example.service.CreateIndex;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ElasticSearchConfig {
    @Value("${es.url}")
    private String url;

    @Value("${es.port}")
    private Integer port;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(url, port, "http")));
    }

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient build = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
        ElasticsearchTransport transport = new RestClientTransport(
                build, new JacksonJsonpMapper());
        ElasticsearchClient elasticsearchClient = new ElasticsearchClient(transport);
        return elasticsearchClient;
    }

    @Bean
    public CreateIndex createIndex(RestHighLevelClient restHighLevelClient) {
        return new CreateIndex(restHighLevelClient);
    }
}
