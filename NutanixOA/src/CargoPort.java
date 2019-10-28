import java.util.*;

public class CargoPort {
	public int cargoPort(List<String> inputs) {
		String[] line1=inputs.get(0).split(" ");
		String[] line2=inputs.get(1).split(" ");
		String[] line3=inputs.get(2).split(" ");
		String[] line4=inputs.get(3).split(" ");
		int N=Integer.parseInt(line1[0]);
		int X=Integer.parseInt(line2[0]);
		int Y=Integer.parseInt(line2[1]);
		int[] weights=new int[line3.length];
		int[] des=new int[line4.length];
		for(int i=0; i<line3.length; i++) {
			weights[i]=Integer.parseInt(line3[i]);
		}
		for(int i=0; i<line4.length; i++) {
			des[i]=Integer.parseInt(line4[i]);
		}
		int result=0;
		int index=0;
		Set<Integer> countDes=new HashSet<>();
		while(index < N) {
			int weight=0;
			int num=0;
			result++;
			countDes.clear();
			while(index < N && weight <= Y && num < X) {
				weight+=weights[index];
				if(weight > Y || num == X) {
					break;
				}
				num++;
				countDes.add(des[index]);
				System.out.println("port # is "+des[index]);
				index++;
			}
			result+=countDes.size();
			System.out.println("delivery times: "+countDes.size());
		}
		return result;
	}
}
