//Say you have an array for which the ith element is the price of a given stock 
//on day i. 
//
// Design an algorithm to find the maximum profit. You may complete as many tran
//sactions as you like (ie, buy one and sell one share of the stock multiple times
//) with the following restrictions: 
//
// 
// You may not engage in multiple transactions at the same time (ie, you must se
//ll the stock before you buy again). 
// After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 
//day) 
// 
//
// Example: 
//
// 
//Input: [1,2,3,0,2]
//Output: 3 
//Explanation: transactions = [buy, sell, cooldown, buy, sell]
// Related Topics Dynamic Programming


package leetcode.leetcode.editor.en;
//Java：Best Time to Buy and Sell Stock with Cooldown
public class P309BestTimeToBuyAndSellStockWithCooldown{
    public static void main(String[] args) {
        Solution solution = new P309BestTimeToBuyAndSellStockWithCooldown().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        // 持有股票
        int dp_0 = 0;
        // 不持有股票
        int dp_1 = -prices[0];
        int dp_pre_0 = 0;
        for(int i = 1; i < prices.length; i++) {
            int temp = dp_0;
            dp_0 = Math.max(dp_0, dp_1+prices[i]);
            dp_1 = Math.max(dp_1, dp_pre_0-prices[i]);
            dp_pre_0 = temp;
        }
        return dp_0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}