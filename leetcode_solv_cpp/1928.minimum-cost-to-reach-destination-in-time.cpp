/*
 * @lc app=leetcode id=1928 lang=cpp
 *
 * [1928] Minimum Cost to Reach Destination in Time
 */

// @lc code=start
#include <climits>
#include <vector>
#include <algorithm>

class Solution {
private:
    static constexpr int INFTY = INT_MAX / 2;

public:
    int minCost(int maxTime, std::vector<std::vector<int>>& edges, std::vector<int>& passingFees) {
        int n = passingFees.size();
        std::vector<std::vector<int>> f(maxTime + 1, std::vector<int>(n, INFTY));
        f[0][0] = passingFees[0];
        for (int t = 1; t <= maxTime; ++t) {
            for (const auto& edge: edges) {
                int i = edge[0], j = edge[1], cost = edge[2];
                if (cost <= t) {
                    f[t][i] = std::min(f[t][i], f[t - cost][j] + passingFees[i]);
                    f[t][j] = std::min(f[t][j], f[t - cost][i] + passingFees[j]);
                }
            }
        }

        int ans = INFTY;
        for (int t = 1; t <= maxTime; ++t) {
            ans = std::min(ans, f[t][n - 1]);
        }
        return ans == INFTY ? -1 : ans;
    }
};
// @lc code=end

