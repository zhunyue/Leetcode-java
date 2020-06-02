package Stack;

/*

402. Remove K Digits

    Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

    Note:
    The length of num is less than 10002 and will be ≥ k.
    The given num does not contain any leading zero.

    Example 1:
    Input: num = "1432219", k = 3
    Output: "1219"
    Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

    Example 2:
    Input: num = "10200", k = 1
    Output: "200"
    Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

    Example 3:
    Input: num = "10", k = 2
    Output: "0"
    Explanation: Remove all the digits from the number and it is left with nothing which is 0.

 */
public class RemoveKDigits {
    // Solution: every time we encounter an elelemnt < stack.peek() and remove time > 0, we replace the peek by the element and decrease remove time
    // Then we remove extra element to ensure we remove k element
    public String removeKdigits(String num, int k) {
        if (k == num.length()) return "0";
        StringBuilder sb = new StringBuilder(num);
        for(int i = 1; k > 0 && i < sb.length(); i++){
            if(sb.charAt(i - 1) > sb.charAt(i)){
                k--;
                sb.replace(i - 1, i, "");
                if(i == 1) i = 0;
                else
                    i = i - 2;
            }
            while(sb.length() > 1 && sb.charAt(0) == '0'){
                sb.deleteCharAt(0);
                i = 0;
            }
        }
        String result = sb.replace(sb.length() - k, sb.length(), "").toString();
        return result.equals("") ? "0" : result;
    }
}
