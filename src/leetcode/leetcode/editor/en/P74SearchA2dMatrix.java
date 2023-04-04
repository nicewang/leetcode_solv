//Write an efficient algorithm that searches for a value in an m x n matrix. Thi
//s matrix has the following properties: 
//
// 
// Integers in each row are sorted from left to right. 
// The first integer of each row is greater than the last integer of the previou
//s row. 
// 
//
// Example 1: 
//
// 
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//Output: true
// 
//
// Example 2: 
//
// 
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//Output: false 
// Related Topics Array Binary Search


package leetcode.leetcode.editor.en;

//Java：Search a 2D Matrix
public class P74SearchA2dMatrix {
    public static void main(String[] args) {
        Solution solution = new P74SearchA2dMatrix().new Solution();
        int[][] matrix = {{1,3,5}};
        System.out.println(solution.searchMatrix(matrix, 3));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 0ms 39.4MB
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }
            if (matrix[0] == null || matrix[0].length == 0) {
                return false;
            }
            if(matrix.length == 1) {
                return binarySearch(matrix[0], target);
            }
            int l1 = matrix.length, l2 = matrix[0].length;
            if (matrix[0][0] > target || matrix[l1 - 1][l2 - 1] < target) {
                return false;
            }
            if (matrix[0][0] == target || matrix[l1 - 1][l2 - 1] == target) {
                return true;
            }
            for (int i = 1; i < l1; i++) {
                if (matrix[i][0] == target) {
                    return true;
                }
                if (matrix[i][0] > target) {
                    return binarySearch(matrix[i - 1], target);
                }
            }
            return binarySearch(matrix[l1 - 1], target);
        }

        private boolean binarySearch(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}