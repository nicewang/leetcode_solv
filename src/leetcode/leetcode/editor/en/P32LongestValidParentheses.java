//Given a string containing just the characters '(' and ')', find the length of 
//the longest valid (well-formed) parentheses substring. 
//
// Example 1: 
//
// 
//Input: "(()"
//Output: 2
//Explanation: The longest valid parentheses substring is "()"
// 
//
// Example 2: 
//
// 
//Input: ")()())"
//Output: 4
//Explanation: The longest valid parentheses substring is "()()"
// 
// Related Topics String Dynamic Programming


package leetcode.leetcode.editor.en;

import java.util.Stack;

//Java：Longest Valid Parentheses
public class P32LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new P32LongestValidParentheses().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法二：动态规划解法 1ms 39.6MB 时间复杂度O(n) 空间复杂度O(n)
        public int longestValidParentheses(String s) {
            int max = 0;
            int[] dp = new int[s.length()];
//            dp[i]表示以下标i字符结尾的最长有效括号的长度
//            显然有效的子串一定以‘)’结尾，因此以‘(’结尾的子串对应的dp值必定为0，只需要求解‘)’dp数组中对应位置的值。
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = ((i > 1) ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        // TODO Attention
                        dp[i] = dp[i - 1] + ((i - dp[i - 1] > 1) ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    max = Math.max(max, dp[i]);
                }
            }
            return max;
        }

        // 解法一：堆栈解法 4ms 39.7MB 时间复杂度O(n) 空间复杂度O(n)
        public int longestValidParenthesesOne(String s) {
            int max = 0;
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
            return max;
        }

        // 解法三：2ms 39.3MB 时间复杂度O(n) 空间复杂度O(1)
        public int longestValidParenthesesThree(String s) {
            int max = 0;
            int left = 0, right = 0;
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if(left == right) {
                    max = Math.max(max, right*2);
                } else if(right > left) {
                    left = 0;
                    right = 0;
                }
            }
            left = 0;
            right = 0;
            for(int i = s.length()-1; i >= 0; i--) {
                if(s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if(left == right) {
                    max = Math.max(max, left*2);
                } else if(left > right) {
                    left = 0;
                    right = 0;
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}