//600. Smallest Rectangle Enclosing Black Pixels
//中文English
//An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
//
//Example
//Example 1:
//
//Input：["0010","0110","0100"]，x=0，y=2
//Output：6
//Explanation：
//The upper left coordinate of the matrix is (0,1), and the lower right coordinate is (2,2).
//Example 2:
//
//Input：["1110","1100","0000","0000"], x = 0, y = 1
//Output：6
//Explanation：
//The upper left coordinate of the matrix is (0, 0), and the lower right coordinate is (1,2).


public class SmallestRectangleEnclosingBlackPixels {
	public int minArea(char[][] image, int x, int y) {
        //在列中需要找出第一个'1'出现的最左侧坐标和最右侧坐标，在行中需要找出第一个'1'出现的最上面坐标和最下面坐标。
        if(image == null || image.length == 0 || image[0].length == 0) return 0;
        int top=searchTop(image, 0, x);
        int bottom=searchBottom(image, x, image.length-1);
        int left=searchLeft(image, 0, y);
        int right=searchRight(image, y, image[0].length-1);
        //注意这里要找连续的 0到x x到image.length-1
        //注意这里要找连续的 0到y y到image[0].length-1
        return (bottom-top+1) * (right-left+1);
    }
    
    private int searchTop(char[][] image, int start, int end){
        while(start + 1 < end){
            int mid=start+(end-start)/2;
            if(isColumnBlack(image, mid)){
                end=mid;
            }else{
                start=mid;
            }
        }
        if(isColumnBlack(image, start)){
            return start;
        }
        return end;
    }
    
    private int searchBottom(char[][] image, int start, int end){
        while(start + 1 < end){
            int mid=start+(end-start)/2;
            if(isColumnBlack(image, mid)){
                start=mid;
            }else{
                end=mid;
            }
        }
        if(isColumnBlack(image, end)){
            return end;
        }
        return start;
    }
    
    private int searchLeft(char[][] image, int start, int end){
        while(start + 1 < end){
            int mid=start+(end-start)/2;
            if(isRowBlack(image, mid)){
                end=mid;
            }else{
                start=mid;
            }
        }
        if(isRowBlack(image, start)){
            return start;
        }
        return end;
    }
    
    private int searchRight(char[][] image, int start, int end){
        while(start + 1 < end){
            int mid=start+(end-start)/2; 
            if(isRowBlack(image, mid)){
                start=mid;
            }else{
                end=mid;
            }
        }
        if(isRowBlack(image, end)){
            return end;
        }
        return start;
    }
    
    private boolean isColumnBlack(char[][] image, int col){
        for(int i=0; i<image[0].length; i++){
            if(image[col][i] == '1'){
                return true;
            }
        }
        return false;
    }
    
    private boolean isRowBlack(char[][] image, int row){
        for(int i=0; i<image.length; i++){
            if(image[i][row] == '1'){
                return true;
            }
        }
        return false;
    }
}

//O(nlogm+mlogn)
