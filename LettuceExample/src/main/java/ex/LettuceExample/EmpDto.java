package ex.LettuceExample;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpDto {
    String empSeq;
    List<AttendInfo> attendInfos;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class AttendInfo {
        String name;
        Long id;
    }
}
