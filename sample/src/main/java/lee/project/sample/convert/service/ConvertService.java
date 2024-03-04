package lee.project.sample.convert.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lee.project.sample.convert.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConvertService {

    @Autowired
    ObjectMapper mapper;

    public Map<String, Object> jsonToMap(String jsonData) throws JsonProcessingException {
        // JSON To Map: readValue
        return mapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {
        });
    }


    public User jsonToUser(String jsonData) throws JsonProcessingException {
        // JSON To DTO: readValue
        User user = mapper.readValue(jsonData, new TypeReference<User>() {
        });
        return user;
    }

    public User mapToUser(Map<String, Object> mapData) {
        // Map To DTO: convertValue
        return mapper.convertValue(mapData, new TypeReference<User>() {
        });
    }

    public List<Map<String, Object>> jsonToListMap(String jsonData) throws JsonProcessingException {
        // JSON To List Map: readValue
        return mapper.readValue(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    public List<User> jsonToDtoList(String jsonData) throws JsonProcessingException {
        // JSON To DTO List: readValue
        return mapper.readValue(jsonData, new TypeReference<List<User>>() {
        });
    }

    public List<User> listMapToDtoList(List<Map<String, Object>> listMapData) {
        // List Map To DTO List: convertValue
        return mapper.convertValue(listMapData, new TypeReference<List<User>>() {
        });
    }

    public List<Map<String, Object>> dtoListToListMap(List<User> dtoListData) {
        // DTO List To List Map
        return mapper.convertValue(dtoListData, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    public String mapToJson(Map<String, Object> mapData) throws JsonProcessingException {
        // Map To Json: writeValueAsString
        return mapper.writeValueAsString(mapData);
    }

    public String dtoToJson(User user) throws JsonProcessingException {
        // User To Json: writeValueAsString
        return mapper.writeValueAsString(user);
    }
}
