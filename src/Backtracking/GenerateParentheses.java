package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(0,0,"",n,res);
        return res;
    }

    public static void helper(int left, int right, String cur, int max, List<String> res){
        if(cur.length() == 2*max){
            res.add(cur);
            return;
        }
        if(left >= right){
            helper(left, right+1, cur+")", max, res);
            if(left < max){
                helper(left+1, right, cur+"(", max, res);
            }
        }


    }

    public static void main(String[] args){
        List<String> res = generateParenthesis(4);
        for(String s : res){
            System.out.println(s);
        }
    }
}
