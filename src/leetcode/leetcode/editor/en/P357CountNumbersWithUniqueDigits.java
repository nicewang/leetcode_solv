//Given a non-negative integer n, count all numbers with unique digits, x, where
// 0 ≤ x < 10n. 
//
// 
// Example: 
//
// 
//Input: 2
//Output: 91 
//Explanation: The answer should be the total numbers in the range of 0 ≤ x < 10
//0, 
//             excluding 11,22,33,44,55,66,77,88,99
// 
// 
// 
// Constraints: 
//
// 
// 0 <= n <= 8 
// 
// Related Topics Math Dynamic Programming Backtracking


package leetcode.leetcode.editor.en;

//Java：Count Numbers with Unique Digits
public class P357CountNumbersWithUniqueDigits {
    public static void main(String[] args) {
        Solution solution = new P357CountNumbersWithUniqueDigits().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 0ms 36.4MB
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) {
                return 1;
            }
            int[] dp = new int[11];
            dp[0] = 1;
            for (int i = 1; i <= 10; i++) {
                dp[i] = dp[i - 1] * (10 - i + 1);
            }
            int[] dp1 = new int[10];
            dp1[0] = 1;
            for (int i = 1; i < 10; i++) {
                dp1[i] = dp1[i - 1] * (9 - i + 1);
            }
            int[] res = new int[n + 1];
            res[0] = dp[0];
            for (int i = 1; i <= n; i++) {
                if (i <= 10) {
                    res[i] = res[i - 1] + dp[i] - dp1[i - 1];
                } else {
                    res[i] = res[i - 1];
                }
            }
            return res[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}