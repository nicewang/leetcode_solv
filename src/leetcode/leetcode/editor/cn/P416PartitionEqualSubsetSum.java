//给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 注意: 
//
// 
// 每个数组中的元素不会超过 100 
// 数组的大小不会超过 200 
// 
//
// 示例 1: 
//
// 输入: [1, 5, 11, 5]
//
//输出: true
//
//解释: 数组可以分割成 [1, 5, 5] 和 [11].
// 
//
// 
//
// 示例 2: 
//
// 输入: [1, 2, 3, 5]
//
//输出: false
//
//解释: 数组不能分割成两个元素和相等的子集.
// 
//
// 
// Related Topics 动态规划


package leetcode.leetcode.editor.cn;

//Java：分割等和子集
public class P416PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Solution solution = new P416PartitionEqualSubsetSum().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 动态规划——01背包问题 35ms 39.7MB
        public boolean canPartition(int[] nums) {
            int len = nums.length;
            if (len == 0) {
                return false;
            }
            int sum = 0;
            for (int i = 0; i < len; i++) {
                sum += nums[i];
            }
            if (sum % 2 == 1) {
                return false;
            }
            int target = sum / 2;
            // 创建二维状态数组，行：物品索引，列：容量（包括 0）
            boolean[][] dp = new boolean[len][target + 1];
            if (nums[0] <= target) {
                dp[0][nums[0]] = true;
            }
            for (int i = 1; i < len; i++) {
                for (int j = 0; j <= target; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (nums[i] == j) {
                        dp[i][j] = true;
                        continue;
                    }
                    if (nums[i] < j) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i]];
                    }
                    if(dp[i][target]) {
                        return true;
                    }
                }
            }
            return dp[len - 1][target];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}