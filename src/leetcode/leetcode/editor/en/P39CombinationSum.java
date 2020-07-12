//Given a set of candidate numbers (candidates) (without duplicates) and a targe
//t number (target), find all unique combinations in candidates where the candidat
//e numbers sums to target. 
//
// The same repeated number may be chosen from candidates unlimited number of ti
//mes. 
//
// Note: 
//
// 
// All numbers (including target) will be positive integers. 
// The solution set must not contain duplicate combinations. 
// 
//
// Example 1: 
//
// 
//Input: candidates = [2,3,6,7], target = 7,
//A solution set is:
//[
//  [7],
//  [2,2,3]
//]
// 
//
// Example 2: 
//
// 
//Input: candidates = [2,3,5], target = 8,
//A solution set is:
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//]
// 
// Related Topics Array Backtracking


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Combination Sum
public class P39CombinationSum {
    public static void main(String[] args) {
        Solution solution = new P39CombinationSum().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();
        // 43ms 40.3MB
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            dfs_find(candidates, target, 0);
            return list;
        }

        private void dfs_find(int[] candidates, int target, int start) {
            if (target == 0) {
                List<Integer> sub_list = new ArrayList<>(stack);
                Collections.sort(sub_list);
                if (!list.contains(sub_list)) {
                    list.add(sub_list);
                }
                return;
            }
            for (int i = 0; i < candidates.length; i++) {
                if (target - candidates[i] < 0) {
                    break;
                }
                stack.push(candidates[i]);
                dfs_find(candidates, target - candidates[i], i);
                stack.pop();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}