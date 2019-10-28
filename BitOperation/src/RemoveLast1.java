//消去最后的一个1
public class RemoveLast1 {
    public int removeLast1(int n) {
        // write your code here
    	int res=n-1;
    	return res & n;
    }
}
