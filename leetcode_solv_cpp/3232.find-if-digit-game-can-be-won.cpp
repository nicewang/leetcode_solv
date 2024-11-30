/*
 * @lc app=leetcode id=3232 lang=cpp
 *
 * [3232] Find if Digit Game Can Be Won
 */

// @lc code=start
#include <vector>

using std::vector;

class Solution {
public:
    bool canAliceWin(vector<int>& nums) {
        int single_digit_sum = 0;
        int double_digit_sum = 0;
        for (auto num : nums) {
            if (num < 10) {
                single_digit_sum += num;
            } else {
                double_digit_sum += num;
            }
        }
        return single_digit_sum != double_digit_sum;
    }
};

// @lc code=end

