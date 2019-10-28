/*116. Jump Game
中文English
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

Notice
This problem have two method which is Greedy and Dynamic Programming.

The time complexity of Greedy method is O(n).

The time complexity of Dynamic Programming method is O(n^2).*/
public class canJump {
    public boolean canJumpSolution(int[] A) {
        // write your code here
        if(A == null || A.length == 0) return true;
        boolean[] can=new boolean[A.length];
        //create an array of can jump status
        can[0]=true;
        //initialize first step
        for(int i=0; i<A.length; i++){
        	//traversal through the array
            for(int j=0; j<i;j++){
            	//from first list to current index
                if( can[j] && j+A[j]>=i){
                	//if the previous step can be reached and this step can reach i index
                    can[i]=true;
                    //set the i index to be true;
                }
            }
        }
        return can[A.length-1];
        //return the last index boolean value
    }
    
/*  1，确定状态
    最后一步
    如果青蛙能到最后一块石头n-1,最后一步是从石头i跳过来的，i < n-1

    需要满足两个条件：
    a, 青蛙可以调到石头i
    b, i 到 最后一步 需要距离 i+a[i]>=n-1

    子问题就是：青蛙能不能到石头i

    状态：
    设can[j] 表示青蛙能不能跳到石头j

    子问题的就是f[i]

    表示能不能，数组设为boolean 型

    2， 转移方程
    f[j] =(0<=i<j) { f[i]},
    对每个f[i]有 i+a[i] >= j (意思就是保证可以从i跳到j)

    这里是对石头j 枚举前面的 所有石头，看能不能到当前。意味着代码里面两个循环

    3，初始条件和边界
    f[0] = true, 表示一块石头，跳到起点，因为青蛙一开始就在石头0号

    没有啥边界

    4，计算顺序
    从小到大

    答案 f[n-1]

    时间复杂度是O(n^2),空间复杂度是O(n)*/
}
