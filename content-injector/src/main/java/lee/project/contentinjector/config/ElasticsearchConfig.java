package lee.project.contentinjector.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Slf4j
@Configuration
public class ElasticsearchConfig {
    @Value("${elasticsearch.url}")
    private String url;

    @Value("${elasticsearch.port}")
    private Integer port;

    ElasticsearchClient elasticsearchClient;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient build = RestClient.builder(new HttpHost(url, port, "http")).build();
        ElasticsearchTransport transport = new RestClientTransport(
                build, new JacksonJsonpMapper());
        elasticsearchClient = new ElasticsearchClient(transport);
        return elasticsearchClient;
    }
}
