/*
 * @lc app=leetcode id=910 lang=cpp
 *
 * [910] Smallest Range II
 */

// @lc code=start
#include <vector>

class Solution {
public:
    int smallestRangeII(std::vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int mi = nums[0], ma = nums.back();
        int res = ma - mi;
        for (int i = 0; i < nums.size() - 1; i++) {
            int a = nums[i], b = nums[i + 1];
            res = std::min(res, std::max(ma - k, a + k) - std::min(mi + k, b - k));
        }
        return res;
    }
};
// @lc code=end

