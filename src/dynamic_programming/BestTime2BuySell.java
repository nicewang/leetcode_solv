package dynamic_programming;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction 
 * (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit. 
 * 
 * Example 1:
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * 
 * Example 2:
 * Input: [7, 6, 4, 3, 1]
 * Output: 0
 * In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTime2BuySell {
	
	public static void main(String[] args) {
		int[] data = {7, 1, 5, 3, 6, 4};
		System.out.println(maxProfit_mySelf(data));
	}
	
	/**
	 * Solution2 fr LeetCode
	 * 空间复杂度n
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int[] prices) {
		// TODO Auto-generated method stub
		int min = Integer.MAX_VALUE;
		int result = 0;
		for(int i = 0; i < prices.length; i++) {
			if(prices[i] < min)
				min = prices[i];
			else if(prices[i] - min > result)
				result = prices[i] - min;
		}
		return result;
	}

	/**
	 * Solution1 fr LeetCode
	 * 空间复杂度n2
	 * @param prices
	 * @return
	 */
	public static int maxProfit_1(int[] prices) {
		int result = 0;
		for(int i = 0; i < prices.length - 1; i++) {
			for(int j = i; j < prices.length; j++) {
				int tmp = prices[j] - prices[i];
				if(tmp > result)
					result = tmp;
			}
		}
		return result;
	}
	
	/**
	 * My Solution
	 * @param prices
	 * @return
	 */
	public static int maxProfit_mySelf(int[] prices) {
		int result = 0;
		int length = prices.length;
		if(length == 1)
			return result;
		// 用一个整型数组label来存放prices中各元素的序号
		int[] label = new int[length];
		for(int i = 0; i < length; i++){
			label[i] = i;
		}
		insertSort(prices, label, length);
		result = getMaxProfit(prices, label, length);
        return result;
    }
	
	private static int getMaxProfit(int[] data, int[] label, int length) {
		// TODO Auto-generated method stub
		int result = 0;
		if(length == 1)
			return result;
		int max = data[length-1];
		int min = data[0];
		if(label[length-1] > label[0]) {
			result = max -min;
		} else {
			// get subset of data and label
			int[] data_sub1 = new int[length-1];
			int[] data_sub2 = new int[length-1];
			int[] label_sub1 = new int[length-1];
			int[] label_sub2 = new int[length-1];
			for(int i = 0; i < length - 1; i++) {
				data_sub1[i] = data[i];
				label_sub1[i] = label[i];
				data_sub2[i] = data[i+1];
				label_sub2[i] = label[i+1];
			}
			int a = getMaxProfit(data_sub1, label_sub1, length-1);
			int b = getMaxProfit(data_sub2, label_sub2, length-1);
			result = a > b ? a : b; 
		}
		return result;
	}

	private static void insertSort(int[] data, int[] label, int length) {
		// TODO Auto-generated method stub
		for(int i = 0; i < length - 1; i++) {
			int tmp = data[i+1];
			int tmp_label = label[i+1];
			int j;
			for(j = i + 1; j > 0 && tmp < data[j-1]; j--) {
				data[j] = data[j-1];
				label[j] = label[j-1];
			}
			data[j] = tmp;
			label[j] = tmp_label;
		}
	}
	
}
