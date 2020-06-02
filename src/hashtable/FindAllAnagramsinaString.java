package hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
438. Find All Anagrams in a String

    Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

    Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

    The order of output does not matter.

    Example 1:

    Input:
    s: "cbaebabacd" p: "abc"

    Output:
    [0, 6]

    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".
    Example 2:

    Input:
    s: "abab" p: "ab"

    Output:
    [0, 1, 2]

    Explanation:
        The substring with start index = 0 is "ab", which is an anagram of "ab".
        The substring with start index = 1 is "ba", which is an anagram of "ab".
        The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsinaString {
    // Similar to problem 567 Permutation in String, but we keep a list of starting index
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) return new ArrayList();
        int[] times = new int[26]; // count in p
        int[] sCount = new int[26]; // count in s
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < pLen; i++){
            int id = p.charAt(i) - 'a';
            times[id]++;
        }
        for(int i = 0; i < sLen; i++){
            int id = s.charAt(i) - 'a';
            sCount[id]++;
            if(i >= pLen){
                int removeId = i - pLen;
                sCount[s.charAt(removeId) - 'a']--; //remove from head
            }
            if(Arrays.equals(times, sCount)){
                res.add(i - pLen + 1);
            }
        }
        return res;
    }
}
