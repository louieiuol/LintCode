package basics;
public class Drone {
	private int id; // Drone id
	Order order; // assigned order
	public Drone(int id, Order order) {
		this.id=id;
		this.order=order;
	}
	
	public void setOrder(Order o) {
		order=o;
	}
	
}
