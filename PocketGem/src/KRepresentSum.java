import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*给你一个int k ,以及一个 int total， 问用[1,k]里的数有多少种不同的方法能表示total
example k = 2 , total = 8 , should return 5;
[11111111]

[1111112]
[111122]
[11222]
[2222]
*/
public class KRepresentSum { 
	    int res = 0;
	    int total = 0;
	    int k = 0;
	    public int nWR( int total , int k )
	    {
	        this.total = total;
	        this.k = k;
	        helper( 0 , 0 );
	        return res;
	    }
	    private void helper( int sub, int pos  )
	    {
	        if( sub > total )
	        {
	            return;
	        }
	        else
	        if( sub == total )
	        {
	            res++;
	        }
	        else
	        {
	            int need = total - sub;
	            int times = 1;
	            for( int i = pos + 1 ; i <= k && i <= need ; i++ )
	            {
	                times = 1;
	                while( times * i + sub <= total) {
	                    helper(times * i + sub, i );
	                    times++;
	                }
	            }
	        }
	    }
}
