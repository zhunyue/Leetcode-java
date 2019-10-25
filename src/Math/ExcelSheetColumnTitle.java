package Math;

/*
    Given a positive integer, return its corresponding column title as appear in an Excel sheet.

    For example:

        1 -> A
        2 -> B
        3 -> C
        ...
        26 -> Z
        27 -> AA
        28 -> AB
        ...
    Example 1:

    Input: 1
    Output: "A"
    Example 2:

    Input: 28
    Output: "AB"
 */
public class ExcelSheetColumnTitle {
    /*
        Convert from 10-base to 26-base
     */
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int quotient = (n-1) / 26;
            int mod = (n-1) % 26;
            char ch = (char) ( mod + 'A');
            sb.append(ch);
            n = quotient;
        }
        return sb.reverse().toString();
    }
}
