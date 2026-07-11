class Solution {
    List<Integer>[] graph;
    boolean[] visited;

    public int countCompleteComponents(int n, int[][] edges) {
        graph = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] res = dfs(i); // {nodes, degreeSum}

                int nodes = res[0];
                int edgeCount = res[1] / 2;

                if (edgeCount == nodes * (nodes - 1) / 2)
                    ans++;
            }
        }

        return ans;
    }

    private int[] dfs(int node) {
        visited[node] = true;

        int nodes = 1;
        int degreeSum = graph[node].size();

        for (int nei : graph[node]) {
            if (!visited[nei]) {
                int[] temp = dfs(nei);
                nodes += temp[0];
                degreeSum += temp[1];
            }
        }

        return new int[]{nodes, degreeSum};
    }
}
