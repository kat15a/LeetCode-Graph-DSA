// Problem: Minesweeper
// Technique: Breadth First Search (BFS)
// Concept: Grid Simulation with 8 Directions
// Time Complexity: O(M * N)
// Space Complexity: O(M * N)

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

    public char[][] updateBoard(char[][] board, int[] click) {

        int m = board.length;
        int n = board[0].length;

        int r = click[0];
        int c = click[1];

        // If mine is clicked
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }

        int[] dr = {-1,-1,-1,0,0,1,1,1};
        int[] dc = {-1,0,1,-1,1,-1,0,1};

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(r, c));

        while (!queue.isEmpty()) {

            Pair curr = queue.poll();
            int row = curr.row;
            int col = curr.col;

            int mineCount = 0;

            // Count adjacent mines
            for (int i = 0; i < 8; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    if (board[nr][nc] == 'M') {
                        mineCount++;
                    }
                }
            }

            if (mineCount > 0) {
                board[row][col] = (char)(mineCount + '0');
            } else {
                board[row][col] = 'B';

                for (int i = 0; i < 8; i++) {
                    int nr = row + dr[i];
                    int nc = col + dc[i];

                    if (nr >= 0 && nr < m &&
                        nc >= 0 && nc < n &&
                        board[nr][nc] == 'E') {

                        queue.add(new Pair(nr, nc));
                        board[nr][nc] = 'B'; // mark visited
                    }
                }
            }
        }

        return board;
    }
}
