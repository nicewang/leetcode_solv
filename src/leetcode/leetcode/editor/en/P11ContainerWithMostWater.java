//Given n non-negative integers a1, a2, ..., an , where each represents a point 
//at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of
// line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis for
//ms a container, such that the container contains the most water. 
//
// Note: You may not slant the container and n is at least 2. 
//
// 
//
// 
//
// The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In thi
//s case, the max area of water (blue section) the container can contain is 49. 
//
// 
//
// Example: 
//
// 
//Input: [1,8,6,2,5,4,8,3,7]
//Output: 49 Related Topics Array Two Pointers


package leetcode.leetcode.editor.en;

//Java：Container With Most Water
public class P11ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new P11ContainerWithMostWater().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：双指针 类似快排 时间复杂度O(n) 空间复杂度O(1) 4ms 40.2MB
        // TODO 看一下快排经典写法
        public int maxArea(int[] height) {
            int area = Integer.MIN_VALUE;
            int l = 0, r = height.length - 1;
            while(l < r) {
                int area_tmp = (r - l) * Math.min(height[r], height[l]);
                area = Math.max(area, area_tmp);
                if(height[l] < height[r]) {
                    l++;
                    continue;
                }
                r--;
            }
            return area;
        }

        // 解法一：类似冒泡排序两层循环 时间复杂度O(n^2) 467ms 40.5MB
        public int maxAreaOne(int[] height) {
            int area = Integer.MIN_VALUE;
            for (int i = 0; i < height.length - 1; i++) {
                for (int j = i + 1; j < height.length; j++) {
                    int area_tmp = (j - i) * Math.min(height[i], height[j]);
                    area = Math.max(area, area_tmp);
                }
            }
            return area;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}