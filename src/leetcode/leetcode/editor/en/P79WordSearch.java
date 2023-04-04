//Given a 2D board and a word, find if the word exists in the grid. 
//
// The word can be constructed from letters of sequentially adjacent cell, where
// "adjacent" cells are those horizontally or vertically neighboring. The same let
//ter cell may not be used more than once. 
//
// Example: 
//
// 
//board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//Given word = "ABCCED", return true.
//Given word = "SEE", return true.
//Given word = "ABCB", return false.
// 
//
// 
// Constraints: 
//
// 
// board and word consists only of lowercase and uppercase English letters. 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics Array Backtracking


package leetcode.leetcode.editor.en;

//Java：Word Search
public class P79WordSearch {
    public static void main(String[] args) {
        Solution solution = new P79WordSearch().new Solution();
        // TO TEST
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(solution.exist(board,"ABCCED"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private boolean flag;
        // 解法二：8ms 41.9MB
        public boolean exist(char[][] board, String word) {
            if(board == null || board.length == 0 || board[0].length == 0 ) {
                return false;
            }
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(dfs(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] board, int i, int j, String word, int cur) {
            if(cur == word.length()) {
                flag = true;
                return true;
            }
            if(i < 0 || i >= board.length || j < 0 || j >= board[0].length
                    || board[i][j] != word.charAt(cur)) {
                return false;
            }
            //如果没有找到解，则继续DFS
            if(!flag) {
                char c = board[i][j];
                board[i][j] = '.';
                boolean ret1 = dfs(board, i + 1, j, word, cur + 1);
                boolean ret2 = dfs(board, i - 1, j, word, cur + 1);
                boolean ret3 = dfs(board, i, j + 1, word, cur + 1);
                boolean ret4 = dfs(board, i, j - 1, word, cur + 1);
                board[i][j] = c;
                return ret1 || ret2 || ret3 || ret4;
            }else {
                //找到解了，直接结束DFS并返回，这就是剪枝
                return true;
            }
        }

        // 解法一：递归回溯 超出时间限制
        public boolean existOne(char[][] board, String word) {
            int m = board.length;
            int n = board[0].length;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    boolean[][] visited = new boolean[m][n];
                    if(dfs(board, visited, "", word, i, j, m, n)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] board, boolean[][] visited, String pre, String target, int i, int j, int m, int n) {
            if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
                return false;
            }
            String now = pre + Character.toString(board[i][j]);
            visited[i][j] = true;
            if (now.equals(target)) {
                return true;
            }
            if(dfs(board, visited, now, target, i, j+1, m, n) || dfs(board, visited, now, target, i+1, j, m, n) || dfs(board, visited, now, target, i, j-1, m, n) || dfs(board, visited, now, target, i-1, j, m, n)) {
                return true;
            }
            // 剪枝
            pre = now.substring(0, now.length()-1);
            visited[i][j] = false;
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}