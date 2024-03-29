//Implement next permutation, which rearranges numbers into the lexicographicall
//y next greater permutation of numbers. 
//
// If such arrangement is not possible, it must rearrange it as the lowest possi
//ble order (ie, sorted in ascending order). 
//
// The replacement must be in-place and use only constant extra memory. 
//
// Here are some examples. Inputs are in the left-hand column and its correspond
//ing outputs are in the right-hand column. 
//
// 1,2,3 → 1,3,2 
//3,2,1 → 1,2,3 
//1,1,5 → 1,5,1 
// Related Topics Array


package leetcode.leetcode.editor.en;

//Java：Next Permutation
public class P31NextPermutation {
    public static void main(String[] args) {
        Solution solution = new P31NextPermutation().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 1ms 39.7MB 时间复杂度O(n) 空间复杂度O(1)
        public void nextPermutation(int[] nums) {
            int i = nums.length-2;
            while(i >= 0 && nums[i+1] <= nums[i]) {
                i--;
            }
            if(i >= 0) {
                int j = nums.length-1;
                while(j >= 0 && nums[j] <= nums[i]) {
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i+1);
        }
        private void reverse(int[] nums, int start) {
            int i =  start, j = nums.length-1;
            while(i<j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}