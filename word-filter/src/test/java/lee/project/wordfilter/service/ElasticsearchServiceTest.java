package lee.project.wordfilter.service;

import co.elastic.clients.elasticsearch.core.BulkResponse;
import lee.project.wordfilter.dto.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ElasticsearchServiceTest {
    @Autowired
    ElasticsearchService service;

    @Test
    void createWordIndex() throws IOException {
        service.createWordIndex();
    }

    @Test
    void bulkWords() throws IOException {
        List<Word> words = new ArrayList<>();
        words.add(new Word("apple", "apple description 1", 1));
        words.add(new Word("banana", "", 2));
        words.add(new Word("peach", "banana description 1", 3));
        words.add(new Word("strawberry", "strawberry description 1", 4));
        words.add(new Word("", "", 5));
        words.add(new Word("", "", 6));
        words.add(new Word("grape", "", 7));
        words.add(new Word("", "eight", 8));
        words.add(new Word("mango", "", 9));

        BulkResponse bulkResponse = service.bulkWords(words);
        System.out.println(bulkResponse);
    }

    @Test
    void scripting() throws IOException {
        service.scripting("word");
    }

    @Test
    void regix() {
        service.usingRegex("word");
    }

    @Test
    void bulkUpdateUsingScript() throws IOException {
        List<String> ids = new ArrayList<>();
        ids.add("2");
        ids.add("5");
        ids.add("6");
        ids.add("7");
        ids.add("9");


        service.bulkUpdateWithScript(ids, "word");
    }
}