/*
 * @lc app=leetcode id=3208 lang=cpp
 *
 * [3208] Alternating Groups II
 */

// @lc code=start
#include <vector>

using std::vector;

class Solution {
public:
    int numberOfAlternatingGroups(vector<int>& colors, int k) {
        int n = colors.size();
        int res = 0, cnt = 1;
        for (int i = -k + 2; i < n; i++) {
            if (colors[(i + n) % n] != colors[(i - 1 + n) % n]) {
                cnt += 1;
            } else {
                cnt = 1;
            }
            if (cnt >= k) {
                res += 1;
            }
        }
        return res;
    }
};
// @lc code=end

