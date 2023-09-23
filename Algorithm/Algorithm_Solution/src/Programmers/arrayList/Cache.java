package Programmers.arrayList;

import java.util.*;

public class Cache {
    // https://school.programmers.co.kr/learn/courses/30/lessons/17680

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if (cacheSize == 0) return cities.length * 5;

        List<String> cache = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toLowerCase();

            if (cache.contains(cities[i])) {
                // cache hit
                answer += 1;
                cache.remove(cities[i]);
                cache.add(cities[i]);
            } else {
                // cache miss
                answer += 5;
                if (cache.size() == cacheSize) {
                    cache.remove(0); // remove는 remove(index) or remove(Object) 두개가 있다.
                    cache.add(cities[i]);
                } else {
                    cache.add(cities[i]);
                }
            }
        }
        return answer;
    }
}
