package example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ContentDoc {

    // asdasd
    public String groupName;

    // asdasd
    public String jobType;

    // asdasd
    public String pkSeq;

    // asdasd
    public String title;

    public List<Map<String, Object>> fileList;
    public String strDate;
    public Date date;
}
