//Given an array of citations (each citation is a non-negative integer) of a res
//earcher, write a function to compute the researcher's h-index. 
//
// According to the definition of h-index on Wikipedia: "A scientist has index h
// if h of his/her N papers have at least h citations each, and the other N − h pa
//pers have no more than h citations each." 
//
// Example: 
//
// 
//Input: citations = [3,0,6,1,5]
//Output: 3 
//Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each o
//f them had 
//             received 3, 0, 6, 1, 5 citations respectively. 
//             Since the researcher has 3 papers with at least 3 citations each 
//and the remaining 
//             two with no more than 3 citations each, her h-index is 3. 
//
// Note: If there are several possible values for h, the maximum one is taken as
// the h-index. 
// Related Topics Hash Table Sort


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Java：H-Index
public class P274HIndex{
    public static void main(String[] args) {
        Solution solution = new P274HIndex().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 5ms 37.5MB
    // TODO 累计法
    public int hIndex(int[] citations) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < citations.length; i++) {
            list.add(citations[i]);
        }
        Collections.sort(list);
        int res = 0;
        while(res < list.size() && list.get(list.size() - 1 - res) > res) {
            res++;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}