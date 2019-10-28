import java.util.*;
public class KClosestPoint {
	 class Point {
		     int x;
		     int y;
		     Point() { x = 0; y = 0; }
		    Point(int a, int b) { x = a; y = b; }
	 }
	    public Point[] kclosestPoint(Point[] points, Point origin, int k) {
	        Point[] result=new Point[k];
	        if(points == null || k<=0 || origin ==null) return result;
	        if(points.length == 0) return result;
	        PriorityQueue<Point> qp=new PriorityQueue<>(k,new Comparator<Point>(){
	            public int compare(Point p1, Point p2){
	                int dis1=Math.abs(p1.x-origin.x)*Math.abs(p1.x-origin.x)+
	                         Math.abs(p1.y-origin.y)*Math.abs(p1.y-origin.y);
	                int dis2=Math.abs(p2.x-origin.x)*Math.abs(p2.x-origin.x)+
	                         Math.abs(p2.y-origin.y)*Math.abs(p2.y-origin.y);
	                if(dis1>dis2){
	                    return -1;
	                }else if(dis1<dis2){
	                    return 1;
	                }else{
	                    if((p1.x-p2.x)>0){
	                        return -1;
	                    }else if((p1.x-p2.x)<0){
	                        return 1;
	                    }else{
	                        return p2.y-p1.y;
	                    }
	                }         
	            }
	            });
	        for(Point p: points){
	            qp.add(p);
	            if(qp.size()>k){
	                qp.poll();
	            }
	        }
	        for(int i=result.length-1; i>=0;i--){
	            if(!qp.isEmpty()){
	                result[i]=qp.poll();
	            }
	        }
	        return result;
	    }
	}
