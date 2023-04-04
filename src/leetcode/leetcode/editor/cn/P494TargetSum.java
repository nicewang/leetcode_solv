//给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选
//择一个符号添加在前面。 
//
// 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。 
//
// 
//
// 示例： 
//
// 输入：nums: [1, 1, 1, 1, 1], S: 3
//输出：5
//解释：
//
//-1+1+1+1+1 = 3
//+1-1+1+1+1 = 3
//+1+1-1+1+1 = 3
//+1+1+1-1+1 = 3
//+1+1+1+1-1 = 3
//
//一共有5种方法让最终目标和为3。
// 
//
// 
//
// 提示： 
//
// 
// 数组非空，且长度不会超过 20 。 
// 初始的数组的和不会超过 1000 。 
// 保证返回的最终结果能被 32 位整数存下。 
// 
// Related Topics 深度优先搜索 动态规划


package leetcode.leetcode.editor.cn;

//Java：目标和
public class P494TargetSum {
    public static void main(String[] args) {
        Solution solution = new P494TargetSum().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法三：动态规划+空间优化 14ms 39.8MB 时间复杂度O(N*S) 空间复杂度O(S)
        public int findTargetSumWays(int[] nums, int S) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int[] dp = new int[2001];
            dp[1000+nums[0]] += 1;
            dp[1000-nums[0]] += 1;
            for (int i = 1; i < nums.length; i++) {
                // TODO attention先用一个临时的next数组来记录
                int[] next = new int[2001];
                for (int j = -1000; j <= 1000; j++) {
                    // TODO 注意边界条件 why？
                    if (dp[j + 1000] > 0) {
                        // dp[i][1000+j] 截至index i,和为j的组合个数
                        next[1000 + j - nums[i]] += dp[1000 + j];
                        next[1000 + j + nums[i]] += dp[1000 + j];
                    }
                }
                dp = next;
            }
            return S > 1000 ? 0 : dp[S+1000];
        }

        // 解法二：动态规划 17ms 39.7MB 时间复杂度O(N*S) 空间复杂度O(N*S)
        public int findTargetSumWaysTwo(int[] nums, int S) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int[][] dp = new int[nums.length][2001];
            dp[0][1000+nums[0]] += 1;
            dp[0][1000-nums[0]] += 1;
            for (int i = 1; i < nums.length; i++) {
                for (int j = -1000; j <= 1000; j++) {
                    // TODO 注意边界条件 why？
                    if (dp[i - 1][j + 1000] > 0) {
                        // dp[i][1000+j] 截至index i,和为j的组合个数
                        dp[i][1000 + j - nums[i]] += dp[i - 1][1000 + j];
                        dp[i][1000 + j + nums[i]] += dp[i - 1][1000 + j];
                    }
                }
            }
            return S > 1000 ? 0 : dp[nums.length - 1][S+1000];
        }

        // 解法一：递归枚举 610ms 37.2MB 时间复杂度O(2^N) 空间复杂度O(N)
        int find1 = 0;

        public int findTargetSumWaysOne(int[] nums, int S) {
            if (nums == null || nums.length == 0) {
                return find1;
            }
            find1(nums, 1, nums[0], S);
            find1(nums, 1, -nums[0], S);
            return find1;
        }

        private void find1(int[] nums, int index, int sum, int S) {
            if (index == nums.length) {
                if (sum == S) {
                    find1++;
                }
                return;
            }
            find1(nums, index + 1, sum + nums[index], S);
            find1(nums, index + 1, sum - nums[index], S);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}