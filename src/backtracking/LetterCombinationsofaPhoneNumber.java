package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

    A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

    Example:
        Input: "23"
        Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */

public class LetterCombinationsofaPhoneNumber {
    public List<String> letterCombinations(String digits) {
        Map<String, String> phone = new HashMap<String, String>();
        phone.put("2", "abc");
        phone.put("3", "def");
        phone.put("4", "ghi");
        phone.put("5", "jkl");
        phone.put("6", "mno");
        phone.put("7", "pqrs");
        phone.put("8", "tuv");
        phone.put("9", "wxyz");
        List<String> res = new ArrayList<>();
        helper(digits, 0, res, "", phone);
        return res;
    }

    private void helper(String digits, int start, List<String> res, String cur, Map<String, String> letters){
        if(digits.length() == 0){
            return ;
        }
        if(cur.length() == digits.length()){
            res.add(cur);
            return;
        }
        String tmp = letters.get(digits.substring(start, start+1));
        for(int i = 0; i < tmp.length(); i++){
            helper(digits, start+1, res, cur+tmp.charAt(i), letters);
        }
    }
}
