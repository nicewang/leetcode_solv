//Given an array nums of n integers where n > 1, return an array output such tha
//t output[i] is equal to the product of all the elements of nums except nums[i]. 
//
//
// Example: 
//
// 
//Input:  [1,2,3,4]
//Output: [24,12,8,6]
// 
//
// Constraint: It's guaranteed that the product of the elements of any prefix or
// suffix of the array (including the whole array) fits in a 32 bit integer. 
//
// Note: Please solve it without division and in O(n). 
//
// Follow up: 
//Could you solve it with constant space complexity? (The output array does not 
//count as extra space for the purpose of space complexity analysis.) 
// Related Topics Array


package leetcode.leetcode.editor.en;

//Java：Product of Array Except Self
public class P238ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        Solution solution = new P238ProductOfArrayExceptSelf().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：左右数组 1ms 48.4MB
        public int[] productExceptSelf(int[] nums) {

            // The length of the input array
            int length = nums.length;

            // The left and right arrays as described in the algorithm
            int[] L = new int[length];
            int[] R = new int[length];

            // Final answer array to be returned
            int[] answer = new int[length];

            // L[i] contains the product of all the elements to the left
            // Note: for the element at index '0', there are no elements to the left,
            // so L[0] would be 1
            L[0] = 1;
            for (int i = 1; i < length; i++) {

                // L[i - 1] already contains the product of elements to the left of 'i - 1'
                // Simply multiplying it with nums[i - 1] would give the product of all
                // elements to the left of index 'i'
                L[i] = nums[i - 1] * L[i - 1];
            }

            // R[i] contains the product of all the elements to the right
            // Note: for the element at index 'length - 1', there are no elements to the right,
            // so the R[length - 1] would be 1
            R[length - 1] = 1;
            for (int i = length - 2; i >= 0; i--) {

                // R[i + 1] already contains the product of elements to the right of 'i + 1'
                // Simply multiplying it with nums[i + 1] would give the product of all
                // elements to the right of index 'i'
                R[i] = nums[i + 1] * R[i + 1];
            }

            // Constructing the answer array
            for (int i = 0; i < length; i++) {
                // For the first element, R[i] would be product except self
                // For the last element of the array, product except self would be L[i]
                // Else, multiple product of all elements to the left and to the right
                answer[i] = L[i] * R[i];
            }

            return answer;
        }

        // 解法一：1760ms 48.4MB
        public int[] productExceptSelfOne(int[] nums) {
            int[] res = new int[nums.length];
            res[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                res[i] = res[i - 1] * nums[i - 1];
                for (int j = 0; j < i; j++) {
                    res[j] *= nums[i];
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}