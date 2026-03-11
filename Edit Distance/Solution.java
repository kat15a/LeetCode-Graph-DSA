class Solution {
    public int minDistance(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        int[][] dp=new int[n+1][m+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(dp[i],-1);
        }
        return helper(0,0,word1,word2,dp);
    }
    private int helper(int i,int j, String word1, String word2,int[][] dp){
        int n=word1.length();
        int m=word2.length();
        if(i==word1.length())return word2.length()-j;
        if(j==word2.length())return word1.length()-i;
        if(dp[i][j]!=-1)return dp[i][j];
        if(word1.charAt(i)==word2.charAt(j)){
            return helper(i+1,j+1,word1,word2,dp);
        }
        int ans=0;
        if(word1.charAt(i)!=word2.charAt(j)){
            int insert=1+helper(i,j+1,word1,word2,dp);
            int delete=1+helper(i+1,j,word1,word2,dp);
            int replace=1+helper(i+1,j+1,word1,word2,dp);
            ans=Math.min(insert,Math.min(delete,replace));
        }
        return dp[i][j]=ans;
    }
}
