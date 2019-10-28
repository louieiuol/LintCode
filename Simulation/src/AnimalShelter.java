//230. Animal Shelter
//中文English
//An animal shelter holds only dogs and cats, and operates on a strictly "first in, first out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter, or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific animal they would like. Create the data structures to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog and dequeueCat.
//
//Example
//Example 1
//
//Input:
//enqueue("james", 1)
//enqueue("tom", 1)
//enqueue("mimi", 0)
//dequeueAny()
//dequeueCat()
//dequeueDog()
//
//Output:
//["james","mini","tom"]
//Example 2
//
//Input:
//enqueue("a",1)
//enqueue("Tom",0)
//dequeueAny()
//dequeueAny()
//
//Output:
//["a","Tom"]
//Challenge
//Can you do it with single Queue?
//
//Notice
//In the input, we will use 0 to refer CAT and use 1 to refer DOG.
import java.util.*;

public class AnimalShelter {
    class Node{
    	//新建一个数据结构建立宠物名字 和 加入时间
        String name;
        int time;
        public Node(String name, int time){
            this.name=name;
            this.time=time;
        }
    }
    //双队列和一个总的timestamp记录顺序 
    Queue<Node> dogQueue;
    Queue<Node> catQueue;
    int time;
    public AnimalShelter(){
        this.dogQueue=new LinkedList<>();
        this.catQueue=new LinkedList<>();
        this.time=0;
    }
     
    public void enqueue(String name, int type) {
        // write your code here
        if(type == 1){
        	//类型为1是狗狗类型 
            dogQueue.offer(new Node(name, time++));
        }else{
            catQueue.offer(new Node(name, time++));
        }
    }

    /*
     * @return: A string
     */
    public String dequeueAny() {
        // 查看队顶的元素 
        Node dog=dogQueue.peek();
        Node cat=catQueue.peek();
        // 如果两个队列有为空的 弹出另外一个 
        if(dog == null){
            return catQueue.poll().name;
        }
        if(cat == null){
            return dogQueue.poll().name;
        }
        //如果都不为空 返回时间小的那个
        if(dog.time > cat.time){
            return catQueue.poll().name;
        }else{
            return dogQueue.poll().name;
        }
    }

    /*
     * @return: A string
     */
    public String dequeueDog() {
        // write your code here
        return dogQueue.poll().name;
    }

    /*
     * @return: A string
     */
    public String dequeueCat() {
        // write your code here
        return catQueue.poll().name;
    }
}
