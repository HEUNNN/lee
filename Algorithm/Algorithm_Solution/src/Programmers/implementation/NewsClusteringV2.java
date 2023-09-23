package Programmers.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NewsClusteringV2 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/17677
    private static final Integer MULTIPLIER = 65536;

    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";
        new NewsClusteringV2().solution(str1, str2);
    }

    public int solution(String str1, String str2) {
        // Stream 활용
        String word1 = str1.toLowerCase();
        String word2 = str2.toLowerCase();
        Map<String, Long> words1 = group(word1);
        Map<String, Long> words2 = group(word2);
        Integer intersectionSize = getIntersectionSize(words1, words2);
        Integer unionSize = getUnionSize(words1, words2);

        if (intersectionSize.equals(unionSize) && unionSize.equals(0)) return MULTIPLIER;
        return (int) (intersectionSize.doubleValue() / unionSize.doubleValue() * MULTIPLIER);
    }

    private Map<String, Long> group(String word) {
        // groupMap key는 2개씩 분할된 문자, value는 그 분할된 문자가 origin word에서 몇개 있는지
        Map<String, Long> groupMap = IntStream.range(0, word.length() - 1)
                                              .mapToObj(idx -> word.substring(idx, idx + 2))
                                              .filter(text -> text.chars().allMatch(character -> Character.isAlphabetic((char) character)))
                                              .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return groupMap;

    }

    private Integer getIntersectionSize(Map<String, Long> words1, Map<String, Long> words2) {
        int sum = words1.entrySet().stream()
                        .filter(entry -> words2.containsKey(entry.getKey()))
                        .map(entry -> Math.min(entry.getValue(), words2.get(entry.getKey())))
                        .mapToInt(Long::intValue) // Long → int 변환
                        .sum();
        return sum;
    }

    private Integer getUnionSize(Map<String, Long> words1, Map<String, Long> words2) {
        Map<String, Long> copiedWords = new HashMap<>(words2);
        words1.forEach((key, val) -> copiedWords.put(key, Math.max(val, words2.getOrDefault(key, 0L))));

        return copiedWords.values().stream()
                          .mapToInt(Long::intValue)
                          .sum();
    }

}
