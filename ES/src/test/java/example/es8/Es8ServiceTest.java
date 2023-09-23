package example.es8;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Es8ServiceTest {

    @Autowired
    public Es8Service elasticService;

    @Test
    public void createIndexContent() throws IOException {
        elasticService.createIndex("content");
    }

    @Test
    public void createIndexFile() throws IOException {
        elasticService.createIndex("file");
    }

    @Test
    public void searchContentIndexUsingTermQuery() throws IOException {
        elasticService.searchByTermQuery("content", "group1", "content", "1");
    }

    @Test
    public void deleteByTermQuery() throws IOException {
        elasticService.deleteByTermQuery("file", "group1", "file", "1");
    }


    @Test
    public void matchAll() throws IOException {
        makeBulk();
        elasticService.matchAllQuery("file");
    }

    @Test
    public void searchAfter() throws Exception {
        elasticService.searchAfter("content");
    }

    public void makeBulk() throws IOException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {

            List<Map<String, Object>> fileList = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            for (int j = 0; j < 2; j++) {
                map.put("key1", Es8Service.makeFileDocument("appl1", "file", Integer.toString(i), "file" + i));
                fileList.add(map);
            }
            list.add(Es8Service.makeContentDocument("apple", "content", Integer.toString(i), "title 1", fileList));
        }
        elasticService.bulk("content", list);
    }
}