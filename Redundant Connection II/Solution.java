class DisjointSet {
    int[] parent;
    int[] rank;

    public DisjointSet(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }
    public boolean union(int u, int v) {
        int ulp_u = find(u);
        int ulp_v = find(v);

        if (ulp_u == ulp_v) return true; 

        if (rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v;
        } else if (rank[ulp_u] > rank[ulp_v]) {
            parent[ulp_v] = ulp_u;
        } else {
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
        return false;
    }
}

class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {

        int n = edges.length;
        int[] indegree = new int[n + 1];
        Arrays.fill(indegree, -1);

        int bl1 = -1, bl2 = -1;
        for (int i = 0; i < n; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            if (indegree[v] == -1) {
                indegree[v] = i;
            } else {
                bl1 = i;      
                bl2 = indegree[v];
                break;
            }
        }
        DisjointSet ds = new DisjointSet(n);

        for (int i = 0; i < n; i++) {

            if (i == bl1) continue;

            int u = edges[i][0];
            int v = edges[i][1];

            if (ds.union(u, v)) {  
                if (bl1 == -1) {
                    return edges[i];
                }
                return edges[bl2];
            }
        }
        return edges[bl1];
    }
}
