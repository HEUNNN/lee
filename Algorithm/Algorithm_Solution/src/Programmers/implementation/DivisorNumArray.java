package Programmers.implementation;

import java.util.*;

public class DivisorNumArray {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12910
    public static void main(String[] args) {
        int[] arr = {5, 9, 7, 10};
        int[] solution = new DivisorNumArray().solution(arr, 5);
        for (int i : solution) {
            System.out.println(i);
        }
    }

    public int[] solution(int[] arr, int divisor) {
        Arrays.sort(arr);
        int[] answer = Arrays.stream(arr).filter(elem -> elem % divisor == 0).toArray();
        if (answer.length == 0) return new int[] {-1};
        return answer;
    }

    public int[] solution2(int[] arr, int divisor) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : arr) {
            if (i % divisor == 0) {
                pq.add(i);
            }
        }
        int[] answer = new int[pq.size()];
        int idx = 0;
        if (pq.size() == 0) return new int[]{-1};
        while (!pq.isEmpty()) {
            answer[idx++] = pq.poll();
        }
        return answer;

    }

    public int[] solution3(int[] arr, int divisor) {
        int[] answer;
        List<Integer> result = new ArrayList<>();
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            if (i % divisor == 0) {
                result.add(i);
            }
        }

        if (result.size() == 0) return new int[]{-1};

        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    private void quickSort(int[] array, int start, int end) {

        if (start >= end) {
            return;
        }

        int pivot = start; // ⭐️
        int lo = start;
        int hi = end;

        while (lo < hi) {

            while (array[hi] > array[pivot] && lo < hi) {
                hi--; // hi 먼저 --
            }

            while (array[lo] <= array[pivot] && lo < hi) { // <= 으로 해야 시간 효율성↑
                lo++;
            }
            swap(array, lo, hi);
        }

        swap(array, start, lo);

        quickSort(array, start, lo - 1);
        quickSort(array, lo + 1, end);
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
