/*
 * @lc app=leetcode id=3258 lang=cpp
 *
 * [3258] Count Substrings That Satisfy K-Constraint I
 */

// @lc code=start
#include <string>

using std::string;

class Solution {
public:
    int countKConstraintSubstrings(string s, int k) {
        int n = s.size(), res = 0;
        for (int i = 0; i < n; ++i) {
            int count[2] = {0};
            for (int j = i; j < n; ++j) {
                count[s[j] - '0']++;
                if (count[0] > k && count[1] > k) {
                    break;
                }
                res++;
            }
        }
        return res;
    }
};
// @lc code=end

