//Maximum weight transformation of a given string
//Given a string consisting of only A’s and B’s. We can transform the given string to another string by toggling any character.
//Thus many transformations of the given string are possible. The task is to find Weight of the maximum weight transformation.
//Weight of a sting is calculated using below formula.
//
//
//Weight of string = Weight of total pairs + 
//                   weight of single characters - 
//                   Total number of toggles.
//
//Two consecutive characters are considered as pair only if they 
//are different. 
//Weight of a single pair (both character are different) = 4
//Weight of a single character = 1 
//
//Input: str = "AA"
//Output: 3
//Transformations of given string are "AA", "AB", "BA" and "BB". 
//Maximum weight transformation is "AB" or "BA".  And weight
//is "One Pair - One Toggle" = 4-1 = 3.
//
//Input: str = "ABB"
//Output: 5
//Transformations are "ABB", "ABA", "AAB", "AAA", "BBB", 
//"BBA", "BAB" and "BAA"
//Maximum weight is of original string 4+1 (One Pair + 1
//character)


public class MaximumWeight {
    static int getMaxRec(String str, int i, 
            int n, int[] lookup)  
    { 
        // Base case 
        if (i >= n)  
        { 
            return 0; 
        } 
  
        // If this subproblem is already solved 
        if (lookup[i] != -1)  
        { 
            return lookup[i]; 
        } 
  
        // Don't make pair, so  
        // weight gained is 1 
        int ans = 1 + getMaxRec(str, i + 1, 
                            n, lookup); 
  
        // If we can make pair 
        if (i + 1 < n) 
        { 
              
            // If elements are dissmilar,  
            // weight gained is 4 
            if (str.charAt(i) != str.charAt(i + 1)) 
            { 
                ans = Math.max(4 + getMaxRec(str, i + 2, 
                                n, lookup), ans); 
            }  
              
            // if elements are similar so for  
            // making a pair we toggle any of  
            // them. Since toggle cost is 
            // 1 so overall weight gain becomes 3 
            else 
            { 
                ans = Math.max(3 + getMaxRec(str, i + 2, 
                                n, lookup), ans); 
            } 
        } 
  
        // save and return maximum 
        // of above cases 
        return lookup[i] = ans; 
    } 
  
    // Initializes lookup table 
    // and calls getMaxRec() 
    static int getMaxWeight(String str)  
    { 
        int n = str.length(); 
  
        // Create and initialize lookup table 
        int[] lookup = new int[n]; 
        for (int i = 0; i < n; i++) 
        { 
            lookup[i] = -1; 
        } 
  
        // Call recursive function 
        return getMaxRec(str, 0, str.length(), 
                            lookup); 
    } 
}
