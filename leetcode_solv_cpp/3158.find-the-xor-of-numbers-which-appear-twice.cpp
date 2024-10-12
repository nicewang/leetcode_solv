/*
 * @lc app=leetcode id=3158 lang=cpp
 *
 * [3158] Find the XOR of Numbers Which Appear Twice
 */

// @lc code=start
#include <vector>
#include <unordered_set>

class Solution {
public:
    int duplicateNumbersXOR(std::vector<int>& nums) {
        std::unordered_set<int> cnt;
        int res = 0;
        for (int num : nums) {
            if (cnt.find(num) != cnt.end()) {
                res ^= num;
            } else {
                cnt.emplace(num);
            }
        }
        return res;
    }
};
// @lc code=end

