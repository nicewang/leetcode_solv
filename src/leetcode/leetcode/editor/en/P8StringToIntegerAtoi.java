//Implement atoi which converts a string to an integer. 
//
// The function first discards as many whitespace characters as necessary until 
//the first non-whitespace character is found. Then, starting from this character,
// takes an optional initial plus or minus sign followed by as many numerical digi
//ts as possible, and interprets them as a numerical value. 
//
// The string can contain additional characters after those that form the integr
//al number, which are ignored and have no effect on the behavior of this function
//. 
//
// If the first sequence of non-whitespace characters in str is not a valid inte
//gral number, or if no such sequence exists because either str is empty or it con
//tains only whitespace characters, no conversion is performed. 
//
// If no valid conversion could be performed, a zero value is returned. 
//
// Note: 
//
// 
// Only the space character ' ' is considered as whitespace character. 
// Assume we are dealing with an environment which could only store integers wit
//hin the 32-bit signed integer range: [−231, 231 − 1]. If the numerical value is 
//out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is
// returned. 
// 
//
// Example 1: 
//
// 
//Input: "42"
//Output: 42
// 
//
// Example 2: 
//
// 
//Input: "   -42"
//Output: -42
//Explanation: The first non-whitespace character is '-', which is the minus sig
//n.
//             Then take as many numerical digits as possible, which gets 42.
// 
//
// Example 3: 
//
// 
//Input: "4193 with words"
//Output: 4193
//Explanation: Conversion stops at digit '3' as the next character is not a nume
//rical digit.
// 
//
// Example 4: 
//
// 
//Input: "words and 987"
//Output: 0
//Explanation: The first non-whitespace character is 'w', which is not a numeric
//al 
//             digit or a +/- sign. Therefore no valid conversion could be perfo
//rmed. 
//
// Example 5: 
//
// 
//Input: "-91283472332"
//Output: -2147483648
//Explanation: The number "-91283472332" is out of the range of a 32-bit signed 
//integer.
//             Thefore INT_MIN (−231) is returned. 
// Related Topics Math String


package leetcode.leetcode.editor.en;

//Java：String to Integer (atoi)
public class P8StringToIntegerAtoi {
    public static void main(String[] args) {
        Solution solution = new P8StringToIntegerAtoi().new Solution();
        System.out.println(solution.myAtoi("9223372036854775808"));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 2ms 39.9MB
        public int myAtoi(String str) {
            long res = 0;
            boolean isStarted = false;
            boolean isNegative = false;
            boolean numStart = false;
            int symbol_cnt = 0;
            for (int i = 0; i < str.length(); i++) {
                if (!isStarted && str.charAt(i) == ' ') {
                    continue;
                }
                int tmp = str.charAt(i) - '0';
                if ((tmp >= 0 && tmp <= 9) || str.charAt(i) == '+' || str.charAt(i) == '-') {
                    isStarted = true;
                    if (str.charAt(i) == '-') {
                        symbol_cnt++;
                        if (symbol_cnt > 1 || numStart) {
                            break;
                        }
                        isNegative = true;
                        continue;
                    } else if (str.charAt(i) == '+') {
                        symbol_cnt++;
                        if (symbol_cnt > 1 || numStart) {
                            break;
                        }
                        continue;
                    }
                    numStart = true;
                    res = res * 10 + tmp;
                    if(!isNegative && res > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                    if(isNegative && (-res) < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    break;
                }
            }
            if (isNegative) {
                res = -res;
            }

            return (int) res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}