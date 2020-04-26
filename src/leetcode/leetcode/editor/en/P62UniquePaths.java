//A robot is located at the top-left corner of a m x n grid (marked 'Start' in t
//he diagram below). 
//
// The robot can only move either down or right at any point in time. The robot 
//is trying to reach the bottom-right corner of the grid (marked 'Finish' in the d
//iagram below). 
//
// How many possible unique paths are there? 
//
// 
//Above is a 7 x 3 grid. How many possible unique paths are there? 
//
// 
// Example 1: 
//
// 
//Input: m = 3, n = 2
//Output: 3
//Explanation:
//From the top-left corner, there are a total of 3 ways to reach the bottom-righ
//t corner:
//1. Right -> Right -> Down
//2. Right -> Down -> Right
//3. Down -> Right -> Right
// 
//
// Example 2: 
//
// 
//Input: m = 7, n = 3
//Output: 28
// 
//
// 
// Constraints: 
//
// 
// 1 <= m, n <= 100 
// It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9. 
// 
// Related Topics Array Dynamic Programming


package leetcode.leetcode.editor.en;
//Java：Unique Paths
public class P62UniquePaths{
    public static void main(String[] args) {
        Solution solution = new P62UniquePaths().new Solution();
        // TO TEST
        System.out.println(solution.uniquePaths(7,3));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // dp[i][j] = dp[i-1][j] + dp[i][j-1]

    // 解法2: 数组求解
    // 1ms 36.5MB
    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }

        }
        return dp[m-1][n-1];
    }

    // 解法1: 递归求解 超时
    public int uniquePathsOne(int m, int n) {
        if(m == 0 || n == 0) {
            return 0;
        }
        return dp(m - 1,n - 1);
    }
    public int dp(int m, int n) {
        if(m == 0 || n == 0) {
            return 1;
        }
        return dp(m, n - 1) + dp(m - 1, n);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}