//The n-queens puzzle is the problem of placing n queens on an n×n chessboard su
//ch that no two queens attack each other. 
//
// 
//
// Given an integer n, return all distinct solutions to the n-queens puzzle. 
//
// Each solution contains a distinct board configuration of the n-queens' placem
//ent, where 'Q' and '.' both indicate a queen and an empty space respectively. 
//
// Example: 
//
// 
//Input: 4
//Output: [
// [".Q..",  // Solution 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // Solution 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//Explanation: There exist two distinct solutions to the 4-queens puzzle as show
//n above.
// 
// Related Topics Backtracking


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：N-Queens
public class P51NQueens {
    public static void main(String[] args) {
        Solution solution = new P51NQueens().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法一：回溯 16ms 40.6MB
        Stack<Integer> stack = new Stack<>();
        List<List<String>> res = new ArrayList<>();
        List<List<Integer>> mark = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            dfs(0, n);
            return res;
        }

        private void dfs(int depth, int n) {
            if (depth == n) {
                List<Integer> list = new ArrayList<>(stack);
                List<String> sub_res = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    StringBuffer sb = new StringBuffer();
                    for (int j = 0; j < n; j++) {
                        if (list.get(i) == j) {
                            sb.append("Q");
                        } else {
                            sb.append(".");
                        }
                    }
                    sub_res.add(sb.toString());
                }
                res.add(sub_res);
                return;
            }
            for (int i = 0; i < n; i++) {
                if (!stack.isEmpty() && stack.contains(i)) {
                    continue;
                }
                List<Integer> l = new ArrayList<>();
                for (int i1 = 0; i1 < depth; i1++) {
                    // TODO stack get出来的和入栈顺序有什么关系
                    int tmp = stack.get(i1);
                    int tmp_l = tmp - (depth - i1);
                    int tmp_r = tmp + (depth - i1);
                    if (tmp_l >= 0 && !l.contains(tmp_l)) {
                        l.add(tmp_l);
                    }
                    if (tmp_r < n && !l.contains(tmp_r)) {
                        l.add(tmp_r);
                    }
                }
                if (l.contains(i)) {
                    continue;
                }
                stack.push(i);
                dfs(depth + 1, n);
                stack.pop();
            }
        }

        // 解法二：本质也是回溯的思想 多加了三个数组存放列、主对角线、次对角线
        // 4ms 39.8MB
        // 时间复杂度 O(N!) 空间复杂度 O(N)
        private List<List<String>> output = new ArrayList<>();
        // 用于标记是否被列方向的皇后被攻击
        int[] rows;
        // 用于标记是否被主对角线方向的皇后攻击
        int[] mains;
        // 用于标记是否被次对角线方向的皇后攻击
        int[] secondary;
        // 用于存储皇后放置的位置
        int[] queens;
        int n;
        public List<List<String>> solveNQueensTwo(int n) {
            // 初始化
            rows = new int[n];
            mains = new int[2 * n - 1];
            secondary = new int[2 * n - 1];
            queens = new int[n];
            this.n = n;

            // 从第一行开始回溯求解 N 皇后
            backtrack(0);

            return output;
        }

        // 在一行中放置一个皇后
        private void backtrack(int row) {
            if (row >= n) return;
            // 分别尝试在 row 行中的每一列中放置皇后
            for (int col = 0; col < n; col++) {
                // 判断当前放置的皇后不被其他皇后的攻击
                if (isNotUnderAttack(row, col)) {
                    // 选择，在当前的位置上放置皇后
                    placeQueen(row, col);
                    // 当当前行是最后一行，则找到了一个解决方案
                    if (row == n - 1) addSolution();
                    // 在下一行中放置皇后
                    backtrack(row + 1);
                    // 撤销，回溯，即将当前位置的皇后去掉
                    removeQueen(row, col);
                }
            }
        }

        // 判断 row 行，col 列这个位置有没有被其他方向的皇后攻击
        private boolean isNotUnderAttack(int row, int col) {
            // 判断的逻辑是：
            //      1. 当前位置的这一列方向没有皇后攻击
            //      2. 当前位置的主对角线方向没有皇后攻击
            //      3. 当前位置的次对角线方向没有皇后攻击
            int res = rows[col] + mains[row - col + n - 1] + secondary[row + col];
            // 如果三个方向都没有攻击的话，则 res = 0，即当前位置不被任何的皇后攻击
            return res == 0;
        }

        // 在指定的位置上放置皇后
        private void placeQueen(int row, int col) {
            // 在 row 行，col 列 放置皇后
            queens[row] = col;
            // 当前位置的列方向已经有皇后了
            rows[col] = 1;
            // 当前位置的主对角线方向已经有皇后了
            mains[row - col + n - 1] = 1;
            // 当前位置的次对角线方向已经有皇后了
            secondary[row + col] = 1;
        }

        // 移除指定位置上的皇后
        private void removeQueen(int row, int col) {
            // 移除 row 行上的皇后
            queens[row] = 0;
            // 当前位置的列方向没有皇后了
            rows[col] = 0;
            // 当前位置的主对角线方向没有皇后了
            mains[row - col + n - 1] = 0;
            // 当前位置的次对角线方向没有皇后了
            secondary[row + col] = 0;
        }

        /**
         * 将满足条件的皇后位置放入output中
         */
        public void addSolution() {
            List<String> solution = new ArrayList<String>();
            for (int i = 0; i < n; ++i) {
                int col = queens[i];
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < col; ++j) sb.append(".");
                sb.append("Q");
                for (int j = 0; j < n - col - 1; ++j) sb.append(".");
                solution.add(sb.toString());
            }
            output.add(solution);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}