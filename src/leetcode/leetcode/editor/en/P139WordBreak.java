//Given a non-empty string s and a dictionary wordDict containing a list of non-
//empty words, determine if s can be segmented into a space-separated sequence of 
//one or more dictionary words. 
//
// Note: 
//
// 
// The same word in the dictionary may be reused multiple times in the segmentat
//ion. 
// You may assume the dictionary does not contain duplicate words. 
// 
//
// Example 1: 
//
// 
//Input: s = "leetcode", wordDict = ["leet", "code"]
//Output: true
//Explanation: Return true because "leetcode" can be segmented as "leet code".
// 
//
// Example 2: 
//
// 
//Input: s = "applepenapple", wordDict = ["apple", "pen"]
//Output: true
//Explanation: Return true because "applepenapple" can be segmented as "apple pe
//n apple".
//             Note that you are allowed to reuse a dictionary word.
// 
//
// Example 3: 
//
// 
//Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//Output: false
// 
// Related Topics Dynamic Programming


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Java：Word Break
public class P139WordBreak {
    public static void main(String[] args) {
        Solution solution = new P139WordBreak().new Solution();
        List<String> wordDict = new ArrayList<>();
        wordDict.add("aaaa");
        wordDict.add("aaa");
        System.out.println(solution.wordBreak("aaaaaaa", wordDict));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法四：动态规划 10ms 40.1MB 时间复杂度O(n^2) 空间复杂度O(n)
        public boolean wordBreak(String s, List<String> wordDict) {
            // 字符串长度为i的子串能通过wordDict拼接出来
            boolean[] dp = new boolean[s.length()+1];
            dp[0] = true;
            for(int i = 1; i <= s.length(); i++) {
                for(int j = 0; j < i; j++) {
                    if(dp[j] && wordDict.contains(s.substring(j,i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }

        // 解法三：广度优先搜索 13ms 40.3MB 时间复杂度O(n^2) 空间复杂度O(n)
        public boolean wordBreakThree(String s, List<String> wordDict) {
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(0);
            boolean[] visited = new boolean[s.length()];
            while(!queue.isEmpty()) {
                int start = queue.remove();
                if(!visited[start]) {
                    // 枚举start开头的所有可能的单词 并把单词结尾的下一个字母加入队列
                    for(int i = start; i <= s.length(); i++) {
                        if(wordDict.contains(s.substring(start, i))) {
                            queue.add(i);
                            if(i == s.length()) {
                                return true;
                            }
                        }
                    }
                    visited[start] = true;
                }
            }
            return false;
        }

        // 解法二：记忆化回溯 超时 8ms 39.8MB 时间复杂度O(n^2) 空间复杂度O(n)
        // 在解法一——暴力回溯解法的基础上，一个memo数组会被用来保存子问题的结果
        public boolean wordBreakTwo(String s, List<String> wordDict) {
            return searchOne(s, wordDict, 0, new Boolean[s.length()]);
        }

        private boolean searchOne(String s, List<String> wordDict, int start, Boolean[] memo) {
            if(start == s.length()) {
                return true;
            }
            // TODO 注意这一步 省去了很多无用迭代
            // TODO 每当访问到已经访问过的后缀串，直接用memo数组中的值返回而不需要继续调用函数
            if (memo[start] != null) {
                return memo[start];
            }
            for(int end = start+1; end <= s.length(); end++) {
                if(wordDict.contains(s.substring(start, end)) && searchOne(s, wordDict, end, memo)) {
                    memo[start] = true;
                    return true;
                }
            }
            memo[start] = false;
            return false;
        }

        // 解法一：递归暴力搜索(用到了回溯的思想) 超时 通过用例数29／36 时间复杂度O(n^n) 空间复杂度O(n)
        public boolean wordBreakOne(String s, List<String> wordDict) {
            return search(s, wordDict, 0, 1);
        }
        private boolean search(String s, List<String> wordDict, int start, int end) {
            if(start == s.length()) {
                return true;
            }
            for(int i = end; i <= s.length(); i++) {
                if(wordDict.contains(s.substring(start, i)) && search(s, wordDict, i, i+1)) {
                    return true;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}