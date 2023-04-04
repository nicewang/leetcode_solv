//请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
// 
//
// 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2
//, 1, 1, 0, 0]。 
//
// 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。 
// Related Topics 栈 哈希表


package leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;

//Java：每日温度
public class P739DailyTemperatures {
    public static void main(String[] args) {
        Solution solution = new P739DailyTemperatures().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法三：使用单调栈 14ms 47.2MB 时间复杂度O(n) 空间复杂度O(n)
        public int[] dailyTemperatures(int[] T) {
            if (T == null || T.length == 0) {
                return new int[0];
            }
            int[] res = new int[T.length];
            LinkedList<Integer> stack = new LinkedList<>();
            for(int i = 0; i < T.length; i++) {
                while(!stack.isEmpty() && T[i] > T[stack.peek()]) {
                    // TODO 要使用pop和push,不能使用add和remove,否则会出错,why?
                    int prev = stack.pop();
                    res[prev] = i-prev;
                }
                stack.push(i);
            }
            return res;
        }

        // 解法二：暴力循环二 14ms 48.2MB 时间复杂度O(n*m) 空间复杂度O(m) m——数组next的长度
        public int[] dailyTemperaturesTwo(int[] T) {
            if (T == null || T.length == 0) {
                return new int[0];
            }
            int[] res = new int[T.length];
            // 维护数组next,记录每一个温度第一次出现的下角标
            int[] next = new int[101];
            Arrays.fill(next, Integer.MAX_VALUE);
            for (int i = T.length - 1; i >= 0; i--) {
                int warmerTemperature = Integer.MAX_VALUE;
                for(int t = T[i]+1; t <= 100; t++) {
                    if(next[t] < warmerTemperature) {
                        warmerTemperature = next[t];
                    }
                }
                if(warmerTemperature < Integer.MAX_VALUE) {
                    res[i] = warmerTemperature - i;
                }
                next[T[i]] = i;
            }
            return res;
        }

        // 解法一：暴力循环 1053ms 48.2MB
        public int[] dailyTemperaturesOne(int[] T) {
            if (T == null || T.length == 0) {
                return new int[0];
            }
            int[] res = new int[T.length];
            for (int i = T.length - 2; i >= 0; i--) {
                for (int j = i + 1; j < T.length; j++) {
                    if (T[j] > T[i]) {
                        res[i] = j - i;
                        break;
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}