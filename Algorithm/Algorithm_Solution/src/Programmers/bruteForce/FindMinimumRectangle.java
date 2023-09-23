package Programmers.bruteForce;

import java.util.Arrays;

public class FindMinimumRectangle {
    // https://school.programmers.co.kr/learn/courses/30/lessons/86491
    public static void main(String[] args) {
        int[][] sizes = new int[][]{
                {60, 50},
                {30, 70},
                {60, 30},
                {80, 40}
        };
        int solution = new FindMinimumRectangle().solution2(sizes);
        System.out.println(solution);
    }

    public int solution(int[][] sizes) {
        int[] width = new int[sizes.length];
        int[] height = new int[sizes.length];

        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                width[i] = sizes[i][1];
                height[i] = sizes[i][0];
            } else {
                width[i] = sizes[i][0];
                height[i] = sizes[i][1];
            }
        }

        Arrays.sort(width);
        Arrays.sort(height);

        int w = width[width.length - 1];
        int h = height[height.length - 1];
        return w * h;
    }

    public int solution2(int[][] sizes) {
        int maxMax = 0;
        int minMax = 0;

        for (int[] size : sizes) {
            int maxTmp = Math.max(size[0], size[1]);
            int minTmp = Math.min(size[0], size[1]);

            if (maxTmp > maxMax) { // 최댓값 모임 중 최대
                maxMax = maxTmp;
            }
            if (minTmp > minMax) { // 최솟값 모임 중 최대
                minMax = minTmp;
            }
        }

        return maxMax * minMax;
    }
}
