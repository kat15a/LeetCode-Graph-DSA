// Problem: Number of Ways to Arrive at Destination
// Technique: Dijkstra + Path Counting
// Time Complexity: O(E log V)
// Space Complexity: O(V + E)

import java.util.*;

class Pair {
    int node;
    long dist;

    Pair(int node, long dist) {
        this.node = node;
        this.dist = dist;
    }
}

class Solution {

    public int countPaths(int n, int[][] roads) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Build graph
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];

            adj.get(u).add(new Pair(v, w));
            adj.get(v).add(new Pair(u, w));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        int mod = 1_000_000_007;
        int[] ways = new int[n];
        ways[0] = 1;

        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

        pq.add(new Pair(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();
            int node = curr.node;
            long d = curr.dist;

            if (d > dist[node]) continue;

            for (Pair edge : adj.get(node)) {

                int next = edge.node;
                long weight = edge.dist;

                // Shorter path found
                if (dist[node] + weight < dist[next]) {

                    dist[next] = dist[node] + weight;
                    ways[next] = ways[node];
                    pq.add(new Pair(next, dist[next]));
                }

                // Another shortest path found
                else if (dist[node] + weight == dist[next]) {

                    ways[next] = (ways[next] + ways[node]) % mod;
                }
            }
        }

        return ways[n - 1];
    }
}
