package Programmers.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DoublePriorityQueueV4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCase; i++) {
            PriorityQueue<Integer> min = new PriorityQueue<>();
            PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
            Map<Integer, Integer> numberToCount = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String op = st.nextToken();
                int number = Integer.parseInt(st.nextToken());
                if (op.equals("I")) {
                    min.add(number);
                    max.add(number);
                    numberToCount.put(number, numberToCount.getOrDefault(number, 0) + 1);
                } else { // "D"
                    if (numberToCount.size() == 0) continue;
                    if (number == 1) delete(max, numberToCount);
                    if (number == -1) delete(min, numberToCount);
                }
            }
            if (numberToCount.size() == 0) {
                sb.append("EMPTY\n");
            } else {
                int result = delete(max, numberToCount);
                sb.append(result + " ");
                if (numberToCount.size() > 0) {
                    result = delete(min, numberToCount);
                }
                sb.append(result + "\n");
            }
        }
        System.out.println(sb.toString());


    }

    private static int delete(PriorityQueue<Integer> pq, Map<Integer, Integer> numberToCount) {
        int res = 0;
        while (true) {
            res = pq.poll();
            int numCount = numberToCount.getOrDefault(res, 0);
            if (numCount == 0) continue;
            if (numCount == 1) numberToCount.remove(res);
            else numberToCount.put(res, numCount - 1);
            break;

        }
        return res;
    }


}
