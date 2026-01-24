// Problem: Number of Provinces
// Technique: Depth First Search (DFS)
// Concept: Connected Components
// Time Complexity: O(N^2)
// Space Complexity: O(N^2)

import java.util.ArrayList;

class Solution {

    public int findCircleNum(int[][] isConnected) {

        int v = isConnected.length;

        // Step 1: Create adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        // Step 2: Convert adjacency matrix to adjacency list
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                }
            }
        }

        int[] visited = new int[v];
        int provinces = 0;

        // Step 3: Count connected components using DFS
        for (int i = 0; i < v; i++) {
            if (visited[i] == 0) {
                provinces++;
                dfs(i, adjList, visited);
            }
        }

        return provinces;
    }

    // DFS traversal
    private void dfs(int node, ArrayList<ArrayList<Integer>> adjList, int[] visited) {
        visited[node] = 1;
        for (int neighbour : adjList.get(node)) {
            if (visited[neighbour] == 0) {
                dfs(neighbour, adjList, visited);
            }
        }
    }
}
