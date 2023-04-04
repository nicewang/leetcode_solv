//Given an array nums of n integers and an integer target, find three integers i
//n nums such that the sum is closest to target. Return the sum of the three integ
//ers. You may assume that each input would have exactly one solution. 
//
// Example: 
//
// 
//Given array nums = [-1, 2, 1, -4], and target = 1.
//
//The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
// 
// Related Topics Array Two Pointers


package leetcode.leetcode.editor.en;

import java.util.Stack;

//Java：3Sum Closest
public class P16ThreeSumClosest {
    public static void main(String[] args) {
        Solution solution = new P16ThreeSumClosest().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int diff = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<>();
        //  解法一：dfs回溯 2250ms 39.8MB TODO 看精选答案
        public int threeSumClosest(int[] nums, int target) {
            dfs(nums, 0, 3, target, 0);
            return res;
        }
        private void dfs(int[] nums, int start, int n, int target, int sum) {
            if(n == 0) {
                if(Math.abs(sum - target) < diff) {
                    diff = Math.abs(sum - target);
                    res = sum;
                }
                return;
            }
            for(int i = start; i < nums.length; i++) {
                sum += nums[i];
                stack.push(nums[i]);
                dfs(nums, i + 1, n - 1, target, sum);
                sum -= nums[i];
                stack.pop();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}