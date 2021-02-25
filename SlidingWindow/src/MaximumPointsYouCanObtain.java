//leetcode 1423. Maximum Points You Can Obtain from Cards

//思路
//
//记数组 cardPoints 的长度为 nn，由于只能从开头和末尾拿 kk 张卡牌，所以最后剩下的必然是连续的 n-kn−k 张卡牌。
//
//我们可以通过求出剩余卡牌点数之和的最小值，来求出拿走卡牌点数之和的最大值。
//
//算法
//
//由于剩余卡牌是连续的，使用一个固定长度为 n-kn−k 的滑动窗口对数组 \textit{cardPoints}cardPoints 进行遍历，
//求出滑动窗口最小值，然后用所有卡牌的点数之和减去该最小值，即得到了拿走卡牌点数之和的最大值。


public class MaximumPointsYouCanObtain {
    public int maxScore(int[] cardPoints, int k) {
        if(cardPoints.length == 0 || k == 0) return 0;
        int total = 0;
        for(int i=0; i< cardPoints.length; i++){
            total += cardPoints[i];
        }
        int j = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int i = 0; i < cardPoints.length; i++){
            while(j < cardPoints.length && j-i < cardPoints.length - k){
                sum += cardPoints[j];
                j++;
            }
            if(j - i == cardPoints.length - k){
                min = Math.min(min, sum);
            }
            sum -= cardPoints[i];
        }
        return total - min;
    }
}