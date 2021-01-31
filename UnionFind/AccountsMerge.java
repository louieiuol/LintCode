import java.util.Map;

import com.sun.tools.javac.util.List;

//Leetcode 721. Accounts Merge
public class AccountsMerge {
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Map<String, Integer> emailToIndexMap = new HashMap<>();
		//建立email到序数的关系
		Map<String, String> emailToNameMap = new HashMap<>();
		//建立email到名字的关系
		int count;
		//生成序数
		for(List<String> account: accounts) {
			String name = account.get(0);
			//获取名字 
			int(i=1; i < account.size(); i++){
				//从index 1 开始之后都是email
				if(!emailToIndexMap.containsKey(name)){
					//去掉之前重复的name, 保持序数指向email唯一性
					emailToIndexMap.put(email, count++);
					//将email和序数关系放进map,序数++ 
					emailToNameMap.put(email, name);
					//将email和name关系放进map
				}
			}
		}
		UnionFind uf = new UnionFind();
		//初始化union find 
		for(List<String> account: accounts) {
			//再次遍历accounts 合并相同列表中的email
			String firstEmail = account.get(0);
			int firstIndex = emailToIndexMap.get(firstEmail);
			//找到第一封email
			for(int i=2; i< account.size(); i++) {
				//后面的email都跟第一封进行union
				String nextEmail = account.get(i);
				int nextIndex = emailToIndexMap.get(nextEmail);
				uf.union(firstIndex, nextIndex);
			}
		}
		Map<Integer, List<String>> map = HashMap<>();
		//建立root序数到对应所有email的列表图
		for(String email: emailToIndexMap.keySet()) {
			int root = uf.find(email);
			//找到email的根的序数
			List<String> sameEmails = map.getOrDefault(root, new List<String>());
			//找到之前放入相同根的所有email, 如果为空则新建
			sameEmails.add(email);
			map.put(root, sameEmails);
			//再将新加入的emails列表覆盖
		}
		List<List<String>> result = new ArrayList<>();
		//建立返回结果
		for(int root: map.keySet()) {
			//遍历所有的根
			List<String> curr = new ArrayList<>();
			//新建当前子结果
			List<String> sameEmails = map.get(root);
			//拿到该根下相同的emails
			String name = emailToNameMap.get(sameEmails.get(0));
			//获得第一个email所属于的人名, 后面的email都属于相同的人名
			curr.add(name);
			curr.addAll(sameEmail);
			result.add(curr);
			//加入结果
		}
		return result;
		
	}
	
	
	class UnionFind{
		Map<Integer, Integer> map;
		
		public UnionFind() {
			map = new HashMap<>();
		}
		
		public int find(int x) {
			if(!map.containsKey(x)) {
				map.put(x, x);
			}
			int xParent = map.get(x);
			if(x != xParent) {
				map.put(x, find(xParent));
			}
			return map.get(x);
		}
		
		public void union(int x, int y) {
			int xParent = find(x);
			int yParent = find(y);
			if(xParent == yParent) {
				 return;
			}
			map.put(xParent, yParent);
		}
	}
}
