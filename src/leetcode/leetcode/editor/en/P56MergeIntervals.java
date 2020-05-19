//Given a collection of intervals, merge all overlapping intervals. 
//
// Example 1: 
//
// 
//Input: [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
// 
//
// Example 2: 
//
// 
//Input: [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping. 
//
// NOTE: input types have been changed on April 15, 2019. Please reset to defaul
//t code definition to get new method signature. 
// Related Topics Array Sort


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

//Java：Merge Intervals
public class P56MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new P56MergeIntervals().new Solution();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        solution.merge(intervals);
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 728ms 42.1MB
        public int[][] merge(int[][] intervals) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < intervals.length; i++) {
                list.add(intervals[i][0]);
            }
            int[] sorteIndex = indexSort(list);
            int[][] res = new int[intervals.length][2];
            int count = 0;
            for (int i = 0; i < intervals.length; i++) {
                if (i < intervals.length - 1 && intervals[sorteIndex[i]][1] >= intervals[sorteIndex[i + 1]][0]) {
                    intervals[sorteIndex[i + 1]][0] = intervals[sorteIndex[i]][0];
                    if(intervals[sorteIndex[i]][1] >= intervals[sorteIndex[i + 1]][1]) {
                        intervals[sorteIndex[i + 1]][1] = intervals[sorteIndex[i]][1];
                    }
                } else {
                    res[count++] = intervals[sorteIndex[i]];
                }
            }
            int[][] res_new = new int[count][2];
            for(int i = 0; i < count; i++) {
                res_new[i] = res[i];
            }
            return res_new;
        }

        private int[] indexSort(List<Integer> list) {
            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i] = i;
            }
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(res[i]) > list.get(res[j])) {
                        int tmp = res[i];
                        res[i] = res[j];
                        res[j] = tmp;
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}