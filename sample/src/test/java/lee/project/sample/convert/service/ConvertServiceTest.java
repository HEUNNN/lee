package lee.project.sample.convert.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lee.project.sample.convert.dto.Item;
import lee.project.sample.convert.dto.User;
import lee.project.sample.convert.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ConvertServiceTest {

    @Autowired
    ConvertService service;

    FileUtil fileUtil = new FileUtil();
    String userJsonData = fileUtil.readFiles("user/user.json");
    String userListJsonData = fileUtil.readFiles("user/users.json");

    @Test
    public void jsonToMapTest() throws JsonProcessingException {
        Map<String, Object> userMap = service.jsonToMap(userJsonData);
        for (String key : userMap.keySet()) {
            System.out.println(key + ": " + userMap.get(key));
        }
    }

    @Test
    public void jsonToUserTest() throws JsonProcessingException {
        User user = service.jsonToUser(userJsonData);
        System.out.println(user.toString());

        System.out.println("===== itemList =====");
        for (Item item : user.getItemList()) {
            if (item instanceof Item) {
                System.out.println(item.toString());
            } else {
                System.out.println("It it not a item.");
            }
        }
        System.out.println("===== itemList =====");
    }

    @Test
    public void mapToUserTest() throws JsonProcessingException {
        User user = service.mapToUser(service.jsonToMap(userJsonData));
        System.out.println(user);
    }

    @Test
    public void jsonToListMapTest() throws JsonProcessingException {
        List<Map<String, Object>> userListMap = service.jsonToListMap(userListJsonData);

        for (Map<String, Object> userMap : userListMap) {
            System.out.println("=======================================");
            for (String key : userMap.keySet()) {
                System.out.println(key + ": " + userMap.get(key));
            }
        }
        System.out.println("=======================================");
    }

    @Test
    public void jsonToUserListTest() throws JsonProcessingException {
        List<User> userList = service.jsonToDtoList(userListJsonData);
        for (User user : userList) {
            System.out.println("=======================================");
            System.out.println(user.toString());
        }
        System.out.println("=======================================");
    }

    @Test
    public void listMapToDtoListTest() throws JsonProcessingException {
        List<Map<String, Object>> userListMap = service.jsonToListMap(userListJsonData);
        List<User> userList = service.listMapToDtoList(userListMap);
        for (User user : userList) {
            System.out.println("=======================================");
            System.out.println(user.toString());
        }
        System.out.println("=======================================");
    }

    @Test
    public void dtoListToListMapTest() throws JsonProcessingException {
        List<User> dtoList = service.jsonToDtoList(userListJsonData);
        for (Map<String, Object> userMap : service.dtoListToListMap(dtoList)) {
            System.out.println("=======================================");
            for (String key : userMap.keySet()) {
                System.out.println(key + ": " + userMap.get(key));
            }
        }
        System.out.println("=======================================");
    }

    @Test
    public void mapToJsonTest() throws JsonProcessingException {
        Map<String, Object> userMap = service.jsonToMap(userJsonData);
        String userJson = service.mapToJson(userMap);
        System.out.println(userJson);
    }

    @Test
    public void dtoToJsonTest() throws JsonProcessingException {
        User user = service.jsonToUser(userJsonData);
        String userJson = service.dtoToJson(user);
        System.out.println(userJson);
    }
}