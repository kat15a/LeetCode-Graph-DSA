class Pair {
    int first;
    int second;
    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {
    public int findMaxFish(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        int result = 0;

        int[] delrow = {-1, 0, 1, 0};
        int[] delcol = {0, 1, 0, -1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] != 0 && vis[i][j] == 0) {

                    int count = 0;
                    Queue<Pair> q = new LinkedList<>();
                    q.add(new Pair(i, j));
                    vis[i][j] = 1;

                    while (!q.isEmpty()) {
                        Pair p = q.poll();
                        int r = p.first;
                        int c = p.second;

                        count += grid[r][c];

                        for (int d = 0; d < 4; d++) {
                            int nr = r + delrow[d];
                            int nc = c + delcol[d];

                            if (nr >= 0 && nr < n &&
                                nc >= 0 && nc < m &&
                                vis[nr][nc] == 0 &&
                                grid[nr][nc] != 0) {

                                vis[nr][nc] = 1;
                                q.add(new Pair(nr, nc));
                            }
                        }
                    }

                    result = Math.max(result, count);
                }
            }
        }

        return result;
    }
}
