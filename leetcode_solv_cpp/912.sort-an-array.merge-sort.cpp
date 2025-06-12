/*
 * @lc app=leetcode id=912 lang=cpp
 *
 * [912] Sort an Array
 */

// @lc code=start

// I will explain my solution while writing code at the same time

// For the method merge-sort, 
// we also utilize the idea of divide-and-conquer

#include <vector>

using namespace std;

class Solution {

private:

    void mergeSort(vector<int>& nums, int l, int r) {
        if (l >= r) {
            return;
        }
        // 1. Dividing: divide nums like binary-search does
        int m = (l+r) >> 1;
        // 2. Conquering:
        // Recursively call the mergeSort 
        // to both left part and the right
        mergeSort(nums, l, m);
        mergeSort(nums, m+1, r);
        // 3. Merging
        vector<int> tmp(r-l+1, 0);
        int i = l, j = m+1;
        int tmp_idx = 0;
        while (i <= m && j <= r) {
            if (nums[i] < nums[j]) {
                tmp[tmp_idx++] = nums[i++];
            } else {
                tmp[tmp_idx++] = nums[j++];
            }
        }
        while (i <= m) {
            tmp[tmp_idx++] = nums[i++];
        }
        while (j <= r) {
            tmp[tmp_idx++] = nums[j++];
        }
        // copy
        for (int k = 0; k < r-l+1; k++) {
            nums[l+k] = tmp[k];
        }
    }

public:
    vector<int> sortArray(vector<int>& nums) {
        int n = nums.size();
        mergeSort(nums, 0, n-1);
        return nums;
    }
};

// Time Complexity: o(nlogn)
//          - o(logn) for recursion depth
//          - o(nlogn) for compare in each layer
// Space Complexity: o(n)
//          - o(n) for tmp array
//          - o(logn) for recursion stack

// @lc code=end