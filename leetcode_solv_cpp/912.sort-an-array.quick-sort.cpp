/*
 * @lc app=leetcode id=912 lang=cpp
 *
 * [912] Sort an Array
 */

// @lc code=start

// I will explain my solution while writing code at the same time
// Applying the idea of divide-and-conquer

#include <vector>

using namespace std;

class Solution {

private:

    int partition(vector<int>& nums, int left, int right) {
        if (left >= right) {
            return -1;
        }
        // 1. Divide
        int pivot_idx = left+rand()%(right-left+1);
        int pivot = nums[pivot_idx];
        // move to pivot to the right end
        swap(nums[right], nums[pivot_idx]);
        // Get left part and right part
        // left: all <= pivot
        // right: all >= pivot
        // utilizing two pointers - i,j
        int i = left, j = right-1;
        while (i <= j) {
            while (i <= right-1 && nums[i] <= pivot) {
                i++;
            }
            while (j >= left && nums[j] >= pivot) {
                j--;
            }
            // the check conditions are not met
            // Do Swap
            if (i < j) {
                swap(nums[i++], nums[j--]);
            }
        }
        swap(nums[i], nums[right]);
        return i;
    }

    void quick_sort(vector<int>& nums, int left, int right) {
        // 1. Divide
        int pivot_idx = partition(nums, left, right);
        if (pivot_idx == -1) {
            return;
        }
        // 2. Conquer
        // recursively call quick_sort to left part and right part
        quick_sort(nums, left, pivot_idx-1);
        quick_sort(nums, pivot_idx+1, right);
    }
    

public:
    vector<int> sortArray(vector<int>& nums) {
        int n = nums.size();
        quick_sort(nums, 0, n-1);
        return nums;
    }
};

// Time Coplexity: o(nlogn) or o(n^2)
// - Number of Divides (Recursion Stack Depth): from o(logn) to o(n)
// - Number of Comparisons in each "layer": o(n)
// Spcae Complexity: o(logn) or o(n) (=ecursion Stack Dapth)

// @lc code=end
