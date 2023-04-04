//A robot is located at the top-left corner of a m x n grid (marked 'Start' in t
//he diagram below). 
//
// The robot can only move either down or right at any point in time. The robot 
//is trying to reach the bottom-right corner of the grid (marked 'Finish' in the d
//iagram below). 
//
// Now consider if some obstacles are added to the grids. How many unique paths 
//would there be? 
//
// 
//
// An obstacle and empty space is marked as 1 and 0 respectively in the grid. 
//
// Note: m and n will be at most 100. 
//
// Example 1: 
//
// 
//Input:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//Output: 2
//Explanation:
//There is one obstacle in the middle of the 3x3 grid above.
//There are two ways to reach the bottom-right corner:
//1. Right -> Right -> Down -> Down
//2. Down -> Down -> Right -> Right
// 
// Related Topics Array Dynamic Programming


package leetcode.leetcode.editor.en;
//Java：Unique Paths II
public class P63UniquePathsIi{
    public static void main(String[] args) {
        Solution solution = new P63UniquePathsIi().new Solution();
        // TO TEST
        int[][] a = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(solution.uniquePathsWithObstacles(a));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 1ms 40MB
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int dp[][] = new int[m][n];
        for(int i = 0; i < m; i++) {
            dp[i][0] = obstacleGrid[m-1-i][n-1] == 1 ? 0 : 1;
            if(i > 0 && dp[i-1][0] == 0) {
                dp[i][0] = 0;
            }
        }
        for(int j = 0; j < n; j++) {
            dp[0][j] = obstacleGrid[m-1][n-1-j] == 1 ? 0 : 1;
            if(j > 0 && dp[0][j-1] == 0) {
                dp[0][j] = 0;
            }
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = obstacleGrid[m-1-i][n-1-j] == 1 ? 0 : dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}