//706. Binary Watch
//中文English
//Given a watch with a binary display time and a non-negative integer n which represents the number of 1s on a given timetable, return all possible time.
//
//Example
//Example1
//
//Input: 1
//Output: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
//Example2
//
//Input: 2
//Output: ["0:03","0:05","0:06","0:09","0:10","0:12","0:17","0:18","0:20","0:24","0:33","0:34","0:36","0:40","0:48","10:00","1:01","1:02","1:04","1:08","1:16","1:32","2:01","2:02","2:04","2:08","2:16","2:32","3:00","4:01","4:02","4:04","4:08","4:16","4:32","5:00","6:00","8:01","8:02","8:04","8:08","8:16","8:32","9:00"]
//Notice
//The order of output does not matter.
//The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
//The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
//hour < 12, minute < 60

import java.util.*;
public class BinaryWatch {
    public List<String> binaryTime(int num) {
		/*
		 * // Use python to solve 
		 * 
		 * 了解python简单api直接从string中数出1的个数
		 * 了解python的format函数格式化时间
         * 注意理解题目中二进制手表的含义：手表是64进制的，所以h * 64以转化成分钟数
         * - 分钟总共有6个二进制位表示最高位为2^5 = 32
		 * - 小时的表示是从右往左数的第6个二进制位表示的，即2^6
         * - 所以把小时和分钟放在一起表的二进制数就是h * 64 + m,然后在整个数中数1的个数即可
		 * 
		 * 
		 * def binaryTime(self, num): # Write your code here
		 * result = [] for h in range(12): for m in range(60): time = bin(h * 64 +
		 * m)[2:] if time.count('1') == num: result.append('{}:{:0>2}'.format(h, m))
		 * 
		 * return result
		 */
    	return null;
    }
}
