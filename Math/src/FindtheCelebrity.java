//45. Find the Celebrity
//中文English
//Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. 
//The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
//Now you want to find out who the celebrity is or verify that there is not one. 
//The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B.
//You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
//You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), 
//your function should minimize the number of calls to knows.
//Example
//Example1
//
//Input:
//2 // next n * (n - 1) lines 
//0 knows 1
//1 does not know 0
//Output: 1
//Explanation:
//Everyone knows 1,and 1 knows no one.
//Example2
//
//Input:
//3 // next n * (n - 1) lines 
//0 does not know 1
//0 does not know 2
//1 knows 0
//1 does not know 2
//2 knows 0
//2 knows 1
//Output: 0
//Explanation:
//Everyone knows 0,and 0 knows no one.
//0 does not know 1,and 1 knows 0.
//2 knows everyone,but 1 does not know 2.
//Notice
//There will be exactly one celebrity if he/she is in the party.
//Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

public class FindtheCelebrity {
	public int findCelebrity(int n) {
		if(n<0) return -1;
		if(n == 0) return 0;
		int res=0; //假设0 是那个名人 
		for(int i=0; i<n; i++) {
			if(knows(res, i)) {
				//如果他认识某个人 则他一定不是名人 名人不能认识其他人
				res=i;
				//我们把i换成假想名人 继续寻找
			}
		}
		//循环完后剩下那个最有可能成为名人的人 因为不可能存在两个名人 名人被所有人认识 而名人不能认识别人以外的人
		//所以要么存在一个名人 要么不存在名人 
		for(int i=0; i<n;i++) {
			//对剩下的最有可能的人进行名人判定 因为可能不存在名人 
			if(res != i && knows(res, i)) {
				return -1;
				//如果该人不是那个名人（普通人）那么名人不应该认识他 因为名人不能认识别人 
			}
			if(res != i && !knows(i, res)) {
				return -1;
				//如果该人不是那个名人（普通人）那么他应该认识名人 因为名人必须被所有人认识
			}
		}
		//通过了名人检测 返回那个名人
		return res;
	}

	private boolean knows(int res, int i) {
		// TODO Auto-generated method stub
		return false;
	}	
}
