/*
 * @lc app=leetcode id=3206 lang=cpp
 *
 * [3206] Alternating Groups I
 */

// @lc code=start
#include <vector>

using std::vector;

class Solution {
public:
    int numberOfAlternatingGroups(vector<int>& colors) {
        int n = colors.size();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (colors[i] != colors[(i - 1 + n) % n] && colors[i] != colors[(i + 1) % n]) {
                res += 1;
            }
        }
        return res;
    }
};
// @lc code=end

