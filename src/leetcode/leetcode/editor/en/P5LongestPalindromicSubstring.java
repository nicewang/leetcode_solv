//Given a string s, find the longest palindromic substring in s. You may assume 
//that the maximum length of s is 1000. 
//
// Example 1: 
//
// 
//Input: "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: "cbbd"
//Output: "bb"
// 
// Related Topics String Dynamic Programming


package leetcode.leetcode.editor.en;

//Java：Longest Palindromic Substring
public class P5LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new P5LongestPalindromicSubstring().new Solution();
        // TO TEST
        System.out.println(solution.longestPalindrome("babad"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // TODO Manacher算法 题解说算法十分复杂 有空看
        //解法二：中心扩展(2) 时间复杂度O(n^2) 空间复杂度O(1) 32ms 38.1MB
        // 核心思想：枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) return "";
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                int len1 = expandAroundCenter(s, i, i);
                int len2 = expandAroundCenter(s, i, i + 1);
                int len = Math.max(len1, len2);
                if (len > end - start) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int expandAroundCenter(String s, int left, int right) {
            int L = left, R = right;
            while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
                L--;
                R++;
            }
            return R - L - 1;
        }

        //解法二：中心扩展(1) 超时 通过用例数93／103
        public String longestPalindrome1(String s) {
            int len = s.length();
            // 回文子串的长度为l+1
            String res = "";
            for (int l = 0; l < len; l++) {
                // 回文子串起始位置为i
                for (int i = 0; i + l < len; i++) {
                    int left, right = i + l / 2 + 1;
                    if (l % 2 == 0) {
                        // 子串长度为奇数
                        left = i + l / 2 - 1;
                    } else {
                        // 子串长度为偶数
                        left = i + l / 2;
                    }
                    boolean isPalindrome = true;
                    while (left >= i) {
                        if (s.charAt(left) != s.charAt(right)) {
                            isPalindrome = false;
                            break;
                        }
                        left--;
                        right++;
                    }
                    if (isPalindrome && l + 1 > res.length()) {
                        res = s.substring(i, i + l + 1);
                    }
                }
            }
            return res;
        }

        //解法一：动态规划 时间复杂度O(n^2) 空间复杂度O(n^2) 134ms 43.9MB
        public String longestPalindromeDP(String s) {
            int len = s.length();
            // 回文子串的长度为l+1
            String res = "";
            boolean[][] dp = new boolean[len][len];
            for (int l = 0; l < len; l++) {
                // 回文子串起始位置为i
                for (int i = 0; i + l < len; i++) {
                    int j = i + l;
                    if (l == 0) {
                        dp[i][j] = true;
                    } else if (l == 1) {
                        dp[i][j] = s.charAt(i) == s.charAt(j);
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                    }
                    if (dp[i][j] && l + 1 > res.length()) {
                        res = s.substring(i, j + 1);
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}