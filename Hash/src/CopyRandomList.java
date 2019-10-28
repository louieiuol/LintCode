import java.util.*;
public class CopyRandomList {
	 class RandomListNode {
		     int label;
		     RandomListNode next, random;
		     RandomListNode(int x) { this.label = x; }
		 };
    public RandomListNode copyRandomList(RandomListNode head) {
        // write your code here
        if(head == null) return null;
        HashMap<RandomListNode, RandomListNode> map=new HashMap<>();
        //create a hashmap that stores <key is original list, value is copy list> 
        copyNode(head,map);
        //copy all the nodes value from current list to map
        copyRelation(head,map);
        ///copy all the nodes relations add to map
        return map.get(head);
        //return copy list head
    }
    
    private void copyNode(RandomListNode head, HashMap<RandomListNode, RandomListNode> map){
        while(head != null){
            map.put(head, new RandomListNode(head.label));
            //map copy all node's value into map
            head=head.next;
            //move the pointer
        }
    }
    
    private void copyRelation(RandomListNode head, HashMap<RandomListNode, RandomListNode> map){
        while(head != null){
            map.get(head).next=map.get(head.next);
            //map copy list current next points to map copy list current next
            map.get(head).random=map.get(head.random);
            //map copy list random next points to map copy list current random
            head=head.next;
            //moves the pointer
        }
    }
}
