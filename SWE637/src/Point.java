
public class Point implements Comparable<Point> {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.x == 0 || o.x == 0) {
				return 0;
			}else {
				if(this.x != o.x) {
					return this.x-o.x;
				}else {
					return this.y-o.y;
				}
			}
		}
}
