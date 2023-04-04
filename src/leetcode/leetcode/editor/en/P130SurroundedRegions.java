//Given a 2D board containing 'X' and 'O' (the letter O), capture all regions su
//rrounded by 'X'. 
//
// A region is captured by flipping all 'O's into 'X's in that surrounded region
//. 
//
// Example: 
//
// 
//X X X X
//X O O X
//X X O X
//X O X X
// 
//
// After running your function, the board should be: 
//
// 
//X X X X
//X X X X
//X X X X
//X O X X
// 
//
// Explanation: 
//
// Surrounded regions shouldn’t be on the border, which means that any 'O' on th
//e border of the board are not flipped to 'X'. Any 'O' that is not on the border 
//and it is not connected to an 'O' on the border will be flipped to 'X'. Two cell
//s are connected if they are adjacent cells connected horizontally or vertically.
// 
// Related Topics Depth-first Search Breadth-first Search Union Find


package leetcode.leetcode.editor.en;

import java.util.LinkedList;
import java.util.Stack;

//Java：Surrounded Regions
public class P130SurroundedRegions{
    public static void main(String[] args) {
        Solution solution = new P130SurroundedRegions().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        // TODO 并查集解法

        // 解法三：bfs 非递归
        // 3ms 41.7MB
        public void solveThree(char[][] board) {
            if(board == null || board.length == 0 || board[0].length == 0) {
                return;
            }
            int m = board.length;
            int n = board[0].length;
            LinkedList<Integer> list_i = new LinkedList<>();
            LinkedList<Integer> list_j = new LinkedList<>();
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if((i == 0 || j == 0 || i == m-1 || j == n-1) && (board[i][j] == 'O')) {
                        // 给在四边的'O'及与它们相连的'O'填上'#'
                        board[i][j] = '#';
                        list_i.add(i);
                        list_j.add(j);
                        while(!list_i.isEmpty()) {
                            int i1 = list_i.peek();
                            int j1 = list_j.peek();
                            // 上
                            if(i1 > 0 && board[i1-1][j1] == 'O') {
                                list_i.add(i1-1);
                                list_j.add(j1);
                                board[i1-1][j1] = '#';
                                // 对比dfs的解法 由于队列是先进先出 故没有continue
                            }
                            // 下
                            if(i1 < m-1 && board[i1+1][j1] == 'O') {
                                list_i.add(i1+1);
                                list_j.add(j1);
                                board[i1+1][j1] = '#';
                            }
                            // 左
                            if(j1 > 0 && board[i1][j1-1] == 'O') {
                                list_i.add(i1);
                                list_j.add(j1-1);
                                board[i1][j1-1] = '#';
                            }
                            // 右
                            if(j1 < n-1 && board[i1][j1+1] == 'O') {
                                list_i.add(i1);
                                list_j.add(j1+1);
                                board[i1][j1+1] = '#';
                            }
                            // 如果上下左右都搜索不到,本次搜索结束，弹出stack
                            list_i.remove();
                            list_j.remove();
                        }
                    }
                }
            }
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    // attention: 这两个if的顺序不能颠倒
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == '#') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        // 解法二：dfs 非递归
        // 4ms 42.8MB
        public void solveTwo(char[][] board) {
            if(board == null || board.length == 0 || board[0].length == 0) {
                return;
            }
            int m = board.length;
            int n = board[0].length;
            Stack<Integer> stack_i = new Stack<>();
            Stack<Integer> stack_j = new Stack<>();
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if((i == 0 || j == 0 || i == m-1 || j == n-1) && (board[i][j] == 'O')) {
                        // 给在四边的'O'及与它们相连的'O'填上'#'
                        board[i][j] = '#';
                        stack_i.push(i);
                        stack_j.push(j);
                        while(!stack_i.isEmpty()) {
                            int i1 = stack_i.peek();
                            int j1 = stack_j.peek();
                            // 上
                            if(i1 > 0 && board[i1-1][j1] == 'O') {
                                stack_i.push(i1-1);
                                stack_j.push(j1);
                                board[i1-1][j1] = '#';
                                continue;
                            }
                            // 下
                            if(i1 < m-1 && board[i1+1][j1] == 'O') {
                                stack_i.push(i1+1);
                                stack_j.push(j1);
                                board[i1+1][j1] = '#';
                                continue;
                            }
                            // 左
                            if(j1 > 0 && board[i1][j1-1] == 'O') {
                                stack_i.push(i1);
                                stack_j.push(j1-1);
                                board[i1][j1-1] = '#';
                                continue;
                            }
                            // 右
                            if(j1 < n-1 && board[i1][j1+1] == 'O') {
                                stack_i.push(i1);
                                stack_j.push(j1+1);
                                board[i1][j1+1] = '#';
                                continue;
                            }
                            // 如果上下左右都搜索不到,本次搜索结束，弹出stack
                            stack_i.pop();
                            stack_j.pop();
                        }
                    }
                }
            }
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    // attention: 这两个if的顺序不能颠倒
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == '#') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

    // 解法一：dfs 递归
    // 2ms 41.9MB
    public void solveOne(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if((i == 0 || j == 0 || i == m-1 || j == n-1) && (board[i][j] == 'O')) {
                    // 给在四边的'O'及与它们相连的'O'填上'#'
                    dfs(i, j, board);
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // attention: 这两个if的顺序不能颠倒
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    private void dfs(int i, int j, char[][] board) {
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        board[i][j] = '#';
        dfs(i-1, j, board);
        dfs(i+1, j, board);
        dfs(i, j-1, board);
        dfs(i, j+1, board);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}