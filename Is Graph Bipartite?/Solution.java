// Problem: Is Graph Bipartite?
// Technique: Breadth First Search (BFS)
// Concept: Graph Coloring
// Time Complexity: O(V + E)
// Space Complexity: O(V)

import java.util.*;

class Solution {

    public boolean isBipartite(int[][] graph) {

        int V = graph.length;
        int[] color = new int[V];
        Arrays.fill(color, -1);

        // Handle disconnected graph
        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!bfs(i, graph, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean bfs(int start, int[][] graph, int[] color) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 0;

        while (!queue.isEmpty()) {

            int node = queue.poll();

            for (int neighbour : graph[node]) {

                if (color[neighbour] == -1) {
                    color[neighbour] = 1 - color[node];
                    queue.add(neighbour);
                }
                // If adjacent nodes have same color, not bipartite
                else if (color[neighbour] == color[node]) {
                    return false;
                }
            }
        }

        return true;
    }
}
