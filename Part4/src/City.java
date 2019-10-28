package cmsc420.meeshquest.part3;
/**  
 * @author Julia Narakornpichit
 * UID: 112478452
 */

import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public abstract class City extends Point2D.Float {

	String name;
	Point2D.Float metroCoordinates; 

	public City (String nameIn, int remoteXIn, int remoteYIn, int localXIn, int localYIn) {
		super(localXIn, localYIn);
		this.name = nameIn;
		this.metroCoordinates = new Point2D.Float(remoteXIn, remoteYIn);
	}

	public String getName() {
		return this.name;
	}
	
	/*
	public String getXString() {
		double xDouble = this.getX();
		return java.lang.Double.toString(xDouble);
	}
	
	public String getYString() {
		double yDouble = this.getY();
		return java.lang.Double.toString(yDouble);
	}
	*/
	
	public Point2D.Float getMetroCoord() {
		return metroCoordinates;
	}
	
	public String getXString() {
		int xInt = (int) this.getX();
		return Integer.toString(xInt);

	}
	
	public String getYString() {
		int yInt = (int) this.getY();
		return Integer.toString(yInt);
	}
	
	public String getMetroXString() {
		int xInt = (int) metroCoordinates.getX();
		return Integer.toString(xInt);

	}
	
	public String getMetroYString() {
		int yInt = (int) metroCoordinates.getY();
		return Integer.toString(yInt);
	}


	//public abstract String getColor();
	
	//public abstract String getRadius();
	//
	
        
	public int hashCode() {
           return this.name.hashCode();
	}

	public boolean equals(City city2) {
           return this.name.equals(city2.getName());
	}

	public abstract String toString();
}