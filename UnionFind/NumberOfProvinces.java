//547. Number of Provinces
public class NumberOfProvinces {
	    public int findCircleNum(int[][] isConnected) {
	        if(isConnected.length == 0) return 0;
	        UnionFind uf = new UnionFind();
	        for(int i=0; i<isConnected.length; i++){
	            for(int j=0; j< isConnected[0].length; j++){
	                if(isConnected[i][j] == 1){
	                    uf.union(i, j);
	                }
	            }
	        }
	        return uf.getCount();
	    }

	    public class UnionFind{
	        Map<Integer, Integer> map;
	        int count;
	        public UnionFind(){
	            map = new HashMap<>();
	            count = 0;
	        }

	        public int getCount(){
	            return this.count;
	        }
	        public int find(int x){
	            if(!map.containsKey(x)){
	                map.put(x, x);
	                count++;
	            }
	            int parent = map.get(x);
	            if(x != parent){
	                map.put(x, find(parent));
	            }
	            return map.get(x);
	        }

	        public void union(int x, int y){
	            int xParent = find(x);
	            int yParent = find(y);
	            if(xParent == yParent){
	                return;
	            }
	            map.put(xParent, yParent);
	            count--;
	        }
	    }
}
