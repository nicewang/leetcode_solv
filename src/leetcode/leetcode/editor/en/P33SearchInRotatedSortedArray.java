//Suppose an array sorted in ascending order is rotated at some pivot unknown to
// you beforehand. 
//
// (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). 
//
// You are given a target value to search. If found in the array return its inde
//x, otherwise return -1. 
//
// You may assume no duplicate exists in the array. 
//
// Your algorithm's runtime complexity must be in the order of O(log n). 
//
// Example 1: 
//
// 
//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: nums = [4,5,6,7,0,1,2], target = 3
//Output: -1 
// Related Topics Array Binary Search


package leetcode.leetcode.editor.en;

//Java：Search in Rotated Sorted Array
public class P33SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new P33SearchInRotatedSortedArray().new Solution();
        int[] nums = {5, 1, 2, 3, 4};
        System.out.println(solution.search(nums, 1));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int index = -1;
        // 0ms 39.3MB TODO 看官方题解
        public int search(int[] nums, int target) {
            binarySearch(nums, target, 0, nums.length - 1);
            return index;
        }

        private boolean binarySearch(int[] nums, int target, int start, int end) {
            int left = start, right = end;
            while (left < right) {
                if (nums[left] == target || nums[right] == target) {
                    if (nums[left] == target) {
                        index = left;
                    } else {
                        index = right;
                    }
                    return true;
                }
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    index = mid;
                    return true;
                }
                if (mid == left && nums[left] < nums[right]) {
                    if (nums[right] == target) {
                        index = right;
                    }
                    return nums[right] == target;
                }
                if (nums[left] < nums[mid] && nums[mid] < nums[right]) {
                    return target < nums[mid] ? ordinaryBinarySearch(nums, target, left, mid) :
                            ordinaryBinarySearch(nums, target, mid, right);
                }
                if (nums[left] > nums[right] && nums[right] > nums[mid]) {
                    if (target < nums[right] && target > nums[mid]) {
                        return ordinaryBinarySearch(nums, target, mid, right);
                    }
                    return binarySearch(nums, target, left, mid);
                }
                // nums[mid] >= nums[left] > nums[right]
                if (target < nums[mid] && target > nums[left]) {
                    return ordinaryBinarySearch(nums, target, left, mid);
                }
                left = mid + 1;
            }
            if (left < nums.length && nums[left] == target) {
                index = left;
            }
            return left < nums.length && nums[left] == target;
        }

        private boolean ordinaryBinarySearch(int[] nums, int target, int start, int end) {
            int left = start, right = end;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    index = mid;
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