/*
 * @lc app=leetcode id=3194 lang=cpp
 *
 * [3194] Minimum Average of Smallest and Largest Elements
 */

// @lc code=start
#include <vector>
#include <limits>
#include <algorithm>

class Solution {
public:
    double minimumAverage(std::vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        double res = std::numeric_limits<double>::max();
        for (int i = 0; i < n / 2; i++) {
            res = std::min(res, (nums[i] + nums[n - 1 - i]) / 2.0);
        }
        return res;
    }
};
// @lc code=end

