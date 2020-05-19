//Given a positive integer n, find the least number of perfect square numbers (f
//or example, 1, 4, 9, 16, ...) which sum to n. 
//
// Example 1: 
//
// 
//Input: n = 12
//Output: 3 
//Explanation: 12 = 4 + 4 + 4. 
//
// Example 2: 
//
// 
//Input: n = 13
//Output: 2
//Explanation: 13 = 4 + 9. Related Topics Math Dynamic Programming Breadth-first
// Search


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Java：Perfect Squares
public class P279PerfectSquares {
    public static void main(String[] args) {
        Solution solution = new P279PerfectSquares().new Solution();
        System.out.println(solution.numSquares(18));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法四：贪心+BFS 52ms 39.6MB
        // TODO 多看几遍解答 理解
        public int numSquares(int n) {
            // TODO 这里必须用ArrayList 否则解答错误 why？！
            ArrayList<Integer> squareNums = new ArrayList<>();
            for(int i = 1; i*i <= n; ++i) {
                squareNums.add(i*i);
            }
            Set<Integer> queue = new HashSet<Integer>();
            queue.add(n);
            int level = 0;
            while(queue.size() > 0) {
                level += 1;
                Set<Integer> nextQueue = new HashSet<Integer>();
                for(Integer now : queue) {
                    for(Integer square : squareNums) {
                        if(now.equals(square)) {
                            return level;
                        } else if(now < square) {
                            break;
                        } else {
                            nextQueue.add(now - square);
                        }
                    }
                }
                queue = nextQueue;
            }
            return level;
        }

        // 解法三：贪心枚举 11ms 38.5MB
//        首先，我们准备一个小于给定数字 n 的完全平方数列表（称为 square_nums）。
//        在主循环中，将组合的大小（称为 count）从 1 迭代到 n，我们检查数字 n 是否可以除以组合的和，即 is_divided_by(n, count)。
//        函数 is_divided_by(n, count) 可以用递归的形式实现，如上面所说。
//        在最下面的例子中，我们有 count==1，我们只需检查数字 n 是否本身是一个完全平方数。可以在 square_nums 中检查，即 n \in \text{square\_nums}n∈square_nums。如果 square_nums 使用的是集合数据结构，我们可以获得比 n == int(sqrt(n)) ^ 2 更快的运行时间。
        public int numSquaresThree(int n) {
            Set<Integer> squareNums = new HashSet<>();
            for(int i = 1; i*i <= n; i++) {
                squareNums.add(i*i);
            }
            for(int count = 1; count <= n; count++) {
                if(isDividedBy(n, count, squareNums)) {
                    return count;
                }
            }
            return n;
        }

        private boolean isDividedBy(int n, int count, Set<Integer> squareNums) {
            if (count == 1) {
                return squareNums.contains(n);
            }
            for (Integer squareNum : squareNums) {
                if (n - squareNum > 0 && isDividedBy(n - squareNum, count - 1, squareNums)) {
                    return true;
                }
            }
            return false;
        }

        // 解法二：动态规划解法 思路：numSquares(n)=min(numSquares(n-k) + 1) ∀ k ∈ square-numbers
        // 43ms 39MB
        public int numSquaresTwo(int n) {
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            int maxSquare = (int) Math.sqrt(n);
            int[] squareNums = new int[maxSquare + 1];
            for (int i = 0; i < maxSquare + 1; i++) {
                squareNums[i] = i * i;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= maxSquare; j++) {
                    if (squareNums[j] > i) {
                        break;
                    }
                    dp[i] = Math.min(dp[i], dp[i - squareNums[j]] + 1);
                }
            }
            return dp[n];
        }

        // 解法一：超出时间限制，思路与使用数组的动态规划思路一致
        // numSquares(n)=min(numSquares(n-k) + 1) ∀ k ∈ square-numbers
        public int numSquaresOne(int n) {
            return find(n, 0);
        }

        private int find(int n, int count) {
            if (n == 0) {
                return count;
            }
            count++;
            if (n == 1) {
                return count;
            }
            int newCount = count;
            int i = 1;
            int resCount = Integer.MAX_VALUE;
            while (i * i <= n) {
                resCount = Math.min(resCount, find(n - i * i, newCount));
                newCount = count;
                i++;
            }
            return resCount;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}