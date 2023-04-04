//给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。 
//
// 示例 1: 
//
// 输入: nums = [1, 5, 1, 1, 6, 4]
//输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6] 
//
// 示例 2: 
//
// 输入: nums = [1, 3, 2, 2, 3, 1]
//输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2] 
//
// 说明: 
//你可以假设所有输入都会得到有效的结果。 
//
// 进阶: 
//你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？ 
// Related Topics 排序


package leetcode.leetcode.editor.cn;

import java.util.Arrays;

//Java：摆动排序 II
public class P324WiggleSortIi {
    public static void main(String[] args) {
        Solution solution = new P324WiggleSortIi().new Solution();
        // TO TEST
        int[] nums = {1, 5, 1, 1, 6, 4};
        solution.wiggleSort(nums);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法一：排序——排序->二分->二分结果转置->合并 4ms 42.8MB
//        eg.对于数组[1, 5, 2, 4, 3]，我们将其排序，得到[1, 2, 3, 4, 5]，然后将其分割为[1, 2, 3]和[4, 5]，
//           对两个数组进行穿插，得到[1, 4, 2, 5, 3]。
//        时间复杂度O(NlogN) 空间复杂度O(N)
        public void wiggleSort(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            Arrays.sort(nums);
            int len = nums.length;
            int[] small_nums = len % 2 == 0 ? Arrays.copyOfRange(nums, 0, len / 2) :
                    Arrays.copyOfRange(nums, 0, len / 2 + 1);
            int[] big_nums = len % 2 == 0 ? Arrays.copyOfRange(nums, len / 2, len) :
                    Arrays.copyOfRange(nums, len / 2 + 1, len);
            int count = 0;
            for(int i = 0; i < len/2; i++) {
                nums[count++] = small_nums[small_nums.length-1-i];
                nums[count++] = big_nums[big_nums.length-1-i];
            }
            if(count == len-1) {
                nums[count] = small_nums[0];
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}