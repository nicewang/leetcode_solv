/*
 * @lc app=leetcode id=633 lang=cpp
 *
 * [633] Sum of Square Numbers
 */

// @lc code=start
#include <cmath>

class Solution {
public:
    // Two-Pointers
    // Time Complexity: o(sqrt(c))
    // Space Complexity: o(1)
    bool judgeSquareSum(int c) {
        long left = 0;
        long right = (int) sqrt(c);
        while (left <= right) {
            long sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }
};
// @lc code=end

