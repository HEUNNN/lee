package BOJ.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Virus {
    // https://www.acmicpc.net/problem/2606
    static int computers;
    static int pairs;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        computers = Integer.parseInt(br.readLine());
        pairs = Integer.parseInt(br.readLine());
        StringTokenizer st;

        initAdjList(computers + 1); // 인접 행렬 init
        visited = new boolean[computers + 1];

        for (int i = 0; i < pairs; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            adjList[first].add(second);
            adjList[second].add(first);
        }

        dfs(1);
        System.out.println(count);

    }

    private static void dfs(int start) {
        visited[start] = true;
        for (Integer next : adjList[start]) {
            if (!visited[next]) {
                count++;
                dfs(next);
            }
        }

    }

    private static void initAdjList(int size) {
        adjList = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            adjList[i] = new ArrayList<>();
        }
    }
}