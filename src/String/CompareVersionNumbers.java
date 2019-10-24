package String;

/*
    Compare two version numbers version1 and version2.
    If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

    You may assume that the version strings are non-empty and contain only digits and the . character.

    The . character does not represent a decimal point and is used to separate number sequences.

    For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

    You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.



    Example 1:

    Input: version1 = "0.1", version2 = "1.1"
    Output: -1
    Example 2:

    Input: version1 = "1.0.1", version2 = "1"
    Output: 1
    Example 3:

    Input: version1 = "7.5.2.4", version2 = "7.5.3"
    Output: -1
    Example 4:

    Input: version1 = "1.01", version2 = "1.001"
    Output: 0
    Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
    Example 5:

    Input: version1 = "1.0", version2 = "1.0.0"
    Output: 0
    Explanation: The first version number does not have a third level revision number, which means its third level revision number is default to "0"
 */
public class CompareVersionNumbers {
    /*
        Solution 1: split into arrays and compare the elements in array
     */
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int i = 0;
        int maxlen=Math.max(v1.length,v2.length);
        while( i < maxlen) {
            int num1 = v1.length <= i ? 0:Integer.parseInt(v1[i]);
            int num2 = v2.length <= i ? 0:Integer.parseInt(v2[i]);

            if(num1 > num2) return 1;
            else if (num1 < num2) return -1;
            i++;
        }
        return 0;
    }

     /*
        Solution 2: Go through each elements, convert substring to numbers and compare
     */
     public int compareVersion2(String version1, String version2) {
         int len1 = version1.length();
         int len2 = version2.length();
         for(int i = 0, j = 0; i < len1 || j < len2; i++, j++){
             int tmp1 = 0;
             int tmp2 = 0;
             while (i < len1 && version1.charAt(i) != '.') {
                 tmp1 = tmp1 * 10 + version1.charAt(i) - '0';
                 i++;
             }
             while (j < len2 && version2.charAt(j) != '.') {
                 tmp2 = tmp2 * 10 + version2.charAt(j) - '0';
                 j++;
             }
             if (tmp1 < tmp2) {
                 return -1;
             } else if (tmp1 > tmp2) {
                 return 1;
             }
         }
         return 0;
     }
}
