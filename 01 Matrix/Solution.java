// Problem: 01 Matrix
// Technique: Multi-source BFS
// Concept: Shortest Distance in Unweighted Graph
// Time Complexity: O(N * M)
// Space Complexity: O(N * M)

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

    public int[][] updateMatrix(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        int[][] visited = new int[n][m];
        int[][] distance = new int[n][m];

        Queue<Pair> queue = new LinkedList<>();

        // Step 1: Push all 0-cells into queue (multi-source BFS)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new Pair(i, j, 0));
                    visited[i][j] = 1;
                }
            }
        }

        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        // Step 2: BFS traversal
        while (!queue.isEmpty()) {

            Pair curr = queue.poll();
            int row = curr.row;
            int col = curr.col;
            int dist = curr.dist;

            distance[row][col] = dist;

            for (int i = 0; i < 4; i++) {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];

                if (nrow >= 0 && nrow < n &&
                    ncol >= 0 && ncol < m &&
                    visited[nrow][ncol] == 0) {

                    visited[nrow][ncol] = 1;
                    queue.add(new Pair(nrow, ncol, dist + 1));
                }
            }
        }

        return distance;
    }
}
