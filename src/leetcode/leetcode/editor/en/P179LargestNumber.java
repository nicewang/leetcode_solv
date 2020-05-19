//Given a list of non negative integers, arrange them such that they form the la
//rgest number. 
//
// Example 1: 
//
// 
//Input: [10,2]
//Output: "210" 
//
// Example 2: 
//
// 
//Input: [3,30,34,5,9]
//Output: "9534330"
// 
//
// Note: The result may be very large, so you need to return a string instead of
// an integer. 
// Related Topics Sort


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Largest Number
public class P179LargestNumber {
    public static void main(String[] args) {
        Solution solution = new P179LargestNumber().new Solution();
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println(solution.largestNumber(nums));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private class LargerNumberComparator implements Comparator<String> {
            @Override
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1);
            }
        }


        public String largestNumber(int[] nums) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(String.valueOf(nums[i]));
            }
            Collections.sort(list, new LargerNumberComparator());
            String res = "";
            for (int i = 0; i < list.size(); i++) {
                if(i == 0 && list.get(i).equals("0")) {
                    return "0";
                }
                res += list.get(i);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}