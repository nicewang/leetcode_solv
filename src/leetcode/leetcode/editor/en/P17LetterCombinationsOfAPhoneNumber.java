//Given a string containing digits from 2-9 inclusive, return all possible lette
//r combinations that the number could represent. 
//
// A mapping of digit to letters (just like on the telephone buttons) is given b
//elow. Note that 1 does not map to any letters. 
//
// 
//
// Example: 
//
// 
//Input: "23"
//Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// Note: 
//
// Although the above answer is in lexicographical order, your answer could be i
//n any order you want. 
// Related Topics String Backtracking


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：Letter Combinations of a Phone Number
public class P17LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new P17LetterCombinationsOfAPhoneNumber().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<String> list = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        String[] mappings = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        // 解法：回溯 2ms 39.6MB
        public List<String> letterCombinations(String digits) {
            if(digits == null || digits.length() == 0) {
                return list;
            }
            backtrack(digits, 0);
            return list;
        }

        private void backtrack(String digits, int index) {
            if (index >= digits.length()) {
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
            int digit = Integer.parseInt(digits.substring(index, index + 1));
            String mapping = mappings[digit - 2];
            for (int i = 0; i < mapping.length(); i++) {
                stack.push(mapping.substring(i, i + 1));
                backtrack(digits, index + 1);
                stack.pop();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}