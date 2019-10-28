
public class RogueKnightSven {
	//记忆化搜索+动态规划
	//动态规划1：确定状态
	//最后一步：从某个星球 i<n跳到星球n, 需要保证i+limit>=n
	//设状态 f[i] 表示有多少种方式从星球0到星球i
	//从i跳到n要花费 cost[n]枚金币 所以需要知道在星球i时有多少枚金币
	//不知道金币 也要把金币记录下来
	//设f[i][j]有多少种方式从0到i，手里还剩j金币 （坐标+状态）
	
	//                                            不能超过m
	//f[i][j]=sum(i-limit<=k<i){f[k][j+cost[i]] | j+ cost[i]<=m}
	//有多少种方式从星球0跳到星球i，手里剩j枚金币
	//从星球k到星球i，要求手里有j+cost[i]枚金币 
	//有多少种方式从星球0跳到星球k,手里剩j+cost[i]枚金币
	
	//初始条件
	//f[0][m]=1  (初始在星球0，手里有m枚金币）
	//f[0][0]= .... = f[0][m-1] = 0
	
	//边界情况 
	//-k>=0 
	//-j+cost[i] <=m (一共只有m枚金币)
	
	//计算顺序 
	//f[0][0], ... f[0][m]
	//f[1][0], ... f[1][m]
	//....
	//f[n][0], ... f[n][m]
	//答案为 f[n][0]+f[n][1]+...+ f[n][m]
	//时间复杂度 O(n^2 * m) 空间复杂度 O(n*m)
	
	int n, m ,limit;
	int[] cost;
	long[][] dp;
	
	
	private void calculate(int i, int j) {
		if(dp[i][j] !=-1){
			return;
		}
		if(i == 0) {
			if(j == m) {
				dp[i][j] = 1;
			}else {
				dp[i][j] = 0;
			}
			return;
		}
		dp[i][j]=0;
		for(int k=i-limit; k<i; k++) {
			if(k<0) {
				continue;
			}
			if(j+cost[i] <=m) {
				calculate(k, j+cost[i]);
				dp[i][j]+=dp[k][j+cost[i]];
			}
		}
	}
	public long getNumberOfWays(int n, int m, int limit, int[] cost) {
        this.n=n;
        this.m=m;
        this.limit=limit;
        this.cost=cost;
        dp=new long[n+1][m+1];
        int i, j;
        for(i=0; i <=n ; i++) {
        	for(j=0; j<=m; j++) {
        		dp[i][j]= -1;
        	}
        }
        long res=0;
        for(i=0; i<=m; i++) {
        	calculate(n, i);
        	res+=dp[n][i];
        }
        return res; 
    }
}
