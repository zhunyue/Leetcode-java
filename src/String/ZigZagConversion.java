package String;

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);


Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

Example 3:

Input: s = "A", numRows = 1
Output: "A"


Constraints:

1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000
 */

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
    /*
      Solution 1:
      Idea: Keep a list of StringBuilder to track each line. We iterate on each character and find proper position for it
     */

    public String convert(String s, int numRows) {
        int n = Math.min(numRows, s.length());
        if(n == 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;
        for (int i = 0; i < s.length(); i++) {
            rows.get(curRow).append(s.charAt(i));
            if(curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        String res = "";
        for(int i = 0; i < rows.size(); i++) {
            String curStr = rows.get(i).toString();
            res += curStr;
        }
        return res;
    }

    /*
      Solution 2:
      Idea: Math Method. For each row, find all characters and append
      n = num of rows
      For row 0, it has all character 0, 0 + 2n - 2, 2*(2n-2) ......
      For row i, it has i, i + 2n -2, i + 2(2n-2) .....
                 it also has k (2n - 2 ) - i
      For row n-1, it has all character n-1, (n-1) + (2n - 2), ......

      So for any row, we will have characters at i +  k * (2n - 2)
      if i is in between, we also have (k + 1) * (2n-2) - i
     */
    public String convert2(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int step = 2 * numRows - 2;
            int j = 0;
            while (i + j < s.length()) {
                res.append(s.charAt(i + j));
                if (i != 0 && i != numRows - 1 && (j + step - i) < s.length()) {
                    res.append(s.charAt(j + step - i));
                }
                j += step;
            }
        }
        return res.toString();
    }
}
