//Given an integer array nums, find the contiguous subarray within an array (con
//taining at least one number) which has the largest product. 
//
// Example 1: 
//
// 
//Input: [2,3,-2,4]
//Output: 6
//Explanation: [2,3] has the largest product 6.
// 
//
// Example 2: 
//
// 
//Input: [-2,0,-1]
//Output: 0
//Explanation: The result cannot be 2, because [-2,-1] is not a subarray. 
// Related Topics Array Dynamic Programming


package leetcode.leetcode.editor.en;

import org.omg.CORBA.MARSHAL;

import java.util.ArrayList;
import java.util.List;

//Java：Maximum Product Subarray
public class P152MaximumProductSubarray {
    public static void main(String[] args) {
        Solution solution = new P152MaximumProductSubarray().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：优化存储空间 2ms 39.8MB
        public int maxProduct(int[] nums) {
            int fmax = nums[0];
            int fmin = nums[0];
            int res = fmax;
            for (int i = 1; i < nums.length; i++) {
                int max_tmp = fmax, min_tmp = fmin;
                fmax = Math.max(max_tmp * nums[i], Math.max(min_tmp * nums[i], nums[i]));
                fmin = Math.min(max_tmp * nums[i], Math.min(min_tmp * nums[i], nums[i]));
                res = Math.max(res, fmax);
            }
            return res;
        }

        // dp 3ms 39.6MB
        public int maxProductOne(int[] nums) {
            int[] fmax = new int[nums.length], fmin = new int[nums.length];
            fmax[0] = nums[0];
            fmin[0] = nums[0];
            int res = fmax[0];
            for (int i = 1; i < nums.length; i++) {
                fmax[i] = Math.max(fmax[i - 1] * nums[i], Math.max(fmin[i - 1] * nums[i], nums[i]));
                fmin[i] = Math.min(fmax[i - 1] * nums[i], Math.min(fmin[i - 1] * nums[i], nums[i]));
                res = Math.max(res, fmax[i]);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}