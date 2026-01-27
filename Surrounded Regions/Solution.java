// Problem: Surrounded Regions
// Technique: Breadth First Search (BFS)
// Concept: Boundary-based Traversal
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

    public void solve(char[][] board) {

        int n = board.length;
        int m = board[0].length;

        Queue<Pair> queue = new LinkedList<>();

        // Step 1: Add all boundary 'O's to the queue
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                queue.add(new Pair(i, 0));
                board[i][0] = '#';
            }
            if (board[i][m - 1] == 'O') {
                queue.add(new Pair(i, m - 1));
                board[i][m - 1] = '#';
            }
        }

        for (int j = 0; j < m; j++) {
            if (board[0][j] == 'O') {
                queue.add(new Pair(0, j));
                board[0][j] = '#';
            }
            if (board[n - 1][j] == 'O') {
                queue.add(new Pair(n - 1, j));
                board[n - 1][j] = '#';
            }
        }

        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        // Step 2: BFS from boundary-connected 'O's
        while (!queue.isEmpty()) {

            Pair curr = queue.poll();
            int r = curr.row;
            int c = curr.col;

            for (int i = 0; i < 4; i++) {
                int nrow = r + drow[i];
                int ncol = c + dcol[i];

                if (nrow >= 0 && nrow < n &&
                    ncol >= 0 && ncol < m &&
                    board[nrow][ncol] == 'O') {

                    board[nrow][ncol] = '#';
                    queue.add(new Pair(nrow, ncol));
                }
            }
        }

        // Step 3: Convert remaining 'O' to 'X' and '#' back to 'O'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
}
