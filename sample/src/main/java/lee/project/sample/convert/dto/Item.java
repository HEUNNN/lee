package lee.project.sample.convert.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {
    @JsonProperty(value = "item_name")
    private String itemName;
    private int price;
}
