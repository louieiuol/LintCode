/*
 * coding1 是removing chocolate，给n个chocolate，每次remove 1一个或3个，问有多少种remove方法。题目比较简单，
 * dp做，比较坑的是n的数量可能非常大，非常容易timeout。在最后几分钟才过了所有testcase
*/
public class minMove {
	public static int minMoves(int n) {
        int[] res = new int[3];
if(n < 1){
    return 0;
}
if(n < 2){
    return 1;
}
if(n < 3){
    return 1;
}
if(n == 3) {
        return 2;
}
res[0] = 1;
res[1] = 1;
res[2] = 2;
for(int i = 3; i < n; i = i + 3){
        res[0] = (res[0] + res[2])%1000000007;
        res[1] = (res[1] + res[0])%1000000007;
        res[2] = (res[2] + res[1])%1000000007;
}
if((n-1) % 3 == 0){
    return res[0];
} 
else if((n-1) % 3 == 1){
    return res[1];
}
else return res[2];
}

}
