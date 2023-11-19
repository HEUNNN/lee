package BOJ.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Friendship {
    // https://www.acmicpc.net/problem/13023
    static int N;
    static int M;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        List<Integer>[] relation = new List[N];
        setFriendList(relation);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relation[a].add(b);
            relation[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            if (result == 1) break;
            dfs(i, relation, new boolean[N], 1);
        }
        System.out.println(result);
    }

    private static void setFriendList(List<Integer>[] friendList) {
        for (int i = 0; i < friendList.length; i++) {
            friendList[i] = new ArrayList<>();
        }
    }

    private static void dfs(int now, List<Integer>[] relation, boolean[] visited, int depth) {
        if (depth == 5 || result == 1) {
            result = 1;
            return;
        }

        for (Integer next : relation[now]) {
            if (!visited[next]) {
                visited[now] = true;
                dfs(next, relation, visited, depth + 1);
            }
        }
    }
}
