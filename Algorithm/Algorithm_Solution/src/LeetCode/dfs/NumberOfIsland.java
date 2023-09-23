package LeetCode.dfs;

public class NumberOfIsland {
    // https://leetcode.com/problems/number-of-islands/
    public int numIslands(char[][] grid) {
        int answer = 0;
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    answer++;
                    DFS(grid, i, j);
                }
            }
        }
        return answer;

    }

    private void DFS(char[][] grid, int i, int j) {

        grid[i][j] = 'X'; // 방문 표시

        // right
        if ((j + 1) < grid[0].length && grid[i][j + 1] == '1') DFS(grid, i, j + 1); // j = row,  j = col

        // left
        if ((j - 1) >= 0 && grid[i][j - 1] == '1') DFS(grid, i, j - 1);

        // up
        if ((i - 1) >= 0 && grid[i - 1][j] == '1') DFS(grid, i - 1, j);

        // down
        if ((i + 1) < grid.length && grid[i + 1][j] == '1') DFS(grid, i + 1, j);
    }
}
