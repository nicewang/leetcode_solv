//Given a sorted integer array without duplicates, return the summary of its ran
//ges. 
//
// Example 1: 
//
// 
//Input:  [0,1,2,4,5,7]
//Output: ["0->2","4->5","7"]
//Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
// 
//
// Example 2: 
//
// 
//Input:  [0,2,3,4,6,8,9]
//Output: ["0","2->4","6","8->9"]
//Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
// 
// Related Topics Array


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：Summary Ranges
public class P228SummaryRanges {
    public static void main(String[] args) {
        Solution solution = new P228SummaryRanges().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 11ms 38MB
        public List<String> summaryRanges(int[] nums) {
            List<String> list = new ArrayList<>();
            if(nums == null || nums.length <= 0) {
                return list;
            }
            int cur = nums[0];
            int head = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] - cur == 1) {
                    cur = nums[i];
                    continue;
                }
                if (head == cur) {
                    list.add(String.valueOf(head));
                } else {
                    list.add(String.valueOf(head) + "->" + String.valueOf(cur));
                }
                head = nums[i];
                cur = nums[i];
            }
            if (head == cur) {
                list.add(String.valueOf(head));
            } else {
                list.add(String.valueOf(head) + "->" + String.valueOf(cur));
            }
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}