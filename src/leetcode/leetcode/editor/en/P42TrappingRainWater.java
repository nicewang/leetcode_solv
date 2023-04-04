//Given n non-negative integers representing an elevation map where the width of
// each bar is 1, compute how much water it is able to trap after raining. 
//
// 
//The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In 
//this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos
// for contributing this image! 
//
// Example: 
//
// 
//Input: [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6 
// Related Topics Array Two Pointers Stack


package leetcode.leetcode.editor.en;

//Java：Trapping Rain Water
public class P42TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new P42TrappingRainWater().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法一：双指针解法 时间复杂度O(n) 空间复杂度O(1) 1ms 39.3MB
        public int trap(int[] height) {
            int left = 0, right = height.length - 1;
            int left_max = 0, right_max = 0;
            int res = 0;
            while (left < right) {
                if (height[left] < height[right]) {
                    // TODO why height[left] < height[right] 时看左边 反之看右边 -> 结合画图理解一下
                    if (height[left] >= left_max) {
                        left_max = height[left];
                    } else {
                        res += left_max - height[left];
                    }
                    left++;
                } else {
                    if (height[right] >= right_max) {
                        right_max = height[right];
                    } else {
                        res += right_max - height[right];
                    }
                    right--;
                }
            }
            return res;
        }

        // 解法二：动态编程法 时间复杂度O(n) 空间复杂度O(n) 1ms 39.6MB
        public int trapTwo(int[] height) {
            if (height == null || height.length == 0) {
                return 0;
            }
            int ans = 0;
            int size = height.length;
            int[] left_max = new int[height.length], right_max = new int[height.length];
            left_max[0] = height[0];
            for (int i = 1; i < size; i++) {
                left_max[i] = Math.max(height[i], left_max[i - 1]);
            }
            right_max[size - 1] = height[size - 1];
            for (int i = size - 2; i >= 0; i--) {
                right_max[i] = Math.max(height[i], right_max[i + 1]);
            }
            for (int i = 1; i < size - 1; i++) {
                ans += Math.min(left_max[i], right_max[i]) - height[i];
            }
            return ans;
        }

        // 解法二：暴力法 时间复杂度O(n^2) 空间复杂度O(1) 102ms 39.5MB
        public int trapThree(int[] height) {
            int ans = 0;
            int size = height.length;
            for (int i = 1; i < size - 1; i++) {
                int max_left = 0, max_right = 0;
                for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                    max_left = Math.max(max_left, height[j]);
                }
                for (int j = i; j < size; j++) { //Search the right part for max bar size
                    max_right = Math.max(max_right, height[j]);
                }
                ans += Math.min(max_left, max_right) - height[i];
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}