/*
 * @lc app=leetcode id=3193 lang=cpp
 *
 * [3193] Count the Number of Inversions
 */

// @lc code=start
#include <vector>
#include <unordered_map>

class Solution {
public:
    int numberOfPermutations(int n, std::vector<std::vector<int>>& requirements) {
        const int MOD = 1e9 + 7;
        std::unordered_map<int, int> reqMap;
        int maxCnt = 0;
        reqMap[0] = 0;
        for (auto& req : requirements) {
            reqMap[req[0]] = req[1];
            maxCnt = std::max(maxCnt, req[1]);
        }
        if (reqMap[0]) {
            return 0;
        }

        std::vector<std::vector<int>> dp(n, std::vector<int>(maxCnt + 1, -1));
        std::function<int(int, int)> dfs = [&](int end, int cnt) -> int {
            if (end == 0) {
                return 1;
            }
            if (dp[end][cnt] != -1) {
                return dp[end][cnt];
            }
            if (reqMap.find(end - 1) != reqMap.end()) {
                int r = reqMap[end - 1];
                if (r <= cnt && cnt <= end + r) {
                    return dp[end][cnt] = dfs(end - 1, r);
                } else {
                    return dp[end][cnt] = 0;
                }
            } else {
                int sm = 0;
                for (int i = 0; i <= std::min(end, cnt); ++i) {
                    sm = (sm + dfs(end - 1, cnt - i)) % MOD;
                }
                return dp[end][cnt] = sm;
            }
        };

        return dfs(n - 1, reqMap[n - 1]);
    }
};
// @lc code=end

