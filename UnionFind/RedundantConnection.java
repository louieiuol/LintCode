public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        List<int[]> result= new ArrayList<>();
        UnionFind uf = new UnionFind();
        for(int[] edge: edges){
            if(!uf.union(edge[0], edge[1])){
                return edge;
            }
        }
        return new int[]{};
    }

    public class UnionFind{
        HashMap<Integer, Integer> map = new HashMap<>();
        public int find(int x){
            if(!map.containsKey(x)){
                map.put(x, x);
                return x;
            }
            int parent = map.get(x);
            if(x != parent){
                map.put(x, find(parent));
            }
            return map.get(x);
        }

        public boolean union(int x, int y){
            int xParent = find(x);
            int yParent = find(y);
            if(xParent == yParent){
                return false;
            }
            map.put(xParent, yParent);
            return true;
        }
    }
}
