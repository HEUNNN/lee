package oop1;

public class MusicPlayer {
    int volume = 0;
    boolean isOn = false;
    
    public void on() {
        this.isOn = true;
        System.out.println("음악 플레이어를 시작합니다.");
    }

    public void off() {
        this.isOn = false;
        System.out.println("음악 플레이어를 종료합니다.");
    }

    public void volumeUp() {
        this.volume ++;
        System.out.println("음악 플레이어 볼륨: " + this.volume);
    }

    public void volumeDown() {
        this.volume--;
        System.out.println("음악 플레이어 볼륨: " + this.volume);
    }

    public void showStatus() {
        if (this.isOn) {
            System.out.println("음악 플레이어 ON, 볼륨: " + this.volume);
        } else {
            System.out.println("음악 플레이어 OFF, 볼륨: " + this.volume);
        }
    }
}
