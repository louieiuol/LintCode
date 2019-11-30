//1308. Factor Combinations
//中文English
//Numbers can be regarded as product of its factors. For example,
//
//8 = 2 x 2 x 2;
//  = 2 x 4.
//Write a function that takes an integer n and return all possible combinations of its factors.
//
//Example
//Example1
//
//Input: 12
//Output: 
//[
//  [2, 6],
//  [2, 2, 3],
//  [3, 4]
//]
//Explanation:
//2*6 = 12
//2*2*3 = 12
//3*4 = 12
//Example2
//
//Input: 32
//Output: 
//[
//  [2, 16],
//  [2, 2, 8],
//  [2, 2, 2, 4],
//  [2, 2, 2, 2, 2],
//  [2, 4, 4],
//  [4, 8]
//]
//Explanation:
//2*16=32
//2*2*8=32
//2*2*2*4=32
//2*2*2*2*2=32
//2*4*4=32
//4*8=32
//Notice
//You may assume that n is always positive.
//Factors should be greater than 1 and less than n

import java.util.*;

public class FactorCombinations {
    List<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> getFactors(int n) {
        // write your code here
        dfs(n, new ArrayList<>(), 2);
        return result;
    }
    
    private void dfs(int n, List<Integer> lst, int index){
        if(n == 1){
            if(lst.size() >1){
                result.add(new ArrayList<>(lst));
            }
            return;
        }
        for(int i=index; i<=n; i++){
            if(n % i == 0){
                lst.add(i);
                dfs(n/i, lst, i);
                lst.remove(lst.size()-1);
            }
        }
    }
}
