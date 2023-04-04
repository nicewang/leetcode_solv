//Given a m x n grid filled with non-negative numbers, find a path from top left
// to bottom right which minimizes the sum of all numbers along its path. 
//
// Note: You can only move either down or right at any point in time. 
//
// Example: 
//
// 
//Input:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//Output: 7
//Explanation: Because the path 1→3→1→1→1 minimizes the sum.
// 
// Related Topics Array Dynamic Programming


package leetcode.leetcode.editor.en;

//Java：Minimum Path Sum
public class P64MinimumPathSum {
    public static void main(String[] args) {
        Solution solution = new P64MinimumPathSum().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法二：一维动态规划(如果仅使用一维动态规划，一维体现在使用的一维dp数组——其长度为columns)+空间压缩(用grid存储路径和替代使用额外的dp数组)
        // 3ms 42.5MB 时间复杂度O(mn) 空间复杂度O(1)
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int rows = grid.length;
            int columns = grid[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (i == 0 && j > 0) {
                        grid[i][j] += grid[i][j - 1];
                    } else if (i > 0 && j == 0) {
                        grid[i][j] += grid[i - 1][j];
                    } else if (i > 0) {
                        grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                    }
                }
            }
            return grid[rows - 1][columns - 1];
        }

        // 解法一：二维动态规划 3ms 42.4MB 时间复杂度O(mn) 空间复杂度O(mn)
        public int minPathSumOne(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int rows = grid.length;
            int columns = grid[0].length;
            int[][] dp = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (i == 0 && j == 0) {
                        dp[i][j] = grid[i][j];
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1] + grid[i][j];
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][j] + grid[i][j];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                    }
                }
            }
            return dp[rows - 1][columns - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}