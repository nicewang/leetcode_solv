//Implement a basic calculator to evaluate a simple expression string. 
//
// The expression string may contain open ( and closing parentheses ), the plus 
//+ or minus sign -, non-negative integers and empty spaces . 
//
// Example 1: 
//
// 
//Input: "1 + 1"
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: " 2-1 + 2 "
//Output: 3 
//
// Example 3: 
//
// 
//Input: "(1+(4+5+2)-3)+(6+8)"
//Output: 23 
//Note:
//
// 
// You may assume that the given expression is always valid. 
// Do not use the eval built-in library function. 
// 
// Related Topics Math Stack


package leetcode.leetcode.editor.en;

import java.util.Stack;

//Java：Basic Calculator
public class P224BasicCalculator {
    public static void main(String[] args) {
        Solution solution = new P224BasicCalculator().new Solution();
        // TO TEST
        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 32ms 43.5MB
        int ptr = 0;
        public int calculate(String s) {
            Stack<String> stack = new Stack<>();
            Stack<Character> operator_stack = new Stack<>();
            operator_stack.push('+');
            while (ptr < s.length()) {
                if (s.charAt(ptr) == ' ') {
                    ptr++;
                    continue;
                }
                if (s.charAt(ptr) == '+' || s.charAt(ptr) == '-') {
                    operator_stack.push(s.charAt(ptr));
                    ptr++;
                    continue;
                }
                if (Character.isDigit(s.charAt(ptr))) {
                    String num = getDigit(s);
                    stack.push(num);
                } else if (s.charAt(ptr) == '(') {
                    stack.push(s.substring(ptr, ptr + 1));
                    operator_stack.push('+');
                    ptr++;
                } else {
                    int sum = 0;
                    while (!stack.peek().equals("(")) {
                        int num = Integer.parseInt(stack.pop());
                        if (operator_stack.pop() == '-') {
                            num = -num;
                        }
                        sum += num;
                    }
                    stack.pop();
                    stack.push(String.valueOf(sum));
                    ptr++;
                }
            }
            int sum = 0;
            while (!stack.isEmpty()) {
                int num = Integer.parseInt(stack.pop());
                if (operator_stack.pop() == '-') {
                    num = -num;
                }
                sum += num;
            }
            stack.push(String.valueOf(sum));
            return sum;
        }

        private String getDigit(String s) {
            StringBuffer sb = new StringBuffer();
            while (ptr < s.length() && Character.isDigit(s.charAt(ptr))) {
                sb.append(s.charAt(ptr++));
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}