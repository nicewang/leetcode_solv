//Given two integers n and k, return all possible combinations of k numbers out 
//of 1 ... n. 
//
// Example: 
//
// 
//Input: n = 4, k = 2
//Output:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
// 
// Related Topics Backtracking


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：Combinations
public class P77Combinations {
    public static void main(String[] args) {
        Solution solution = new P77Combinations().new Solution();
        // TO TEST
        solution.combine(4, 2);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();

        // 74ms 41.4MB
        public List<List<Integer>> combine(int n, int k) {
            List<Integer> n_list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                n_list.add(i + 1);
            }
            dfsFind(n_list, k);
            return list;
        }

        private void dfsFind(List<Integer> n_list, int k) {
            if (stack.size() == k) {
                list.add(new ArrayList<>(stack));
                return;
            }
            for (int i = 0; i < n_list.size(); i++) {
                stack.push(n_list.get(i));
                List<Integer> new_list = new ArrayList<>();
                if (i + 1 < n_list.size()) {
                    new_list.addAll(n_list.subList(i + 1, n_list.size()));
                }
                dfsFind(new_list, k);
                stack.pop();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}