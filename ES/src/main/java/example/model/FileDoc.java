package example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FileDoc {
    public String groupName;
    public String jobType;
    public String pkSeq;
    public String fileName;
}
