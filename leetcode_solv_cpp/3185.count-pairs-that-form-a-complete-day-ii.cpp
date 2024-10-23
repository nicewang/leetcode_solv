/*
 * @lc app=leetcode id=3185 lang=cpp
 *
 * [3185] Count Pairs That Form a Complete Day II
 */

// @lc code=start
#include <vector>

class Solution {
public:
    long long countCompleteDayPairs(std::vector<int>& hours) {
        long long ans = 0;
        std::vector<int> cnt(24);
        for (int hour : hours) {
            ans += cnt[(24 - hour % 24) % 24];
            cnt[hour % 24]++;
        }
        return ans;
    }
};
// @lc code=end

