//
//Given n pairs of parentheses, write a function to generate all combinations of
// well-formed parentheses.
// 
//
// 
//For example, given n = 3, a solution set is:
// 
// 
//[
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
// Related Topics String Backtracking


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：Generate Parentheses
public class P22GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new P22GenerateParentheses().new Solution();
        // TO TEST
        solution.generateParenthesis(2);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        // 回溯法 15ms 39.7MB
        public List<String> generateParenthesis(int n) {
            dfs(n*2, 0, 0, n);
            return list;
        }

        private void dfs(int n, int open, int close, int max) {

            if (n == 0) {
                List<String> l = new ArrayList<>(stack);
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < l.size(); i++) {
                    sb.append(l.get(i));
                }
                if (!list.contains(sb.toString())) {
                    list.add(sb.toString());
                }
                return;
            }
            // TODO close和open是关键
            if(open < max) {
                stack.push("(");
                dfs(n-1, open+1, close, max);
                stack.pop();
            }
            // TODO 此处的作用是：1.每次open到max时 补齐close 2.每次回溯到上一个open时 补齐close数量至与open数量相同
            if(close < open) {
                stack.push(")");
                dfs(n-1, open, close+1, max);
                stack.pop();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}