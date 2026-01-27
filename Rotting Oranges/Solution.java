// Problem: Rotting Oranges
// Technique: Multi-source BFS (Level Order Traversal)
// Time Complexity: O(N * M)
// Space Complexity: O(N * M)

import java.util.*;

class Solution {

    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        // Step 1: Add all initially rotten oranges to the queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int time = 0;

        // Step 2: BFS traversal
        while (!queue.isEmpty()) {

            int size = queue.size();
            boolean rotted = false;

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];

                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];

                    if (nx >= 0 && nx < n &&
                        ny >= 0 && ny < m &&
                        grid[nx][ny] == 1 &&
                        !visited[nx][ny]) {

                        visited[nx][ny] = true;
                        grid[nx][ny] = 2;
                        queue.add(new int[]{nx, ny});
                        rotted = true;
                    }
                }
            }

            // Increment time only if any orange rotted in this level
            if (rotted) {
                time++;
            }
        }

        // Step 3: Check if any fresh orange remains
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return time;
    }
}
