
public class PointCorrect implements Comparable<PointCorrect>{
	int x;
	int y;
	
	public PointCorrect(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	@Override
	public int compareTo(PointCorrect o) {
			if(this.x != o.x) {
				return this.x-o.x;
			}else {
				return this.y-o.y;
			}
	}
}
