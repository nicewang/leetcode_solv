//Given an array of integers nums sorted in ascending order, find the starting a
//nd ending position of a given target value. 
//
// Your algorithm's runtime complexity must be in the order of O(log n). 
//
// If the target is not found in the array, return [-1, -1]. 
//
// Example 1: 
//
// 
//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4] 
//
// Example 2: 
//
// 
//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1] 
// Related Topics Array Binary Search


package leetcode.leetcode.editor.en;

//Java：Find First and Last Position of Element in Sorted Array
public class P34FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new P34FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        // TO TEST
        int[] nums = {5,7,7,8,8,10};
        solution.searchRange(nums, 8);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int start_index = Integer.MAX_VALUE;
        int end_index = Integer.MIN_VALUE;

        // 解法二：二分查找(二) 0ms 42.9MB
        public int[] searchRange(int[] nums, int target) {

            int[] res = {-1, -1};
            if (nums == null || nums.length == 0) {
                return res;
            }
            int leftIndex = binaryFind(nums, target, true);
            if(leftIndex == nums.length || nums[leftIndex] > target) {
                return res;
            }
            res[0] = leftIndex;
            res[1] = binaryFind(nums, target, false)-1;
            return res;
        }

        private int binaryFind(int[] nums, int target, boolean isLeft) {
            int left = 0, right = nums.length;
            while (left<right) {
                int mid = (left+right)/2;
                if(nums[mid] > target || (isLeft && nums[mid] == target)) {
                    right = mid;
                } else {
                    left = mid+1;
                }
            }
            return left;
        }

        // 解法一：二分查找(一) 1ms 43.1MB
        public int[] searchRangeOne(int[] nums, int target) {

            int[] res = {-1, -1};
            if (nums == null || nums.length == 0) {
                return res;
            }
            binarySerch(nums, target, 0, nums.length - 1);
            if (start_index < Integer.MAX_VALUE) {
                res[0] = start_index;
                res[1] = end_index;
            }
            return res;
        }

        private void binarySerch(int[] nums, int target, int start, int end) {
            int left = start, right = end;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    start_index = Math.min(mid, start_index);
                    end_index = Math.max(mid, end_index);
                    binarySerch(nums, target, left, mid - 1);
                    binarySerch(nums, target, mid + 1, right);
                    return;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}