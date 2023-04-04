//Say you have an array for which the ith element is the price of a given stock 
//on day i. 
//
// Design an algorithm to find the maximum profit. You may complete at most two 
//transactions. 
//
// Note: You may not engage in multiple transactions at the same time (i.e., you
// must sell the stock before you buy again). 
//
// Example 1: 
//
// 
//Input: [3,3,5,0,0,3,1,4]
//Output: 6
//Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 
//3-0 = 3.
//             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), prof
//it = 4-1 = 3. 
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
// Related Topics Array Dynamic Programming


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

//Java：Best Time to Buy and Sell Stock III
public class P123BestTimeToBuyAndSellStockIii {
    public static void main(String[] args) {
        Solution solution = new P123BestTimeToBuyAndSellStockIii().new Solution();
        // TO TEST
        int[] prices = {1, 2, 4, 2, 5, 7, 2, 4, 9};
        System.out.println(solution.maxProfit(prices));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法：峰谷法+递归 7ms 39.4MB
        public int maxProfit(int[] prices) {
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
            return find(valley, peak, 0, 2);
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