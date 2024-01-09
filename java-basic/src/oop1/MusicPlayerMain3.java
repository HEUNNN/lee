package oop1;

public class MusicPlayerMain3 {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();

        // 메서드 추출 → 데이터와 기능이 분리, 데이터의 경우 MusicPlayerData / 기능의 경우 MusicPlayerMain3에 존재
        // 객체 지향은 데이터와 기능이 한 곳에서 생성되고 관리된다.

        // on
        player.on();

        // volume up
        player.volumeUp();

        // volume up
        player.volumeUp();

        // volume down
        player.volumeDown();

        // volume down
        player.volumeDown();

        // 음악 플레이어 상태
        player.showStatus();

        // 음악 플레이어 끄기
        player.off();
    }
}
