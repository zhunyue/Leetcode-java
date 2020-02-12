package BitManipulation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences {
    /*
        Solution 1 : Using Sliding windown, go through each possible substring and store in set
     */
    public List<String> findRepeatedDnaSequences(String s) {
        int start = 0;
        int end = 9;
        HashSet<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        while(end < s.length()){
            String cur = s.substring(start, end+1);
            if(set.contains(cur)&&!list.contains(cur)){
                list.add(cur);
            } else{
                if(!set.contains(cur)){
                    set.add(cur);
                }
            }
            start++;
            end++;
        }
        return list;
    }

    /*
        Storing the substring might consume too much memory.
        Instead, to represent the 10-digit string, we could use 10 * 2 = 20 bits to store the string by
        representing A, C, G, T as 00, 01, 10, 11
     */
    public List<String> findRepeatedDnaSequences2(String s) {
        List<String> res = new ArrayList<>();
        Set<Integer> word = new HashSet<>();
        Set<Integer> doubleWord = new HashSet<>();
        int[] map = new int[26];
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        int num = 0;
        for(int i = 0; i < s.length(); i++){
            num = num << 2;
            num = num | map[s.charAt(i) - 'A'];
            num &= 0xfffff;
            if(i < 9) continue;;
            if(!word.add(num) && doubleWord.add(num)){
                res.add(s.substring(i-9, i+1));
            }
        }
        return res;
    }
}
