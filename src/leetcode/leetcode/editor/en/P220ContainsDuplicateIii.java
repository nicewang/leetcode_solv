//Given an array of integers, find out whether there are two distinct indices i 
//and j in the array such that the absolute difference between nums[i] and nums[j]
// is at most t and the absolute difference between i and j is at most k. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,1], k = 3, t = 0
//Output: true
// 
//
// 
// Example 2: 
//
// 
//Input: nums = [1,0,1,1], k = 1, t = 2
//Output: true
// 
//
// 
// Example 3: 
//
// 
//Input: nums = [1,5,9,1,5,9], k = 2, t = 3
//Output: false
// 
// 
// 
// Related Topics Sort Ordered Map


package leetcode.leetcode.editor.en;

//Java：Contains Duplicate III
public class P220ContainsDuplicateIii {
    public static void main(String[] args) {
        Solution solution = new P220ContainsDuplicateIii().new Solution();
        int[] nums = {-1, 2147483647};
        System.out.println(solution.containsNearbyAlmostDuplicate(nums, 1, 2147483647));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 625ms 39.5MB
        // TODO 二叉搜索树解法 桶解法
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = 1; i + j < nums.length && j <= k; j++) {
                    long tmp = nums[i] > nums[i + j] ? (long) nums[i] - (long) nums[i + j] : (long) nums[i + j] - (long) nums[i];
                    if (tmp <= t){
                        return true;
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}