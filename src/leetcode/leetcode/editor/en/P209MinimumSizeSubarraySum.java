//Given an array of n positive integers and a positive integer s, find the minim
//al length of a contiguous subarray of which the sum ≥ s. If there isn't one, ret
//urn 0 instead. 
//
// Example: 
//
// 
//Input: s = 7, nums = [2,3,1,2,4,3]
//Output: 2
//Explanation: the subarray [4,3] has the minimal length under the problem const
//raint. 
//
// Follow up: 
//
// If you have figured out the O(n) solution, try coding another solution of whi
//ch the time complexity is O(n log n). 
// Related Topics Array Two Pointers Binary Search


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Minimum Size Subarray Sum
public class P209MinimumSizeSubarraySum {
    public static void main(String[] args) {
        Solution solution = new P209MinimumSizeSubarraySum().new Solution();
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(solution.minSubArrayLen(7, nums));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法二：二分查找 7ms 39.4MB
        public int minSubArrayLen(int s, int[] nums) {
            if (nums == null || nums.length <= 0) {
                return 0;
            }
            int n = nums.length;
            int[] sums = new int[n];
            sums[0] = nums[0];
            for (int i = 1; i < n; i++) {
                sums[i] = sums[i - 1] + nums[i];
            }
            int minSize = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                // to find : sums[i-1] + nums[i] + ... + nums[k] >= sums[i-1] + s
                int k = binarySearch(i, n - 1, sums, s-nums[i]+sums[i]);
                if (k != -1) {
                    minSize = Math.min(minSize, k - i + 1);
                }
            }
            return minSize == Integer.MAX_VALUE ? 0 : minSize;
        }

        private int binarySearch(int start, int end, int[] sums, int target) {
            int mid = (start + end) / 2;
            while (start <= end) {
                mid = (start + end) / 2;
                if(sums[mid] == target) {
                    // TODO 这一步一定不能少！
                    return mid;
                } else if (sums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            return sums[mid] > target ? mid : -1;
        }

        // 解法一：类似双指针解法 18ms 39.9MB
        public int minSubArrayLenOne(int s, int[] nums) {
            if (nums == null || nums.length <= 0) {
                return 0;
            }
            List<Integer> list = new ArrayList<>();
            int sum = 0;
            int minSize = Integer.MAX_VALUE;
            int i = 0;
            boolean hasSub = false;
            while (i < nums.length) {
                if (sum < s) {
                    list.add(nums[i]);
                    sum += nums[i];
                    i++;
                } else {
                    hasSub = true;
                    minSize = Math.min(minSize, list.size());
                    sum -= list.get(0);
                    list.remove(0);
                }
            }
            while (sum >= s) {
                hasSub = true;
                minSize = Math.min(minSize, list.size());
                sum -= list.get(0);
                list.remove(0);
            }
            if (hasSub) {
                return minSize;
            }
            return 0;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}