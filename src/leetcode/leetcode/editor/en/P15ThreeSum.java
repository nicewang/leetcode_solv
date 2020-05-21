//Given an array nums of n integers, are there elements a, b, c in nums such tha
//t a + b + c = 0? Find all unique triplets in the array which gives the sum of ze
//ro. 
//
// Note: 
//
// The solution set must not contain duplicate triplets. 
//
// Example: 
//
// 
//Given array nums = [-1, 0, 1, 2, -1, -4],
//
//A solution set is:
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics Array Two Pointers


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：3Sum
public class P15ThreeSum {
    public static void main(String[] args) {
        Solution solution = new P15ThreeSum().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 25ms 43.7MB
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length < 3) {
                return result;
            }
            Arrays.sort(nums);
            int len = nums.length;
            for (int i = 0; i < len - 2; i++) {
                if (nums[i] > 0) {
                    return result;
                }
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int l = i + 1;
                int r = len - 1;
                while(l < r) {
                    int curr = nums[i] + nums[l] + nums[r];
                    if(curr == 0) {
                        result.add(Arrays.asList(nums[i],nums[l],nums[r]));
                        while(l < r && nums[l] == nums[l+1]) {
                            l++;
                        }
                        while(l < r && nums[r] == nums[r-1]) {
                            r--;
                        }
                        l++;
                        r--;
                    } else if(curr < 0) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}