//You are a professional robber planning to rob houses along a street. Each hous
//e has a certain amount of money stashed. All houses at this place are arranged i
//n a circle. That means the first house is the neighbor of the last one. Meanwhil
//e, adjacent houses have security system connected and it will automatically cont
//act the police if two adjacent houses were broken into on the same night. 
//
// Given a list of non-negative integers representing the amount of money of eac
//h house, determine the maximum amount of money you can rob tonight without alert
//ing the police. 
//
// Example 1: 
//
// 
//Input: [2,3,2]
//Output: 3
//Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 
//2),
//             because they are adjacent houses.
// 
//
// Example 2: 
//
// 
//Input: [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//             Total amount you can rob = 1 + 3 = 4. 
// Related Topics Dynamic Programming


package leetcode.leetcode.editor.en;

//Java：House Robber II
public class P213HouseRobberIi {
    public static void main(String[] args) {
        Solution solution = new P213HouseRobberIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 0ms 37.1MB
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            int max1 = nums[0], max2 = nums[1];
            int[] dp1 = new int[nums.length - 1], dp2 = new int[nums.length - 1];
            dp1[0] = nums[0];
            dp2[0] = nums[1];
            for (int i = 1; i < nums.length - 1; i++) {
                if (i == 1) {
                    dp1[i] = Math.max(dp1[i - 1], nums[i]);
                    dp2[i] = Math.max(dp2[i - 1], nums[i + 1]);
                } else {
                    dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
                    dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i + 1]);
                }
                max1 = Math.max(max1, dp1[i]);
                max2 = Math.max(max2, dp2[i]);
            }
            return Math.max(max1, max2);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}