// Problem: Find the City With the Smallest Number of Neighbors
// Technique: Floyd–Warshall Algorithm
// Concept: All-Pairs Shortest Path
// Time Complexity: O(N^3)
// Space Complexity: O(N^2)

import java.util.*;

class Solution {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        int[][] dist = new int[n][n];

        // Step 1: Initialize distance matrix
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        // Step 2: Fill direct edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            dist[u][v] = w;
            dist[v][u] = w;
        }

        // Step 3: Floyd–Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (dist[i][k] != Integer.MAX_VALUE &&
                        dist[k][j] != Integer.MAX_VALUE) {

                        dist[i][j] = Math.min(
                            dist[i][j],
                            dist[i][k] + dist[k][j]
                        );
                    }
                }
            }
        }

        // Step 4: Find city with smallest reachable count
        int cityNo = -1;
        int minCount = n;

        for (int city = 0; city < n; city++) {

            int count = 0;

            for (int adjCity = 0; adjCity < n; adjCity++) {
                if (dist[city][adjCity] <= distanceThreshold) {
                    count++;
                }
            }

            // If tie, choose larger city index
            if (count <= minCount) {
                minCount = count;
                cityNo = city;
            }
        }

        return cityNo;
    }
}
