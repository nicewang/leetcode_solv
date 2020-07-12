//Given a set of distinct integers, nums, return all possible subsets (the power
// set). 
//
// Note: The solution set must not contain duplicate subsets. 
//
// Example: 
//
// 
//Input: nums = [1,2,3]
//Output:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics Array Backtracking Bit Manipulation


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

//Java：Subsets
public class P78Subsets {
    public static void main(String[] args) {
        Solution solution = new P78Subsets().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> output = new ArrayList();
            output.add(new ArrayList<Integer>());

            for (int num : nums) {
                List<List<Integer>> newSubsets = new ArrayList();
                for (List<Integer> curr : output) {
                    newSubsets.add(new ArrayList<Integer>(curr) {{
                        add(num);
                    }});
                }
                for (List<Integer> curr : newSubsets) {
                    output.add(curr);
                }
            }
            return output;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}