// Problem: Keys and Rooms
// Technique: Breadth First Search (BFS)
// Concept: Graph Reachability
// Time Complexity: O(V + E)
// Space Complexity: O(V)

import java.util.*;

class Solution {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        int n = rooms.size();
        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;

        // BFS traversal
        while (!queue.isEmpty()) {

            int room = queue.poll();

            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    visited[key] = true;
                    queue.offer(key);
                }
            }
        }

        // Check if all rooms are visited
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }
}
