import java.util.*;
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BeforeAndAfterPuzzle test=new BeforeAndAfterPuzzle();
		String[] input=new String[9];
		input[0]="mission statement";
		input[1]="a quite bite to eat";
		input[2]="a chip off the old block";
		input[3]="chocolate bar";
		input[4]="mission impossible";
		input[5]="a man on a mission";
		input[6]="block party";
		input[7]="eat my word";
		input[8]="bar of soap";
		String[] result=test.beforeAndAfterPuzzle(input);
		for(String str: result) {
			System.out.println(str);
		}
	}

}
