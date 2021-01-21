//399 EvaluateDivision
public class EvaluateDivision {
	//dfs solution
	double target = 0;
	boolean flag = false;
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		if(equations.size() == 0 || values.length == 0 || queries.size() == 0) return new double[]{};
		HashMap<String, List<String[]>> map = new HashMap<>();
		for(int i=0; i<equations.size(); i++){
			String numerator = equations.get(i).get(0);
			String nominator = equations.get(i).get(1);
			if(!map.containsKey(numerator)){
				map.put(numerator, new ArrayList<String[]>());
			}
			if(!map.containsKey(nominator)){
				map.put(nominator, new ArrayList<String[]>());
			}
			List<String[]> m1 = map.get(numerator);
			String[] array1 = new String[2];
			array1[0] = nominator;
			array1[1] = String.valueOf(values[i]);
			m1.add(array1);
			List<String[]> m2 = map.get(nominator);
			String[] array2 = new String[2];
			array2[0] = numerator;
			array2[1] = String.valueOf(1/values[i]);
			m2.add(array2);
		}
		double[] result= new double[queries.size()];
		for(int i=0 ; i<queries.size(); i++){
			target = 0; 
			String start = queries.get(i).get(0);
			String end = queries.get(i).get(1);
			double value = 1.0;
			List<String> path = new ArrayList<>();
			path.add(start);
			flag = false;
			dfs(map, start, end, value, path);
			if(flag){
				result[i] = target;
			}else{
				result[i] = -1.0;
			}
		}
		return result;
	}

	private void dfs(HashMap<String, List<String[]>> map, String start, String end, 
			double value, List<String> path){
		if(!map.containsKey(start) || !map.containsKey(end)){
			flag=true;
			target = -1.0;
			return;
		}
		if(start.equals(end)){
			flag=true;
			target = 1.0;
			return;
		}
		if(flag){
			return;
		}
		List<String[]> next = map.get(start);
		for(String[] pair: next){
			if(pair[0].equals(end)){
				flag = true;
				target = value * Double.parseDouble(pair[1]);
				return;
			}else{
				if(!path.contains(pair[0])){
					path.add(pair[0]);
					dfs(map, pair[0], end, value * Double.parseDouble(pair[1]), path);
					path.remove(path.size()-1);
				}
			}
		}
	}
}
