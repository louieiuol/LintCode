package data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import basics.Order;

/**
 * 
 * @author Guanhua Liu
 * This class is used for reading input data 
 * 
 */
public class DataReader {
	HashMap<String, Order> datalist;
	String inputPath;
	//Initialize variables, if contains error throw exception
	public DataReader(String path) throws IOException {
		this.inputPath = path;
		File file=new File(path);
		if(!file.exists()) {
			throw new FileNotFoundException(String.format("File %s not found.", file.getAbsolutePath()));
		}
		//store data into HashMap
		datalist=new HashMap<String,Order>();
		//Initialize reader buffer
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line="";
		while ((line = br.readLine()) != null) {
			String[] element=line.split(" ");
			if(element!= null) {
				if(element.length == 0) continue;
				//checking string array is not null, not empty and legal number of arguments
				if(element.length != 3 && !element[0].equals("")) {
					System.out.println("line contains invaild data: " +line);
					continue;
				}
				//Initialize data validate process
				DataValidator dv=new DataValidator(element[0], element[1], element[2]);
				if(dv.validate()) {
					//if all data are valid
					Order order = new Order(element[0], element[1], element[2]);
					if(order != null) {
						if(!datalist.containsKey(order.getId())) {
							//store into HashMap, if HashMap doesn't contain
							datalist.put(order.getId(),order);
						}else {
							System.out.println("Contains same id at line: "+line);
						}
					}
				}

			}
		}
		this.inputPath = path;
		br.close();
	}

	/**
	 * 
	 * @return HashMap
	 * return a map contains data read by buffer
	 */
	public HashMap<String, Order> getData(){
		return this.datalist;
	}
}
