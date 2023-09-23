package example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Data {
    private String fileName;
    private String title;
    private String groupName;
    private String jobType;
    private String pkSeq;
}
