// Problem: Cheapest Flights Within K Stops
// Technique: Modified Dijkstra
// Concept: Shortest Path with Stop Constraint
// Time Complexity: O(E * log V)
// Space Complexity: O(V + E)

import java.util.*;

class Pair {
    int cost;
    int node;
    int stops;

    Pair(int cost, int node, int stops) {
        this.cost = cost;
        this.node = node;
        this.stops = stops;
    }
}

class Solution {

    public int findCheapestPrice(int n, int[][] flights,
                                 int src, int dst, int k) {

        // Build adjacency list
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        // Min-heap based on cost
        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> a.cost - b.cost);

        pq.add(new Pair(0, src, 0));

        int[] minStops = new int[n];
        Arrays.fill(minStops, Integer.MAX_VALUE);

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();
            int cost = curr.cost;
            int node = curr.node;
            int stops = curr.stops;

            if (node == dst) {
                return cost;
            }

            if (stops > k || minStops[node] < stops) {
                continue;
            }

            minStops[node] = stops;

            for (int[] next : adj.get(node)) {
                pq.add(new Pair(
                        cost + next[1],
                        next[0],
                        stops + 1
                ));
            }
        }

        return -1;
    }
}
