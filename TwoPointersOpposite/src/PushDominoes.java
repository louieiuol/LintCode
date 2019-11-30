//1431. Push Dominoes
//中文English
//There are N dominoes in a line, and we place each domino vertically upright.
//
//In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
//After each second, each domino that is falling to the left pushes the adjacent domino on the left.
//
//Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
//
//When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
//
//For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
//
//Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.
//
//Return a string representing the final state.
//
//Example
//Example 1:
//
//Input: ".L.R...LR..L.."
//Output: "LL.RR.LLRRLL.."
//Example 2:
//
//Input: "RR.L"
//Output: "RR.L"
//Explanation: The first domino expends no additional force on the second domino.
//Notice
//0 <= N <= 10^5
//String dominoes contains only 'L', 'R' and '.'

//
//Intuition
//
//We can calculate the net force applied on every domino. 
//The forces we care about are how close a domino is to a leftward 'R', and to a rightward 'L': the closer we are,
//the stronger the force.
//
//Algorithm
//
//Scanning from left to right, our force decays by 1 every iteration, and resets to N if we meet an 'R', so that force[i] is higher (than force[j]) 
//if and only if dominoes[i] is closer (looking leftward) to 'R' (than dominoes[j]).
//
//Similarly, scanning from right to left, we can find the force going rightward (closeness to 'L').
//
//For some domino answer[i], if the forces are equal, then the answer is '.'. Otherwise, the answer is implied by whichever force is stronger.
//
//Example
//
//Here is a worked example on the string S = 'R.R...L': We find the force going from left to right is [7, 6, 7, 6, 5, 4, 0].
//The force going from right to left is [0, 0, 0, -4, -5, -6, -7].
//Combining them (taking their vector addition), the combined force is [7, 6, 7, 2, 0, -2, -7], for a final answer of RRRR.LL.

public class PushDominoes {
    public String pushDominoes(String dominoes) {
        // Write your code here
        if(dominoes == null || dominoes.length() == 0) return "";
        int force=0;
        int[] rightArray=new int[dominoes.length()];
        int[] leftArray=new int[dominoes.length()];
        char[] result=new char[dominoes.length()];
        for(int i=0; i<rightArray.length; i++){
            if(dominoes.charAt(i) == 'R'){
                force=rightArray.length;
            }else if(dominoes.charAt(i) == 'L'){
                force=0;
            }else{
                force=Math.max(force-1, 0);
            }
            rightArray[i]=force;
        }
        force=0;
        for(int i=leftArray.length-1; i>=0 ; i--){
            if(dominoes.charAt(i) == 'L'){
                force=-leftArray.length;
            }else if(dominoes.charAt(i) == 'R'){
                force=0;
            }else{
                force=Math.min(force+1, 0);
            }
            leftArray[i]=force;
        }
        
        for(int i=0; i<result.length; i++){
            if(leftArray[i] + rightArray[i] > 0){
                result[i]='R';
            }else if(leftArray[i] + rightArray[i] < 0){
                result[i]='L';
            }else{
                result[i]='.';
            }
        }
        return new String(result);
    }
}
