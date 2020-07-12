//Given a matrix of m x n elements (m rows, n columns), return all elements of t
//he matrix in spiral order. 
//
// Example 1: 
//
// 
//Input:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//Output: [1,2,3,6,9,8,7,4,5]
// 
//
// Example 2: 
// 
//Input:
//[
//  [1, 2, 3, 4],
//  [5, 6, 7, 8],
//  [9,10,11,12]
//]
//Output: [1,2,3,4,8,12,11,10,9,5,6,7]
// Related Topics Array


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

//Java：Spiral Matrix
public class P54SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new P54SpiralMatrix().new Solution();
        // TO TEST
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        solution.spiralOrder(matrix);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 0ms 37.4MB
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            if (matrix == null || matrix.length == 0) {
                return res;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            boolean[][] visited = new boolean[m][n];
            int i = 0, j = 0;
            int cnt = 0, visited_cnt = 1;
            res.add(matrix[0][0]);
            visited[0][0] = true;
            while (true) {
                if (visited_cnt == m * n) {
                    break;
                }
                if (cnt == 0) {
                    // go right
                    if (j + 1 == n || visited[i][j + 1]) {
                        cnt++;
                    } else {
                        j++;
                        res.add(matrix[i][j]);
                        visited[i][j] = true;
                        visited_cnt++;
                    }
                } else if (cnt == 1) {
                    // go down
                    if (i + 1 == m || visited[i + 1][j]) {
                        cnt++;
                    } else {
                        i++;
                        res.add(matrix[i][j]);
                        visited[i][j] = true;
                        visited_cnt++;
                    }
                } else if (cnt == 2) {
                    // go left
                    if (j - 1 < 0 || visited[i][j - 1]) {
                        cnt++;
                    } else {
                        j--;
                        res.add(matrix[i][j]);
                        visited[i][j] = true;
                        visited_cnt++;
                    }
                } else {
                    // go up
                    if (i - 1 < 0 || visited[i - 1][j]) {
                        cnt = 0;
                    } else {
                        i--;
                        res.add(matrix[i][j]);
                        visited[i][j] = true;
                        visited_cnt++;
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}