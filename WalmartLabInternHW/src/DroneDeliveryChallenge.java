import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import basics.Order;
import basics.Time;
import controllers.BFAlgorithm;
import controllers.PriorityAlgorithm;
import controllers.RandomAlgorithm;
import data.DataReader;
import data.DataWriter;

public class DroneDeliveryChallenge {

	public static void main(String[] args) throws IOException {	
		String inputFile ="input2.txt";
		String outputFile = "output.txt";
		String mode = "N";
		boolean defaultOutput=true;
		ArrayList<Order> datalist=new ArrayList<Order>();;
		DataReader dp;
		if(args.length ==2 || args.length == 4 || args.length == 6 ) {
			
			if(args.length == 2) {
				if(args[0].substring(1).equals("input")){
					inputFile=args[1];
				}else {
					throw new IllegalArgumentException("run java -jar DroneDeliveryChallenge.jar -help ");
				}
			}else if(args.length == 4) {
				if(args[0].substring(1).equals("input")){
					inputFile=args[1];
				}else {
					System.out.println("run java \"-jar DroneDeliveryChallenge.jar -help \" for help");
					throw new IllegalArgumentException();
				}
				if(args[2].substring(1).equals("output")) {
					outputFile=args[3];
					defaultOutput=false;
				}else if(args[2].substring(1).equals("mode")) {
					mode=args[3];
				}else {
					System.out.println("run java \"-jar DroneDeliveryChallenge.jar -help \" for help");
					throw new IllegalArgumentException();
				}
			}else if(args.length == 6) {
				if(args[0].substring(1).equals("input")){
					inputFile=args[1];
				}else {
					System.out.println("run java \"-jar DroneDeliveryChallenge.jar -help \" for help");
					throw new IllegalArgumentException();
				}
				if(args[2].substring(1).equals("output")) {
					outputFile=args[3];
					defaultOutput=false;
				}else {
					System.out.println("run java \"-jar DroneDeliveryChallenge.jar -help \" for help");
					throw new IllegalArgumentException();
				}
				if(args[4].substring(1).equals("mode")) {
					mode=args[5];
				}else {
					System.out.println("run java \"-jar DroneDeliveryChallenge.jar -help \" for help");
					throw new IllegalArgumentException();
				}
			}else {
				printHelp();
				return;
			}
		}else {
			printHelp();
			return;
		}
		
		try {
			dp=new DataReader(inputFile);
		}catch(IOException e) {
			throw new IOException("File Not Found");
		}
		HashMap<String, Order> map=dp.getData();
		for(String str: map.keySet()) {
			if(str!=null ) {
				datalist.add(map.get(str));
			}
		}
		
		if(datalist!=null) {
			if(mode.equals("N")) {
				Time start=new Time(6,0,0);
				Time end=new Time(22,0,0);
				PriorityAlgorithm controller=new PriorityAlgorithm(datalist, start, end);
				controller.init();
				controller.start();
				DataWriter dw=new DataWriter(getCompletePath(inputFile,outputFile, defaultOutput),controller.getPrint());
				dw.writeBuffer();
				System.out.println(
						"----------------------------------------------------------------\n"+
						"Output Location:    \n" + getCompletePath(inputFile,outputFile, defaultOutput)+ "\n"+
						"----------------------------------------------------------------\n");
			}else if(mode.equals("S")){
				Time start=new Time(6,0,0);
				Time end=new Time(22,0,0);
				BFAlgorithm controller=new BFAlgorithm(datalist, start, end);
				controller.init();
				controller.start();
				DataWriter dw=new DataWriter(getCompletePath(inputFile,outputFile, defaultOutput),controller.getPrint());
				dw.writeBuffer();
				System.out.println(
						"----------------------------------------------------------------\n"+
						"Output Location:    \n" + getCompletePath(inputFile,outputFile, defaultOutput)+ "\n"+
						"----------------------------------------------------------------\n");
			}else if (mode.equals("A")){
				Time start=new Time(6,0,0);
				Time end=new Time(22,0,0);
				RandomAlgorithm controller=new RandomAlgorithm(datalist, start, end);
				controller.init();
				controller.start();
				controller.execute();
				DataWriter dw=new DataWriter(getCompletePath(inputFile,outputFile, defaultOutput),controller.getPrint());
				dw.writeBuffer();
				System.out.println(
						"----------------------------------------------------------------\n"+
						"Output Location:    \n"+ getCompletePath(inputFile,outputFile, defaultOutput)+ "\n"+
						"----------------------------------------------------------------\n");
			}else {
				throw new IllegalArgumentException("Invalid mode, Valid modes are N, S, A. ");
			}
		}
	}

	private static String getCompletePath(String inputFile, String outputFile, boolean flag) {
		if(inputFile == null || inputFile.length()==0) throw new IllegalArgumentException();
		if(!flag) return outputFile;
		String[] inputStr=inputFile.split("/");
		inputStr[inputStr.length-1]=outputFile;
		String outputAbsolutePath="";
		for(String str: inputStr) {
			outputAbsolutePath +=str+"/";
		}
		return outputAbsolutePath;
	}

	private static void printHelp() {
		// TODO Auto-generated method stub
		System.out.println(
				"---------------------------------------------------------------------- \n"+
				"Prerequisite: \n" +
				"              You must have installed JAVA 1.8 enviornment and JDK  \n"+
				"---------------------------------------------------------------------- \n"+
				"Compile: \n"+
				"     1. Find the Java file \" DroneDeliveryChallenge.java \" location \n"+
				"Or   1. Find the Jar file  \" DroneDeliveryChallenge.jar \" location  \n"+
				"(If it is .java file, run command line:  \n" +
				" 					\"javac DroneDeliveryChallenge.java \" ) \n" +
				"---------------------------------------------------------------------- \n"+
				"Usage(Jar File): \n" + 
				"    java -jar DroneDeliveryChallenge.jar -input [input_file_path]  \n" + 
				"Or  java -jar DroneDeliveryChallenge.jar -input [input_file_path] -output [output_file_path] \n" + 
				"Or  java -jar DroneDeliveryChallenge.jar -input [input_file_path] -mode [N] \n"+ 
				"Or  java -jar DroneDeliveryChallenge.jar -input [input_file_path] -output [output_file_path] -mode [N] \n"+ 
				"Usage(.class File): \n" + 
				"    java DroneDeliveryChallenge.java -input [input_file_path] \n "+
				"Or  java DroneDeliveryChallenge.java -input [input_file_path] -output [output_file_path] \n" +
				"Or  java DroneDeliveryChallenge.java -input [input_file_path] -mode [N]\n" + 
				"Or  java DroneDeliveryChallenge.java -input [input_file_path] -output [output_file_path] -mode [N]\n" + 
				"----------------------------------------------------------------------- \n"+
				"Mode Options: \n" + 
				"-mode      : N: Normal \n" + 
				"           : S: Simple \n" +
				"           : A: Advanced \n "+ 
				"----------------------------------------------------------------------- \n"+
				"Get Help: \n"+
				"  	 java -jar DroneDeliveryChallenge.jar -help \n"+
				"Or	 java DroneDeliveryChallenge.java -help \n" + 
				"----------------------------------------------------------------------- \n");
	}

}
