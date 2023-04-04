//There are two sorted arrays nums1 and nums2 of size m and n respectively. 
//
// Find the median of the two sorted arrays. The overall run time complexity sho
//uld be O(log (m+n)). 
//
// You may assume nums1 and nums2 cannot be both empty. 
//
// Example 1: 
//
// 
//nums1 = [1, 3]
//nums2 = [2]
//
//The median is 2.0
// 
//
// Example 2: 
//
// 
//nums1 = [1, 2]
//nums2 = [3, 4]
//
//The median is (2 + 3)/2 = 2.5
// 
// Related Topics Array Binary Search Divide and Conquer


package leetcode.leetcode.editor.en;

//Java：Median of Two Sorted Arrays
public class P4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new P4MedianOfTwoSortedArrays().new Solution();
        // TO TEST
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法二：归并的方法 使用到归并排序的思想 把 nums1和nums2归并到同一个数组里
        // 3ms 41MB
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if(nums1 == null || nums1.length == 0) {
                int length = nums2.length;
                return length % 2 == 0 ?
                        (nums2[length / 2 - 1] + nums2[length / 2]) / 2.0 :
                        (double) nums2[length / 2];
            } else if(nums2 == null || nums2.length == 0) {
                int length = nums1.length;
                return length % 2 == 0 ?
                        (nums1[length / 2 - 1] + nums1[length / 2]) / 2.0 :
                        (double) nums1[length / 2];
            } else {
                int length = nums1.length + nums2.length;
                return length % 2 == 0 ?
                        (getNum(nums1, nums2, length/2-1) + getNum(nums1, nums2, length/2)) / 2.0 :
                        getNum(nums1, nums2, length/2);
            }

        }
        private double getNum(int[] nums1, int[] nums2, int index) {
            int i = 0, j = 0, count = 0;
            int[] nums = new int[nums1.length + nums2.length];
            while(i < nums1.length && j < nums2.length && count <= index) {
                if(nums1[i] < nums2[j]) {
                    nums[count++] = nums1[i++];
                } else {
                    nums[count++] = nums2[j++];
                }
            }
            while(i < nums1.length && count <= index) {
                nums[count++] = nums1[i++];
            }
            while(j < nums2.length && count <= index) {
                nums[count++] = nums2[j++];
            }
            return nums[index];
        }

        // 解法一：二分查找 时间复杂度O(log(m+n)) 2ms 40.7MB
        public double findMedianSortedArraysOne(int[] nums1, int[] nums2) {
            int length = nums1.length + nums2.length;
            // 选择较小的进行查找
            if (nums1.length > nums2.length) {
                return findMedianSortedArraysOne(nums2, nums1);
            }
            if (nums1.length == 0) {
                return length % 2 == 0 ? (nums2[length / 2 - 1] + nums2[length / 2]) / 2.0 : (double) nums2[length / 2];
            }
            int L_edge = 0, R_edge = nums1.length;
            double res = 0.0;
            while (L_edge <= R_edge) {
                // cur1——nums1放在新数组左边部分最靠近中位线元素的序号 TODO attention 从1开始计数
                int cur1 = (L_edge + R_edge) / 2;
                // cur2——nums2放在新数组左边部分最靠近中位线元素的序号 TODO attention 从1开始计数
                // (length+1)/2上取整保证一定为为合并后的数组的中位数序号 TODO attention 中位数序号从1开始计数
                int cur2 = (length + 1) / 2 - cur1;
                int L1 = cur1 == 0 ? Integer.MIN_VALUE : nums1[cur1 - 1];
                int R1 = cur1 == nums1.length ? Integer.MAX_VALUE : nums1[cur1];
                int L2 = cur2 == 0 ? Integer.MIN_VALUE : nums2[cur2 - 1];
                int R2 = cur2 == nums2.length ? Integer.MAX_VALUE : nums2[cur2];
                if (L1 > R2) {
                    // 需要缩小L1
                    R_edge = cur1 - 1;
                } else if (L2 > R1) {
                    // 需要增大R1
                    L_edge = cur1 + 1;
                } else {
                    if (length % 2 == 0) {
                        res = (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
                    } else {
                        res = Math.max(L1, L2);
                    }
                    break;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}