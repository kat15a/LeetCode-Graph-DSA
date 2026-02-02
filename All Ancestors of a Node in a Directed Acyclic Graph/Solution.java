// Problem: All Ancestors of a Node in a Directed Acyclic Graph
// Technique: Depth First Search (DFS)
// Concept: Reverse Graph Traversal
// Time Complexity: O(N * (N + E))
// Space Complexity: O(N + E)

import java.util.*;

class Solution {

    private void dfs(int node,
                     ArrayList<ArrayList<Integer>> adj,
                     List<Integer> ancestors,
                     boolean[] visited) {

        for (int parent : adj.get(node)) {
            if (!visited[parent]) {
                visited[parent] = true;
                ancestors.add(parent);
                dfs(parent, adj, ancestors, visited);
            }
        }
    }

    public List<List<Integer>> getAncestors(int n, int[][] edges) {

        // Step 1: Build reversed adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(v).add(u); // reverse edge
        }

        // Step 2: For each node, run DFS
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            List<Integer> ancestors = new ArrayList<>();

            dfs(i, adj, ancestors, visited);
            Collections.sort(ancestors);
            result.add(ancestors);
        }

        return result;
    }
}
