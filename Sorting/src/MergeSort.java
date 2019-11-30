
public class MergeSort {
	public void mergeSort(int arr[], int left, int right) {
		if( left >= right)  return; //递归的出口
		int mid=left + (right-left)/2; //找中间点
		mergeSort(arr, left, mid); //分两部分递归
		mergeSort(arr, mid+1, right);
		merge(arr, left, mid, right); //合并
	}
	
	private void merge(int arr[], int left, int mid, int right) {
		int leftIndex=0;
		int rightIndex=0;
		int originIndex=left;
		//原来array index指向开始left位置
		//建立大小为左右两边的array来装原来的元素
		int[] leftArray=new int[mid-left+1]; 
		//左边包括mid 长度是左边到mid+1
		int[] rightArray=new int[right-mid];
		//右边不包括mid 长度mid到右
		//merge sort需要额外的空间装原来的array 
		for(leftIndex=0; leftIndex< leftArray.length; leftIndex++) {
			leftArray[leftIndex]=arr[originIndex++];
		}
		//左array装入原来array left到mid的元素
		for(rightIndex=0; rightIndex< rightArray.length; rightIndex++) {
			rightArray[rightIndex]=arr[originIndex++];
		}
		//右array装入原来array mid+1到right的元素
		leftIndex=0;
		rightIndex=0;
		originIndex=left;
		//重置index
		while( leftIndex < leftArray.length && rightIndex < rightArray.length) {
			//当两边array指针都不指到底时
			if(leftArray[leftIndex] < rightArray[rightIndex]) {
				//排列成从小到大
				//左边的index的值 小于右边的index的值 用左边的去覆盖
				arr[originIndex++]=leftArray[leftIndex++];	
			}else {
				//大于的时候 用右边的值去覆盖
				arr[originIndex++]=rightArray[rightIndex++];
			}
		}
		//对两边指针没指到底部时候进行循环 覆盖原来的index 
		while( leftIndex < leftArray.length) {
			arr[originIndex++]=leftArray[leftIndex++];
		}
		while( rightIndex < rightArray.length) {
			arr[originIndex++]=rightArray[rightIndex++];
		}
	}
	
}
