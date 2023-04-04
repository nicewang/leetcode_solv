//Given a triangle, find the minimum path sum from top to bottom. Each step you 
//may move to adjacent numbers on the row below. 
//
// For example, given the following triangle 
//
// 
//[
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11). 
//
// Note: 
//
// Bonus point if you are able to do this using only O(n) extra space, where n i
//s the total number of rows in the triangle. 
// Related Topics Array Dynamic Programming


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Triangle
public class P120Triangle {
    public static void main(String[] args) {
        Solution solution = new P120Triangle().new Solution();
        // TO TEST
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> l0 = new ArrayList<>();
        l0.add(2);
        triangle.add(l0);
        List<Integer> l1 = new ArrayList<>();
        l1.add(3);
        l1.add(4);
        triangle.add(l1);
        List<Integer> l2 = new ArrayList<>();
        l2.add(6);
        l2.add(5);
        l2.add(7);
        triangle.add(l2);
        List<Integer> l3 = new ArrayList<>();
        l3.add(4);
        l3.add(1);
        l3.add(8);
        l3.add(3);
        triangle.add(l3);
        System.out.println(solution.minimumTotal(triangle));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法四：动态规划(三) 自底向上 3ms 39.6MB 时间复杂度O(n^2) 空间复杂度O(n^2)
        public int minimumTotal(List<List<Integer>> triangle) {
            // 特判
            if (triangle == null || triangle.size() == 0) {
                return 0;
            }

            int rows = triangle.size() + 1;
            int columns = triangle.get(triangle.size() - 1).size() + 1;
            int[][] dp = new int[rows][columns];
            for (int i = triangle.size() - 1; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
                }
            }
            return dp[0][0];
        }

        // 解法三：动态规划(二) 空间优化 3ms 40.3MB 时间复杂度O(n^2) 空间复杂度O(n)
        public int minimumTotalThree(List<List<Integer>> triangle) {
            // 特判
            if (triangle == null || triangle.size() == 0) {
                return 0;
            }

            int row = triangle.size();
            int column = triangle.get(row - 1).size();

            int[][] dp = new int[row][column];
            dp[0][0] = triangle.get(0).get(0);
            int res = Integer.MAX_VALUE;

            for (int i = 1; i < row; i++) {
                //对每一行的元素进行推导
                for (int j = 0; j <= i; j++) {
                    if (j == 0) {
                        // 最左端特殊处理
                        dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                    } else if (j == i) {
                        // 最右端特殊处理
                        dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                    }
                }
            }

            // dp最后一行记录了最小路径
            for (int i = 0; i < column; i++) {
                res = Math.min(res, dp[row - 1][i]);
            }
            return res;
        }

        // 解法二：动态规划(一) 3ms 40.1MB 时间复杂度O(n^2) 空间复杂度O(n^2)
        public int minimumTotalTwo(List<List<Integer>> triangle) {
            if (triangle == null || triangle.size() == 0) {
                return 0;
            }

            int row = triangle.size();
            int column = triangle.get(row - 1).size();

            int[][] dp = new int[row][column];
            dp[0][0] = triangle.get(0).get(0);
            int res = Integer.MAX_VALUE;

            for (int i = 1; i < row; i++) {
                //对每一行的元素进行推导
                for (int j = 0; j <= i; j++) {
                    if (j == 0) {
                        // 最左端特殊处理
                        dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                    } else if (j == i) {
                        // 最右端特殊处理
                        dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                    }
                }
            }

            // dp最后一行记录了最小路径
            for (int i = 0; i < column; i++) {
                res = Math.min(res, dp[row - 1][i]);
            }
            return res;
        }

        // 解法一：dfs 超过时间限制 通过用例数42／43
        public int minimumTotalOne(List<List<Integer>> triangle) {
            int n = triangle.size();
            int[][] visitedCont = new int[n][n];
            Stack<Integer> stack = new Stack<>();
            int layer = 0, cnt = 0, sum = 0;
            stack.push(triangle.get(0).get(0));
            int res = Integer.MAX_VALUE;
            Map<Integer, Integer> curLayerIndexMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                curLayerIndexMap.put(i, 0);
            }
            while (!stack.isEmpty()) {
                int curLayerIndex = curLayerIndexMap.get(layer);
                if (curLayerIndex < layer + 1 && visitedCont[layer][curLayerIndex] == 0) {
                    sum += stack.peek();
                }
                if (layer == n - 1) {
                    res = Math.min(res, sum);
                    sum -= stack.pop();
                    layer--;
                    continue;
                }
                if (curLayerIndex < layer + 1) {
                    if (visitedCont[layer][curLayerIndex] == 0) {
                        stack.push(triangle.get(layer + 1).get(curLayerIndex));
                        curLayerIndexMap.remove(layer + 1);
                        curLayerIndexMap.put(layer + 1, curLayerIndex);
                        visitedCont[layer][curLayerIndex]++;
                        layer++;
                    } else if (visitedCont[layer][curLayerIndex] == 1) {
                        stack.push(triangle.get(layer + 1).get(curLayerIndex + 1));
                        curLayerIndexMap.remove(layer + 1);
                        curLayerIndexMap.put(layer + 1, curLayerIndex + 1);
                        visitedCont[layer][curLayerIndex]++;
                        layer++;
                    } else {
                        visitedCont[layer][curLayerIndex] = 0;
                        sum -= stack.pop();
                        layer--;
                    }
                } else {
                    layer--;
                }

            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}