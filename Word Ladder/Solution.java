// Problem: Word Ladder
// Technique: Breadth First Search (BFS)
// Concept: Shortest Path in Unweighted Graph
// Time Complexity: O(N * L * 26)
// Space Complexity: O(N)

import java.util.*;

class Pair {
    String word;
    int steps;

    Pair(String word, int steps) {
        this.word = word;
        this.steps = steps;
    }
}

class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);

        // If endWord is not present, no transformation possible
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));
        wordSet.remove(beginWord);

        while (!queue.isEmpty()) {

            Pair curr = queue.poll();
            String word = curr.word;
            int steps = curr.steps;

            if (word.equals(endWord)) {
                return steps;
            }

            // Try changing each character
            for (int i = 0; i < word.length(); i++) {
                char[] arr = word.toCharArray();

                for (char ch = 'a'; ch <= 'z'; ch++) {
                    arr[i] = ch;
                    String newWord = new String(arr);

                    if (wordSet.contains(newWord)) {
                        wordSet.remove(newWord);
                        queue.add(new Pair(newWord, steps + 1));
                    }
                }
            }
        }

        return 0;
    }
}
