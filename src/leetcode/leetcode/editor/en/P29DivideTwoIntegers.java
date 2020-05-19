//Given two integers dividend and divisor, divide two integers without using mul
//tiplication, division and mod operator. 
//
// Return the quotient after dividing dividend by divisor. 
//
// The integer division should truncate toward zero, which means losing its frac
//tional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2. 
//
// Example 1: 
//
// 
//Input: dividend = 10, divisor = 3
//Output: 3
//Explanation: 10/3 = truncate(3.33333..) = 3.
// 
//
// Example 2: 
//
// 
//Input: dividend = 7, divisor = -3
//Output: -2
//Explanation: 7/-3 = truncate(-2.33333..) = -2.
// 
//
// Note: 
//
// 
// Both dividend and divisor will be 32-bit signed integers. 
// The divisor will never be 0. 
// Assume we are dealing with an environment which could only store integers wit
//hin the 32-bit signed integer range: [−231, 231 − 1]. For the purpose of this pr
//oblem, assume that your function returns 231 − 1 when the division result overfl
//ows. 
// 
// Related Topics Math Binary Search


package leetcode.leetcode.editor.en;

//Java：Divide Two Integers
public class P29DivideTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new P29DivideTwoIntegers().new Solution();
        // TO TEST
        System.out.println(solution.divide(-2147483648, -1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 1ms 37.5MB
        public int divide(int dividend, int divisor) {
            long res = 0;
            boolean isNegative = false;
            long Dividend = dividend, Divisor = divisor;
            if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
                isNegative = true;
            }
            if (Dividend < 0) {
                Dividend = -Dividend;
            }
            if (Divisor < 0) {
                Divisor = -Divisor;
            }
            res = div(Dividend, Divisor);
            if (isNegative) {
                res = -res;
            } else {
                res = res > Integer.MAX_VALUE ? Integer.MAX_VALUE : res;
            }
            return (int) res;
        }
        private long div(long dividend, long divisor) {
            long count = 0;
            if(dividend < divisor) {
                return count;
            }
            long contribute = divisor;
            count = 1;
            while(dividend - contribute >= 0) {
                count *= 2;
                contribute *= 2;
            }
            return count / 2 + div(dividend - contribute / 2, divisor);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}