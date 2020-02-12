package sort;

import java.util.Arrays;
import java.util.Comparator;

/*
    Given a list of non negative integers, arrange them such that they form the largest number.

    Example 1:

    Input: [10,2]
    Output: "210"
    Example 2:

    Input: [3,30,34,5,9]
    Output: "9534330"
 */
public class LargestNumber {
    /*
        Re-write a comparator to compare String a+b and b+a
     */
    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b){
            return (b+a).compareTo(a+b);
        }
    }
    public String largestNumber(int[] nums) {
        String[] sarr = new String[nums.length];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length; i++){
            sarr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(sarr, new LargerNumberComparator());
        if(sarr[0].equals("0"))
            return "0";
        for(String s : sarr){
            sb.append(s);
        }
        return sb.toString();
    }

}
