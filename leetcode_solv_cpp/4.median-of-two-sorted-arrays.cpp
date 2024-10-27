/*
 * @lc app=leetcode id=4 lang=cpp
 *
 * [4] Median of Two Sorted Arrays
 */

// @lc code=start
#include <vector>

class Solution {
public:

    // way-1: Binary Search (of total length m+n)
    // m: len(nums1), n: len(nums2)
    // Time Complexity: o(log(m+n))
    // Space Complexity: o(1)
    double findMedianSortedArrays(std::vector<int>& nums1, std::vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        int len = m + n;
        return (len % 2 == 0) ? (findKth(nums1, nums2, len/2-1) + findKth(nums1, nums2, len/2))/2.0 : findKth(nums1, nums2, len/2);
    }

    // Find the kth largest number
    // k start from 0
    double findKth(const std::vector<int>& nums1, const std::vector<int>& nums2, int k) {
        int m = nums1.size(), n = nums2.size();
        int idx1 = 0, idx2 = 0;
        while (true) {
            if (idx1 == m) {
                return nums2[idx2+k];
            }
            if (idx2 == n) {
                return nums1[idx1+k];
            }
            if (k == 0) {
                return std::min(nums1[idx1], nums2[idx2]);
            }
            // nums1 and nums2 each move forward about half the distance
            // distance = k+1
            int half = (k+1)/2;
            int new_idx1 = std::min(idx1+half, m) -1;
            int new_idx2 = std::min(idx2+half, n) - 1;
            int pivot1 = nums1[new_idx1];
            int pivot2 = nums2[new_idx2];
            if (pivot1 < pivot2) {
                // nums1 should move forward
                // whereas nums2 should move back -> but now we won't change the position of pointer at nums2
                k -= (new_idx1 + 1 - idx1);
                idx1 = new_idx1 + 1;
            } else {
                // situation: nums2 should move forward
                k -= (new_idx2 + 1 - idx2);
                idx2 = new_idx2 + 1;
            }
        }
    }
    
};
// @lc code=end

