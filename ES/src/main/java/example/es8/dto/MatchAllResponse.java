package example.es8.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MatchAllResponse {

    private String groupName;
    private String jobType;
    private String pkSeq;
    private List<Map<String, Object>> fileList;
}
