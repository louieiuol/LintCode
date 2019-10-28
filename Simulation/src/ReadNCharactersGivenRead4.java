//660. Read N Characters Given Read4 II - Call multiple times
//中文English
//The API: int read4(char *buf) reads 4 characters at a time from a file.
//
//The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
//
//By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
//
//Example
//Example 1
//
//Input:
//"filetestbuffer"
//read(6)
//read(5)
//read(4)
//read(3)
//read(2)
//read(1)
//read(10)
//Output:
//6, buf = "filete"
//5, buf = "stbuf"
//3, buf = "fer"
//0, buf = ""
//0, buf = ""
//0, buf = ""
//0, buf = ""
//Example 2
//
//Input:
//"abcdef"
//read(1)
//read(5)
//Output:
//1, buf = "a"
//5, buf = "bcdef"
//Notice
//The read function may be called multiple times.

public class ReadNCharactersGivenRead4 {
	 char[] buffer=new char[4];
	 //用buffer来存从文件中读到的字符 因为每次只能读4个 我们把我们的queue也设置成4个size
	 //利用滚动操作来覆盖之前的读数
	 int head=0, tail=0;
	 //用head 和 tail来标注buffer中的两个指针 模拟queue的进出 
	 //用queue来模拟缓存 缓存用于从系统硬盘中读文件 然后I/O从缓存中读取 为什么用queue？ FIFO
	 //设为global变量是希望每次读取都记住原来的位置
	    /**
	     * @param buf destination buffer 给你的buf是需要你写入的 
	     * @param n maximum number of characters to read 需要读多少个字节
	     * @return the number of characters read 返回实际读取的字节
	     */
	 private final int read4(char[] input) {
		 return 0;
	 }
	 
	 public int read(char[] buf, int n) {
		 int counter=0; //储存实际读取的字节个数
		 //每次read的时候 counter 都从0开始 buf 每次都会给一个empty的array
		 while(counter < n) {
			 //当实际读取的字节小于所需个数的时候 访问queue 持续读取
			 //一开始先检查缓存里是否有剩余值可以读取 
			 if(head == tail) {
				 //缓存已经为空 从硬盘中读取数
				 head=0; //把queue的头指针指向0
				 tail=read4(buffer); //read4(dest) 是把file读取到dest中 返回从file中读到的个数
				 //把queue的尾指针指向读取到的个数 0<=X <4 
				 if(tail == 0) {
					 break; //如果file里面已经是空了 那么read4 表示没有读取到任何数会返回0 tail还在初始位置 那么我们不在读取数了 
					 //break后会返回当前已经读取到的总数 不能直接return 0 因为之前还读取过数 已经增加了counter
				 }
			 }else {
				 //缓存不为空 则先读缓存里面的数
				 while(head < tail && counter < n) {
					 //需要满足头指针小于尾指针 这样当缓存里未满时 保证读取到有效数值 
					 //同时需要满足 只读到我们所需要的个数 
					 buf[counter++]=buffer[head++];
					 //转移数据 注意buf的添加的位置是当前counter所指向的位置 （应该是空）
					 //buffer的数据移动位置是头指针指向的位置 
					 //移动完后两边同时+1 
				 }
			 }
		 }
		 return counter;// 返回一共已经读到的个数
	 }
}
