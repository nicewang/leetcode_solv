//Given an array of strings, group anagrams together. 
//
// Example: 
//
// 
//Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
//Output:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// Note: 
//
// 
// All inputs will be in lowercase. 
// The order of your output does not matter. 
// 
// Related Topics Hash Table String


package leetcode.leetcode.editor.en;

import com.sun.javafx.collections.MappingChange;

import java.util.*;

//Java：Group Anagrams
public class P49GroupAnagrams {
    public static void main(String[] args) {
        Solution solution = new P49GroupAnagrams().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：按计数分类 21ms 43.7MB
        public List<List<String>> groupAnagrams(String[] strs) {
            if (strs.length == 0) return new ArrayList();
            Map<String, List> ans = new HashMap<String, List>();
            int[] count = new int[26];
            for (String s : strs) {
                Arrays.fill(count, 0);
                for (char c : s.toCharArray()) count[c - 'a']++;

                StringBuilder sb = new StringBuilder("");
                for (int i = 0; i < 26; i++) {
                    sb.append('#');
                    sb.append(count[i]);
                }
                String key = sb.toString();
                if (!ans.containsKey(key)) ans.put(key, new ArrayList());
                ans.get(key).add(s);
            }
            return new ArrayList(ans.values());
        }

        // 9ms 42.6MB 解法一：排序字符串分类
        public List<List<String>> groupAnagramsOne(String[] strs) {
            List<List<String>> list = new ArrayList<>();
            Map<String, List<String>> map = new HashMap<>();
            for (int i = 0; i < strs.length; i++) {
                String tmp = strs[i];
                char[] chars = tmp.toCharArray();
                Arrays.sort(chars);
                String tmp_new = String.valueOf(chars);
                if(map.containsKey(tmp_new)) {
                    map.get(tmp_new).add(tmp);
                } else {
                    List<String> listNow = new ArrayList<>();
                    listNow.add(tmp);
                    map.put(tmp_new, listNow);
                }
            }
            list = new ArrayList<>(map.values());
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}