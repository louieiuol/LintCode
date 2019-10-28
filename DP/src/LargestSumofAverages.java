//1004. Largest Sum of Averages
//中文English
//We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?
//
//Note that our partition must use every number in A, and that scores are not necessarily integers.
//
//Example
//Example 1
//
//Input: 
//[9,1,2,3,9]
//3
//Output: 20
//Explanation: 
//The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
//We could have also partitioned A into [9, 1], [2], [3, 9], for example.
//That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
//Example 2
//
//Input:
//[9,3]
//2
//Output: 12
//Notice
//1 <= A.length <= 100.
//1 <= A[i] <= 10000.
//1 <= K <= A.length.
//Answers within 10^-6 of the correct answer will be accepted as correct.

//dp[i][k] 表示：前i个数字切割成k份的最大平均数加和是多少(0 < k <= Min(K, i));
//转移方程： dp[i][k] = dp[j][k - 1] + avg(A[j， i - 1]);
//corner case:
//如果k == 1只能从j == 0(dp[0][0])转移过来。不然会出现dp[j > 1][0] 不合法（长度大于0的数组不能切割成0份，要全取）。

public class LargestSumofAverages {
    public double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        double[][] dp = new double[K + 1][n + 1];
        double[] sum = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + A[i - 1];
            dp[1][i] = sum[i] / i;
        }
        for (int i = 2; i <= K; i++) {
            for (int j = i; j <= n; j++) {
                for (int k = i - 1; k < j; k++){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + (sum[j]-sum[k]) / (j-k));
                }
            }
        }
        return dp[K][n];
    }
}
