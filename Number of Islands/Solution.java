// Problem: Number of Islands
// Technique: Breadth First Search (BFS)
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

    // BFS to traverse all connected land cells
    private void bfs(int row, int col, int[][] vis, char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(row, col));
        vis[row][col] = 1;

        // Directions: up, right, down, left
        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nrow = curr.row + drow[i];
                int ncol = curr.col + dcol[i];

                // Check boundaries and unvisited land
                if (nrow >= 0 && nrow < n &&
                    ncol >= 0 && ncol < m &&
                    grid[nrow][ncol] == '1' &&
                    vis[nrow][ncol] == 0) {

                    vis[nrow][ncol] = 1;
                    queue.add(new Pair(nrow, ncol));
                }
            }
        }
    }

    public int numIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        int islands = 0;

        // Traverse grid
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {

                // If unvisited land is found, it's a new island
                if (grid[row][col] == '1' && vis[row][col] == 0) {
                    islands++;
                    bfs(row, col, vis, grid);
                }
            }
        }
        return islands;
    }
}
