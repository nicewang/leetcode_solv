//Given an input string, reverse the string word by word. 
//
// 
//
// Example 1: 
//
// 
//Input: "the sky is blue"
//Output: "blue is sky the"
// 
//
// Example 2: 
//
// 
//Input: "  hello world!  "
//Output: "world! hello"
//Explanation: Your reversed string should not contain leading or trailing space
//s.
// 
//
// Example 3: 
//
// 
//Input: "a good   example"
//Output: "example good a"
//Explanation: You need to reduce multiple spaces between two words to a single 
//space in the reversed string.
// 
//
// 
//
// Note: 
//
// 
// A word is defined as a sequence of non-space characters. 
// Input string may contain leading or trailing spaces. However, your reversed s
//tring should not contain leading or trailing spaces. 
// You need to reduce multiple spaces between two words to a single space in the
// reversed string. 
// 
//
// 
//
// Follow up: 
//
// For C programmers, try to solve it in-place in O(1) extra space. Related Topi
//cs String


package leetcode.leetcode.editor.en;

import java.util.LinkedList;
import java.util.Stack;

//Java：Reverse Words in a String
public class P151ReverseWordsInAString {
    public static void main(String[] args) {
        Solution solution = new P151ReverseWordsInAString().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 17ms 41.7MB TODO 有空看看题解答案
        public String reverseWords(String s) {
            StringBuffer sb = new StringBuffer();
            if (s == null || s.length() <= 0) {
                return sb.toString();
            }
            Stack<LinkedList<Character>> stack = new Stack<>();
            boolean isNew = false;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    isNew = false;
                    continue;
                }
                if (!isNew) {
                    LinkedList<Character> cList = new LinkedList<>();
                    cList.add(s.charAt(i));
                    stack.push(cList);
                    isNew = true;
                } else {
                    LinkedList<Character> cList = stack.pop();
                    cList.add(s.charAt(i));
                    stack.push(cList);
                }
            }
            while (!stack.isEmpty()) {
                LinkedList<Character> cList = stack.pop();
                while (!cList.isEmpty()) {
                    sb.append(cList.remove());
                }
                if (!stack.isEmpty()) {
                    sb.append(" ");
                }
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}