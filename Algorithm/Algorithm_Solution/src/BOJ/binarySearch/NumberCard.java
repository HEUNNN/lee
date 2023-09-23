package BOJ.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberCard {
    // https://www.acmicpc.net/problem/10815
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            int result = new NumberCard().binarySearch(0, numbers.length - 1, numbers, target);
            if (result != -1) System.out.print(1 + " ");
            else System.out.print(0 + " ");
        }
    }

    private int binarySearch(int low, int high, int[] numbers, int target) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (target > numbers[mid]) return binarySearch(mid + 1, high, numbers, target);
        else if (target < numbers[mid]) return binarySearch(low, mid - 1, numbers, target);
        else return mid;
    }
}