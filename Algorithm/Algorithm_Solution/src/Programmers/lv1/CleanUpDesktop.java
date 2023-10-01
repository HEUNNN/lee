package Programmers.lv1;

public class CleanUpDesktop {
    public static void main(String[] args) {
        CleanUpDesktop cleanUpDesktop = new CleanUpDesktop();
        String[] wallpaper1 = {".#...", "..#..", "...#."};
        String[] wallpaper2 = {"..........", ".....#....", "......##..", "...##.....", "....#....."};
        String[] wallpaper3 = {".##...##.", "#..#.#..#", "#...#...#", ".#.....#.", "..#...#..", "...#.#...", "....#...."};
        String[] wallpaper4 = {"..", "#."};

        int[] answer = cleanUpDesktop.solution(wallpaper1);

        System.out.print("[ ");
        for (int i : answer) {
            System.out.print(i + " ");
        }
        System.out.print("]");
    }

    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        int rowMax = 0;
        int rowMin = 50;
        int colMax = 0;
        int colMin = 50;

        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    colMax = Math.max(colMax, i);
                    colMin = Math.min(colMin, i);
                    rowMax = Math.max(rowMax, j);
                    rowMin = Math.min(rowMin, j);

                }
            }
        }

        answer[0] = colMin;
        answer[1] = rowMin;
        answer[2] = (colMax + 1);
        answer[3] = (rowMax + 1);

        return answer;
    }
}
