import java.util.*;
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] test1= {
				{'B', 'B', 'B', 'B', 'B'},
				{'B', 'G', 'G', 'G', 'B'},
				{'B', 'G', 'B', 'G', 'B'},
				{'B', 'G', 'G', 'G', 'B'},
				{'B', 'B', 'B', 'G', 'B'} 
		};
		char[][] test2= {
				{'B', 'B', 'B', 'B', 'B'},
				{'B', 'G', 'B', 'G', 'B'},
				{'B', 'G', 'B', 'G', 'B'},
				{'B', 'G', 'G', 'G', 'B'},
				{'B', 'B', 'B', 'G', 'B'} 
		};
		
		char[][] test3= {
				{'B', 'R', 'B', 'B', 'B'},
				{'B', 'G', 'B', 'G', 'B'},
				{'B', 'G', 'B', 'G', 'B'},
				{'B', 'G', 'G', 'R', 'R'},
				{'B', 'B', 'B', 'R', 'B'} 				
		};
		
		//MatrixLoop ml=new MatrixLoop();
		//System.out.println(ml.findLoop(test1));
		//System.out.println(ml.findLoop(test2));
		//System.out.println(ml.findLoop(test3));
		
		
		//GumballMachine gm=new GumballMachine();
		//List<String> eg1=new ArrayList<>();
		//eg1.addAll(Arrays.asList(new String[]{"red", "green", "blue", "red", "white"}));
		//System.out.println(gm.gumballMachine(eg1));
		CargoPort cp=new CargoPort();
		List<String> ex1=new ArrayList<>();
		ex1.add("3 5");
		ex1.add("2 150");
		ex1.add("70 60 30");
		ex1.add("2 4 1");
		System.out.println("Stops: "+cp.cargoPort(ex1));
	
		CargoPort cp2=new CargoPort();
		List<String> ex2=new ArrayList<>();
		ex2.add("5 3");
		ex2.add("5 180");
		ex2.add("30 30 100 90 50");
		ex2.add("3 3 1 1 3");
		System.out.println("Stops: "+cp2.cargoPort(ex2));
	}

}
