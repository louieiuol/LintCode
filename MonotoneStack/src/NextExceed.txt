//input: 5,3,1,2,4
//
//return: -1 3 1 1 -1
//
//Explaination: 对于第0个数字5，之后没有比它更大的数字，因此是-1，对于第1个数字3，需要走3步才能达到4（第一个比3大的元素），
//对于第2和第3个数字，都只需要走1步，就可以遇到比自己大的元素。对于最后一个数字4，因为之后没有更多的元素，所以是-1。


public class NextExceed {
	vector<int> nextExceed(vector<int> &input) {
		vector<int> result (input.size(), -1);
		stack<int> monoStack;
		for(int i = 0; i < input.size(); ++i) {	
			while(!monoStack.empty() && input[monoStack.top()] < input[i]) {
				result[monoStack.top()] = i - monoStack.top();
				monoStack.pop();
			}
			monoStack.push(i);
		}
		return result;
	}
}
