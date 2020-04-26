//A sequence of numbers is called a wiggle sequence if the differences between s
//uccessive numbers strictly alternate between positive and negative. The first di
//fference (if one exists) may be either positive or negative. A sequence with few
//er than two elements is trivially a wiggle sequence. 
//
// For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3
//,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,
//7,4,5,5] are not wiggle sequences, the first because its first two differences a
//re positive and the second because its last difference is zero. 
//
// Given a sequence of integers, return the length of the longest subsequence th
//at is a wiggle sequence. A subsequence is obtained by deleting some number of el
//ements (eventually, also zero) from the original sequence, leaving the remaining
// elements in their original order. 
//
// Example 1: 
//
// 
//Input: [1,7,4,9,2,5]
//Output: 6
//Explanation: The entire sequence is a wiggle sequence. 
//
// 
// Example 2: 
//
// 
//Input: [1,17,5,10,13,15,10,5,16,8]
//Output: 7
//Explanation: There are several subsequences that achieve this length. One is [
//1,17,10,13,10,16,8]. 
//
// 
// Example 3: 
//
// 
//Input: [1,2,3,4,5,6,7,8,9]
//Output: 2 
//
// Follow up: 
//Can you do it in O(n) time? 
// 
// 
// Related Topics Dynamic Programming Greedy


package leetcode.leetcode.editor.en;

import com.sun.xml.internal.rngom.parse.host.GrammarHost;

//Java：Wiggle Subsequence
public class P376WiggleSubsequence {
    public static void main(String[] args) {
        Solution solution = new P376WiggleSubsequence().new Solution();
        int[] a = {1,2,3,4,5,6,7,8,9};
        System.out.println(solution.wiggleMaxLengthTwo(a));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法五：贪心算法 计算拐点个数 总数=拐点个数+2 时间复杂度o(n) 空间复杂度o(1)
        // 0ms 37.1MB
        public int wiggleMaxLength(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }
            if(nums.length < 2) {
                return 1;
            }
            int pre_diff = nums[1] - nums[0], diff = 0;
            int maxCount = 0;
            // 计算拐点个数
            for(int i = 2; i < nums.length; i++) {
                diff = nums[i] - nums[i-1];
                if(pre_diff != 0 && diff != 0) {
                    maxCount = pre_diff * diff > 0 ? maxCount : 1 + maxCount;
                    pre_diff = diff;
                } else if(diff != 0) {
                    pre_diff = diff;
                }
            }
            if(maxCount == 0) {
                // 无拐点的情况
                return diff == 0 ? 1 : 2;
            }
            return maxCount + 2;
        }

        // 解法四：空间优化的动态规划 时间复杂度o(n) 空间复杂度o(1)
        // 0ms 37MB
        public int wiggleMaxLengthFour(int[] nums) {
//            这个方法与方法 3 一样。
//            但可以发现， DP 过程中更新 up[i]up[i] 和 down[i]down[i] ，其实只需要 up[i-1]up[i−1] 和 down[i-1]down[i−1] 。
//            因此，可以通过只记录最后一个元素的值而不使用数组来节省空间。
            if(nums == null || nums.length == 0) {
                return 0;
            }
            if(nums.length < 2) {
                return 1;
            }
            int down = 1, up = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1]) {
                    up = down + 1;
                } else if (nums[i] < nums[i - 1]) {
                    down = up + 1;
                }
            }
            return Math.max(down, up);
        }

        // 解法三：线性动态规划 时间复杂度o(n) 空间复杂度o(n)
        // 0ms 37.1MB
        public int wiggleMaxLengthThree(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }
            if(nums.length < 2) {
                return 1;
            }
            //up[i] 存的是目前为止最长的以第 i 个元素结尾的上升摆动序列的长度
            int[] up = new int[nums.length];
            int[] down = new int[nums.length];
            for(int i = 1; i < nums.length; i++) {
//                上升的位置，意味着 nums[i] > nums[i - 1]nums[i]>nums[i−1]
//                下降的位置，意味着 nums[i] < nums[i - 1]nums[i]<nums[i−1]
//                相同的位置，意味着 nums[i] == nums[i - 1]nums[i]==nums[i−1]
                if(nums[i] > nums[i-1]) {
                    up[i] = down[i-1] + 1;
                    down[i] = down[i-1];
                } else if(nums[i] < nums[i-1]) {
                    down[i] = up[i-1] + 1;
                    up[i] = up[i-1];
                } else {
                    up[i] = up[i-1];
                    down[i] = down[i-1];
                }
            }
            return 1 + Math.max(up[nums.length-1],down[nums.length-1]);
        }

        // 解法二：动态规划 时间复杂度o(n^2) 空间复杂度o(n)
        // 8ms 36.8MB
        public int wiggleMaxLengthTwo(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }
            if(nums.length < 2) {
                return 1;
            }
            //up[i] 存的是目前为止最长的以第 i 个元素结尾的上升摆动序列的长度
            int[] up = new int[nums.length];
            int[] down = new int[nums.length];
            for(int i = 1; i < nums.length; i++) {
                for(int j = 0; j < i; j++) {
                    if(nums[i] > nums[j]) {
                        up[i] = Math.max(up[i], down[j]+1);
                    } else if(nums[i] < nums[j]) {
                        down[i] = Math.max(down[i], up[j]+1);
                    }
                }
            }
            return 1 + Math.max(up[nums.length-1],down[nums.length-1]);
        }

        // 解法一：暴力法 运行超时 时间复杂度o(n!) 空间复杂度o(n)
        public int wiggleMaxLengthOne(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }
            return nums.length < 2 ? 1 : 1 + Math.max(calculateMaxOfLenOfLongestSubWiggleSeqStartAtIndex(nums, 0, true), calculateMaxOfLenOfLongestSubWiggleSeqStartAtIndex(nums, 0, false));
        }
        private int calculateMaxOfLenOfLongestSubWiggleSeqStartAtIndex(int[] nums, int index, boolean isUp) {
            int maxCount = 0;
            for (int i = index + 1; i < nums.length; i++) {
                if ((isUp && nums[i] - nums[index] > 0) || (!isUp && nums[i] - nums[index] < 0)) {
                    int tmpCount = 1 + calculateMaxOfLenOfLongestSubWiggleSeqStartAtIndex(nums, i, !isUp);
                    maxCount = Math.max(maxCount, tmpCount);
                }
            }
            return maxCount;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}