//Given a collection of distinct integers, return all possible permutations. 
//
// Example: 
//
// 
//Input: [1,2,3]
//Output:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// 
// Related Topics Backtracking


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Permutations
public class P46Permutations {
    public static void main(String[] args) {
        Solution solution = new P46Permutations().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：搜索回溯——交换->递归->交换撤销 1ms 40.2MB
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new LinkedList();

            ArrayList<Integer> output = new ArrayList<Integer>();
            for (int num : nums)
                output.add(num);

            int n = nums.length;
            backtrack(n, output, res, 0);
            return res;
        }
        public void backtrack(int n,
                              ArrayList<Integer> output,
                              List<List<Integer>> res,
                              int first) {
            // 所有数都填完了
            if (first == n)
                res.add(new ArrayList<Integer>(output));
            for (int i = first; i < n; i++) {
                // 动态维护数组
                Collections.swap(output, first, i);
                // 继续递归填下一个数
                backtrack(n, output, res, first + 1);
                // 撤销操作
                Collections.swap(output, first, i);
            }
        }
        // 解法一：使用堆栈的回溯 4ms 39.9MB
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        public List<List<Integer>> permuteOne(int[] nums) {
            find(nums, 0);
            return res;
        }
        private void find(int[] nums, int count) {
            if(count == nums.length) {
                res.add(new ArrayList<>(stack));
                return;
            }
            for(int i = 0; i < nums.length; i++) {
                if(!stack.contains(nums[i])) {
                    stack.add(nums[i]);
                    find(nums, count+1);
                    stack.pop();
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}