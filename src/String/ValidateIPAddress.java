package String;

import java.util.regex.Pattern;

/*
68. Validate IP Address

Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

Note: You may assume there is no extra space or special characters in the input string.

Example 1:
Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".

Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".

Example 3:
Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.
 */
public class ValidateIPAddress {
    String IPv4 = "IPv4";
    String IPv6 = "IPv6";
    String Neither = "Neither";
    private String valiateIPv4(String IP){
        String[] addr = IP.split("\\.");
        if(IP.endsWith(".") || addr.length != 4) return Neither;
        for(String x : addr) {
            // 1. length is between 1 and 3
            if(x.length() < 1 || x.length() > 3) return Neither;
            // 2. no extra leading zeros
            if(x.length() > 1 && x.charAt(0) == '0') return Neither;
            // 3. only digits are allowed
            for(char c : x.toCharArray()){
                if (!Character.isDigit(c)) return Neither;
            }
            // 4. less than 255
            if (Integer.parseInt(x) > 255) return Neither;
        }
        return IPv4;
    }

    private String validateIPv6(String IP){
        String[] addr = IP.split(":");
        String hexdigits = "0123456789abcdefABCDEF";
        if(IP.endsWith(":") || addr.length != 8) return Neither;
        for(String x : addr) {
            // 1. at least one and not more than 4 hexdigits in one chunk
            if(x.length() < 1 || x.length() > 4) return Neither;
            // 2. only hexdigits are allowed: 0-9, a-f, A-F
            for(char c : x.toCharArray()){
                if (hexdigits.indexOf(c) == -1) return Neither;
            }
        }
        return IPv6;
    }

    // Solution 1: directly deal with string
    public String validIPAddress(String IP) {

        if (IP.contains("+") || IP.contains("-")) {
            return Neither;
        }
        if(IP.indexOf('.') != -1){  // possible IPv4
            return valiateIPv4(IP);
        } else if(IP.indexOf(':') != -1){ // possible IPv6
            return validateIPv6(IP);
        } else{
            return Neither;
        }
    }

    // Solution 2: Rugular expression
    public String validIPAddress2(String IP) {
        String IPv4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
        Pattern patten4 = Pattern.compile("^(" + IPv4 + "\\.){3}" + IPv4 + "$");
        String IPv6 = "([0-9a-fA-F]{1,4})";
        Pattern patten6 = Pattern.compile("^(" + IPv6 + "\\:){7}" + IPv6 + "$");
        if (patten4.matcher(IP).matches()) return "IPv4";
        return (patten6.matcher(IP).matches()) ? "IPv6" : "Neither";
    }
}
