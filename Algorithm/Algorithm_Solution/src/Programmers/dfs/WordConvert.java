package Programmers.dfs;

public class WordConvert {
    // https://school.programmers.co.kr/learn/courses/30/lessons/43163
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) {
        int answer = new WordConvert().solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
        System.out.println(answer);
    }

    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        dfs(begin, target, words, 0);
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private void dfs(String begin, String target, String[] words, int cnt) {
        if (begin.equals(target)) {
            min = Math.min(min, cnt);
            return;
        }
        int[] similar = similar(begin, words);
        for (int i = 0; i < similar.length; i++) {
            if (similar[i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(words[i], target, words, cnt + 1);
                visited[i] = false;
            }
        }

    }

    private int[] similar(String currWord, String[] words) {
        int[] similars = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int diff = 0;
            for (int j = 0; j < currWord.length(); j++) {
                if (!currWord.substring(j, j + 1).equals(words[i].substring(j, j + 1))) diff++;
            }
            if (diff == 1 && !visited[i]) {
                similars[i] = 1;
            }
        }
        return similars;
    }
}
