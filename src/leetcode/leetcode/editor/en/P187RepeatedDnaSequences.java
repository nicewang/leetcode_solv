//All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, 
//for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify
// repeated sequences within the DNA. 
//
// Write a function to find all the 10-letter-long sequences (substrings) that o
//ccur more than once in a DNA molecule. 
//
// Example: 
//
// 
//Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//
//Output: ["AAAAACCCCC", "CCCCCAAAAA"]
// 
// Related Topics Hash Table Bit Manipulation


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//Java：Repeated DNA Sequences
public class P187RepeatedDnaSequences {
    public static void main(String[] args) {
        Solution solution = new P187RepeatedDnaSequences().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法一：21ms 48.4MB attention：使用HashSet 使用List会超时
        public List<String> findRepeatedDnaSequences(String s) {
            HashSet<String> list = new HashSet<>();
            HashSet<String> compList = new HashSet<>();
            int total = s.length() - 10 + 1;
            for (int i = 0; i < total; i++) {
                String sTmp = s.substring(i, i + 10);
                if (compList.contains(sTmp)) {
                    list.add(sTmp);
                    compList.remove(sTmp);
                }
                if (!list.contains(sTmp)) {
                    compList.add(sTmp);
                }
            }
            return new ArrayList<String>(list);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}