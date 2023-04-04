//A peak element is an element that is greater than its neighbors. 
//
// Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and
// return its index. 
//
// The array may contain multiple peaks, in that case return the index to any on
//e of the peaks is fine. 
//
// You may imagine that nums[-1] = nums[n] = -∞. 
//
// Example 1: 
//
// 
//Input: nums = [1,2,3,1]
//Output: 2
//Explanation: 3 is a peak element and your function should return the index num
//ber 2. 
//
// Example 2: 
//
// 
//Input: nums = [1,2,1,3,5,6,4]
//Output: 1 or 5 
//Explanation: Your function can return either index number 1 where the peak ele
//ment is 2, 
//             or index number 5 where the peak element is 6.
// 
//
// Note: 
//
// Your solution should be in logarithmic complexity. 
// Related Topics Array Binary Search


package leetcode.leetcode.editor.en;

//Java：Find Peak Element
public class P162FindPeakElement {
    public static void main(String[] args) {
        Solution solution = new P162FindPeakElement().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：二分查找 0ms 39.4MB
        public int findPeakElement(int[] nums) {
            int l = 0, r = nums.length - 1;
            while(l < r) {
                int mid = (l + r) / 2;
                if(nums[mid] > nums[mid+1]) {
                    // 已经开始下降
                    r = mid;
                } else {
                    // 还在上升／持平
                    l = mid + 1;
                }

            }
            return l;
        }

        // 0ms 39.2MB
        public int findPeakElementOne(int[] nums) {
            int max = nums[0];
            int peek_index = 0;
            for (int i = 1; i < nums.length; i++) {
                if(nums[i] > max) {
                    max = nums[i];
                    peek_index = i;
                }
            }
            return peek_index;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}