/*
 * @lc app=leetcode id=908 lang=cpp
 *
 * [908] Smallest Range I
 */

// @lc code=start
#include <vector>

class Solution {
public:
    int smallestRangeI(std::vector<int>& nums, int k) {
        int minNum = *min_element(nums.begin(), nums.end());
        int maxNum = *max_element(nums.begin(), nums.end());
        return maxNum - minNum <= 2 * k ? 0 : maxNum - minNum - 2 * k;
    }
};
// @lc code=end

