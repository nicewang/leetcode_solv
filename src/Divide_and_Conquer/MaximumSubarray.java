package Divide_and_Conquer;

public class MaximumSubarray {
	
	public static void main(String[] args) {
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		MaximumSubarray ms = new MaximumSubarray();
		int result = ms.maxSubArray(nums);
		System.out.println(result);
	}
	
	/**
	 * 动态规划解法
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        result = Math.max(result, dp[0]);
        for(int i = 1; i < nums.length; i++) {
        	// dp[i]存放到第0到第i个元素中可能的和最大子集(起点不一定是0，终点也不一定是i)的和
        	dp[i] = nums[i] + (dp[i-1] > 0 ? dp[i-1] : 0);
        	result = Math.max(result, dp[i]);
        }
        return result;
    }

}
