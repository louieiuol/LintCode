/*149. Best Time to Buy and Sell Stock
中文English
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example
Example 1

Input: [3, 2, 3, 1, 2]
Output: 1
Explanation: You can buy at the third day and then sell it at the 4th day. The profit is 2 - 1 = 1
Example 2

Input: [1, 2, 3, 4, 5]
Output: 4
Explanation: You can buy at the 0th day and then sell it at the 4th day. The profit is 5 - 1 = 4
Example 3

Input: [5, 4, 3, 2, 1]
Output: 0
Explanation: You can do nothing and get nothing.*/


public class BestTimeToBuyAndSell {
    public int maxProfit(int[] prices) {
        // write your code here
        if(prices == null || prices.length == 0) return 0;
        int minValue=Integer.MAX_VALUE; //memorize the time price is minimum
        int profit=0; //memorize the maximum profit at least 0
        for(int i=0; i<prices.length; i++){
            if(prices[i] < minValue) {
                minValue=prices[i];
            }
            //update current minimum value
            if(prices[i]-minValue > profit){
                profit=prices[i]-minValue;
            }
            //update current maximum profit
        }
        return profit;
    }
}
