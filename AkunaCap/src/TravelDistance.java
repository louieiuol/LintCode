import java.util.*;

public class TravelDistance {
	class Location{
		double latitude;
		double longtitude;
		public Location(double latitude, double longtitude) {
			this.latitude=latitude;
			this.longtitude=longtitude;
		}
	}
	HashMap<String, Location> map=new HashMap<>();
	public String addInput(String str) {
		if(str == null || str.length() == 0) return "Invalid input";
		String[] lines=str.split(":");
		if(lines[0].equals("LOC")) {
			String city=lines[1];
			double latitude=Double.parseDouble(lines[2]);
			double longtitude=Double.parseDouble(lines[3]);
			map.put(city, new Location(latitude, longtitude));
			return city;
		}
		if(lines[0].equals("TRIP")) {
			String account=lines[1];
			String n1=lines[2];
			String n2=lines[3];
			Location c1=map.get(lines[2]);
			Location c2=map.get(lines[3]);
			if(c1 == null || c2 == null) return "Invalid input";
			double absDistance=Math.abs(Math.toRadians(c1.longtitude) - Math.toRadians(c2.longtitude));
			double angle=Math.acos(Math.sin(Math.toRadians(c1.latitude)) * Math.sin(Math.toRadians(c2.latitude))+
					Math.cos(Math.toRadians(c1.latitude)) * Math.cos(Math.toRadians(c2.latitude))*Math.cos(absDistance));
			double distance=angle*3963;
			String result="";
			result += ( account + ":" + n1 + ":" + n2 + ":" + angle +":" + (int) distance);
			return result;
		}
		return "Invalid input";
	}
}
