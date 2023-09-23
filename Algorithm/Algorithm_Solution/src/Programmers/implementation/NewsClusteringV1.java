package Programmers.implementation;

import java.util.*;

public class NewsClusteringV1 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/17677
    public int solution(String str1, String str2) {
        List<String> strList1 = splitString(str1.toLowerCase());
        List<String> strList2 = splitString(str2.toLowerCase());
        int gyo = intersectionSize(strList1, strList2);
        strList1.addAll(strList2);
        int hap = strList1.size() - gyo;

        if (gyo == 0 && hap == 0) return 65536;
        return gyo == 0 ? 0 : (gyo * 65536) / hap;
    }

    private List<String> splitString(String str) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            if (hasSign(str.substring(i, i + 2))) {
                list.add(str.substring(i, i + 2));
            }
        }
        return list;
    }

    private boolean hasSign(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < 97 || str.charAt(i) > 122) {
                return false;
            }
        }
        return true;
    }

    private int intersectionSize(List<String> list1, List<String> list2) { // 교집합 개수 반환
        List<String> tmpList = new ArrayList<>();
        tmpList.addAll(list2); // 깊은 복사
        int size = 0;
        for (String s : list1) {
            if (tmpList.contains(s)) {
                size++;
                tmpList.remove(tmpList.indexOf(s));
            }
            if (tmpList.size() == 0) break;
        }

        return size;
    }
}
