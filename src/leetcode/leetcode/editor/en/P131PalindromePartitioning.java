//Given a string s, partition s such that every substring of the partition is a 
//palindrome. 
//
// Return all possible palindrome partitioning of s. 
//
// Example: 
//
// 
//Input: "aab"
//Output:
//[
//  ["aa","b"],
//  ["a","a","b"]
//]
// 
// Related Topics Backtracking


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：Palindrome Partitioning
public class P131PalindromePartitioning {
    public static void main(String[] args) {
        Solution solution = new P131PalindromePartitioning().new Solution();
        solution.partition("aab");
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 5ms 40.7MB
        Stack<String> stack = new Stack<>();
        List<List<String>> res = new ArrayList<>();
        public List<List<String>> partition(String s) {
            backtracking(s, 0);
            return res;
        }

        private void backtracking(String s, int index) {
            if (index == s.length()) {
                res.add(new ArrayList<>(stack));
                return;
            }
            for (int i = index + 1; i <= s.length(); i++) {
                String tmp = s.substring(index, i);
                if (isPalindrome(tmp)) {
                    stack.push(tmp);
                    backtracking(s, i);
                    stack.pop();
                }
            }
        }

        private boolean isPalindrome(String s) {
            int left = 0, right = s.length() - 1;
            while (left <= right) {
                if (s.charAt(left++) != s.charAt(right--)) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}