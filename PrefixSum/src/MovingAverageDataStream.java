//642. Moving Average from Data Stream
//中文English
//Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
//
//Example
//Example 1:
//
//MovingAverage m = new MovingAverage(3);
//m.next(1) = 1 // return 1.00000
//m.next(10) = (1 + 10) / 2 // return 5.50000
//m.next(3) = (1 + 10 + 3) / 3 // return 4.66667
//m.next(5) = (10 + 3 + 5) / 3 // return 6.00000
public class MovingAverageDataStream{
	public class MovingAverageDataStream1 {
	//define private variables
	private double[] data; 
	private int index;
	private int size;
	//initialize constructor
	public MovingAverageDataStream1(int size) {
		data=new double[10000]; //未修改成滚动数组前
		this.size=size;
		index=1; //前缀和数组从1开始 
		data[0]=0; //初始化前缀和0 
		//利用前缀和数组改进 时间复杂度 O(n) -> O(1)
	}
	
	//
	public double next(int val) {
		double avg=0.0;
		data[index]=data[index-1]+val;
		//前缀和为上一个位置的前缀和+当前值
		if(index<size) {
			//如果总数小于 size 那么平均值是0到index之和/包含个数
			avg= data[index]/index;
		}else {
			//如果总数大于 size 那么平均值是 index-size长度 到 index 的和/size
			avg=(data[index]-data[index-size])/size;
		}
		//增加index 不能提前增加 这样会影响平均值计算
		index++;
		return avg;
	}
}

public class MovingAverageDataStream2 {
	//define private variables
	private double[] data; 
	private int index;
	private int size;
	//initialize constructor
	public MovingAverageDataStream2(int size) {
		
		data=new double[size+1]; //修改成滚动数组前 大小就是原来的size 因为是滚动前缀和数组 用A[0]记录初始值 所以比原来的size+1 
		//大一些是无所谓的 小一些会有影响
		//利用滚动数组改进 空间复杂度 O(n) -> O(k)
		this.size=size;
		//size还是parameter里面的
		index=1; //前缀和数组从1开始 
		data[0]=0; //初始化前缀和0 
		//利用前缀和数组改进 时间复杂度 O(n) -> O(1)
	}
	
	//定义mod方程 避免代码重复冗杂
	private int mod(int n) {
		return n % (size+1);
		//取mod永远根据array长度 这样保证不会out of bounds
	}
	//
	public double next(int val) {
		double avg=0.0;
		//对所有前缀和数组中存值和取值的地方加mod
		//注意此时index仍然是绝对的index index不随着取mod而改变 
		//这样方便知道到底一共个数是大于还是小于 size
		data[mod(index)]=data[mod(index-1)]+val;
		//存值加 mod
		//注意：mod要给正个取值添加不是仅仅是index
		//前缀和为上一个位置的前缀和+当前值
		//取值加mod
		if(index<size) {
			//如果总数小于 size 那么平均值是0到index之和/包含个数
			avg= data[mod(index)]/index;
		}else {
			//如果总数大于 size 那么平均值是 index-size长度 到 index 的和/size
			avg=(data[mod(index)]-data[mod(index-size)])/size;
		}
		//增加index 不能提前增加 这样会影响平均值计算
		index++;
		return avg;
	}
}
}


