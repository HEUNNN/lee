package access;

public class SpeakerMain {
    public static void main(String[] args) {
        Speaker speaker = new Speaker(90);
        speaker.showVolume();
        speaker.volumeUp();
        speaker.showVolume();
        speaker.volumeUp();

        // 필드 직접 접근 → Speaker 객체를 사용하는 사용자는 필드, 메서드 등에 접근이 가능하다.
        // private 접근 제어자를 통해 Speaker 클래스 외부에서 직접 접근할 수 없도록 하였다.
//        System.out.println("volume 필드 직접 접근 수정");
//        speaker.volume = 200;
//        speaker.showVolume();
    }
}
