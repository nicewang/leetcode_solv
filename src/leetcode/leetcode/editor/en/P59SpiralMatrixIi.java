//Given a positive integer n, generate a square matrix filled with elements from
// 1 to n2 in spiral order. 
//
// Example: 
//
// 
//Input: 3
//Output:
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//]
// 
// Related Topics Array


package leetcode.leetcode.editor.en;

//Java：Spiral Matrix II
public class P59SpiralMatrixIi {
    public static void main(String[] args) {
        Solution solution = new P59SpiralMatrixIi().new Solution();
        // TO TEST
        solution.generateMatrix(3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 0ms 37.8MB
        public int[][] generateMatrix(int n) {
            int[][] res = new int[n][n];
            if (n == 0) {
                return res;
            }
            boolean[][] visited = new boolean[n][n];
            int i = 0, j = 0;
            int cnt = 0;
            res[0][0] = 1;
            visited[0][0] = true;
            for (int k = 2; k <= n * n; k++) {
                if (cnt == 0) {
                    // go right
                    if (j + 1 == n || visited[i][j + 1]) {
                        cnt++;
                        k--;
                    } else {
                        j++;
                        res[i][j] = k;
                        visited[i][j] = true;
                    }
                } else if (cnt == 1) {
                    // go down
                    if (i + 1 == n || visited[i + 1][j]) {
                        cnt++;
                        k--;
                    } else {
                        i++;
                        res[i][j] = k;
                        visited[i][j] = true;
                    }
                } else if (cnt == 2) {
                    // go left
                    if (j - 1 < 0 || visited[i][j - 1]) {
                        cnt++;
                        k--;
                    } else {
                        j--;
                        res[i][j] = k;
                        visited[i][j] = true;
                    }
                } else {
                    // go up
                    if (i - 1 < 0 || visited[i - 1][j]) {
                        cnt = 0;
                        k--;
                    } else {
                        i--;
                        res[i][j] = k;
                        visited[i][j] = true;
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}