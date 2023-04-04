//给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。 
//请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例： 
//
// matrix = [
//   [ 1,  5,  9],
//   [10, 11, 13],
//   [12, 13, 15]
//],
//k = 8,
//
//返回 13。
// 
//
// 
//
// 提示： 
//你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。 
// Related Topics 堆 二分查找


package leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//Java：有序矩阵中第K小的元素
public class P378KthSmallestElementInASortedMatrix {
    public static void main(String[] args) {
        Solution solution = new P378KthSmallestElementInASortedMatrix().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：归并排序 29ms 45.6MB
        public int kthSmallest(int[][] matrix, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            });
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                pq.offer(new int[]{matrix[i][0], i, 0});
            }
            for (int i = 0; i < k - 1; i++) {
                int[] now = pq.poll();
                if (now[2] != n - 1) {
                    pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
                }
            }
            return pq.poll()[0];
        }


        // 解法一：直接排序 9ms 45.3MB
        public int kthSmallestOne(int[][] matrix, int k) {
            int[] res = new int[matrix.length*matrix[0].length];
            int num = 0;
            for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[0].length; j++) {
                    res[num++] = matrix[i][j];
                }
            }
            Arrays.sort(res);
            return res[k-1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}