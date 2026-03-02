class Solution {
    public int numSquares(int n) {
         Queue<Integer> q = new LinkedList<>();
         boolean[] visited = new boolean[n+1];
         q.add(n);
         visited[n] = true;

         int steps = 0;
        while(!q.isEmpty()){
            steps++;
            int size = q.size();

            while(size-- >0){
               int curr = q.poll();
               for(int i=1; i*i<=curr; i++){
                int next = curr - i*i;

                if(next == 0) return steps;
                if(!visited[next]){
                    visited[next] = true;
                    q.add(next);
                }
               }
            }
        }
        return steps;

    }
}
