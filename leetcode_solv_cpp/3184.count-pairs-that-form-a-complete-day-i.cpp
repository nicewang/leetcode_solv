/*
 * @lc app=leetcode id=3184 lang=cpp
 *
 * [3184] Count Pairs That Form a Complete Day I
 */

// @lc code=start
#include <vector>

class Solution {
public:
    int countCompleteDayPairs(std::vector<int>& hours) {
        int ans = 0;
        for (int i = 1; i < hours.size(); i++) {
            for (int j = 0; j < i; j++) {
                if ((hours[i] + hours[j]) % 24 == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
};
// @lc code=end

