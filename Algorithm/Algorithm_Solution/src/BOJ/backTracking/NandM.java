package BOJ.backTracking;

import java.util.ArrayList;

public class NandM {
    // https://www.acmicpc.net/problem/15649

    public static void main(String[] args) {
        ArrayList<Integer[]> result = new NandM().Solution(4, 2);
        for (Integer[] integers : result) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<Integer[]> Solution(int N, int M) {
        boolean[] visited = new boolean[N];
        Integer[] resultTmp = new Integer[M];
        ArrayList<Integer[]> result = new ArrayList<>();
        DFS(visited, resultTmp, N, M, 0, result);
        return result;
    }

    public void DFS(boolean[] visited, Integer[] resultTmp, int N, int M, int depth, ArrayList<Integer[]> result) {

        if (depth == M) {
            Integer[] copyArr = copyArr(resultTmp, new Integer[M]);
            result.add(copyArr);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                resultTmp[depth] = i + 1;
                DFS(visited, resultTmp, N, M, depth + 1, result);

                visited[i] = false; // 자식노드 방문이 모두 끝나고 visited 다시 false
            }
        }
    }

    private Integer[] copyArr(Integer[] origin, Integer[] copy) {
        int index = 0;
        for (Integer integer : origin) {
            copy[index++] = integer;
        }
        return copy;
    }


}
