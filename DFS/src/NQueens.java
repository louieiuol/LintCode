/*33. N-Queens
中文English
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example
Example 1:

Input:1
Output:
   [["Q"]]


Example 2:

Input:4
Output:
[
  // Solution 1
  [".Q..",
   "...Q",
   "Q...",
   "..Q."
  ],
  // Solution 2
  ["..Q.",
   "Q...",
   "...Q",
   ".Q.."
  ]
]

Challenge
Can you do it without recursion?
*/


import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        // write your code here
    	List<List<String>> result=new ArrayList<List<String>>(); //initialize return list
    	if(n>0) {          
    		dfs(result, new ArrayList<Integer>(), n); //if n > 0, begin dfs 
    	}
    	return result;
    }

	private void dfs(List<List<String>> result, ArrayList<Integer> lst, int n) {
		// TODO Auto-generated method stub
		if(lst.size() == n) { // return condition, if list size equals to n
			result.add(drawChess(lst));  //add chess board to result, by passing current list to function
			return; //stop recursion
		}
		
		for(int i=0; i<n; i++) {
			//try each cell to put a queen
			if(canAttack(lst,i)) {
				//if current position is attack with other queens positions
				continue;
				//jump to next iteration
			}
			lst.add(i);
			//add this position to list
			dfs(result, lst, n);
			//perform next dfs with updated list
			lst.remove(lst.size()-1);
			//remove this position from list
		}
	}

	private boolean canAttack(ArrayList<Integer> lst, int num) {
		int size=lst.size();
		for(int i=0; i<lst.size();i++) {
			//i is dy, list.get(i) is dx
			//list.size() is dy, num is dx
			//traversal all queens in the board
			if(lst.get(i) == num) {
				return true;
			}
			//if at same position, they can attack
			
			if(i+lst.get(i)==size+num) {
				return true;
			}
			//if xi+yi is same as xn+yn, false => reason is from the same slope -1: y1=-x1+n, y2=-x2+n, they can attack
			
			if(i-lst.get(i)==size-num) {
				return true;
			}
			//if xi-yi is same as xn-yn, false => reason is from the same slope 1: y1=x1+n, y2=x2+n, they can attack
		}
		return false;
	}

	private List<String> drawChess(ArrayList<Integer> lst) {
		// TODO Auto-generated method stub
		List<String> res=new ArrayList<String>();
			for(int i=0; i<lst.size();i++) {
				//print number of rows 
				String str="";
				for(int j=0;j<lst.get(i);j++) {
					str+=".";
				}
				//before i are all empty
				str+="Q";
				//queen is at ith position
				for(int j=lst.get(i)+1;j<lst.size();j++) {
					str+=".";
				}
				//after i are all empty
				res.add(str);
				//add the line to result
			}
		return res;
		//return one of the solutions
	}
}
