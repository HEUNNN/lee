package programmers.lv2;

import java.util.*;
import java.util.List;

public class SelectTangerine {
    public static void main(String[] args) {
        int[] tangerine = {1, 1, 1, 1, 2, 2, 2, 3};
        int k = 2;
        int answer = 0;

        Map<Integer, Integer> sizeToCountMap = getSizeToCountMap(tangerine);

//        answer = selectTangerineUsingPriorityQueue(sizeToCountMap, k);
        answer = selectTangerineUsingList(sizeToCountMap, k);


        System.out.println(answer);
    }

    private static Map<Integer, Integer> getSizeToCountMap(int[] tangerine) {
        Map<Integer, Integer> sizeToCount = new HashMap<>();

        for (int size : tangerine) {
            if (!sizeToCount.containsKey(size)) {
                sizeToCount.put(size, 1);
                continue;
            }
            sizeToCount.put(size, sizeToCount.get(size) + 1);
        }
        return sizeToCount;
    }

    private static int selectTangerineUsingPriorityQueue(Map<Integer, Integer> sizeToCountMap, int k) {
        int answer = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        for (Integer size : sizeToCountMap.keySet()) {
            priorityQueue.add(sizeToCountMap.get(size));
        }

        while (k > 0) {
            Integer count = priorityQueue.poll();
            answer++;
            k -= count;
        }
        return answer;
    }

    private static int selectTangerineUsingList(Map<Integer, Integer> sizeToCountMap, int k) {
        int answer = 0;

        List<Integer> list = new ArrayList<>();

        for (Integer size : sizeToCountMap.keySet()) {
            list.add(sizeToCountMap.get(size));
        }

        list.sort(Comparator.reverseOrder());

        while (k > 0) {
            Integer count = list.removeFirst();
            answer++;
            k -= count;
        }

        return answer;
    }
}