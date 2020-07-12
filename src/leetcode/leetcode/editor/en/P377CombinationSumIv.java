//Given an integer array with all positive numbers and no duplicates, find the n
//umber of possible combinations that add up to a positive integer target. 
//
// Example: 
//
// 
//nums = [1, 2, 3]
//target = 4
//
//The possible combination ways are:
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//
//Note that different sequences are counted as different combinations.
//
//Therefore the output is 7.
// 
//
// 
//
// Follow up: 
//What if negative numbers are allowed in the given array? 
//How does it change the problem? 
//What limitation we need to add to the question to allow negative numbers? 
//
// Credits: 
//Special thanks to @pbrother for adding this problem and creating all test case
//s. 
// Related Topics Dynamic Programming


package leetcode.leetcode.editor.en;

import java.util.Arrays;
import java.util.Stack;

//Java：Combination Sum IV
public class P377CombinationSumIv {
    public static void main(String[] args) {
        Solution solution = new P377CombinationSumIv().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Stack<Integer> stack = new Stack<>();
        int res = 0;

        // 解法一：dfs回溯 超出时间限制
        public int combinationSum4One(int[] nums, int target) {
            Arrays.sort(nums);
            dfs_find(nums, target, 0);
            return res;
        }

        private void dfs_find(int[] candidates, int target, int start) {
            if (target == 0) {
                res++;
                return;
            }
            for (int i = 0; i < candidates.length; i++) {
                if (target - candidates[i] < 0) {
                    break;
                }
                stack.push(candidates[i]);
                dfs_find(candidates, target - candidates[i], i);
                stack.pop();
            }
        }

        // 解法二：动态规划 2ms 37.1MB
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];
            dp[0] = 1;
            for (int i = 1; i <= target; i++) {
                for (int j = 0; j < nums.length; j++) {
                    if (i >= nums[j]) {
                        dp[i] += dp[i - nums[j]];
                    }
                }
            }
            return dp[target];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}