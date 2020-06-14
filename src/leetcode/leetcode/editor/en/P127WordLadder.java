//Given two words (beginWord and endWord), and a dictionary's word list, find th
//e length of shortest transformation sequence from beginWord to endWord, such tha
//t: 
//
// 
// Only one letter can be changed at a time. 
// Each transformed word must exist in the word list. 
// 
//
// Note: 
//
// 
// Return 0 if there is no such transformation sequence. 
// All words have the same length. 
// All words contain only lowercase alphabetic characters. 
// You may assume no duplicates in the word list. 
// You may assume beginWord and endWord are non-empty and are not the same. 
// 
//
// Example 1: 
//
// 
//Input:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//Output: 5
//
//Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog
//" -> "cog",
//return its length 5.
// 
//
// Example 2: 
//
// 
//Input:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//Output: 0
//
//Explanation: The endWord "cog" is not in wordList, therefore no possible trans
//formation.
// 
//
// 
// 
// Related Topics Breadth-first Search


package leetcode.leetcode.editor.en;

import javafx.util.Pair;

import java.util.*;

//Java：Word Ladder
public class P127WordLadder {
    public static void main(String[] args) {
        Solution solution = new P127WordLadder().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 1955ms 47.3MB
        // TODO 脑子不昏时写一下双向BFS解法
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (wordList == null || wordList.size() == 0) {
                return 0;
            }
            int l = beginWord.length();
            HashMap<String, List<String>> allComboDict = new HashMap<>();
            wordList.forEach(word -> {
                for (int i = 0; i < l; i++) {
                    String newWord = word.substring(0, i) + "*" + word.substring(i + 1, l);
                    List<String> comboList = allComboDict.getOrDefault(newWord, new ArrayList<>());
                    comboList.add(word);
                    allComboDict.put(newWord, comboList);
                }
            });
            List<String> visited = new ArrayList<>();
            Queue<Pair<String, Integer>> q = new LinkedList<>();
            q.add(new Pair<>(beginWord, 1));
            visited.add(beginWord);
            while (!q.isEmpty()) {
                Pair<String, Integer> p = q.remove();
                String word = p.getKey();
                int level = p.getValue();
                for (int i = 0; i < l; i++) {
                    String newWord = word.substring(0, i) + "*" + word.substring(i + 1, l);
                    for (String adjacent : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                        if (adjacent.equals(endWord)) {
                            return level + 1;
                        }
                        if (!visited.contains(adjacent)) {
                            visited.add(adjacent);
                            q.add(new Pair<>(adjacent, level + 1));
                        }
                    }
                }
            }
            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}