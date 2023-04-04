//Write an efficient algorithm that searches for a value in an m x n matrix. Thi
//s matrix has the following properties: 
//
// 
// Integers in each row are sorted in ascending from left to right. 
// Integers in each column are sorted in ascending from top to bottom. 
// 
//
// Example: 
//
// Consider the following matrix: 
//
// 
//[
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// Given target = 5, return true. 
//
// Given target = 20, return false. 
// Related Topics Binary Search Divide and Conquer


package leetcode.leetcode.editor.en;

//Java：Search a 2D Matrix II
public class P240SearchA2dMatrixIi {
    public static void main(String[] args) {
        Solution solution = new P240SearchA2dMatrixIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 12ms 45.2MB
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }
            int l1 = matrix.length, l2 = matrix[0].length;
            // 从左上直角开始 一层层往里剥
            for (int i = 0; i < Math.min(l1, l2); i++) {
                boolean findVertical = binarySearchMatrix(matrix, i, target, true);
                boolean findHorizon = binarySearchMatrix(matrix, i, target, false);
                if (findHorizon || findVertical) {
                    return true;
                }
            }
            return false;
        }

        private boolean binarySearchMatrix(int[][] matrix, int start, int target, boolean vertical) {
            int left = start;
            int right = vertical ? matrix[0].length - 1 : matrix.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (vertical) {
                    if (matrix[start][mid] == target) {
                        return true;
                    } else if (matrix[start][mid] < target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    if (matrix[mid][start] == target) {
                        return true;
                    } else if (matrix[mid][start] < target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}