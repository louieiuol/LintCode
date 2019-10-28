import java.util.*;
import java.io.*;

public class Main {
	
	static List<Character> consonants=new ArrayList<>();
	static List<Character> vowels=new ArrayList<>();
	public static void main(String[] args) throws Exception {
		File file=new File("part3test1.txt");
		@SuppressWarnings("resource")
		BufferedReader br=new BufferedReader(new FileReader(file));
		String line=null;
		consonants.addAll(Arrays.asList(new Character[] {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'x', 'w', 'y', 'z'}));
		vowels.addAll(Arrays.asList(new Character[] {'a', 'e', 'i', 'o', 'u'}));
		List<String> inputFile=new ArrayList<>();
		while((line = br.readLine()) != null) {
			inputFile.add(line);
		}
		if(inputFile.size() > 0) {
			int option=Integer.parseInt(inputFile.get(0));
			switch(option) {
				case 1: List<Long> result1=part1(inputFile, new ArrayList<String>());
						printOutput(result1);
						break;
				case 2: List<Long> result2=part2(inputFile);
						printOutput(result2);
						break;
				case 3: List<String> result3=part3(inputFile);
						printOutputString(result3);
						break;
				default: throw new Exception("invalid file data type");
			}
		}else {
			throw new Exception("invalid inputFile size");
		}
	}
	
	private static void printOutputString(List<String> result) {
		for(String str: result) {
			System.out.println(str);
		}
	}

	private static List<String> part3(List<String> inputFile) throws Exception{
		if(inputFile.size() == 4) {
			Galaxy g=new Galaxy(Integer.parseInt(inputFile.get(3)), inputFile.get(1), inputFile.get(2));
			List<String> result=new ArrayList<>();
			result.add("Number of planets: " + g.getNumofPlanets());
			result.add("Total mass: "+ g.getMass());
			result.add("Largest sun(s):");
			String suns="";
			for(String s: g.getLargestSun()) {
				suns+= (s+" ");
			}
			result.add(suns);
			String planets="";
			for(String p: g.getLargestPlanet()) {
				planets+=(p+" ");
			}
			result.add(planets);
			return result;
		}else {
			throw new Exception("invalid inputFile size");
		}
		
	}

	private static List<Long> dispatchDistribution(List<String> inputFile, List<String> part2Input, RandomNumberGenerator rng) throws Exception{
		switch(Integer.parseInt(part2Input.get(0))) {
			case 1: return uniformDistribution(inputFile, part2Input, rng);
			case 2: return normalDistribution(inputFile, part2Input, rng);
			default: throw new Exception("invalid distribution type");
		}
	} 
	
	static class Galaxy{
		long N;
		String rngType;
		String parameters;
		List<SolarSystem> solarSystems;
		
		Galaxy(long N, String rngType, String parameters) throws Exception{
			this.N=N;
			this.rngType=rngType;
			this.parameters=parameters;
			this.solarSystems=new ArrayList<>();
			List<String> names=createName(rngType, parameters ,N);
			for(long i=0; i<N; i++) {
				SolarSystem s=new SolarSystem(names.get((int)i), rngType, parameters);
				solarSystems.add(s);
			}
		}
		
		long getMass() {
			long mass=0;
			for(SolarSystem s: solarSystems) {
				mass+=s.getTotalMass();
			}
			return mass;
		}
		
		long getNumofPlanets() {
			long count=0;
			for(SolarSystem s: solarSystems) {
					count+=s.numOfPlanets;
			}
			return count;
		}
		
		List<String> getLargestSun(){
			long maxMass=0;
			List<String> sunNames=new ArrayList<>();
			for(SolarSystem s: solarSystems) {
				if(s.getSunMass() >= maxMass) {
					maxMass=s.getSunMass();
					sunNames.add(s.getName());
				}
			}
			return sunNames;
		}
		
		List<String> getLargestPlanet(){
			long maxMass=0;
			List<String> planetNames=new ArrayList<>();
			for(SolarSystem s: solarSystems) {
				for(Planet p: s.planets) {
					if(p.getMass() >= maxMass) {
						maxMass=p.getMass();
						planetNames.add(p.name);
					}
				}
			}
			return planetNames;
		}
	}
	
	
	
	static class SolarSystem{
		String name;
		long massOfSun;
		long numOfPlanets;
		List<Planet> planets;
		String rngType;
		String parameters;
		SolarSystem(String name, String rngType, String parameters) throws Exception{
			this.name=name;
			this.rngType=rngType;
			this.parameters=parameters;
			this.massOfSun=generateMass(rngType, parameters, 100000, 10000);
			this.numOfPlanets=generateNumberOfPlanents(rngType, parameters);
			this.planets=new ArrayList<>();
			for(int i=0; i<numOfPlanets; i++) {
				long planetMass=generateMass(rngType, parameters, 1500, 250);
				Planet p=new Planet(name+" "+i, planetMass);
				planets.add(p);
			}
		}
		
		long getSunMass() {
			return this.massOfSun;
		}
		
		long getTotalMass(){
			long totalMass=0;
			for(Planet p: planets) {
				totalMass+=p.getMass();
			}
			return totalMass+massOfSun;
		}
		
		String getName() {
			return this.name;
		}
	}
	
	static class Planet{
		String name;
		long mass;
		Planet(String name, long mass){
			this.name=name;
			this.mass=mass;
		}
		
		long getMass() {
			return this.mass;
		}
		
		String getName() {
			return this.name;
		}
	}
	
	
	private static long generateMass(String rngType, String rngParameter, int lowerBound, int upperBound) throws Exception {
		List<String> inputFileForSunMass=new ArrayList<>();
		inputFileForSunMass.add("2");
		inputFileForSunMass.add(rngType);
		inputFileForSunMass.add(rngParameter);
		inputFileForSunMass.add("2");
		inputFileForSunMass.add(Integer.toString(lowerBound)+" "+Integer.toString(upperBound));
		inputFileForSunMass.add("100");
		List<Long> sunMassRange=part2(inputFileForSunMass);
		return sunMassRange.get((int) Math.random()*100);
	}
	
	private static long generateNumberOfPlanents(String rngType, String rngParameter) throws Exception {
		List<String> inputFileForSunMass=new ArrayList<>();
		inputFileForSunMass.add("2");
		inputFileForSunMass.add(rngType);
		inputFileForSunMass.add(rngParameter);
		inputFileForSunMass.add("1");
		inputFileForSunMass.add("1 25");
		inputFileForSunMass.add("25");
		List<Long> sunMassRange=part2(inputFileForSunMass);
		return sunMassRange.get((int) Math.random()*25+1);		
	}

	private static List<Long> normalDistribution(List<String> inputFile, List<String> part2Input,
			RandomNumberGenerator rng) throws Exception {
			List<Long> result=new ArrayList<>();
		   String[] bounds=part2Input.get(1).split(" ");
		   if(bounds.length == 2) {
			   long mean= Integer.parseInt(bounds[0]);
			   long sd=Integer.parseInt(bounds[1]);
			   long numOfOutput=Integer.parseInt(part2Input.get(2));
			   for(int i=0; i< numOfOutput; i++) {
				   result.add(normalDistributionAlogithm(rng, mean, sd));
			   }
			   return result;
		   }else {
			   throw new Exception("bound parameter error");
		   }
	}
	
	public static List<String> createName(String rngType, String rngParameter, long numberOfNames) throws Exception{
		List<String> namesList=new ArrayList<>();
		List<String> inputFileForNameLength=new ArrayList<>();
		inputFileForNameLength.add("2");
		inputFileForNameLength.add(rngType);
		inputFileForNameLength.add(rngParameter);
		inputFileForNameLength.add("1");
		inputFileForNameLength.add("4 10");
		inputFileForNameLength.add(Integer.toString((int)numberOfNames));
		List<Long> namesLength=part2(inputFileForNameLength);
		List<String> inputFileForUniformProbability=new ArrayList<>();
		inputFileForUniformProbability.add("2");
		inputFileForUniformProbability.add(rngType);
		inputFileForUniformProbability.add(rngParameter);
		inputFileForUniformProbability.add("1");
		inputFileForUniformProbability.add("0 3");
		inputFileForUniformProbability.add("9");
		List<Long> uniformProbabilityRange=part2(inputFileForUniformProbability);
		List<String> inputFileForCharC=new ArrayList<>();
		inputFileForCharC.add("2");
		inputFileForCharC.add(rngType);
		inputFileForCharC.add(rngParameter);
		inputFileForCharC.add("1");
		inputFileForCharC.add("0 "+Integer.toString(consonants.size()));
		inputFileForCharC.add("21");
		List<Long> charOutputC=part2(inputFileForCharC);
		List<String> inputFileForCharV=new ArrayList<>();
		inputFileForCharV.add("2");
		inputFileForCharV.add(rngType);
		inputFileForCharV.add(rngParameter);
		inputFileForCharV.add("1");
		inputFileForCharV.add("0 "+Integer.toString(vowels.size()));
		inputFileForCharV.add("5");
		List<Long> charOutputV=part2(inputFileForCharV);
		int outputCounterV=0;
		int outputCounterC=0;
		int uniformCounter=0;
		for(long nameLength: namesLength) {
			String name="";
			char prev='a';
			for(long i=0; i<nameLength; i++) {
				if(i == 0 || !consonants.contains(prev)) {
					if(uniformProbabilityRange.get(uniformCounter) == 0 || uniformProbabilityRange.get(uniformCounter) == 1 ) {
						long index=charOutputC.get(outputCounterC);
						prev=consonants.get((int) index);
						outputCounterC++;
						outputCounterC %= (charOutputC.size());
					}else {
						long index=part2(inputFileForCharV).get(outputCounterV);	
						prev=vowels.get((int) index);
						outputCounterV++;
						outputCounterV %= (charOutputV.size());			
					}
					uniformCounter++;
					uniformCounter %= uniformProbabilityRange.size();
				}else {
					long index=part2(inputFileForCharV).get(outputCounterV);	
					prev=vowels.get((int) index);
					outputCounterV++;
					outputCounterV %= (charOutputV.size());	
				}
				name+=prev;
			}
			namesList.add(name);
		}
		return namesList;
		
	}

	private static Long normalDistributionAlogithm(RandomNumberGenerator rng, long mean, long sd) throws Exception {
			long num;
			double U0=getRandomBetween0And1(rng);
			double U1=getRandomBetween0And1(rng);
			num= (long)(Math.sqrt(-2.0 * Math.log(U0)) * Math.cos(6.28318530718 * U1)* sd + mean);
			return num;
	}
	

	private static double getRandomBetween0And1(RandomNumberGenerator rng) throws Exception {
		double num;
		if(rng instanceof RepeatingSequence){
			RepeatingSequence rs=(RepeatingSequence) rng;
			num= ((rs.next() % 999) +1) / 1000.0;
		}else if(rng instanceof LCG) {
			LCG lcg =(LCG) rng;
			num= ((lcg.next() % 999) +1) / 1000.0;
		}else if(rng instanceof XorShift32 ) {
			XorShift32 xos=(XorShift32) rng;
			num= ((xos.next() % 999) +1) / 1000.0;
		}else {
			throw new Exception("invalid random number generator");
		}
		return num;
	}

	private static List<Long> uniformDistribution(List<String> inputFile, List<String> part2Input,
			RandomNumberGenerator rng) throws Exception{
				List<Long> result=new ArrayList<>();
			   String[] bounds=part2Input.get(1).split(" ");
			   if(bounds.length == 2) {
				   long lowerBound= Integer.parseInt(bounds[0]);
				   long upperBound=Integer.parseInt(bounds[1]);
				   long numOfOutput=Integer.parseInt(part2Input.get(2));
				   for(int i=0; i< numOfOutput; i++) {
					   result.add(uniformAlogithm(rng, lowerBound, upperBound)+ lowerBound);
				   }
				   return result;
			   }else {
				   throw new Exception("bound parameter error");
			   }
			  
	}

	private static long uniformAlogithm(RandomNumberGenerator rng, long lowerBound, long upperBound) throws Exception{
		if(rng instanceof RepeatingSequence){
			RepeatingSequence rs=(RepeatingSequence) rng;
			long num;
			do {
				num=rs.next();
			}while( num > rs.max() - (rs.max() % (upperBound-lowerBound)));
			return num % (upperBound-lowerBound);
		}else if(rng instanceof  LCG) {
			LCG lcg=(LCG) rng;
			long num;
			do {
				num=lcg.next();
			}while( num > lcg.max() - (lcg.max() % (upperBound-lowerBound)));
			return num % (upperBound-lowerBound);			
		}else if(rng instanceof XorShift32) {
			XorShift32 xos=(XorShift32) rng;
			long num;
			do {
				num=xos.next();
			}while( num > xos.max() - (xos.max() % (upperBound-lowerBound)));
			return num % (upperBound-lowerBound);			
		}else {
			throw new Exception("Invalid random number generator");
		}
	}

	private static List<Long> part2(List<String> inputFile) throws Exception{
		if(inputFile.size() == 6) {
			List<String> rngInputs=new ArrayList<>();
			rngInputs.add("1");
			rngInputs.add(inputFile.get(1));
			rngInputs.add(inputFile.get(2));
			List<String> part2=new ArrayList<>();
			part2.add(inputFile.get(3));
			part2.add(inputFile.get(4));
			part2.add(inputFile.get(5));
			return part1(rngInputs, part2);
		}else {
			throw new Exception("invalid inputFile size");
		}
		
	}

	private static List<Long> part1(List<String> inputFile, List<String> part2) throws Exception {
		if(inputFile.size() >= 3 ) {
			int typeOfGenerator=Integer.parseInt(inputFile.get(1));
			switch (typeOfGenerator) {
				case 1: return repeatingSequenceGenerator(inputFile, part2);
				case 2: return LCGGenerator(inputFile, part2);
				case 3: return XorShift32Generator(inputFile, part2);
			default: throw new Exception("invalid file data type");
			}
		}else {
			throw new Exception("invalid inputFile size");
		}
	}
	
	
	private static List<Long> XorShift32Generator(List<String> inputFile, List<String> part2) throws Exception {
		if(inputFile.size() >=3 ) {
			String[] parameters=inputFile.get(2).split(" ");
			List<Long> result=new ArrayList<>();
			if(parameters.length == 2) {
				long X0=Integer.parseInt(parameters[0]);
				long m=Integer.parseInt(parameters[1]);
				XorShift32 xos=new XorShift32(X0, m);
				if(part2.size() == 0) {
					long numOfOutput=Integer.parseInt(inputFile.get(3));
					for(long i=0; i<numOfOutput; i++) {
						result.add(xos.next());
					}
					return result;
				}else {
					return dispatchDistribution(inputFile, part2, xos);
				}
			}else {
				throw new Exception("invalid parameter length");
			}
		}else {
			throw new Exception("invalid inputFile size");
		}
	}


	private static List<Long> LCGGenerator(List<String> inputFile, List<String> part2) throws Exception{
		if(inputFile.size()  >= 3 ) {
			String[] parameters=inputFile.get(2).split(" ");
			List<Long> result=new ArrayList<>();
			if(parameters.length == 4) {
				long A=Integer.parseInt(parameters[0]);
				long X0=Integer.parseInt(parameters[1]);
				long C=Integer.parseInt(parameters[2]);
				long m=Integer.parseInt(parameters[3]);
				LCG lcg=new LCG(A,X0,C,m);
				if(part2.size() == 0) {
					long numOfOutput=Integer.parseInt(inputFile.get(3));
					for(long i=0; i<numOfOutput; i++) {
						result.add(lcg.next());
					}
					return result;
				}else {
					return dispatchDistribution(inputFile, part2, lcg);
				}
			}else {
				throw new Exception("invalid parameter length");
			}
		}else {
			throw new Exception("invalid inputFile size");
		}
	}
	
	private static List<Long> repeatingSequenceGenerator(List<String> inputFile, List<String> part2) throws Exception{
		if(inputFile.size() >= 3) {
			String[] parameters=inputFile.get(2).split(" ");
			List<Long> result=new ArrayList<>();
			if(parameters.length == 2) {
				long X1=Integer.parseInt(parameters[0]);
				long C=Integer.parseInt(parameters[1]);
				RepeatingSequence rs=new RepeatingSequence(X1, C);
				if(part2.size() == 0) {
					long numOfOutput=Integer.parseInt(inputFile.get(3));
					for(long i=0; i<numOfOutput; i++) {
						result.add(rs.next());
					}
				}else {
					return dispatchDistribution(inputFile, part2, rs);
				}
				return result;
			}else {
				throw new Exception("invalid parameter length");
			}
		}else {
			throw new Exception("invalid inputFile size");
		}
	}
	
	private static void printOutput(List<Long> input) {
		for(long num: input) {
			System.out.print(num + " ");
		}
	}
	
	static class RandomNumberGenerator{
		RandomNumberGenerator(){
		}
	}

	static class RepeatingSequence extends RandomNumberGenerator{
		long X1;
		long C;
		long num;
		RepeatingSequence(long X1, long C){
			super();
			this.X1=X1;
			this.C=C;
			this.num=X1;
		}
		
		long next(){
			if(num < X1 + C) {
				return num++;
			}else {
				num=X1;
				num++;
				return X1;
			}
		}
		
		long max() {
			return X1 + C -1;
		}
	}
	
	static class LCG extends RandomNumberGenerator{
		long A;
		long X0;
		long C;
		long m;
		long num;
		
		LCG(long A, long X0, long C, long m){
			super();
			this.A=A;
			this.X0=X0;
			this.C=C;
			this.m=m;
			this.num=X0;
		}
		
		long next() {
			num=(A*num + C) % m;
			return num;
		}
		
		long max() {
			return m-1;
		}
	}
	
	static class XorShift32 extends RandomNumberGenerator{
		long X0;
		long m;
		long num;
		
		XorShift32(long X0, long m){
			super();
			this.X0=X0;
			this.m=m;
			this.num=X0;
		}
		
		long next() {
			long Xa=(long)(num ^ (num << 13)) % (long) (Math.pow(2, 32));
			long Xb=(long)(Xa ^ (Xa >> 17)) % (long) (Math.pow(2, 32));
			long Xc=(long)(Xb ^ (Xb << 5)) % (long) (Math.pow(2, 32));
			num=Xc;
			return num % m;
		}
		
		long max() {
			return m-1;
		}
	}

}
