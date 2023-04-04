//给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。 
//
// 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。 
//
// 说明： 
//
// 
// 字母异位词指字母相同，但排列不同的字符串。 
// 不考虑答案输出的顺序。 
// 
//
// 示例 1: 
//
// 
//输入:
//s: "cbaebabacd" p: "abc"
//
//输出:
//[0, 6]
//
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
// 
//
// 示例 2: 
//
// 
//输入:
//s: "abab" p: "ab"
//
//输出:
//[0, 1, 2]
//
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
// 
// Related Topics 哈希表


package leetcode.leetcode.editor.cn;

import java.util.*;

//Java：找到字符串中所有字母异位词
public class P438FindAllAnagramsInAString {
    public static void main(String[] args) {
        Solution solution = new P438FindAllAnagramsInAString().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //  解法二：滑动窗口+数组排序 2023ms 40.8MB
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<>();
            if (s == null || s.length() == 0 || p == null || p.length() == 0) {
                return res;
            }
            int len = p.length();
            char[] ch_p = p.toCharArray();
            Arrays.sort(ch_p);
            for(int i = 0; i <= s.length()-len; i++) {
                char[] ch = s.substring(i, i+len).toCharArray();
                Arrays.sort(ch);
                if(Arrays.equals(ch, ch_p)) {
                    res.add(i);
                }
            }
            return res;
        }

        //  解法一：暴力循环 超时 通过用例数:34/36
        public List<Integer> findAnagramsOne(String s, String p) {
            List<Integer> res = new ArrayList<>();
            if (s == null || s.length() == 0 || p == null || p.length() == 0) {
                return res;
            }
            int len = p.length();
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < len; i++) {
                map.put(p.substring(i, i + 1), map.getOrDefault(p.substring(i, i + 1), 0) + 1);
            }
            for (int i = 0; i <= s.length() - len; i++) {
                Map<String, Integer> tmp_map = new HashMap<>(map);
                int j = 0;
                for(; j < len; j++) {
                    String tmp_s = s.substring(i+j, i+j+1);
                    if(!tmp_map.containsKey(tmp_s) || tmp_map.get(tmp_s) == 0) {
                        break;
                    }
                    tmp_map.put(tmp_s, tmp_map.get(tmp_s)-1);
                }
                if(j == len) {
                    res.add(i);
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}