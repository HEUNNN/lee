package Programmers.hash;

import java.util.*;
import java.util.stream.Collectors;

public class BestAlbum {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42579
    static Map<Integer, String[]> idToInfo; // 고유번호와 [장르, 재생횟수]
    static Map<String, Integer> genreToSum; // 장르별 총 재생횟수
    static List<Integer> result;

    public static void main(String[] args) {
        String[] genres = new String[]{"classic", "pop", "classic", "classic", "pop"};
        int[] plays = new int[]{500, 600, 150, 800, 2500};
        int[] solution = new BestAlbum().solution(genres, plays);
        for (int i : solution) {
            System.out.println(i);
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        result = new ArrayList<>();
        idToInfo = new HashMap<>();
        genreToSum = new HashMap<>();
        initMap(genres, plays);
        List<Map.Entry<String, Integer>> sortedGenreSum = sortGenreSum();
        for (Map.Entry<String, Integer> genre : sortedGenreSum) {
            makeAlbum(genre.getKey());
        }
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    private void makeAlbum(String genre) {
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] < o2[1]) return 1;
            else if (o1[1].equals(o2[1])) return o1[0] - o2[0];
            else return -1;
        });
        for (Integer id : idToInfo.keySet()) {
            if (idToInfo.get(id)[0].equals(genre)) {
                pq.add(new Integer[]{id, Integer.parseInt(idToInfo.get(id)[1])});
            }
        }

        if (pq.size() == 1) {
            result.add(pq.poll()[0]);
        } else if (pq.size() >= 2) {
            result.add(pq.poll()[0]);
            result.add(pq.poll()[0]);
        }
    }

    private void initMap(String[] genres, int[] plays) {
        for (int i = 0; i < genres.length; i++) {
            idToInfo.put(i, new String[]{genres[i], String.valueOf(plays[i])});
            if (!genreToSum.containsKey(genres[i])) {
                genreToSum.put(genres[i], 0);
            }
            genreToSum.put(genres[i], genreToSum.get(genres[i]) + plays[i]);
        }
    }


    private List<Map.Entry<String, Integer>> sortGenreSum() {
        return genreToSum.entrySet()
                         .stream()
                         .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                         .collect(Collectors.toList());
    }
}
