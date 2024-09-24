/*
 * @lc app=leetcode id=2207 lang=cpp
 *
 * [2207] Maximize Number of Subsequences in a String
 */

// @lc code=start
#include <algorithm>
#include <string>

class Solution {
public:
    // Traversal + Frequency Statistics
    long long maximumSubsequenceCount(std::string text, std::string pattern) {
        long long res = 0;
        int cnt_0 = 0, cnt_1 = 0;
        for (char ch : text) {
            if (ch == pattern[1]) {
                cnt_1 += 1;
                res += cnt_0;
            }
            if (ch == pattern[0]) {
                cnt_0 += 1;
            }
        }
        return res + std::max(cnt_0, cnt_1);
    }
};
// @lc code=end

