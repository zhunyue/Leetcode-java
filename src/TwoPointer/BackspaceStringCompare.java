package TwoPointer;

import java.util.Stack;

/*
844. Backspace String Compare

Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?

 */

public class BackspaceStringCompare {
    // Solution 1: Using 2 Stack. Everytime we encounter a '#', we pop up one character
    // O(n) time and space complexity
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            if(c != '#'){
                s1.push(c);
            } else{
                if(!s1.empty())
                    s1.pop();
            }
        }

        for(int i = 0; i < T.length(); i++){
            char c = T.charAt(i);
            if(c != '#'){
                s2.push(c);
            } else{
                if(!s2.empty())
                    s2.pop();
            }
        }

        return s1.equals(s2);
    }

    // Solution 2: Iterate two string from the end. For every iteration, we find one remaining character to compare
    // (skip those that are removed by '#')
    // O(n) time complexity and O(1) space complexity
    public int findChar(String s, int i){
        int skip = 0; // the number of characters we skip in this round
        while(i >= 0){
            if(s.charAt(i) == '#') {
                skip++;
                i--;
            }else if(skip > 0) { //we have to skip
                skip--;
                i--;
            }else { //find a character
                break;
            }
        }
        return i;
    }
    public boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        while(i >= 0 || j >=0){
            i = findChar(S, i);
            j = findChar(T, j);
            if (i < 0 && j < 0) return true; // if both comparable characters are "", then return true
            if (i < 0 || j < 0 || S.charAt(i) != T.charAt(j)) { //if only one of them is "" or the two characters are not equal, return false
                return false;
            }

            i--;
            j--;
        }
        return true;
    }

}
