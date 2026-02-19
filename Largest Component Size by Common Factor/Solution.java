class DisjointSet{
    int[] parent;
    int[] rank;
    public DisjointSet(int n){
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
            rank[i]=0;
        }
    }
    public int find(int node){
        if(parent[node]==node){
            return node;
        }
        return parent[node]=find(parent[node]);
    }
    public void union(int u,int v){
        int ulp_u=find(u);
        int ulp_v=find(v);
        if(ulp_u==ulp_v)return ;
        if(rank[ulp_u]<rank[ulp_v]){
            parent[ulp_u]=ulp_v;
        }
        else if(rank[ulp_u]>rank[ulp_v]){
            parent[ulp_v]=ulp_u;
        }
        else{
            parent[ulp_u]=ulp_v;
            rank[ulp_u]++;
        }
    }
}
class Solution {
    public int largestComponentSize(int[] nums){
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        DisjointSet ds=new DisjointSet(max+1);
        for(int num:nums){
            for(int fac=2;fac*fac<=num;fac++){
                if(num%fac==0){
                    ds.union(num,fac);
                    ds.union(num,num/fac);
                }
            }
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            int parent = ds.find(num);
            map.put(parent, map.getOrDefault(parent, 0) + 1);
            res = Math.max(res, map.get(parent));
        }
        return res;
    }
}
