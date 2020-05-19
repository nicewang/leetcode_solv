//Implement pow(x, n), which calculates x raised to the power n (xn). 
//
// Example 1: 
//
// 
//Input: 2.00000, 10
//Output: 1024.00000
// 
//
// Example 2: 
//
// 
//Input: 2.10000, 3
//Output: 9.26100
// 
//
// Example 3: 
//
// 
//Input: 2.00000, -2
//Output: 0.25000
//Explanation: 2-2 = 1/22 = 1/4 = 0.25
// 
//
// Note: 
//
// 
// -100.0 < x < 100.0 
// n is a 32-bit signed integer, within the range [−231, 231 − 1] 
// 
// Related Topics Math Binary Search


package leetcode.leetcode.editor.en;

//Java：Pow(x, n)
public class P50PowxN {
    public static void main(String[] args) {
        Solution solution = new P50PowxN().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法一：快速幂+迭代 1ms 37.7MB
        // 时间复杂度 O(log n) 空间复杂度O(1)
        public double myPow(double x, int n) {
            long N = n;
            return n < 0 ? 1.0 / (quickMul2(x, -N)) : quickMul2(x, N);
        }

        private double quickMul2(double x, long n) {
            if (n == 0) {
                return 1.0;
            }
            double ans = 1.0;
            double x_contributor = x;
            while (n > 0) {
                if(n % 2 == 1) {
                    ans *= x_contributor;
                }
                x_contributor *= x_contributor;
                n /= 2;
            }
            return ans;
        }

        // 解法一：快速幂+递归 1ms 37.3MB
        // 时间复杂度 O(log n) 空间复杂度O(log n)
        public double myPowOne(double x, int n) {
            return n < 0 ? 1.0 / (quickMul(x, -n)) : quickMul(x, n);
        }

        private double quickMul(double x, int n) {
            if (n == 0) {
                return 1.0;
            }
            double y = quickMul(x, n / 2);
            return n % 2 == 0 ? y * y : y * y * x;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}