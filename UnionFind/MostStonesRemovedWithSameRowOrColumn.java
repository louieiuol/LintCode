//947. Most Stones Removed with Same Row or Column
public class MostStonesRemovedWithSameRowOrColumn {
	    public int removeStones(int[][] stones) {
	        if(stones.length == 0) return 0;
	        UnionFind uf =  new UnionFind();
	        for(int i=0; i<stones.length; i++){
	            uf.union(stones[i][0]+ 10000, stones[i][1]);
	        }
	        return stones.length - uf.getCount();
	    }

	    public class UnionFind{
	        Map<Integer, Integer> map;
	        int count;

	        public UnionFind(){
	            map = new HashMap<>();
	            count = 0;
	        }

	        public int getCount(){
	            return count;
	        }

	        public int find(int x){
	            if(!map.containsKey(x)){
	                map.put(x, x);
	                count++;
	            }
	            if( x != map.get(x)){
	                map.put(x, find(map.get(x)));
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
