package Programmers.implementation;

public class Friends4Block {
    // https://school.programmers.co.kr/learn/courses/30/lessons/17679
    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(new Friends4Block().solution(m, n, board));
    }

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] blockBoard = new char[m][n];

        for (int i = 0; i < m; i++) {
            blockBoard[i] = board[i].toCharArray();
        }

        while (true) {
            int count = checkBlock(blockBoard, m, n);
            if (count == 0) break;
            answer += count;
            dropBlock(blockBoard, m, n);
        }
        return answer;
    }

    private int checkBlock(char[][] blockBoard, int m, int n) {
        boolean[][] isChecked = new boolean[m][n];
        int cnt = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (blockBoard[i][j] == 'X') continue;
                check4Block(blockBoard, isChecked, i, j); // 특정 (i, j) 블록의 주위 3개가 같은지 확인하고, isChecked에만 일단 표시
            }
        }

        // isChecked가 true 개수를 count하고, true인 위치의 blockBoard를 'X'로 변경
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isChecked[i][j]) {
                    cnt++;
                    blockBoard[i][j] = 'X';
                }
            }
        }

        return cnt;
    }

    private void check4Block(char[][] blockBoard, boolean[][] isChecked, int i, int j) {
        char target = blockBoard[i][j];
        for (int k = i; k < i + 2; k++) {
            for (int l = j; l < j + 2; l++) {
                if (blockBoard[k][l] != target) return;
            }
        }

        for (int k = i; k < i + 2; k++) {
            for (int l = j; l < j + 2; l++) {
                isChecked[k][l] = true;
            }
        }

    }

    private void dropBlock(char[][] blockBoard, int m, int n) {
        for (int i = m - 1; i >= 0; i--) { // 범위 벗어나는지 잘 생각해보기
            for (int j = 0; j < n; j++) {
                if (blockBoard[i][j] == 'X') {
                    for (int k = i - 1; k >= 0; k--) {
                        if (blockBoard[k][j] != 'X') {
                            blockBoard[i][j] = blockBoard[k][j];
                            blockBoard[k][j] = 'X';
                            break;
                        }
                    }
                }
            }
        }
    }
}
