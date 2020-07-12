//Given a collection of integers that might contain duplicates, nums, return all
// possible subsets (the power set). 
//
// Note: The solution set must not contain duplicate subsets. 
//
// Example: 
//
// 
//Input: [1,2,2]
//Output:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//]
// 
// Related Topics Array Backtracking


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

//Java：Subsets II
public class P90SubsetsIi {
    public static void main(String[] args) {
        Solution solution = new P90SubsetsIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法一：使用堆栈的回溯法 418ms 40.6MB
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> subsets(int[] nums) {
            if(nums == null) {
                return null;
            }
            List<Integer> list = new ArrayList<Integer>();
            if(nums.length == 0) {
                res.add(list);
                return res;
            }
            backtracking(nums, 0, nums.length-1);
            res.add(list);
            return res;
        }
        private void backtracking(int[] nums, int start, int end) {
            if(start > end) {
                List<Integer> list = new ArrayList<Integer>(stack);
                Collections.sort(list);
                if(!res.contains(list)) {
                    res.add(list);
                }
                return;
            }
            for(int i = end; i >= 0; i--) {
                for(int j = start; j <= end; j++) {
                    stack.push(nums[j]);
                    backtracking(nums, j+1, i);
                    stack.pop();
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}