//Given an array of integers and an integer k, you need to find the total number
// of continuous subarrays whose sum equals to k. 
//
// Example 1: 
//
// 
//Input:nums = [1,1,1], k = 2
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// The length of the array is in range [1, 20,000]. 
// The range of numbers in the array is [-1000, 1000] and the range of the integ
//er k is [-1e7, 1e7]. 
// 
// Related Topics Array Hash Table


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Subarray Sum Equals K
public class P560SubarraySumEqualsK {
    public static void main(String[] args) {
        Solution solution = new P560SubarraySumEqualsK().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法三：暴力解法 时间复杂度O(n^2) 空间复杂度O(1)
        // 375ms 40.9MB
        public int subarraySumThree(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int len = nums.length;
            int res = 0;
            for (int i = 0; i < len; i++) {
                int sum = 0;
                for (int j = i; j < len; j++) {
                    sum += nums[j];
                    if (sum == k) {
                        res++;
                    }
                }
            }
            return res;
        }

        // 解法二：前缀和+哈希表(优化存储空间) 时间复杂度O(n) 空间复杂度O(n)
        // 22ms 40.6MB
        public int subarraySumTwo(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int len = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            int pre = 0;
            int res = 0;
            // attention
            map.put(0, 1);
            for (int i = 0; i < len; i++) {
                pre += nums[i];
                int diff = pre - k;
                if (map.containsKey(diff)) {
                    res += map.get(diff);
                }
                map.put(pre, map.getOrDefault(pre, 0) + 1);
            }
            return res;
        }

        // 解法一：前缀和+哈希表 时间复杂度O(n) 空间复杂度O(n)
        // 23ms 40.9MB
        public int subarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int len = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            int[] dp = new int[len];
            int res = 0;
            // attention
            map.put(0, 1);
            for (int i = 0; i < len; i++) {
                if (i == 0) {
                    // dp[i] = sum(0,i)
                    dp[i] = nums[i];
                } else {
                    dp[i] = dp[i - 1] + nums[i];
                }
                //sum(i,j) = sum(0,j)-sum(0,i-1) i-1<j
                int diff = dp[i] - k;
                if (map.containsKey(diff)) {
                    res += map.get(diff);
                }
                map.put(dp[i], map.getOrDefault(dp[i], 0) + 1);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}