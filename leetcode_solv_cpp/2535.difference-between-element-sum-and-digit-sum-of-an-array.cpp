/*
 * @lc app=leetcode id=2535 lang=cpp
 *
 * [2535] Difference Between Element Sum and Digit Sum of an Array
 */

// @lc code=start
#include <vector>
class Solution {
public:
    int differenceOfSum(std::vector<int>& nums) {
        int element_sum = 0, digit_sum = 0;
        for (int num : nums) {
            element_sum += num;
            int tmp = num;
            while (tmp > 0) {
                digit_sum += tmp % 10;
                tmp /= 10;
            }
        }
        return element_sum - digit_sum;
    }
};
// @lc code=end

