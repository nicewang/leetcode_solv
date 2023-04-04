//Say you have an array for which the i-th element is the price of a given stock
// on day i. 
//
// Design an algorithm to find the maximum profit. You may complete at most k tr
//ansactions. 
//
// Note: 
//You may not engage in multiple transactions at the same time (ie, you must sel
//l the stock before you buy again). 
//
// Example 1: 
//
// 
//Input: [2,4,1], k = 2
//Output: 2
//Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 
//4-2 = 2.
// 
//
// Example 2: 
//
// 
//Input: [3,2,6,5,0,3], k = 2
//Output: 7
//Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 
//6-2 = 4.
//             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), prof
//it = 3-0 = 3.
// 
// Related Topics Dynamic Programming


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

//Java：Best Time to Buy and Sell Stock IV
public class P188BestTimeToBuyAndSellStockIv {
    public static void main(String[] args) {
        Solution solution = new P188BestTimeToBuyAndSellStockIv().new Solution();
        // TO TEST
        int[] prices = {1, 3};
        System.out.println(solution.maxProfit(0, prices));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法三：别人解法 4ms 39.6MB TODO 多看几遍
        public int maxProfitThree(int k, int[] prices) {
            if (k < 1 ) { return 0; }

            // k 超过了上限，也就变成了 无限次交易问题
            if (k > prices.length / 2) {
                return maxProfitOfII(prices);
            }
            // 状态定义
            int [][] dp = new int[k][2];

            // 初始化
            for (int i = 0; i < k; i++) {
                dp[i][0] = Integer.MIN_VALUE;
            }
            // 遍历每一天，模拟 k 次交易，计算并更新状态
            for (int p : prices) {

                dp[0][0] = Math.max(dp[0][0], 0 - p);                  // 第 1 次买
                dp[0][1] = Math.max(dp[0][1], dp[0][0] + p);           // 第 1 次卖

                for (int i = 1; i < k; i++) {
                    dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] - p);   // 第 i 次买
                    dp[i][1] = Math.max(dp[i][1], dp[i][0] + p);       // 第 i 次卖
                }
            }
            return dp[k - 1][1];
        }
        // 解决无限次交易的方法
        private int maxProfitOfII(int[] prices) {
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    res += prices[i] - prices[i - 1];
                }
            }
            return res;
        }

        // 解法二：动态规划 超出内存限制 通过用例数：209／211
        public int maxProfit(int k, int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            if (k == 0) {
                return 0;
            }
            int[][][] dp = new int[prices.length][k + 1][2];
            // 因为内存超了 所以做一些修改
            int[][] dp1 = new int[k + 1][2];
            // 内存超了 修改2
            int dp_0 = 0, dp_1 = -prices[0];
            int[] tmp_0 = new int[k + 1], tmp_1 = new int[k + 1];
            for (int i = 0; i < prices.length; i++) {
                for (int K = k; K >= 1; K--) {
                    if (i == 0) {
                        // 0——持有股票 1—— 不持有股票
                        dp[i][K][0] = 0;
                        dp[i][K][1] = -prices[i];
                        // 因为内存超了 所以做一些修改
                        dp1[K][0] = 0;
                        dp1[K][1] = -prices[i];
                        // 内存超了 修改2
                        dp_0 = 0;
                        dp_1 = -prices[i];
                    } else {
                        // 0——持有股票 1—— 不持有股票
                        dp[i][K][0] = Math.max(dp[i - 1][K][0], dp[i - 1][K][1] + prices[i]);
                        dp[i][K][1] = Math.max(dp[i - 1][K][1], dp[i - 1][K - 1][0] - prices[i]);
                        // 因为内存超了 所以做一些修改
                        dp1[K][0] = Math.max(dp1[K][0], dp1[K][1] + prices[i]);
                        dp1[K][1] = Math.max(dp1[K][1], dp1[K - 1][0] - prices[i]);
                        // 内存超了 修改2
                        dp_0 = Math.max(tmp_0[K], tmp_1[K] + prices[i]);
                        dp_1 = Math.max(tmp_1[K], tmp_0[K - 1] - prices[i]);
                    }
                    tmp_0[K] = dp_0;
                    tmp_1[K] = dp_1;
                }
            }
//            return dp[prices.length-1][k][0];
//            return dp1[k][0];
            return tmp_0[k];
        }

        // 解法一：峰谷法+递归 超时 通过用例数：208／211
        public int maxProfitOne(int k, int[] prices) {
            int i = 0;
            List<Integer> valley = new ArrayList<>(), peak = new ArrayList<>();
            while (i < prices.length) {
                while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                    i++;
                }
                valley.add(prices[i]);
                while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                    i++;
                }
                peak.add(prices[i]);
                i++;
            }
            if (k == 0) {
                return 0;
            }
            return find(valley, peak, 0, k);
        }

        private int find(List<Integer> valley, List<Integer> peak, int start, int k) {
            if (start >= valley.size()) {
                return 0;
            }
            int min = Integer.MAX_VALUE;
            int max = 0;
            for (int i = start; i < valley.size(); i++) {
                min = Math.min(min, valley.get(i));
                if (k == 1) {
                    max = Math.max(max, peak.get(i) - min);
                } else {
                    max = Math.max(max, peak.get(i) - min + find(valley, peak, i + 1, k - 1));
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}