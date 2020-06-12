//Say you have an array prices for which the ith element is the price of a given
// stock on day i. 
//
// Design an algorithm to find the maximum profit. You may complete as many tran
//sactions as you like (i.e., buy one and sell one share of the stock multiple tim
//es). 
//
// Note: You may not engage in multiple transactions at the same time (i.e., you
// must sell the stock before you buy again). 
//
// Example 1: 
//
// 
//Input: [7,1,5,3,6,4]
//Output: 7
//Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 
//5-1 = 4.
//             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), prof
//it = 6-3 = 3.
// 
//
// Example 2: 
//
// 
//Input: [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 
//5-1 = 4.
//             Note that you cannot buy on day 1, buy on day 2 and sell them lat
//er, as you are
//             engaging multiple transactions at the same time. You must sell be
//fore buying again.
// 
//
// Example 3: 
//
// 
//Input: [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transaction is done, i.e. max profit = 0. 
//
// 
// Constraints: 
//
// 
// 1 <= prices.length <= 3 * 10 ^ 4 
// 0 <= prices[i] <= 10 ^ 4 
// 
// Related Topics Array Greedy


package leetcode.leetcode.editor.en;

//Java：Best Time to Buy and Sell Stock II
public class P122BestTimeToBuyAndSellStockIi {
    public static void main(String[] args) {
        Solution solution = new P122BestTimeToBuyAndSellStockIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法三：一次迭代 1ms 39.4MB 时间复杂度O(n) 空间复杂度O(1)
        public int maxProfit(int[] prices) {
            int res = 0;
            for(int i = 0; i < prices.length-1; i++) {
                if(prices[i+1] - prices[i] > 0) {
                    res += prices[i+1]-prices[i];
                }
            }
            return res;
        }
        // 解法二：峰谷法 1ms 39.8MB 时间复杂度O(n) 空间复杂度O(1)
        public int maxProfitTwo(int[] prices) {
            int i = 0;
            int res = 0;
            int valley = prices[0];
            int peak = prices[0];
            while(i < prices.length) {
                while(i < prices.length-1 && prices[i] >= prices[i+1]) {
                    i++;
                }
                valley = prices[i];
                while(i < prices.length-1 && prices[i] <= prices[i+1]) {
                    i++;
                }
                peak = prices[i];
                res += peak - valley;
                i++;
            }
            return res;
        }
        // 解法一：暴力法 超时 通过用例数198／200
        public int maxProfitOne(int[] prices) {
            return calMax(prices, 0);
        }
        private int calMax(int[] prices, int start) {
            if(start >= prices.length) {
                return 0;
            }
            int res = 0;
            for(int i = start; i < prices.length-1; i++) {
                int max = 0;
                for(int j = i+1; j < prices.length; j++) {
                    if(prices[j]-prices[i] > 0) {
                        max = Math.max(max, calMax(prices, j) + prices[j] - prices[i]);
                    }
                }
                res = Math.max(res, max);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}