//Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle c
//ontaining only 1's and return its area. 
//
// Example: 
//
// 
//Input:
//[
//  ["1","0","1","0","0"],
//  ["1","0","1","1","1"],
//  ["1","1","1","1","1"],
//  ["1","0","0","1","0"]
//]
//Output: 6
// 
// Related Topics Array Hash Table Dynamic Programming Stack


package leetcode.leetcode.editor.en;

import java.util.Arrays;

//Java：Maximal Rectangle
public class P85MaximalRectangle {
    public static void main(String[] args) {
        Solution solution = new P85MaximalRectangle().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：动态规划解法二 3ms 43.3MB 时间复杂度O(NM) 空间复杂度O(M)
        public int maximalRectangle(char[][] matrix) {
            if(matrix == null) {
                return 0;
            }
            if(matrix.length == 0) {
                return 0;
            }
            int m = matrix.length, n = matrix[0].length;
            int[] height = new int[n];
            int[] left = new int[n];
            int[] right = new int[n];
            Arrays.fill(right, n); // TODO attention "n"
            int maxArea = 0;
            for(int i = 0; i < m; i++) {
                int cur_left =0, cur_right = n;
                for(int j = 0; j < n; j++) {
                    if(matrix[i][j] == '1') {
                        height[j]++;
                    } else {
                        height[j] = 0;
                    }
                }
                for(int j = 0; j < n; j++) {
                    if(matrix[i][j] == '1') {
                        left[j] = Math.max(left[j], cur_left);
                    } else {
                        left[j] = 0;
                        cur_left = j+1;
                    }
                }
                for(int j = n-1; j >= 0; j--) {
                    if(matrix[i][j] == '1') {
                        right[j] = Math.min(right[j], cur_right);
                    } else {
                        right[j] = n; // TODO attention "n"
                        cur_right = j;
                    }
                }
                for(int j = 0; j < n; j++) {
                    maxArea = Math.max(maxArea, height[j]*(right[j]-left[j]));
                }
            }
            return maxArea;
        }

        // 解法一：动态规划解法一 20ms 42.8MB 时间复杂度O(N^2*M) 空间复杂度O(NM)
        public int maximalRectangleOne(char[][] matrix) {
            if(matrix == null) {
                return 0;
            }
            if(matrix.length == 0) {
                return 0;
            }
            int m = matrix.length, n = matrix[0].length;
            int[][] dp = new int[m][n];
            int maxArea = 0;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(matrix[i][j] == '1') {
                        dp[i][j] = j == 0 ? 1 : dp[i][j-1]+1;
                    } else {
                        dp[i][j] = 0;
                    }
                    int width = dp[i][j];
                    for(int k = i; k >=0; k--) {
                        width = Math.min(width, dp[k][j]);
                        maxArea = Math.max(maxArea, width*(i-k+1));
                    }
                }
            }
            return maxArea;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}