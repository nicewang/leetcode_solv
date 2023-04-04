//A message containing letters from A-Z is being encoded to numbers using the fo
//llowing mapping: 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// Given a non-empty string containing only digits, determine the total number o
//f ways to decode it. 
//
// Example 1: 
//
// 
//Input: "12"
//Output: 2
//Explanation: It could be decoded as "AB" (1 2) or "L" (12).
// 
//
// Example 2: 
//
// 
//Input: "226"
//Output: 3
//Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6)
//. 
// Related Topics String Dynamic Programming


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：Decode Ways
public class P91DecodeWays {
    public static void main(String[] args) {
        Solution solution = new P91DecodeWays().new Solution();
        // TO TEST
        System.out.println(solution.numDecodings("226"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Stack<String> stack = new Stack<>();
        List<List<String>> list = new ArrayList<>();

        // 解法三：动态规划-数组 1、3ms 38.3MB TODO 没看懂 多看几遍
        public int numDecodings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int len = s.length();

            int[] dp = new int[len + 1];
            dp[len] = 1;
            dp[len - 1] = s.charAt(len - 1) == '0' ? 0 : 1;
            for (int i = len - 2; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    dp[i] = 0;
                    continue;
                }
                if (Integer.parseInt(s.substring(i, i + 2)) <= 26) {
                    dp[i] = dp[i + 1] + dp[i + 2];
                } else {
                    dp[i] = dp[i + 1];
                }
            }
            return dp[0];
        }

        // 解法二：动态规划-递归 超出时间限制 通过用例数239／258
        public int numDecodingsTwo(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            return dp(s, 0);
        }

        private int dp(String s, int index) {
            if (index == s.length() - 1) {
                return s.charAt(index) == '0' ? 0 : 1;
            }

            if (s.charAt(index) == '0') {
                return 0;
            }
            if (Integer.parseInt(s.substring(index, Math.min(s.length(), index+2))) <= 26) {
                if (index == s.length() - 2) {
                    return dp(s, index + 1) + 1;
                } else {
                    return dp(s, index + 1) + dp(s, index + 2);
                }
            }
            return dp(s, index + 1);

        }

        // 解法一：dfs回溯 超时 通过用例数221／258
        public int numDecodingsOne(String s) {
            dfs(s, 0);
            return list.size();
        }

        private void dfs(String s, int start) {
            if (start >= s.length()) {
                List<String> l = new ArrayList<>(stack);
                if (!list.contains(l)) {
                    list.add(l);
                }
                return;
            }
            for (int i = 1; i <= 2 && start + i <= s.length(); i++) {
                String s_tmp = s.substring(start, start + i);
                if (Integer.parseInt(s_tmp) > 0 && Integer.parseInt(s_tmp) <= 26 && s_tmp.charAt(0) != '0') {
                    stack.push(s_tmp);
                    dfs(s, start + i);
                    stack.pop();
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}