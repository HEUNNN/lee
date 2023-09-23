package example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CreateIndexTest {

    @Autowired
    private CreateIndex createIndex;

    @Test
    public void createIndex() throws Exception {
        createIndex.createIndex();
    }
}