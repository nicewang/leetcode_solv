//Design a data structure that supports the following two operations: 
//
// 
//void addWord(word)
//bool search(word)
// 
//
// search(word) can search a literal word or a regular expression string contain
//ing only letters a-z or .. A . means it can represent any one letter. 
//
// Example: 
//
// 
//addWord("bad")
//addWord("dad")
//addWord("mad")
//search("pad") -> false
//search("bad") -> true
//search(".ad") -> true
//search("b..") -> true
// 
//
// Note: 
//You may assume that all words are consist of lowercase letters a-z. 
// Related Topics Backtracking Design Trie


package leetcode.leetcode.editor.en;

//Java：Add and Search Word - Data structure design
public class P211AddAndSearchWordDataStructureDesign {
    public static void main(String[] args) {
        WordDictionary solution = new P211AddAndSearchWordDataStructureDesign().new WordDictionary();
        // TO TEST
//        solution.addWord("ran");
        solution.addWord("rune");
        solution.addWord("runs");
        solution.addWord("runner");
//        solution.addWord("add");
//        solution.addWord("adds");
//        solution.addWord("adder");
//        solution.addWord("addee");
        System.out.println(solution.search("..n.r"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 55ms 50.1MB
    class WordDictionary {

        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new TrieNode();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                if (!node.containsKey(currentChar)) {
                    node.put(currentChar, new TrieNode());
                }
                node = node.get(currentChar);
            }
            node.setEnd();
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return dfs(0, word.length(), word, root);
        }

        private boolean dfs(int index, int limit, String word, TrieNode node) {
            if (index == limit) {
                return node.isEnd();
            }
            if (word.charAt(index) != '.' && node.get(word.charAt(index)) != null) {
                return dfs(index + 1, limit, word, node.get(word.charAt(index)));
            } else if (word.charAt(index) != '.') {
                return false;
            }
            for (int i = 0; i < 26; i++) {
                char ch = (char) ('a' + i);
                if (node.get(ch) == null) {
                    continue;
                }
                if (word.charAt(index) != '.') {
                    if (dfs(index, limit, word, node.get(ch))) {
                        return true;
                    }
                } else {
                    if (dfs(index + 1, limit, word, node.get(ch))) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    class TrieNode {
        // R links to node children
        private TrieNode[] links;

        private final int R = 26;

        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd() {
            this.isEnd = true;
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)

}