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


public class MovingAverage {
    private double[] data;
    private int size;
    private int curr=1;
    public MovingAverage(int size) {
        // do intialization if necessary
        data=new double[size+1];
        this.size=size;
        curr=1;
        data[0]=0;
    }

    /*
     * @param val: An integer
     * @return:  
     */
    public double next(int val) {
        // write your code here
        double sum=0.0;
        data[mod(curr)]=data[mod(curr-1)]+val;
        if(curr<size){
            sum=data[mod(curr)]/curr;
        }else{
            sum=(data[mod(curr)]-data[mod(curr-size)])/size;
        }
        curr++;
        return sum;
    }
    
    private int mod(int n){
        return n % (size+1);
    }
}
