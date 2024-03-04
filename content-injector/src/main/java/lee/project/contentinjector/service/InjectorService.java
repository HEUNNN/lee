package lee.project.contentinjector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectorService {

    @Autowired
    ElasticsearchService elasticsearchService;
    @Autowired
    RedisService redisService;
}
