// Problem: Island Perimeter
// Technique: Breadth First Search (BFS)
// Concept: Boundary Counting in Grid
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

    private int bfs(int[][] grid, int[][] visited, int row, int col) {

        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(row, col));
        visited[row][col] = 1;

        int perimeter = 0;

        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        while (!queue.isEmpty()) {

            Pair curr = queue.poll();
            int r = curr.row;
            int c = curr.col;

            for (int i = 0; i < 4; i++) {
                int nrow = r + drow[i];
                int ncol = c + dcol[i];

                // Out of bounds contributes to perimeter
                if (nrow < 0 || nrow >= n || ncol < 0 || ncol >= m) {
                    perimeter++;
                }
                // Water cell contributes to perimeter
                else if (grid[nrow][ncol] == 0) {
                    perimeter++;
                }
                // Unvisited land cell
                else if (visited[nrow][ncol] == 0) {
                    visited[nrow][ncol] = 1;
                    queue.add(new Pair(nrow, ncol));
                }
            }
        }

        return perimeter;
    }

    public int islandPerimeter(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];
        int totalPerimeter = 0;

        // Find the land cell and start BFS
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    totalPerimeter += bfs(grid, visited, i, j);
                }
            }
        }

        return totalPerimeter;
    }
}
