// Problem: Count Battleships on a Board
// Technique: Grid Traversal
// Concept: Counting only starting cells
// Time Complexity: O(N * M)
// Space Complexity: O(1)

class Solution {

    public int countBattleships(char[][] board) {

        int n = board.length;
        int m = board[0].length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (board[i][j] == 'X') {

                    // If part of an existing ship, skip
                    if ((i > 0 && board[i - 1][j] == 'X') ||
                        (j > 0 && board[i][j - 1] == 'X')) {
                        continue;
                    }

                    // New battleship found
                    count++;
                }
            }
        }

        return count;
    }
}
