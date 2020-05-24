package Greedy;

import java.util.Stack;

/*

678. Valid Parenthesis String

Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].

 */
public class ValidParenthesisString {
    // Solution 1 : Using two stacks, ')' match '(' first
    public boolean checkValidString(String s) {
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c != ')')
                s1.push(c);
            else{
                while(!s1.isEmpty() && s1.peek() == '*'){
                    s2.push(s1.pop());
                }
                if(!s1.isEmpty()){
                    s1.pop();
                    while(!s2.isEmpty()){
                        s1.push(s2.pop());
                    }
                } else if(!s2.isEmpty()){
                    while(!s2.isEmpty()){
                        s1.push(s2.pop());
                    }
                    s1.pop();

                } else{
                    return false;
                }
            }
        }
        if(!s1.empty() && s1.peek() == '('){
            return false;
        }

        while(!s1.empty()  && s1.peek() == '*') {
            while(!s1.empty() && s1.peek() == '*'){
                s2.push(s1.pop());
            }
            while(!s1.empty()  && s1.peek() == '(' && !s2.empty()){
                s1.pop();
                s2.pop();
            }
        }
        return s1.empty();
    }

    // Solution 2: keep track of the number of '(', minimum number and maximum number
    // minimum number >= 0 and once in the process the maximum number < 0 we return false;
    // In the end, we return if the minimum number is 0
    public boolean checkValidString2(String s) {
        int least_left = 0;
        int most_left = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                least_left++;
                most_left++;
            } else if (c == ')'){
                least_left--;
                most_left--;
            } else{
                least_left--;
                most_left++;
            }
            if(most_left < 0) return false;
            least_left = Math.max(least_left, 0);
        }
        return least_left == 0;
    }
}
