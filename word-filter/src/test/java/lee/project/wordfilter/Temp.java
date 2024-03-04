package lee.project.wordfilter;

public class Temp {
    public static int[] solution(int x, int y) {
        int[] result = new int[2]; // [이기는 플레이어, 동전을 가져간 총횟수]

        // 이기는 플레이어를 계산
        int winningPlayer = 0; // 처음에는 A가 시작

        // 게임이 끝날 때까지 반복
        while (x > 0 || y > 0) {
            if (x == y) { // 두 동전 더미의 개수가 같을 때
                if (winningPlayer == 0) {
                    result[0] = 1; // A가 승리
                } else {
                    result[0] = 0; // B가 승리
                }
                result[1]++; // 총 횟수 증가
                break; // 게임 종료
            } else {
                // 두 더미 중 큰 더미를 선택해서 한 개씩 가져감
                int maxCoins = Math.max(x, y);
                if (x > y) {
                    x--;
                } else {
                    y--;
                }
            }

            // 플레이어를 변경
            winningPlayer = 1 - winningPlayer;
            result[1]++; // 총 횟수 증가
        }

        result[0] = winningPlayer; // 이기는 플레이어 설정

        return result;
    }

    public static void main(String[] args) {
        int x1 = 0, y1 = 100;
        int[] result1 = solution(x1, y1);
        System.out.println("x=" + x1 + ", y=" + y1 + " -> result=[" + result1[0] + "," + result1[1] + "]");

        int x2 = 2, y2 = 3;
        int[] result2 = solution(x2, y2);
        System.out.println("x=" + x2 + ", y=" + y2 + " -> result=[" + result2[0] + "," + result2[1] + "]");
    }
}
