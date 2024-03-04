package lee.project.sample.convert.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
public class User {
    private String name;
    private int age;
    private String id;
    @JsonProperty(value = "item_list")
    private List<Item> itemList;
}
