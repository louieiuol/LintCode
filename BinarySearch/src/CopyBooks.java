//437. Copy Books
//中文English
//Given n books and the i-th book has pages[i] pages. There are k persons to copy these books.
//
//These books list in a row and each person can claim a continous range of books. For example, one copier can copy the books from i-th to j-th continously, but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).
//
//They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. What's the best strategy to assign books so that the slowest copier can finish at earliest time?
//
//Return the shortest time that the slowest copier spends.
//
//Example
//Example 1:
//
//Input: pages = [3, 2, 4], k = 2
//Output: 5
//Explanation: 
//    First person spends 5 minutes to copy book 1 and book 2.
//    Second person spends 4 minutes to copy book 3.
//Example 2:
//
//Input: pages = [3, 2, 4], k = 3
//Output: 4
//Explanation: Each person copies one of the books.
//Challenge


public class CopyBooks {
    public int copyBooks(int[] pages, int k) {
        // write your code here
        if(pages == null || pages.length == 0 || k == 0) return 0;
        int start=0;
        int end=Integer.MAX_VALUE;
        while(start < end){
        	//也是没有target的情况 用趋近于一个值的方法
            int mid=start+(end-start)/2;
            if(isValid(pages, k, mid)){
                end=mid;
            }else{
                start=mid+1;
            }
        }
        return start;
    }
    
    private boolean isValid(int[] pages, int k, int limit){
        int num=0;
        int remain=0;
        for(int page: pages){
            if(page > limit){
                return false;
            }
            if(page > remain){
                //如果当前页数大于累计减少下来能容纳的页数 那边把remain重新设为limit 增加额外的一个人
                num++;
                remain=limit;
            }
            remain -= page;
            //累计减少页数
        }
        return num <= k;
    }
    
  //O(nk) time
}
