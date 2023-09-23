package Programmers.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParkingFeeV2 {
    public static void main(String[] args) {
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] fees = {180, 5000, 10, 600};
        int[] solution = new ParkingFeeV2().solution(fees, records);
        for (int i : solution) {
            System.out.println(i);
        }
    }

    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> minuteMap = getMinuteMap(records);
        ArrayList<Map.Entry<String, Integer>> parkingFees = new ArrayList<>(minuteMap.entrySet());
        Collections.sort(parkingFees, (o1, o2) -> {
            if ((Integer.parseInt(o1.getKey()) > Integer.parseInt(o2.getKey()))) return 1;
            else return -1;
        });
        int[] answer = new int[parkingFees.size()];
        for (int i = 0; i < answer.length; i++) {
            int totalFee = getTotalFee(fees, parkingFees.get(i).getValue());
            answer[i] = totalFee;
        }
        return answer;
    }

    private Map<String, Integer> getMinuteMap(String[] records) {
        Map<String, String> timeMap = new HashMap<>(); // 운전자가 IN, OUT 하는 시간을 기록할 Map
        Map<String, Integer> minuteMap = new HashMap<>(); // 총 주차 (시간)분을 저장할 Map
        for (String record : records) { // 초기화
            minuteMap.put(record.split(" ")[1], 0);
        }
        for (String r : records) {
            String[] record = r.split(" ");
            if (!timeMap.containsKey(record[1])) { // IN
                timeMap.put(record[1], record[0]);
            } else { // OUT
                String[] inTime = timeMap.remove(record[1]).split(":"); // 특정 key를 remove하면 삭제한 key의 value가 반환된다.
                String[] outTime = record[0].split(":");

                int h = Integer.parseInt(outTime[0]) - Integer.parseInt(inTime[0]);
                int m = Integer.parseInt(outTime[1]) - Integer.parseInt(inTime[1]);
                minuteMap.put(record[1], minuteMap.get(record[1]) + ((h * 60) + m));
                // map replace는 put과 유사하지만 key를 contains하고 있지 않은 상태에서 replace할 경우 null이 반환된다.
            }
        }
        for (String driver : timeMap.keySet()) {
            String[] restIn = timeMap.get(driver).split(":"); // OUT 이 없어 23:59로 처리해줘야함
            int h = 23 - Integer.parseInt(restIn[0]);
            int m = 59 - Integer.parseInt(restIn[1]);
            minuteMap.put(driver, minuteMap.get(driver) + (h * 60) + m);
        }
        return minuteMap;
    }

    private int getTotalFee(int[] fees, Integer minute) {
        if (minute <= fees[0]) return fees[1];
        int exceedUnitMinute = (int) Math.ceil((minute - fees[0]) / (double) fees[2]);
        return exceedUnitMinute * fees[3] + fees[1];
    }
}
