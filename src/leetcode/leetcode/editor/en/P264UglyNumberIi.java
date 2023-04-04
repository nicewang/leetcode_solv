//Write a program to find the n-th ugly number. 
//
// Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
//
// Example: 
//
// 
//Input: n = 10
//Output: 12
//Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ug
//ly numbers. 
//
// Note: 
//
// 
// 1 is typically treated as an ugly number. 
// n does not exceed 1690. 
// Related Topics Math Dynamic Programming Heap


package leetcode.leetcode.editor.en;

import java.util.PriorityQueue;

//Java：Ugly Number II
public class P264UglyNumberIi {
    public static void main(String[] args) {
        Solution solution = new P264UglyNumberIi().new Solution();
        System.out.println(solution.nthUglyNumber(10));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：使用堆(优先队列) 用例全部通过 但超出时间限制
        public int nthUglyNumber(int n) {
            PriorityQueue<Long> heap = new PriorityQueue<Long>();
            int[] uglyNums = new int[1690];
            heap.add(1l);
            for(int i = 0; i < 1690; i++) {
                long uglyNum = heap.poll();
                uglyNums[i] = (int) uglyNum;
                if(!heap.contains(uglyNum * 2)) {
                    heap.add(uglyNum * 2);
                }
                if(!heap.contains(uglyNum * 3)) {
                    heap.add(uglyNum * 3);
                }
                if(!heap.contains(uglyNum * 5)) {
                    heap.add(uglyNum * 5);
                }
            }
            return uglyNums[n-1];
        }

        // 解法一：动态规划 19ms 38.9MB
        public int nthUglyNumberOne(int n) {
            if (n == 0) {
                return 0;
            }
            int[] uglyNums = new int[1690];
            uglyNums[0] = 1;
            int i1 = 0, i2 = 0, i3 = 0;
            for(int i = 1; i < 1690; i++) {
                int uglyNum = Math.min(Math.min(uglyNums[i1]*2,uglyNums[i2]*3),uglyNums[i3]*5);
                uglyNums[i] = uglyNum;
                if(uglyNum/2==uglyNums[i1]) {
                    i1++;
                }
                if(uglyNum/3==uglyNums[i2]) {
                    i2++;
                }
                if(uglyNum/5==uglyNums[i3]) {
                    i3++;
                }
            }
            return uglyNums[n-1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}