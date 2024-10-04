/*
 * @lc app=leetcode id=912 lang=cpp
 *
 * [912] Sort an Array
 */

// @lc code=start
#include <vector>
#include <utility>

class Solution {
    // Quick Sort 1: exceed time limit
    int partition_1(std::vector<int>& nums, int l, int r, int pivot) {
        std::swap(nums[pivot], nums[r]);
        int i = l, j = r-1;
        while (i <= j) {
            if (nums[i] > nums[r]) {
                std::swap(nums[i], nums[j]);
                j--;
            } else {
                i++;
            }
        }
        std::swap(nums[i], nums[r]);
        return i;
    }

    // Quick Sort 2: exceed time limit
    int partition_2(std::vector<int>& nums, int l, int r, int pivot) {
        std::swap(nums[pivot], nums[r]);
        int i = l-1, j = l;
        while (j < r) {
            if (nums[j] <= nums[r]) {
                i++;
                std::swap(nums[i], nums[j]);
            }
            j++;
        }
        std::swap(nums[i+1], nums[r]);
        return i+1;
    }

    // Quick Sort 3: AC
    int partition(std::vector<int>& nums, int l, int r, int pivot) {
        int i = l, j = r;
        while (i <= j) {
            while (nums[i] < pivot) {
                i++;
            }
            while (nums[j] > pivot) {
                j--;
            }
            if (i <= j) {
                std::swap(nums[i], nums[j]);
                i++;
                j--;
            }
        }
        return i;
    }

    void quick_sort(std::vector<int>& nums, int l, int r) {
        if (l >= r) {
            return;
        }
        // int pivot = rand()%(r-l+1) + l;
        int pivot = nums[l+rand()%(r-l+1)];
        int m = partition(nums, l, r, pivot);
        quick_sort(nums, l, m-1);
        // quick_sort(nums, m+1, r);
        quick_sort(nums, m, r);
    }

    // Merge Sort
    std::vector<int> tmp;
    void merge_sort(std::vector<int>& nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = (l + r) >> 1;
        merge_sort(nums, l, m);
        merge_sort(nums, m+1, r);
        int i = l, j = m+1;
        int cnt = 0;
        while (i <= m && j <= r) {
            if (nums[i] <= nums[j]) {
                tmp[cnt++] = nums[i++];
            } else {
                tmp[cnt++] = nums[j++];
            }
        }
        while (i <= m) {
            tmp[cnt++] = nums[i++];
        }
        while (j <= r) {
            tmp[cnt++] = nums[j++];
        }
        for (int k = 0; k < r-l+1; k++) {
            nums[l+k] = tmp[k];
        }
    }

public:
    std::vector<int> sortArray(std::vector<int>& nums) {
        int n = nums.size();
        // quick_sort(nums, 0, n-1);
        tmp.resize(n, 0);
        merge_sort(nums, 0, n-1);
        return nums;
    }
};
// @lc code=end

