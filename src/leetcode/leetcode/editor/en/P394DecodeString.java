//Given an encoded string, return its decoded string. 
//
// The encoding rule is: k[encoded_string], where the encoded_string inside the 
//square brackets is being repeated exactly k times. Note that k is guaranteed to 
//be a positive integer. 
//
// You may assume that the input string is always valid; No extra white spaces, 
//square brackets are well-formed, etc. 
//
// Furthermore, you may assume that the original data does not contain any digit
//s and that digits are only for those repeat numbers, k. For example, there won't
// be input like 3a or 2[4]. 
//
// 
// Example 1: 
// Input: s = "3[a]2[bc]"
//Output: "aaabcbc"
// Example 2: 
// Input: s = "3[a2[c]]"
//Output: "accaccacc"
// Example 3: 
// Input: s = "2[abc]3[cd]ef"
//Output: "abcabccdcdcdef"
// Example 4: 
// Input: s = "abc3[cd]xyz"
//Output: "abccdcdcdxyz"
// Related Topics Stack Depth-first Search


package leetcode.leetcode.editor.en;

import java.util.Collections;
import java.util.LinkedList;

//Java：Decode String
public class P394DecodeString {
    public static void main(String[] args) {
        Solution solution = new P394DecodeString().new Solution();
        // TO TEST
        System.out.println(solution.decodeString("abc3[cd]xyz"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法二：堆栈解法 1ms 37.4MB
        // TODO 思路牛逼清晰 多回顾回顾
//        1.如果当前的字符为数位，解析出一个数字（连续的多个数位）并进栈
//        2.如果当前的字符为字母或者左括号，直接进栈
//        3.如果当前的字符为右括号，开始出栈，一直到左括号出栈，出栈序列反转后拼接成一个字符串，
//          此时取出栈顶的数字，就是这个字符串应该出现的次数，我们根据这个次数和字符串构造出新的字符串并进栈
        int ptr = 0;
        public String decodeString(String s) {
            if (s == null) {
                return null;
            }
            LinkedList<String> stack = new LinkedList<>();
            while(ptr < s.length()) {
                if(Character.isDigit(s.charAt(ptr))) {
                    String digit = getDigit(s);
                    stack.addLast(digit);
                } else if(Character.isLetter(s.charAt(ptr)) || s.charAt(ptr) == '[') {
                    stack.addLast(String.valueOf(s.charAt(ptr++)));
                } else {
                    ptr++;
                    LinkedList<String> sub = new LinkedList<>();
                    while(!stack.peekLast().equals("[")) {
                        sub.addLast(stack.removeLast());
                    }
                    Collections.reverse(sub);
                    // 左括号出栈
                    stack.removeLast();
                    // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                    int repeatTime = Integer.parseInt(stack.removeLast());
                    String sub_s = getString(sub);
                    StringBuffer sb1 = new StringBuffer();
                    for(int i = 0; i < repeatTime; i++) {
                        sb1.append(sub_s);
                    }
                    stack.addLast(sb1.toString());
                }
            }
            return getString(stack);
        }

        private String getDigit(String s) {
            StringBuffer sb = new StringBuffer();
            while(Character.isDigit(s.charAt(ptr))) {
                sb.append(s.charAt(ptr++));
            }
            return sb.toString();
        }

        private String getString(LinkedList<String> sub) {
            StringBuffer sb = new StringBuffer();
            for(String s : sub) {
                sb.append(s);
            }
            return  sb.toString();
        }

        // 解法一：暴力递归 1ms 37.7MB
        public String decodeStringOne(String s) {
            String res = "";
            if (s == null) {
                return res;
            }
            int len = s.length();
            res = decode(s, 0, len - 1);
            return res;
        }

        private String decode(String s, int start, int end) {
            String res = "";
            StringBuilder sb = new StringBuilder();
            int i = start;
            int repeatCnt = 0;
            while (i <= end) {
                if (Character.isDigit(s.charAt(i))) {
                    if (repeatCnt > 0) {
                        StringBuilder sb1 = new StringBuilder();
                        sb1.append(res);
                        for (int i1 = 0; i1 < repeatCnt; i1++) {
                            sb1.append(sb.toString());
                        }
                        res = sb1.toString();
                        repeatCnt = 0;
                        sb.delete(0, sb.length());
                    }
                    while (Character.isDigit(s.charAt(i))) {
                        repeatCnt *= 10;
                        repeatCnt += Integer.parseInt(s.substring(i, i + 1));
                        i++;
                    }
                    continue;
                }
                if (Character.isLetter(s.charAt(i))) {
                    sb.append(s.substring(i, i + 1));
                    i++;
                    continue;
                }
                if (s.charAt(i) == '[') {
                    int new_start = i + 1;
                    i++;
                    int leftCnt = 1, rightCnt = 0;
                    while (rightCnt < leftCnt) {
                        if (s.charAt(i) == '[') {
                            leftCnt++;
                        }
                        if (s.charAt(i) == ']') {
                            rightCnt++;
                        }
                        i++;
                    }
                    int new_end = i - 2;
                    String s_tmp = decode(s, new_start, new_end);
                    if(repeatCnt == 0) {
                        sb.append(s_tmp);
                    } else {
                        for (int i1 = 0; i1 < repeatCnt; i1++) {
                            sb.append(s_tmp);
                        }
                    }
                    res += sb.toString();
                    repeatCnt = 0;
                    sb.delete(0, sb.length());
                }
            }
            if(repeatCnt > 0) {
                for(int i1 = 0; i1 < repeatCnt; i1++) {
                    res += sb.toString();
                }
                return res;
            }
            return res+sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}