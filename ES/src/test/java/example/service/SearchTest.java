package example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchTest {

    @Autowired
    private SearchDoc searchDoc;

    @Test
    public void  matchAllTest() {
        searchDoc.matchAll();
    }

    @Test
    public void fetchSourceSearchTest() {
        searchDoc.fetchSourceSearch("content", "hye", "note");
    }

    @Test
    public void boolMustQuerySearchTest() {
        searchDoc.boolMustQuerySearch("content", "gn", "jt");
    }
}