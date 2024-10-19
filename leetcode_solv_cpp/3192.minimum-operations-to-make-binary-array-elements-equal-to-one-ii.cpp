/*
 * @lc app=leetcode id=3192 lang=cpp
 *
 * [3192] Minimum Operations to Make Binary Array Elements Equal to One II
 */

// @lc code=start
#include <vector>

class Solution {
public:
    int minOperations(std::vector<int>& nums) {
        int operation = 0;
        for (int num : nums) {
            if (num == (operation % 2)) {
                operation++;
            }
        }
        return operation;
    }
};
// @lc code=end

