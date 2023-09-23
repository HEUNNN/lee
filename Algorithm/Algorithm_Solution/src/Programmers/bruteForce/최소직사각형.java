package Programmers.bruteForce;

import java.util.HashMap;
import java.util.Map;

public class 최소직사각형 {
    public static void main(String[] args) {
        int[][] sizes1 = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int[][] sizes2 = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
        System.out.println(new 최소직사각형().solution(sizes2));
    }

    public int solution(int[][] sizes) {
        int rowMax = 0;
        int colMax = 0;

        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                swap(sizes[i]);
            }
        }

        for (int[] card : sizes) {
            if (rowMax < card[0]) rowMax = card[0];
            if (colMax < card[1]) colMax = card[1];
        }

        return rowMax * colMax;
    }

    public void swap(int[] size) {
        int tmp = size[0];
        size[0] = size[1];
        size[1] = tmp;
    }
}
