//Given a set of distinct positive integers, find the largest subset such that e
//very pair (Si, Sj) of elements in this subset satisfies: 
//
// Si % Sj = 0 or Sj % Si = 0. 
//
// If there are multiple solutions, return any subset is fine. 
//
// Example 1: 
//
// 
// 
//Input: [1,2,3]
//Output: [1,2] (of course, [1,3] will also be ok)
// 
//
// 
// Example 2: 
//
// 
//Input: [1,2,4,8]
//Output: [1,2,4,8]
// 
// 
// Related Topics Math Dynamic Programming


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Largest Divisible Subset
public class P368LargestDivisibleSubset {
    public static void main(String[] args) {
        Solution solution = new P368LargestDivisibleSubset().new Solution();
        // TO TEST
        int[] nums = {2, 3, 4, 9, 8};
//        solution.largestDivisibleSubset(nums);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法三：节约空间的动态规划 时间复杂度O(n^2) 空间复杂度O(n) 20ms 39.5MB
        public List<Integer> largestDivisibleSubset(int[] nums) {
            if (nums == null) {
                return null;
            }
            if (nums.length == 0) {
                return new ArrayList<>();
            }
            if (nums.length == 1) {
                return Arrays.asList(nums[0]);
            }
            Arrays.sort(nums);
            // dp[i]表示以第i个数结尾的整除子集的最大长度
            int[] dp = new int[nums.length];
            int maxIndex = 0;
            int maxCount = 0;
            for (int i = 0; i < nums.length; i++) {
                int maxSize = 0;
                for (int j = 0; j < i; j++) {
                    if (nums[i] % nums[j] == 0 && maxSize < dp[j]) {
                        maxSize = dp[j];
                    }
                }
                dp[i] = maxSize + 1;
                if (dp[i] > maxCount) {
                    maxCount = dp[i];
                    maxIndex = i;
                }
            }
            LinkedList<Integer> res = new LinkedList<>();
            res.addFirst(nums[maxIndex]);
            maxCount--;
            for (int i = maxIndex - 1; i >= 0; i--) {
                if (res.peek() % nums[i] == 0 && dp[i] == maxCount) {
                    res.addFirst(nums[i]);
                    maxCount--;
                }
            }
            return res;
        }


        HashMap<Integer, List<Integer>> _eds = new HashMap<Integer, List<Integer>>();
        int[] _nums = {};

        // 解法四：记忆化递归 时间复杂度O(n^2) 空间复杂度O(n^2) 37ms 40MB
        public List<Integer> largestDivisibleSubsetFour(int[] nums) {
            // Test case with empty set.
            int n = nums.length;
            if (n == 0) return new ArrayList<>();

            // Container to keep the largest divisible subset
            //   that ends with each of the nums
            // key: the index of the element
            this._eds = new HashMap<Integer, List<Integer>>();

            /* Sort the original list in ascending order. */
            Arrays.sort(nums);
            this._nums = nums;

            List<Integer> maxSubset = new ArrayList<>();
    /* Calculate the values of all EDS(X_i),
       while keeping track of the largest one as the result value. */
            for (int i = 0; i < n; ++i) {
                List<Integer> subset = EDS(i);
                if (maxSubset.size() < subset.size()) maxSubset = subset;
            }

            // return the largest one
            return maxSubset;
        }

        private List<Integer> EDS(Integer i) {
            // memoization
            if (this._eds.containsKey(i)) {
                return this._eds.get(i);
            }

            List<Integer> maxSubset = new ArrayList<>();
            // Find the largest divisible subset of previous elements.
            for (int k = 0; k < i; ++k) {
                if (this._nums[i] % this._nums[k] == 0) {
                    List<Integer> subset = EDS(k);
                    if (maxSubset.size() < subset.size()) {
                        maxSubset = subset;
                    }
                }
            }
            // Extend the found subset with the element itself.
            List<Integer> newEntry = new ArrayList<>();
            newEntry.addAll(maxSubset);
            newEntry.add(this._nums[i]);

            // update the cached values
            this._eds.put(i, newEntry);
            return newEntry;
        }


        // 解法二：动态规划 时间复杂度O(n^2) 空间复杂度O(n^2) 24ms 39.7MB
        public List<Integer> largestDivisibleSubsetTwo(int[] nums) {
            // Test case with empty set.
            int n = nums.length;
            if (n == 0) {
                return new ArrayList<>();
            }

            // Container to keep the largest divisible subset
            //   that ends with each of the nums.
            List<ArrayList<Integer>> EDS = new ArrayList<>();
            for (int num : nums) {
                EDS.add(new ArrayList<>());
            }

            /* Sort the original list in ascending order. */
            Arrays.sort(nums);

            /* Calculate all the values of EDS(X_i) */
            for (int i = 0; i < n; ++i) {
                List<Integer> maxSubset = new ArrayList<>();

                // Find the largest divisible subset of previous elements.
                for (int k = 0; k < i; ++k)
                    if (nums[i] % nums[k] == 0 && maxSubset.size() < EDS.get(k).size()) {
                        maxSubset = EDS.get(k);
                    }

                // Extend the found subset with the element itself.
                EDS.get(i).addAll(maxSubset);
                EDS.get(i).add(nums[i]);
            }
            /* Find the largest of EDS values  */
            List<Integer> ret = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (ret.size() < EDS.get(i).size()) {
                    ret = EDS.get(i);
                }
            }
            return ret;
        }

        // 解法一：回溯 超时 通过用例数42／43
        Stack<Integer> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();

        public List<Integer> largestDivisibleSubsetOne(int[] nums) {
            if (nums == null) {
                return null;
            }
            if (nums.length == 0) {
                return new ArrayList<>();
            }
            if (nums.length == 1) {
                return Arrays.asList(nums[0]);
            }
            Arrays.sort(nums);
            backtracking(nums, 0);
            return res;
        }

        private void backtracking(int[] nums, int index) {
            if (index == nums.length) {
                if (stack.size() > res.size()) {
                    res = new ArrayList<>(stack);
                }
                return;
            }
            for (int i = index; i < nums.length; i++) {
                if (stack.isEmpty() || nums[i] % stack.peek() == 0) {
                    stack.push(nums[i]);
                    backtracking(nums, i + 1);
                    stack.pop();
                }
            }
            if (stack.size() > res.size()) {
                res = new ArrayList<>(stack);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}