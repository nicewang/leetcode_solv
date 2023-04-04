//Suppose an array sorted in ascending order is rotated at some pivot unknown to
// you beforehand. 
//
// (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). 
//
// Find the minimum element. 
//
// You may assume no duplicate exists in the array. 
//
// Example 1: 
//
// 
//Input: [3,4,5,1,2] 
//Output: 1
// 
//
// Example 2: 
//
// 
//Input: [4,5,6,7,0,1,2]
//Output: 0
// 
// Related Topics Array Binary Search


package leetcode.leetcode.editor.en;

//Java：Find Minimum in Rotated Sorted Array
public class P153FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new P153FindMinimumInRotatedSortedArray().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 0ms 39.5MB
        public int findMin(int[] nums) {
            return binarySearch(nums, 0, nums.length - 1);
        }

        private int binarySearch(int[] nums, int start, int end) {
            int left = start;
            while (left < end) {
                int mid = (left + end) / 2;
                if (mid == left && nums[left] < nums[end]) {
                    return nums[left];
                }
                if (nums[left] < nums[mid] && nums[mid] < nums[end]) {
                    return nums[left];
                }
                if (nums[left] > nums[end] && nums[end] > nums[mid]) {
                    return binarySearch(nums, left, mid);
                }
                left = mid+1;
            }
            return nums[left];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}