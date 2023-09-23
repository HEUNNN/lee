package BOJ.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FindParent {
    static int[] parent;
    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        adjList = new ArrayList[N + 1];
        initList();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(end);
            adjList[end].add(start);
        }
        dfs(1);
        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append('\n');
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int curr) {
        for (Integer next : adjList[curr]) {
            if (parent[next] == 0) {
                parent[next] = curr;
                dfs(next);
            }
        }

    }

    public static void initList() {
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
    }
}
