package boj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberCard {
    // 10815 https://www.acmicpc.net/problem/10815

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            System.out.print(binarySearch(Integer.parseInt(st.nextToken()), numbers) + " ");
        }

    }

    public static int binarySearch(int targetNum, int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;

        int isExists = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (numbers[mid] > targetNum) {
                high = mid - 1;
            } else if (numbers[mid] < targetNum) {
                low = mid + 1;
            } else {
                return 1;
            }
        }
        return isExists;
    }


}
