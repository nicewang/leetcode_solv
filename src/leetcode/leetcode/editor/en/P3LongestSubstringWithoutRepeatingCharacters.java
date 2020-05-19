//Given a string, find the length of the longest substring without repeating cha
//racters. 
//
// 
// Example 1: 
//
// 
//Input: "abcabcbb"
//Output: 3 
//Explanation: The answer is "abc", with the length of 3. 
// 
//
// 
// Example 2: 
//
// 
//Input: "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
// 
//
// 
// Example 3: 
//
// 
//Input: "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3. 
//             Note that the answer must be a substring, "pwke" is a subsequence
// and not a substring.
// 
// 
// 
// 
// Related Topics Hash Table Two Pointers String Sliding Window
package leetcode.leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

//Java：Longest Substring Without Repeating Characters
public class P3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new P3LongestSubstringWithoutRepeatingCharacters().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 10ms
        public int lengthOfLongestSubstring(String s) {
            if(s == null || s.length() <= 0) {
                return 0;
            }
            Map<String, Integer> map = new HashMap<>();
            int len = 1;
            map.put(s.substring(0, 1), 0);
            int start = 0;
            for (int i = 1; i < s.length(); i++) {
                String s_now = s.substring(i, i + 1);
                if (map.containsKey(s_now) && map.get(s_now) >= start) {
                    len = Math.max(len, i - start);
                    start = map.get(s_now) + 1;
                }
                map.put(s_now, i);
            }
            len = Math.max(len, s.length() - start);
            return len;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}