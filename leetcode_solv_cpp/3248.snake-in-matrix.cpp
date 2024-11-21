/*
 * @lc app=leetcode id=3248 lang=cpp
 *
 * [3248] Snake in Matrix
 */

// @lc code=start
#include <vector>
#include <string>

using std::vector;
using std::string;

class Solution {
public:
    int finalPositionOfSnake(int n, vector<string>& commands) {
        int ans = 0;
        for (const string& c: commands) {
            if (c[0] == 'U') {
                ans -= n;
            }
            else if (c[0] == 'D') {
                ans += n;
            }
            else if (c[0] == 'L') {
                --ans;
            }
            else {
                ++ans;
            }
        }
        return ans;
    }
};
// @lc code=end

