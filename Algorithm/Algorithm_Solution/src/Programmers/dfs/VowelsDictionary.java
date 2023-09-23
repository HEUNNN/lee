package Programmers.dfs;

public class VowelsDictionary {
    // https://school.programmers.co.kr/learn/courses/30/lessons/84512
    static int count = 0;
    static boolean find = false;
    static String[] alphabets = {"A", "E", "I", "O", "U"};

    public static void main(String[] args) {
        System.out.println(new VowelsDictionary().solution("EIO"));
    }

    public int solution(String word) {
        dfs(0, word, "");
        return count;
    }

    private void dfs(int depth, String word, String tmp) {

        if (word.equals(tmp)) find = true;
        if (depth == 5) return;
        for (int i = 0; i < 5; i++) {
            if (find) break;
            count++;
            dfs(depth + 1, word, tmp + alphabets[i]);
        }
    }
}
