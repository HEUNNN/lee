package BOJ.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BakePizza {
    // https://www.acmicpc.net/problem/1756
    static int D;
    static int N;
    static int[] ovenWidth;
    static int prevPosition;
    static int position;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        ovenWidth = new int[D];
        st = new StringTokenizer(br.readLine());
        for (int i = D - 1; i >= 0; i--) { // 3 2 6 3 4 6 5 → 2 2 3 3 4 5 5
            ovenWidth[i] = Integer.parseInt(st.nextToken());
        }
        sortOven();
        prevPosition = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            binarySearch(prevPosition, D - 1, ovenWidth, Integer.parseInt(st.nextToken()));
            if (position == -1) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(D - position);
    }

    public static void binarySearch(int low, int high, int[] ovenWidth, int pizzaWidth) {
        position = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (pizzaWidth <= ovenWidth[mid]) {
                position = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        prevPosition = position + 1;
    }

    public static void sortOven() {
        for (int i = D - 1; i >= 1; i--) {
            if (ovenWidth[i] < ovenWidth[i - 1]) {
                ovenWidth[i - 1] = ovenWidth[i];
            }
        }
    }
}
