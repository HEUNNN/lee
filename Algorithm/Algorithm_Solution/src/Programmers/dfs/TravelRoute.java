package Programmers.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TravelRoute {
    // https://school.programmers.co.kr/learn/courses/30/lessons/43164

    static List<String> result = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) {
        String[][] tickets1 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        String[][] tickets2 = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets3 = {{"ICN", "A"}, {"A", "B"}, {"A", "C"}, {"B", "A"}, {"C", "A"}};
        String[][] tickets4 = {{"ICN", "B"}, {"ICN", "A"}, {"B", "C"}, {"C", "ICN"}};
        String[] solution = new TravelRoute().solution(tickets1);
    }

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        dfs(0, "ICN", "ICN", tickets);
        Collections.sort(result);
        return result.get(0).split(" ");
    }

    private void dfs(int depth, String now, String route, String[][] tickets) {
        if (depth == tickets.length) {
            result.add(route);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(now)) {
                visited[i] = true;
                dfs(depth + 1, tickets[i][1], route + " " + tickets[i][1], tickets);
                visited[i] = false;
            }
        }
    }
}
