/*
 * @lc app=leetcode id=3238 lang=cpp
 *
 * [3238] Find the Number of Winning Players
 */

// @lc code=start
#include <vector>

using std::vector;

class Solution {
public:
    int winningPlayerCount(int n, vector<vector<int>>& pick) {
        vector<vector<int>> cnt(n, vector<int>(11));
        for (auto &p : pick) {
            cnt[p[0]][p[1]]++;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 10; j++) {
                if (cnt[i][j] > i) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
};
// @lc code=end

