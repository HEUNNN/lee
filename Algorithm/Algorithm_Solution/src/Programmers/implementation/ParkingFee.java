package Programmers.implementation;

import java.util.*;

public class ParkingFee {
    // https://school.programmers.co.kr/learn/courses/30/lessons/92341
    public static void main(String[] args) {
        String[] records = {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};
        int[] fees = {120, 0, 60, 591};
        int[] solution = new ParkingFee().solution(fees, records);
        for (int i : solution) {
            System.out.println(i);
        }
    }

    public int[] solution(int[] fees, String[] records) {
        Map<String, List<String>> driverToTime = parkingInfo(records);
        PriorityQueue<int[]> totalFee = new PriorityQueue<>((o1, o2) -> {
            if (o2[0] > o1[0]) return -1;
            else return 1;
        });
        outCheck(driverToTime);
        for (String drvier : driverToTime.keySet()) {
            int[] tmp = {Integer.parseInt(drvier), getTotalFee(driverToTime.get(drvier), fees)};
            totalFee.add(tmp);
        }
        int[] answer = new int[totalFee.size()];
        int idx = 0;
        for (int[] ints : totalFee) {
            answer[idx ++] = ints[1];
        }
        return answer;
    }

    private Map<String, List<String>> parkingInfo(String[] records) {
        Map<String, List<String>> driverToTime = new HashMap<>();
        for (String r : records) {
            String[] record = r.split(" ");
            if (!driverToTime.containsKey(record[1])) {
                List<String> timeList = new ArrayList<>();
                driverToTime.put(record[1], timeList);
            }
            driverToTime.get(record[1]).add(record[0]);
        }
        return driverToTime;
    }

    private void outCheck(Map<String, List<String>> driverToTime) {
        for (String driver : driverToTime.keySet()) {
            Boolean aBoolean = driverToTime.get(driver).size() % 2 != 0 ? driverToTime.get(driver).add("23:59") : null;
        }
    }

    private Integer getTotalFee(List<String> time, int[] fees) {
        Integer minuite = 0;
        for (int i = 0; i < time.size(); i += 2) {
            String[] timeArr1 = time.get(i).split(":");
            String[] timeArr2 = time.get(i + 1).split(":");
            Integer t1 = Integer.parseInt(timeArr2[0]) * 60 + Integer.parseInt(timeArr2[1]);
            Integer t2 = Integer.parseInt(timeArr1[0]) * 60 + Integer.parseInt(timeArr1[1]);
            minuite += (t1 - t2);
        }
        int rest = minuite - fees[0];
        if (rest <= 0) return fees[1]; // 기본요금

        return rest % fees[2] != 0 ? (rest / fees[2] + 1) * fees[3] + fees[1] : (rest / fees[2]) * fees[3] + fees[1];
    }
}
