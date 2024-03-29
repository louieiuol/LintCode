import java.util.*;

public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
        // write your code here
        List<Integer> res=new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int steps=0;
        //四个方向来回判断
        int minRow=0;
        int maxRow=matrix.length-1; //一定要用length-1 和 <= 如果用 length 和 <有边界条件无法实现
        int minCol=0;
        int maxCol=matrix[0].length-1;
        //记录4条边界 外面循环保证4条边都在界内
        while(minRow<=maxRow && minCol<=maxCol){
        	// 固定横坐标为最小 纵坐标依次从最小到最大增长 -->
            if(steps % 4 == 0){
                for(int i=minCol; i<=maxCol; i++){
                    res.add(matrix[minRow][i]);
                    //加入结果
                }
                //横坐标向下移动一层
                minRow++;
            }
            //      |
            //      V
            //固定纵坐标为最大 横坐标依次从最小到最大增长 
            if(steps % 4 == 1){
                for(int i=minRow; i<=maxRow; i++){
                    res.add(matrix[i][maxCol]);
                } 
                //纵坐标向左移动一层        
                maxCol--;
            }
           //固定横坐标为最大 纵坐标依次从最大到最小减小 <--
            if(steps % 4 == 2){
                for(int i=maxCol; i>=minCol; i--){
                    res.add(matrix[maxRow][i]);
                }
                maxRow--; 
                //横坐标向上移动一层
            }
            
           //固定纵坐标为最小 横坐标依次从最大到最小减小 ^
           //                                     | 
            if(steps % 4 == 3){
                for(int i=maxRow; i>=minRow; i--){
                    res.add(matrix[i][minCol]);
                }
                minCol++;
            }
            //每四个一个周期
            steps++;
        }
        return res;
    }
}
