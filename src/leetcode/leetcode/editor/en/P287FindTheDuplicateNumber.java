//Given an array nums containing n + 1 integers where each integer is between 1 
//and n (inclusive), prove that at least one duplicate number must exist. Assume t
//hat there is only one duplicate number, find the duplicate one. 
//
// Example 1: 
//
// 
//Input: [1,3,4,2,2]
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: [3,1,3,4,2]
//Output: 3 
//
// Note: 
//
// 
// You must not modify the array (assume the array is read only). 
// You must use only constant, O(1) extra space. 
// Your runtime complexity should be less than O(n2). 
// There is only one duplicate number in the array, but it could be repeated mor
//e than once. 
// 
// Related Topics Array Two Pointers Binary Search


package leetcode.leetcode.editor.en;

import java.util.Arrays;

//Java：Find the Duplicate Number
public class P287FindTheDuplicateNumber {
    public static void main(String[] args) {
        Solution solution = new P287FindTheDuplicateNumber().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 3ms 39.8MB
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums);
            int res = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (res == nums[i]) {
                    return res;
                }
                res = nums[i];
            }
            return res;
        }

        // TODO 弗洛伊德的乌龟和兔子（循环检测）0ms 40MB 理解一下
        public int findDuplicateHareAndTortoise(int[] nums) {
            // Find the intersection point of the two runners.
            int tortoise = nums[0];
            int hare = nums[0];
            do {
                tortoise = nums[tortoise];
                hare = nums[nums[hare]];
            } while (tortoise != hare);

            // Find the "entrance" to the cycle.
            int ptr1 = nums[0];
            int ptr2 = tortoise;
            while (ptr1 != ptr2) {
                ptr1 = nums[ptr1];
                ptr2 = nums[ptr2];
            }

            return ptr1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}