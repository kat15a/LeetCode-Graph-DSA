class DisjointSet {
    int[] parent;
    int[] size;

    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int node) {
        if (parent[node] == node)
            return node;

        return parent[node] = find(parent[node]);
    }

    public void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if (pu == pv) return;
        if (size[pu] < size[pv]) {
            parent[pu] = pv;
            size[pv] += size[pu];
        } else {
            parent[pv] = pu;
            size[pu] += size[pv];
        }
    }
}
class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n * n);

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {

                if (grid[r][c] == 0) continue;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr >= 0 && nc >= 0 && nr < n && nc < n 
                        && grid[nr][nc] == 1) {

                        int node1 = r * n + c;
                        int node2 = nr * n + nc;

                        ds.union(node1, node2);
                    }
                }
            }
        }

        int max = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {

                if (grid[r][c] == 1) continue;

                HashSet<Integer> set = new HashSet<>();
                int total = 1;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr >= 0 && nc >= 0 && nr < n && nc < n 
                        && grid[nr][nc] == 1) {

                        int parent = ds.find(nr * n + nc);
                        set.add(parent);
                    }
                }

                for (int parent : set) {
                    total += ds.size[parent];
                }

                max = Math.max(max, total);
            }
        }
        for (int i = 0; i < n * n; i++) {
            max = Math.max(max, ds.size[ds.find(i)]);
        }

        return max;
    }
}
