package example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DeleteDataTest {

    @Autowired
    private DeleteDataRefactoring deleteData;

    @Test
    public void deleteDataWithoutContent() throws IOException {
        deleteData.getFileIdWithoutInfo();
    }

    @Test
    public void deleteByBoolQueryTest() throws IOException {
        deleteData.deleteByBoolQuery("content", "gn", "jt");
    }
}