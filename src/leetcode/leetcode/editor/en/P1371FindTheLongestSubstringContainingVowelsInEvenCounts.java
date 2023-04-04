//Given the string s, return the size of the longest substring containing each v
//owel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear a
//n even number of times. 
//
// 
// Example 1: 
//
// 
//Input: s = "eleetminicoworoep"
//Output: 13
//Explanation: The longest substring is "leetminicowor" which contains two each 
//of the vowels: e, i and o and zero of the vowels: a and u.
// 
//
// Example 2: 
//
// 
//Input: s = "leetcodeisgreat"
//Output: 5
//Explanation: The longest substring is "leetc" which contains two e's.
// 
//
// Example 3: 
//
// 
//Input: s = "bcbcbc"
//Output: 6
//Explanation: In this case, the given string "bcbcbc" is the longest because al
//l vowels: a, e, i, o and u appear zero times.
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 5 x 10^5 
// s contains only lowercase English letters. 
// Related Topics String


package leetcode.leetcode.editor.en;

import java.util.Arrays;

//Java：Find the Longest Substring Containing Vowels in Even Counts
public class P1371FindTheLongestSubstringContainingVowelsInEvenCounts {
    public static void main(String[] args) {
        Solution solution = new P1371FindTheLongestSubstringContainingVowelsInEvenCounts().new Solution();
        // TO TEST
        System.out.println(solution.findTheLongestSubstring("eleetminicoworoep"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解题思路：前缀和+状态压缩 14ms 44.2MB TODO 前缀和 多看几遍 后续再来理解(今天理解不动了)
        public int findTheLongestSubstring(String s) {
            int n = s.length();
            int[] pos = new int[1 << 5];
            Arrays.fill(pos, -1);
            int ans = 0, status = 0;
            pos[0] = 0;
            for (int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                if (ch == 'a') {
                    status ^= (1 << 0);
                } else if (ch == 'e') {
                    status ^= (1 << 1);
                } else if (ch == 'i') {
                    status ^= (1 << 2);
                } else if (ch == 'o') {
                    status ^= (1 << 3);
                } else if (ch == 'u') {
                    status ^= (1 << 4);
                }
                if (pos[status] >= 0) {
                    ans = Math.max(ans, i + 1 - pos[status]);
                } else {
                    pos[status] = i + 1;
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}