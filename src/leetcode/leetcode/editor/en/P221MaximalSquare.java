//Given a 2D binary matrix filled with 0's and 1's, find the largest square cont
//aining only 1's and return its area. 
//
// Example: 
//
// 
//Input: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//Output: 4
// Related Topics Dynamic Programming


package leetcode.leetcode.editor.en;
//Java：Maximal Square
public class P221MaximalSquare{
    public static void main(String[] args) {
        Solution solution = new P221MaximalSquare().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        // 解法二：动态规划 7ms 42.9MB
        public int maximalSquare(char[][] matrix) {
            if(matrix == null || matrix.length <= 0) {
                return 0;
            }
            int l1 = matrix.length;
            int l2 = matrix[0].length;
            int maxSide = 0;
            int[][] maxSizeMatrix = new int[l1][l2];
            for(int i = 0; i < l1; i++) {
                for(int j = 0; j < l2; j++) {
//                    maxSizeMatrix(i,j) 表示以 (i,j) 为右下角，且只包含 1 的正方形的边长最大值
//                    如果该位置的值是 0，则 maxSizeMatrix(i, j) = 0dp(i,j)=0，因为当前位置不可能在由 1 组成的正方形中
                    if(matrix[i][j] == '1') {
                        if(i == 0 || j == 0) {
                            maxSizeMatrix[i][j] = 1;
                        } else {
//                            如果该位置的值是 1，则 maxSizeMatrix(i,j) 的值由其上方、左方和左上方的三个相邻位置的 maxSizeMatrix 值决定(取min)
                            maxSizeMatrix[i][j] = Math.min(Math.min(maxSizeMatrix[i-1][j],maxSizeMatrix[i][j-1]), maxSizeMatrix[i-1][j-1])+1;
                        }
                    }
                    maxSide = Math.max(maxSide,maxSizeMatrix[i][j]);
                }
            }
            return maxSide*maxSide;
        }

    // 解法一：暴力法 超时 58/69 case通过
    public int maximalSquareOne(char[][] matrix) {
        if(matrix == null || matrix.length <= 0) {
            return 0;
        }
        int l1 = matrix.length;
        int l2 = matrix[0].length;
        if(l1 > l2) {
            int res = 0;
            for(int i = 0; i <= l1-l2; i++) {
                res = Math.max(res, find(matrix, i, i+l2-1, 0, l2-1));
            }
            return res;
        } else {
            int res = 0;
            for(int i = 0; i <= l2-l1; i++) {
                res = Math.max(res, find(matrix, 0, l1-1, i, i+l1-1));
            }
            return res;
        }
    }
    // i1、i2、j1、j2对应matrix四个顶点坐标
    private int find(char[][] matrix, int i1, int i2, int j1, int j2) {
        if(i1 > i2 || j1 > j2) {
            return 0;
        }
        boolean flag = true;
        for(int i = i1; i <= i2; i++) {
            for(int j = j1; j <= j2; j++) {
                if(matrix[i][j] == '0') {
                    flag = false;
                    break;
                }
            }
            if(!flag) {
                break;
            }
        }
        if(flag) {
            return (i2 - i1 + 1) * (j2 - j1 + 1);
        }
        int res;
        int a1 = find(matrix, i1+1, i2, j1+1, j2);
        int a2 = find(matrix, i1+1, i2, j1, j2-1);
        res = Math.max(a1, a2);
        int a3 = find(matrix, i1, i2-1, j1+1, j2);
        res = Math.max(res, a3);
        int a4 = find(matrix, i1, i2-1, j1, j2-1);
        return Math.max(res, a4);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}