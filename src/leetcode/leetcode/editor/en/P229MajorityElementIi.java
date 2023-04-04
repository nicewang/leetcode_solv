//Given an integer array of size n, find all elements that appear more than ⌊ n/
//3 ⌋ times. 
//
// Note: The algorithm should run in linear time and in O(1) space. 
//
// Example 1: 
//
// 
//Input: [3,2,3]
//Output: [3] 
//
// Example 2: 
//
// 
//Input: [1,1,1,3,3,2,2,2]
//Output: [1,2] 
// Related Topics Array


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Java：Majority Element II
public class P229MajorityElementIi {
    public static void main(String[] args) {
        Solution solution = new P229MajorityElementIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法二：摩尔投票法 2ms 43.8MB TODO 多理解·
        public List<Integer> majorityElement(int[] nums) {
            // 创建返回值
            List<Integer> res = new ArrayList<>();
            if (nums == null || nums.length == 0) return res;
            // 初始化两个候选人candidate，和他们的计票
            int cand1 = nums[0], count1 = 0;
            int cand2 = nums[0], count2 = 0;

            // 摩尔投票法，分为两个阶段：配对阶段和计数阶段
            // 配对阶段
            for (int num : nums) {
                // 投票
                if (cand1 == num) {
                    count1++;
                    continue;
                }
                if (cand2 == num) {
                    count2++;
                    continue;
                }

                // 第1个候选人配对
                if (count1 == 0) {
                    cand1 = num;
                    count1++;
                    continue;
                }
                // 第2个候选人配对
                if (count2 == 0) {
                    cand2 = num;
                    count2++;
                    continue;
                }

                count1--;
                count2--;
            }

            // 计数阶段
            // 找到了两个候选人之后，需要确定票数是否满足大于 N/3
            count1 = 0;
            count2 = 0;
            for (int num : nums) {
                if (cand1 == num) count1++;
                else if (cand2 == num) count2++;
            }

            if (count1 > nums.length / 3) res.add(cand1);
            if (count2 > nums.length / 3) res.add(cand2);

            return res;
        }

        // 解法一：map解法 23ms 42.5MB
        public List<Integer> majorityElementOne(int[] nums) {
            List<Integer> list = new ArrayList<>();
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (list.contains(nums[i])) {
                    continue;
                }
                if (!map.containsKey(nums[i])) {
                    if (nums.length / 3 < 1) {
                        list.add(nums[i]);
                    } else {
                        map.put(nums[i], 1);
                    }
                } else {
                    int count = map.get(nums[i]) + 1;
                    map.remove(nums[i]);
                    if (count > nums.length / 3) {
                        list.add(nums[i]);
                    } else {
                        map.put(nums[i], count);
                    }
                }
            }
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}