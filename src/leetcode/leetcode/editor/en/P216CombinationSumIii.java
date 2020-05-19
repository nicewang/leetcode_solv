//
// Find all possible combinations of k numbers that add up to a number n, given 
//that only numbers from 1 to 9 can be used and each combination should be a uniqu
//e set of numbers. 
//
// Note: 
//
// 
// All numbers will be positive integers. 
// The solution set must not contain duplicate combinations. 
// 
//
// Example 1: 
//
// 
//Input: k = 3, n = 7
//Output: [[1,2,4]]
// 
//
// Example 2: 
//
// 
//Input: k = 3, n = 9
//Output: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics Array Backtracking


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：Combination Sum III
public class P216CombinationSumIii {
    public static void main(String[] args) {
        Solution solution = new P216CombinationSumIii().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();

        // 1ms 37.1MB 回溯+剪枝
        public List<List<Integer>> combinationSum3(int k, int n) {
            dfs(k, n, 1);
            return list;
        }

        private void dfs(int k, int n, int start) {
            if (k < 0 || n < 0) {
                // 剪枝
                return;
            }
            if (k == 0 && n == 0) {
                if (!stack.isEmpty()) {
                    list.add(new ArrayList<>(stack));
                }
                return;
            }
            for (int i = start; i < 10; i++) {
                stack.push(i);
                dfs(k - 1, n - i,  i + 1);
                stack.pop();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}