//Suppose an array sorted in ascending order is rotated at some pivot unknown to
// you beforehand. 
//
// (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]). 
//
// You are given a target value to search. If found in the array return true, ot
//herwise return false. 
//
// Example 1: 
//
// 
//Input: nums = [2,5,6,0,0,1,2], target = 0
//Output: true
// 
//
// Example 2: 
//
// 
//Input: nums = [2,5,6,0,0,1,2], target = 3
//Output: false 
//
// Follow up: 
//
// 
// This is a follow up problem to Search in Rotated Sorted Array, where nums may
// contain duplicates. 
// Would this affect the run-time complexity? How and why? 
// 
// Related Topics Array Binary Search


package leetcode.leetcode.editor.en;

//Java：Search in Rotated Sorted Array II
public class P81SearchInRotatedSortedArrayIi {
    public static void main(String[] args) {
        Solution solution = new P81SearchInRotatedSortedArrayIi().new Solution();
        // TO TEST
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(solution.search(nums, 0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 1ms 39.5MB TODO 看精选答案
        public boolean search(int[] nums, int target) {
            return binarySearch(nums, target, 0, nums.length - 1);
        }

        private boolean binarySearch(int[] nums, int target, int start, int end) {
            int left = start, right = end;
            while (left < right) {
                if(nums[left] == target || nums[right] == target) {
                    return true;
                }
                while (left < nums.length - 1 && nums[left] == nums[left + 1]) {
                    left++;
                }
                left++;
                while (right > 0 && nums[right] == nums[right - 1]) {
                    right--;
                }
                right--;
                if (left > right) {
                    return false;
                }
                if (left == right) {
                    return nums[left] == target;
                }
                if (nums[left] == target || nums[right] == target) {
                    return true;
                }
                if(nums[left] == nums[right]) {
                    left--;
                    right++;
                }
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return true;
                }
                if (mid == left && nums[left] < nums[right]) {
                    return nums[right] == target;
                }
                if (nums[left] < nums[mid] && nums[mid] < nums[right]) {
                    return target < nums[mid] ? ordinaryBinarySearch(nums, target, left, mid) : ordinaryBinarySearch(nums, target, mid, right);
                }
                if (nums[left] > nums[right] && nums[right] > nums[mid]) {
                    if (target < nums[right] && target > nums[mid]) {
                        return ordinaryBinarySearch(nums, target, mid, right);
                    }
                    return binarySearch(nums, target, left, mid);
                }
                // nums[mid] >= nums[left] > nums[right]
                if(target < nums[mid] && target > nums[left]) {
                    return ordinaryBinarySearch(nums, target, left, mid);
                }
                left = mid + 1;
            }
            return left < nums.length && nums[left] == target;
        }

        private boolean ordinaryBinarySearch(int[] nums, int target, int start, int end) {
            int left = start, right = end;
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