// Problem: Flood Fill
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
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int startColor = image[sr][sc];
        if (startColor == color) return image;

        int n = image.length;
        int m = image[0].length;

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(sr, sc));

        image[sr][sc] = color;

        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nrow = curr.row + drow[i];
                int ncol = curr.col + dcol[i];

                if (nrow >= 0 && nrow < n &&
                    ncol >= 0 && ncol < m &&
                    image[nrow][ncol] == startColor) {

                    image[nrow][ncol] = color;
                    queue.add(new Pair(nrow, ncol));
                }
            }
        }
        return image;
    }
}
