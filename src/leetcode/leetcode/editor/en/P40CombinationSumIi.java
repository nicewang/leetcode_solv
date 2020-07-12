//Given a collection of candidate numbers (candidates) and a target number (targ
//et), find all unique combinations in candidates where the candidate numbers sums
// to target. 
//
// Each number in candidates may only be used once in the combination. 
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
//Input: candidates = [10,1,2,7,6,1,5], target = 8,
//A solution set is:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// Example 2: 
//
// 
//Input: candidates = [2,5,2,1,2], target = 5,
//A solution set is:
//[
//  [1,2,2],
//  [5]
//]
// 
// Related Topics Array Backtracking


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Combination Sum II
public class P40CombinationSumIi {
    public static void main(String[] args) {
        Solution solution = new P40CombinationSumIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();
        // 15ms 40.5MB
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<Integer> candidateList = new ArrayList<>();
            for (int i = 0; i < candidates.length; i++) {
                candidateList.add(candidates[i]);
            }
            dfs_find(candidateList, target, 0);
            return list;
        }

        private void dfs_find(List<Integer> candidateList, int target, int start) {
            if (target == 0) {
                List<Integer> sub_list = new ArrayList<>(stack);
                Collections.sort(sub_list);
                if (!list.contains(sub_list)) {
                    list.add(sub_list);
                }
                return;
            }
            for (int i = 0; i < candidateList.size(); i++) {
                if (target - candidateList.get(i) < 0) {
                    break;
                }
                stack.push(candidateList.get(i));
                List<Integer> newCandidates = new ArrayList<>();
                if (i + 1 < candidateList.size()) {
                    newCandidates.addAll(candidateList.subList(i + 1, candidateList.size()));
                }
                dfs_find(newCandidates, target - candidateList.get(i), i + 1);
                stack.pop();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}