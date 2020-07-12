//Find the kth largest element in an unsorted array. Note that it is the kth lar
//gest element in the sorted order, not the kth distinct element. 
//
// Example 1: 
//
// 
//Input: [3,2,1,5,6,4] and k = 2
//Output: 5
// 
//
// Example 2: 
//
// 
//Input: [3,2,3,1,2,4,5,5,6] and k = 4
//Output: 4 
//
// Note: 
//You may assume k is always valid, 1 ≤ k ≤ array's length. 
// Related Topics Divide and Conquer Heap


package leetcode.leetcode.editor.en;

import java.util.PriorityQueue;

//Java：Kth Largest Element in an Array
public class P215KthLargestElementInAnArray {
    public static void main(String[] args) {
        Solution solution = new P215KthLargestElementInAnArray().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法一：使用优先队列 7ms 40.2MB
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);
            for (int i = 0; i < nums.length; i++) {
                heap.add(nums[i]);
            }
            int res = 0;
            for (int i = 0; i < k; i++) {
                res = heap.poll();
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}