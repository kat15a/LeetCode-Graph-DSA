// Problem: High Score (CSES)
// Technique: Bellman-Ford + Positive Cycle Detection
// Concept: Longest Path in Directed Graph
// Time Complexity: O(N * M)
// Space Complexity: O(N + M)

import java.util.*;

public class HighScore {

    static List<List<Integer>> adj;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        long[][] edges = new long[m][3];

        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Read edges
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            long w = sc.nextLong();

            edges[i][0] = u;
            edges[i][1] = v;
            edges[i][2] = w;

            adj.get(u).add(v);
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MIN_VALUE);
        dist[0] = 0;

        // Bellman-Ford Relaxation (n-1 times)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {

                int u = (int) edges[j][0];
                int v = (int) edges[j][1];
                long w = edges[j][2];

                if (dist[u] != Long.MIN_VALUE &&
                    dist[u] + w > dist[v]) {

                    dist[v] = dist[u] + w;
                }
            }
        }

        boolean[] visited = new boolean[n];

        // Check for positive cycle affecting destination
        for (int j = 0; j < m; j++) {

            int u = (int) edges[j][0];
            int v = (int) edges[j][1];
            long w = edges[j][2];

            if (dist[u] != Long.MIN_VALUE &&
                dist[u] + w > dist[v]) {

                Arrays.fill(visited, false);

                if (dfs(v, n - 1, visited)) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(dist[n - 1]);
    }

    static boolean dfs(int node, int target, boolean[] visited) {

        if (node == target) return true;

        visited[node] = true;

        for (int nei : adj.get(node)) {
            if (!visited[nei]) {
                if (dfs(nei, target, visited)) {
                    return true;
                }
            }
        }

        return false;
    }
}
