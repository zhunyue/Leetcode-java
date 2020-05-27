package hashtable;

import java.util.HashMap;

/*
Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"

 */
public class FractiontoRecurringDecimal {

    // Keep a hash map to store numerator and corresponding result to check if recurring decimal is happening
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator==0) return "0";
        StringBuilder res = new StringBuilder();
        if((numerator > 0) ^ (denominator > 0))
            res.append("-");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        res.append(num/den);
        num %= den;
        if(num == 0)
            return res.toString();

        res.append(".");
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(num, res.length()); // A map to store numerator and corresponding result
        while(num != 0){
            num *= 10;
            res.append(num/den);
            num %= den;
            // Integer id = map.get(num);
            if(map.containsKey(num)) {            // If we seen the same numerator then recursion can occur
                int id = map.get(num);
                res.insert(id, "(");
                res.append(")");
                return res.toString();
            } else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
}
