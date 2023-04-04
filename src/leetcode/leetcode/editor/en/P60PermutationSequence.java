//The set [1,2,3,...,n] contains a total of n! unique permutations. 
//
// By listing and labeling all of the permutations in order, we get the followin
//g sequence for n = 3: 
//
// 
// "123" 
// "132" 
// "213" 
// "231" 
// "312" 
// "321" 
// 
//
// Given n and k, return the kth permutation sequence. 
//
// Note: 
//
// 
// Given n will be between 1 and 9 inclusive. 
// Given k will be between 1 and n! inclusive. 
// 
//
// Example 1: 
//
// 
//Input: n = 3, k = 3
//Output: "213"
// 
//
// Example 2: 
//
// 
//Input: n = 4, k = 9
//Output: "2314"
// 
// Related Topics Math Backtracking


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：Permutation Sequence
public class P60PermutationSequence {
    public static void main(String[] args) {
        Solution solution = new P60PermutationSequence().new Solution();
        // TO TEST
        System.out.println(solution.getPermutation(3, 3));
    }
//    面试中主要有三种类型的排列问题：
//
//    全排列
//            下一个排列
//    第 k 个排列（当前问题）
//    如果排列的顺序不重要，可以使用“交换”的思想回溯写出全排列。生成N!个全排列需要时间O(N×N!)。该算法可以解决第一类问题。
//
//    D.E. Knuth 算法按照字典顺序生成全排列，在O(N)时间内完成。该算法可以解决第二类问题。
//
//    但是这两个算法不能解决第三类问题：
//     1)良好的时间复杂度，即无回溯。
//     2)先前排列未知，即不能使用 D.E. Knuth 算法。
//
//    为了解决这两个问题，可以使用映射的思路，因为生成数字的排列更容易。
//     *使用数字生成排列，然后映射到组合/子集/排列中。


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二： 2ms 37.2MB 说实话 没看懂 TODO 多看几遍这个解法
        public String getPermutation(int n, int k) {
            int[] fac = new int[n];
            fac[0] = 1;
            List<Integer> bucket = new ArrayList<Integer>();
            for(int i = 1; i < n; i++) {
                fac[i] = i * fac[i - 1];
            }
            for(int i = 0; i < n; i++) {
                bucket.add(i + 1);
            }

            k--;
            StringBuilder sb = new StringBuilder();
            for(int i = n - 1; i >=0; i--) {
                int idx = k / fac[i];
                sb.append(bucket.get(idx));
                k -= idx * fac[i];
                bucket.remove(idx);
            }
            return sb.toString();
        }

        // 解法一：dfs回溯 超时 通过用例数164／200
        int count = 0;
        Stack<Integer> stack = new Stack<>();

        public String getPermutationOne(int n, int k) {
            List<Integer> result = dfs(n, k);
            if(result == null || result.size() <= 0) {
                return "";
            }
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < result.size(); i++) {
                sb.append(result.get(i));
            }
            return sb.toString();
        }

        private List<Integer> dfs(int n, int k) {
            if (stack.size() == n) {
                count++;
                return new ArrayList<>(stack);
            }
            for (int i = 1; i <= n; i++) {
                if (!stack.contains(i)) {
                    stack.push(i);
                    List<Integer> result = dfs(n, k);
                    if (count == k) {
                        return result;
                    }
                    stack.pop();
                }
            }
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}