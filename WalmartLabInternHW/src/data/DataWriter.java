	/**
	 * 
	 * @author Guanhua Liu
	 * This class is used for write data to path
	 * 
	 */


package data;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataWriter {
	String outputPath;
	ArrayList<String> strlist;
	//Initialize data
	public DataWriter(String output, ArrayList<String> strlist) {
		this.outputPath=output;
		this.strlist=strlist;
	}
	
	//write buffer to output path, if fails catch IOException
	public void writeBuffer() throws IOException {
		if(!outputPath.equals("")) {
			FileWriter fw=new FileWriter(outputPath);
			BufferedWriter writer = new BufferedWriter(fw);
			//create a new write buffer
			//change flag to false
			for(String str: strlist) {
				writer.write(str);
			}
			//write the file and close
			writer.close();
		}else {
			//error message if the file name is empty
			System.out.println("file name cannot be empty, please re-enter");
		}
	}
}
