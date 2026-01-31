// Problem: Max Area of Island
// Technique: Breadth First Search (BFS)
// Concept: Connected Components
// Time Complexity: O(N * M)
// Space Complexity: O(N * M)

import java.util.*;

class Pair {
    int row;
    int col;

    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {

    private int bfs(int row, int col, int[][] grid, int[][] visited) {

        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(row, col));
        visited[row][col] = 1;

        int area = 1;

        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        while (!queue.isEmpty()) {

            Pair curr = queue.poll();
            int r = curr.row;
            int c = curr.col;

            for (int i = 0; i < 4; i++) {
                int nrow = r + drow[i];
                int ncol = c + dcol[i];

                if (nrow >= 0 && nrow < n &&
                    ncol >= 0 && ncol < m &&
                    grid[nrow][ncol] == 1 &&
                    visited[nrow][ncol] == 0) {

                    visited[nrow][ncol] = 1;
                    area++;
                    queue.add(new Pair(nrow, ncol));
                }
            }
        }

        return area;
    }

    public int maxAreaOfIsland(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] visited = new int[n][m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    maxArea = Math.max(maxArea, bfs(i, j, grid, visited));
                }
            }
        }

        return maxArea;
    }
}
