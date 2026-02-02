// Problem: Path With Minimum Effort
// Technique: Dijkstra’s Algorithm
// Concept: Minimax Path
// Time Complexity: O(N * M * log(N * M))
// Space Complexity: O(N * M)

import java.util.*;

class Pair {
    int row;
    int col;
    int effort;

    Pair(int row, int col, int effort) {
        this.row = row;
        this.col = col;
        this.effort = effort;
    }
}

class Solution {

    public int minimumEffortPath(int[][] heights) {

        int n = heights.length;
        int m = heights[0].length;

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> a.effort - b.effort
        );

        pq.add(new Pair(0, 0, 0));
        dist[0][0] = 0;

        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();
            int r = curr.row;
            int c = curr.col;
            int currEffort = curr.effort;

            // Destination reached with minimum effort
            if (r == n - 1 && c == m - 1) {
                return currEffort;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + drow[i];
                int nc = c + dcol[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m) {

                    int newEffort = Math.max(
                        currEffort,
                        Math.abs(heights[r][c] - heights[nr][nc])
                    );

                    if (newEffort < dist[nr][nc]) {
                        dist[nr][nc] = newEffort;
                        pq.add(new Pair(nr, nc, newEffort));
                    }
                }
            }
        }

        return 0;
    }
}
