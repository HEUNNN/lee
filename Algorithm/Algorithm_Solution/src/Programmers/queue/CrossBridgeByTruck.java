package Programmers.queue;

import java.util.LinkedList;
import java.util.Queue;

public class CrossBridgeByTruck {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42583
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int sum = 0;
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < truck_weights.length; i++) {
            int w = truck_weights[i];
            while (true) {
                if (bridge.isEmpty()) {
                    bridge.add(w);
                    sum += w;
                    time++;
                    break;
                } else if (bridge.size() == bridge_length) {
                    sum -= bridge.poll();
                } else if (bridge.size() < bridge_length) {
                    if (sum + w <= weight) {
                        bridge.add(w);
                        sum += w;
                        time++;
                        break;
                    } else {
                        bridge.add(0);
                        time++;
                    }
                }
            }
        }
        return time + bridge_length;
    }
}
