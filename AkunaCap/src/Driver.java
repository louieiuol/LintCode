
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//AccountNumberValidation anv=new AccountNumberValidation();
		//System.out.println(anv.isValid("BADF00D5"));
		//System.out.println(anv.isValid("1CC0FfEE"));
		TravelDistance td=new TravelDistance();
		System.out.println(td.addInput("LOC:CHI:41.836944:-87.684722"));
		System.out.println(td.addInput("LOC:NYC:40.7127:-74.0059"));
		System.out.println(td.addInput("TRIP:C0FFEE1C:CHI:NYC"));
	}

}
