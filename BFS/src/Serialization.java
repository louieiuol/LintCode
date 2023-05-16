import java.util.*;

public class Serialization {
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public String serialize(TreeNode root) {
        // write your code here
        StringBuilder sb=new StringBuilder();
        if(root == null) return "{}";
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        sb.append(root.val);
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0; i<size ;i++){
                TreeNode curr=queue.poll();
                if(curr.left!=null){
                    queue.offer(curr.left);
                    sb.append(",");
                    sb.append(curr.left.val);
                }else{
                    sb.append(",#");
                }
                if(curr.right!=null){
                    queue.offer(curr.right);
                    sb.append(",");
                    sb.append(curr.right.val);
                }else{
                    sb.append(",#");
                }
            }
        }
        int index=sb.length()-1;
        while(sb.charAt(index) == '#' || sb.charAt(index) == ','){
            sb.deleteCharAt(index--);
        }
        return sb.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if(data.equals("{}")) return null;
        String[] dataArray=data.split(",");
        List<TreeNode> lst=new ArrayList<>();
        int index=0;
        lst.add(new TreeNode(Integer.parseInt(dataArray[0])));
        boolean isLeft=true;
        for(int i=1; i<dataArray.length; i++) {
            if(!dataArray[i].equals("#")) {
                TreeNode node=new TreeNode(Integer.parseInt(dataArray[i]));
                if(isLeft) {
                    lst.get(index).left=node;
                }else {
                    lst.get(index).right=node;
                }
                lst.add(node);
            }
            if(!isLeft) {
                index++;
            }
            isLeft=!isLeft;
        }
        return lst.get(0);
    }
}
