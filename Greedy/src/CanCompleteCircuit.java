//187. Gas Station
//中文English
//There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
//
//You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
//
//Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
//
//Example
//Example 1:
//
//Input:gas[i]=[1,1,3,1],cost[i]=[2,2,1,1]
//Output:2
//Example 2:
//
//Input:gas[i]=[1,1,3,1],cost[i]=[2,2,10,1]
//Output:-1
//Challenge
//O(n) time and O(1) extra space
//
//Notice
//The solution is guaranteed to be unique.


public class CanCompleteCircuit {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		if(gas == null || cost == null || gas.length == 0 || cost.length == 0) return -1;
		if(gas.length != cost.length) return -1;
		int total=0;
		for(int i=0; i<gas.length; i++) {
			total+=gas[i]-cost[i];
		}
		if(total<0) return -1;
		//如果总共的油都不够花费 那么则不存在可能性
		//如果总共的油足够花费 则至少存在一种可能性
		int index=0;
		int sum=0;
		//设定起始点在0 每次到某点还有sum的油
		for(int i=0; i<gas.length; i++) {
			sum+=gas[i]-cost[i];
			if(sum<0) {
				index=i+1;
				sum=0;
			//如果sum<0 则代表该点开始失败了 index顺移到i后面的点
				/*
				 * 有一个简单的推论， 即 如果以某个位置i作为起始位置，去求累加和，假设到了j 处，此时的累加和为负数。那么从i起,
				 * i+1,i+2....j-1,j都不可能是有效的起始位置。
				 * 这个理解起来很简单，因为从i+1处，少累加了一个非负数，之前的均为正数，累加到j处所得到的累加和只可能比i到j的累加和更小。由于本来i到j的累加和就已经
				 * 为负数了，那么以i+1,i+2...j开始的累加和只可能更小。所以都不可能为可行解。去寻找可行解只可能从j+1处开始了。
				 */
			//sum清零
			}
		}
		return index;
	}
}
