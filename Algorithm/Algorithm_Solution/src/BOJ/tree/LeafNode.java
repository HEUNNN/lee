package BOJ.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LeafNode {
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    static int root;
    static int leafCnt = 0;

    public static void main(String[] args) throws IOException {
        // https://www.acmicpc.net/problem/1068

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N];
        init();
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
                continue;
            }
            adjList[i].add(parent);
            adjList[parent].add(i);
        }
        int cut = Integer.parseInt(br.readLine());
        if (cut == root) {
            System.out.println(0);
            return;
        }
        dfs(root, cut);
        System.out.println(leafCnt);
    }

    public static void dfs(int root, int cut) {
        int childCnt = 0;
        visited[root] = true;
        for (Integer child : adjList[root]) {
            if (!visited[child] && child != cut) {
                childCnt++;
                dfs(child, cut);
            }
        }
        if (childCnt == 0) {
            leafCnt++;
        }
    }

    public static void init() {
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
    }
}
