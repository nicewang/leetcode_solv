//Given an array of citations sorted in ascending order (each citation is a non-
//negative integer) of a researcher, write a function to compute the researcher's 
//h-index. 
//
// According to the definition of h-index on Wikipedia: "A scientist has index h
// if h of his/her N papers have at least h citations each, and the other N − h pa
//pers have no more than h citations each." 
//
// Example: 
//
// 
//Input: citations = [0,1,3,5,6]
//Output: 3 
//Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each o
//f them had 
//             received 0, 1, 3, 5, 6 citations respectively. 
//             Since the researcher has 3 papers with at least 3 citations each 
//and the remaining 
//             two with no more than 3 citations each, her h-index is 3. 
//
// Note: 
//
// If there are several possible values for h, the maximum one is taken as the h
//-index. 
//
// Follow up: 
//
// 
// This is a follow up problem to H-Index, where citations is now guaranteed to 
//be sorted in ascending order. 
// Could you solve it in logarithmic time complexity? 
// 
// Related Topics Binary Search


package leetcode.leetcode.editor.en;

//Java：H-Index II
public class P275HIndexIi {
    public static void main(String[] args) {
        Solution solution = new P275HIndexIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：二分查找 时间复杂度O(log n) 空间复杂度O(1) 0ms 47MB
        public int hIndex(int[] citations) {
            int res = 0, left = 0, right = citations.length-1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if(citations[mid] == citations.length-mid) {
                    return citations.length-mid;
                } else if(citations[mid] < citations.length-mid) {
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            }
            return citations.length-left;
        }

        // 解法一：线性搜索 时间复杂度O(n) 空间复杂度O(1) 8ms 46.5MB
        public int hIndexOne(int[] citations) {
            int res = 0;
            while (res < citations.length && citations[citations.length-1-res] > res) {
                res++;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}