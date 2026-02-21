class DisjointSet {
    int[] parent;
    int[] size;

    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int node) {
        if (parent[node] == node)
            return node;

        return parent[node] = find(parent[node]);
    }

    public void union(int u, int v) {
        int ulp_u = find(u);
        int ulp_v = find(v);

        if (ulp_u == ulp_v) return;

        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        } else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}

class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n=s.length();
        DisjointSet d=new DisjointSet(n);
        for(List<Integer> e: pairs){
            int u=e.get(0);
            int v=e.get(1);
            d.union(u,v);
        }
        ArrayList<ArrayList<Integer>> comp=new ArrayList<>();
        for(int i=0;i<n;i++){
            comp.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int parent = d.find(i);  
            comp.get(parent).add(i);
        }
        char[] result=new char[n];
        char[] str=s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (comp.get(i).size() > 0) {

                ArrayList<Integer> id = comp.get(i);
                ArrayList<Character> temp = new ArrayList<>();

                for (int x : id) {
                    temp.add(str[x]);
                }

                Collections.sort(temp);

                for (int j = 0; j < id.size(); j++) {
                    result[id.get(j)] = temp.get(j);
                }
            }
        }

        return new String(result);
    }
}

