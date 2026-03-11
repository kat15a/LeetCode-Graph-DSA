class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        String t = new StringBuilder(s).reverse().toString();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return n - lcs(0, 0, s, t, dp);

    }

    private int lcs(int i, int j, String s, String t, int[][] dp) {

        if (i == s.length() || j == t.length())
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (s.charAt(i) == t.charAt(j)) {
            return dp[i][j] = 1 + lcs(i + 1, j + 1, s, t, dp);
        }

        return dp[i][j] = Math.max(
                lcs(i + 1, j, s, t, dp),
                lcs(i, j + 1, s, t, dp));
    }
}
