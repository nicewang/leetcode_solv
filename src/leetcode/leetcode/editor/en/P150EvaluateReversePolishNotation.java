//Evaluate the value of an arithmetic expression in Reverse Polish Notation. 
//
// Valid operators are +, -, *, /. Each operand may be an integer or another exp
//ression. 
//
// Note: 
//
// 
// Division between two integers should truncate toward zero. 
// The given RPN expression is always valid. That means the expression would alw
//ays evaluate to a result and there won't be any divide by zero operation. 
// 
//
// Example 1: 
//
// 
//Input: ["2", "1", "+", "3", "*"]
//Output: 9
//Explanation: ((2 + 1) * 3) = 9
// 
//
// Example 2: 
//
// 
//Input: ["4", "13", "5", "/", "+"]
//Output: 6
//Explanation: (4 + (13 / 5)) = 6
// 
//
// Example 3: 
//
// 
//Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
//Output: 22
//Explanation: 
//  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//= ((10 * (6 / (12 * -11))) + 17) + 5
//= ((10 * (6 / -132)) + 17) + 5
//= ((10 * 0) + 17) + 5
//= (0 + 17) + 5
//= 17 + 5
//= 22
// 
// Related Topics Stack


package leetcode.leetcode.editor.en;

import java.util.Stack;

//Java：Evaluate Reverse Polish Notation
public class P150EvaluateReversePolishNotation {
    public static void main(String[] args) {
        Solution solution = new P150EvaluateReversePolishNotation().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 8ms 39.8MB
        public int evalRPN(String[] tokens) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < tokens.length; i++) {
                String tmp = tokens[i];
                if (tmp.charAt(0) == '-' && tmp.length() > 1) {
                    int num = Integer.parseInt(tmp.substring(1));
                    num = 0 - num;
                    stack.push(num);
                } else if (Character.isDigit(tmp.charAt(0))) {
                    int num = Integer.parseInt(tmp);
                    stack.push(num);
                } else {
                    int num2 = stack.pop();
                    int num1 = stack.pop();
                    if (tmp.equals("+")) {
                        stack.push(num1 + num2);
                    } else if (tmp.equals("-")) {
                        stack.push(num1 - num2);
                    } else if (tmp.equals("*")) {
                        stack.push(num1 * num2);
                    } else if (tmp.equals("/")) {
                        stack.push(num1 / num2);
                    }
                }
            }
            return stack.pop();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}