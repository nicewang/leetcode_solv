//Implement a basic calculator to evaluate a simple expression string. 
//
// The expression string contains only non-negative integers, +, -, *, / operato
//rs and empty spaces . The integer division should truncate toward zero. 
//
// Example 1: 
//
// 
//Input: "3+2*2"
//Output: 7
// 
//
// Example 2: 
//
// 
//Input: " 3/2 "
//Output: 1 
//
// Example 3: 
//
// 
//Input: " 3+5 / 2 "
//Output: 5
// 
//
// Note: 
//
// 
// You may assume that the given expression is always valid. 
// Do not use the eval built-in library function. 
// 
// Related Topics String


package leetcode.leetcode.editor.en;

import java.util.Stack;

//Java：Basic Calculator II
public class P227BasicCalculatorIi {
    public static void main(String[] args) {
        Solution solution = new P227BasicCalculatorIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 11ms 40MB
//        1.先跳过空格
//        2.出现了数字则记录整个数字是多少，然后根据之前的运算符决定下一步：
//          如果是加号'+'，说明前面的运算独立于以后的运算，可以将结果暂时放入栈；
//          如果是减号'-'，可以看成-1 * tempNum，然后将-tempNum入栈；
//          如果是乘号'*'或者除号'/'，由于前面的运算独立于此，可以先计算lastNum和tempNum积，然后结果入栈。
//        3.最后将栈中的所有元素相加就是答案。
        public int calculate(String s) {
            Stack<Integer> stack = new Stack<>();
            char operator = '+';
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == ' ') {
                    continue;
                }
                if (Character.isDigit(arr[i])) {
                    int num = arr[i] - '0';
                    while (++i < arr.length && Character.isDigit(arr[i])) {
                        num = num * 10 + arr[i] - '0';
                    }
                    i--;
                    if (operator == '+') {
                        stack.push(num);
                    } else if (operator == '-') {
                        stack.push(-num);
                    } else if (operator == '*') {
                        stack.push(stack.pop() * num);
                    } else if (operator == '/') {
                        stack.push(stack.pop() / num);
                    }
                } else {
                    operator = arr[i];
                }
            }
            int res = 0;
            for (int num : stack) {
                res += num;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}