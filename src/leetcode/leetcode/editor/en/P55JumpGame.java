//Given an array of non-negative integers, you are initially positioned at the f
//irst index of the array. 
//
// Each element in the array represents your maximum jump length at that positio
//n. 
//
// Determine if you are able to reach the last index. 
//
// Example 1: 
//
// 
//Input: [2,3,1,1,4]
//Output: true
//Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
// 
//
// Example 2: 
//
// 
//Input: [3,2,1,0,4]
//Output: false
//Explanation: You will always arrive at index 3 no matter what. Its maximum
//             jump length is 0, which makes it impossible to reach the last ind
//ex.
// 
// Related Topics Array Greedy


package leetcode.leetcode.editor.en;

//Java：Jump Game
public class P55JumpGame {
    public static void main(String[] args) {
        Solution solution = new P55JumpGame().new Solution();
        // TO TEST
        int[] input = {4, 0, 4, 2, 2, 0, 1, 3, 3, 0, 3};
        System.out.println(solution.canJump(input));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法三：维护最远可达位置rightMost，并实时更新 时间复杂度o(n) 空间复杂度o(1)
        // 2ms 41.9MB
        public boolean canJump(int[] nums) {
            int rightMost = 0;
            for(int i = 0; i < nums.length; i++) {
                if(i <= rightMost) {
                    rightMost = Math.max(rightMost, i + nums[i]);
                    if(rightMost >= nums.length - 1) {
                        return true;
                    }
                }
            }
            return false;
        }

        // 解法二：递归暴力求解
        // 运行超时 74／75通过的测试用例
        public boolean canJumpTwo(int[] nums) {
            return jumpToEnd(nums, 0);
        }

        private boolean jumpToEnd(int[] nums, int curIndex) {
            if(curIndex == nums.length - 1) {
                return true;
            } else if(nums[curIndex] == 0) {
                return false;
            }
            for(int i = 1; i <= nums[curIndex]; i++) {
                if(jumpToEnd(nums, curIndex + i)) {
                    return true;
                }
            }
            return false;
        }

        // 解法一：1.根据index对应值由大到小排序 -> 2.尽可能往对应值更大的index跳
        // 不能完全解答 反eg {4, 0, 4, 2, 2, 0, 1, 3, 3, 0, 3}
        // PS.使用到index排序这么繁琐的东西就应该知道这种方法不靠谱
        public boolean canJumpOne(int[] nums) {
            int[] sortedIndex = indexSort(nums);
            boolean[] invalidIndexFlags = new boolean[nums.length];
            int curIndex = 0;
            for (int i = 0; i < nums.length; i++) {
                if (invalidIndexFlags[sortedIndex[i]]) {
                    continue;
                }
                invalidIndexFlags[curIndex] = true;
                if (curIndex == nums.length - 1) {
                    return true;
                }
                if (nums[curIndex] >= sortedIndex[i] - curIndex) {
                    curIndex = sortedIndex[i];
                    if (nums[curIndex] >= nums.length - 1 - curIndex) {
                        return true;
                    }
                    i = -1;
                    invalidIndexFlags[curIndex] = true;
                }
            }

            return curIndex == nums.length - 1;
        }

        private int[] indexSort(int[] nums) {
            int[] sortedIndex = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                sortedIndex[i] = i;
            }
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[sortedIndex[i]] < nums[sortedIndex[j]]) {
                        int tmp = sortedIndex[i];
                        sortedIndex[i] = sortedIndex[j];
                        sortedIndex[j] = tmp;
                    } else if (nums[sortedIndex[i]] == nums[sortedIndex[j]]) {
                        // index对应值相等的情形下，把较大的index放到sortedIndex数组的前面
                        int tmp_i = sortedIndex[i];
                        int tmp_j = sortedIndex[j];
                        sortedIndex[i] = tmp_i > tmp_j ? tmp_i : tmp_j;
                        sortedIndex[j] = tmp_i > tmp_j ? tmp_j : tmp_i;
                    }
                }
            }
            return sortedIndex;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}