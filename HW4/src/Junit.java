import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.io.*;


public class Junit {

	Cal test=new Cal();
	@Test
	public void test1() throws Exception {
		File file=new File("pairwise.txt");
		@SuppressWarnings("resource")
		BufferedReader br=new BufferedReader(new FileReader(file));
		List<String> inputFile=new ArrayList<>();
		String line=null;
		while((line = br.readLine()) != null) {
			inputFile.add(line);
		}
		for(String str: inputFile) {
			String[] inputs=str.split(",");
			int month1=Integer.parseInt(inputs[0]);
			int date1=Integer.parseInt(inputs[1]);
			int month2=Integer.parseInt(inputs[2]);
			int date2=Integer.parseInt(inputs[3]);
			boolean isLeapYear=Boolean.parseBoolean(inputs[4]);
			String year="";
			if(isLeapYear) {
				year="4";
			}else {
				year="1";
			}
			String str1=inputs[0]+"/"+inputs[1]+"/"+year;
			String str2=inputs[2]+"/"+inputs[3]+"/"+year;
			assertTrue(Math.abs(test.cal(month1, date1, month2, date2, Integer.parseInt(year))) == actualDayDiff(str1, str2));
		} 
	}
	
	public int actualDayDiff(String str1, String str2)
			  throws ParseException {
			  
			    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			    Date firstDate = sdf.parse(str1);
			    Date secondDate = sdf.parse(str2);
			 
			    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
			    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			 
			    return (int) diff;
	}
	
	public boolean diffEquals(int diff1, int diff2) {
		if(diff1 + 1  == diff2 || diff1 == diff2 || diff1 - 1 == diff2) return true;
		return false;
	}
}
