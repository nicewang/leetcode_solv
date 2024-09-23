/*
 * @lc app=leetcode id=1014 lang=cpp
 *
 * [1014] Best Sightseeing Pair
 */

// @lc code=start
#include <vector>
#include <algorithm>
class Solution {
public:
    // Dynamic Programming: Simplified Space Complexity
    int maxScoreSightseeingPair(std::vector<int>& values) {
        int n = values.size();
        // dp[j]: max value of pair(i,j), i from [0, j-1]
        // vector<int> dp(n);
        int ans = values[0] - 0;
        int max_val_i = ans;
        // dp[0] = ans;
        for (int j = 1; j < n; j++) {
            // dp[j] = max_val_i + values[j] - j;
            // ans = max(ans, dp[j]);
            int tmp = max_val_i + values[j] - j;
            ans = std::max(ans, tmp);
            max_val_i = std::max(max_val_i, values[j]+j);
        }
        return ans;
    }
};
// @lc code=end

