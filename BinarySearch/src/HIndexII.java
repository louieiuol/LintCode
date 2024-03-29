//1303. H-Index II
//中文English
//Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
//
//According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
//and the other N − h papers have no more than h citations each."
//
//Example
//Input: citations = [0,1,3,5,6]
//Output: 3 
//Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
//             received 0, 1, 3, 5, 6 citations respectively. 
//             Since the researcher has 3 papers with at least 3 citations each and the remaining 
//						 two with no more than 3 citations each, her h-index is 3.
//Challenge
//This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
//Could you solve it in logarithmic time complexity?
//Notice
//If there are several possible values for h, the maximum one is taken as the h-index.

public class HIndexII {
    public int hIndex(int[] citations) {
        // citation 已经被 sort 好 利用二分法 
        if(citations == null || citations.length == 0) return 0;
        int start=0;
        int end=citations.length-1;
        int ans=0;
        //无法找到确定的medium 进行比较时候 使用 start<end
        while(start < end){
            int mid=start+(end-start)/2;
            int h=citations.length-mid;
            //h值是h个有至少h次搜索 所以用长度减去当前mid index 保证h个
            if(citations[mid] >= h){
            	//如果中位数对应的值比h还要或相等大 则当前h值时至少有h个元素比他大 可能有更多的元素 在左边 
            	//值比当前mid位置小一点 但是h在增加 我们在左边去照更大的值
                ans=Math.max(ans, h);
                end=mid;
            }else{
            	//如果中位数对应的值比h要小 则当前元素所在位置对应值成立为h, 可能有更大的所对应的值 在右边 
            	//并且h也比当前 对应mid值要大点 我们在右边找更大的值 
                ans=Math.max(ans, citations[mid]);
                start=mid+1;//标准写法 start于end 错开
            }
        }
        return ans;
    }
}
