package lee.project.contentinjector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileSource {
    private String targetSeq;
    private String uniqueSeq;
    private String fileName;
    private String content;
    private String extension;
}
