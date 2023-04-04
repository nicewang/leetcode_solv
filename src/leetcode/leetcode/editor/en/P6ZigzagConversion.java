//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number o
//f rows like this: (you may want to display this pattern in a fixed font for bett
//er legibility) 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R
// 
//
// And then read line by line: "PAHNAPLSIIGYIR" 
//
// Write the code that will take a string and make this conversion given a numbe
//r of rows: 
//
// 
//string convert(string s, int numRows); 
//
// Example 1: 
//
// 
//Input: s = "PAYPALISHIRING", numRows = 3
//Output: "PAHNAPLSIIGYIR"
// 
//
// Example 2: 
//
// 
//Input: s = "PAYPALISHIRING", numRows = 4
//Output: "PINALSIGYAHRPI"
//Explanation:
//
//P     I    N
//A   L S  I G
//Y A   H R
//P     I 
// Related Topics String


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Java：ZigZag Conversion
public class P6ZigzagConversion {
    public static void main(String[] args) {
        Solution solution = new P6ZigzagConversion().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<LinkedList<String>> list = new ArrayList<>();
        // 12ms 40.4MB
        public String convert(String s, int numRows) {
            if (s == null || s.length() == 0) {
                return "";
            }
            if(numRows == 1) {
                return s;
            }
            for (int i = 0; i < numRows; i++) {
                LinkedList<String> queue = new LinkedList<>();
                list.add(queue);
            }
            int count = 0;
            boolean isAdd = true;
            for (int i = 0; i < s.length(); i++) {
                if(isAdd && count == numRows-1) {
                    isAdd = false;
                } else if(!isAdd && count == 0) {
                    isAdd = true;
                }
                list.get(count).add(s.substring(i,i+1));
                if(isAdd) {
                    count++;
                } else {
                    count--;
                }
            }
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < numRows; i++) {
                LinkedList<String> queue = list.get(i);
                while(!queue.isEmpty()) {
                    sb.append(queue.remove());
                }
            }
            return sb.toString();

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}