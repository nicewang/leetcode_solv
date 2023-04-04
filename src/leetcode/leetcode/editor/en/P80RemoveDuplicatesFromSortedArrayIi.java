//Given a sorted array nums, remove the duplicates in-place such that duplicates
// appeared at most twice and return the new length. 
//
// Do not allocate extra space for another array, you must do this by modifying 
//the input array in-place with O(1) extra memory. 
//
// Example 1: 
//
// 
//Given nums = [1,1,1,2,2,3],
//
//Your function should return length = 5, with the first five elements of nums b
//eing 1, 1, 2, 2 and 3 respectively.
//
//It doesn't matter what you leave beyond the returned length. 
//
// Example 2: 
//
// 
//Given nums = [0,0,1,1,1,1,2,3,3],
//
//Your function should return length = 7, with the first seven elements of nums 
//being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
//
//It doesn't matter what values are set beyond the returned length.
// 
//
// Clarification: 
//
// Confused why the returned value is an integer but your answer is an array? 
//
// Note that the input array is passed in by reference, which means modification
// to the input array will be known to the caller as well. 
//
// Internally you can think of this: 
//
// 
//// nums is passed in by reference. (i.e., without making a copy)
//int len = removeDuplicates(nums);
//
//// any modification to nums in your function would be known by the caller.
//// using the length returned by your function, it prints the first len element
//s.
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
// 
// Related Topics Array Two Pointers


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

//Java：Remove Duplicates from Sorted Array II
public class P80RemoveDuplicatesFromSortedArrayIi {
    public static void main(String[] args) {
        Solution solution = new P80RemoveDuplicatesFromSortedArrayIi().new Solution();
        // TO TEST
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.print(solution.removeDuplicates(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 1ms 40.5MB
        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length <= 0) {
                return 0;
            }
            List<Integer> list = new ArrayList<>();
            int res = nums.length;
            int count = 0;
            list.add(nums[0]);
            int j = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    count++;
                } else {
                    count = 0;
                }
                if(count <= 1) {
                    nums[j++] = nums[i];
                } else {
                    res -= 1;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}