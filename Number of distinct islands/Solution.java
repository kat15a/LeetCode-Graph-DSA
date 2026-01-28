// Problem: Number of Distinct Islands
// Technique: Depth First Search (DFS)
// Concept: Shape Encoding using Relative Coordinates
// Time Complexity: O(N * M)
// Space Complexity: O(N * M)

import java.util.*;

class Solution {

    public int numDistinctIslands(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        Set<String> shapes = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    StringBuilder shape = new StringBuilder();
                    dfs(grid, visited, i, j, i, j, shape);
                    shapes.add(shape.toString());
                }
            }
        }

        return shapes.size();
    }

    private void dfs(int[][] grid, boolean[][] visited,
                     int row, int col,
                     int baseRow, int baseCol,
                     StringBuilder shape) {

        int n = grid.length;
        int m = grid[0].length;

        visited[row][col] = true;

        // store relative position
        shape.append((row - baseRow) + "," + (col - baseCol) + ";");

        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nrow = row + drow[i];
            int ncol = col + dcol[i];

            if (nrow >= 0 && nrow < n &&
                ncol >= 0 && ncol < m &&
                grid[nrow][ncol] == 1 &&
                !visited[nrow][ncol]) {

                dfs(grid, visited, nrow, ncol, baseRow, baseCol, shape);
            }
        }
    }
}
