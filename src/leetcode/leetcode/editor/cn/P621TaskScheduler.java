//给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务
//都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。 
//
// 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。 
//
// 你需要计算完成所有任务所需要的最短时间。 
//
// 
//
// 示例 ： 
//
// 输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
//     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
//
// 
//
// 提示： 
//
// 
// 任务的总个数为 [1, 10000]。 
// n 的取值范围为 [0, 100]。 
// 
// Related Topics 贪心算法 队列 数组


package leetcode.leetcode.editor.cn;

import java.util.*;

//Java：任务调度器
public class P621TaskScheduler {
    public static void main(String[] args) {
        Solution solution = new P621TaskScheduler().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法二：优先队列 45ms 40.6MB TODO 后面再理解理解
        public int leastInterval(char[] tasks, int n) {
            int[] map = new int[26];
            for (char c : tasks) {
                map[c - 'A']++;
            }
            PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
            for (int f : map) {
                if (f > 0) {
                    queue.add(f);
                }
            }
            int time = 0;
            while (!queue.isEmpty()) {
                int i = 0;
                List<Integer> temp = new ArrayList<>();
                while (i <= n) {
                    if (!queue.isEmpty()) {
                        if (queue.peek() > 1) {
                            temp.add(queue.poll() - 1);
                        } else {
                            queue.poll();
                        }
                    }
                    time++;
                    if (queue.isEmpty() && temp.size() == 0) {
                        break;
                    }
                    i++;
                }
                queue.addAll(temp);
            }
            return time;
        }

        // 解法一：排序 7ms 41.4MB
        public int leastIntervalOne(char[] tasks, int n) {
            int[] map = new int[26];
            for (char ch : tasks) {
                map[ch - 'A']++;
            }
            int time = 0;
            Arrays.sort(map);
            while (map[25] > 0) {
                int i = 0;
                while (i <= n) {
                    if (map[25] == 0) {
                        break;
                    }
                    if (i < 26 && map[25 - i] > 0) {
                        map[25 - i]--;
                    }
                    i++;
                    time++;
                }
                Arrays.sort(map);
            }
            return time;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}