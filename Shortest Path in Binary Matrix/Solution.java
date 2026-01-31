// Problem: Shortest Path in Binary Matrix
// Technique: Breadth First Search (BFS)
// Concept: Shortest Path in Unweighted Grid
// Time Complexity: O(N * N)
// Space Complexity: O(N * N)

import java.util.*;

class Pair {
    int row;
    int col;
    int dist;

    Pair(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}

class Solution {

    private int bfs(int[][] grid, int[][] visited) {

        int n = grid.length;
        Queue<Pair> queue = new LinkedList<>();

        visited[0][0] = 1;
        queue.add(new Pair(0, 0, 1));

        while (!queue.isEmpty()) {

            Pair curr = queue.poll();
            int r = curr.row;
            int c = curr.col;
            int d = curr.dist;

            // Destination reached
            if (r == n - 1 && c == n - 1) {
                return d;
            }

            // Explore 8 directions
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {

                    int nrow = r + dr;
                    int ncol = c + dc;

                    if (nrow >= 0 && nrow < n &&
                        ncol >= 0 && ncol < n &&
                        visited[nrow][ncol] == 0 &&
                        grid[nrow][ncol] == 0) {

                        visited[nrow][ncol] = 1;
                        queue.add(new Pair(nrow, ncol, d + 1));
                    }
                }
            }
        }

        return -1;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;
        int[][] visited = new int[n][n];

        // If start or end is blocked
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        return bfs(grid, visited);
    }
}
