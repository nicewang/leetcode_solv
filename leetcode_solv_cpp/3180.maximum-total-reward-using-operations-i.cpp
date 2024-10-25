/*
 * @lc app=leetcode id=3180 lang=cpp
 *
 * [3180] Maximum Total Reward Using Operations I
 */

// @lc code=start
#include <vector>

class Solution {
public:
    int maxTotalReward(std::vector<int>& rewardValues) {
        sort(rewardValues.begin(), rewardValues.end());
        // m: max_reward_val
        int m = rewardValues.back();
        int res = 0;
        // dp[i]: 
        // True: "sum(rewardVals) == i" exists
        // Greedly consider we pick the max_reward_val m, the pre_sum < m
        // So max_possible_reward_sum = pre_sum + m < 2m
        // So dp[i] range from dp[0] to dp[2m-1]
        std::vector<bool> dp(2*m);
        // "reward_sum == 0" is possible
        dp[0] = true;
        for (int x : rewardValues) {
            for (int reward_sum = 2*x-1; reward_sum >= x; reward_sum--) {
                // Since:
                // 0 =< pre_sum < x -> x =< reward_sum(=pre+x) < 2x
                if (dp[reward_sum-x]) {
                    // pre(=reward_sum-x) exists
                    dp[reward_sum] = true;
                    res = std::max(res, reward_sum);
                }
            }
        }
        return res;
    }
};
// @lc code=end

