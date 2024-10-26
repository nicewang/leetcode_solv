/*
 * @lc app=leetcode id=3181 lang=cpp
 *
 * [3181] Maximum Total Reward Using Operations II
 */

// @lc code=start
#include <vector>
#include <bitset>

class Solution {
public:
    int maxTotalReward(std::vector<int>& rewardValues) {
        int n = rewardValues.size();
        sort(rewardValues.begin(), rewardValues.end());
        if (n >= 2 && rewardValues[n - 2] == rewardValues[n - 1] - 1) {
            return 2 * rewardValues[n - 1] - 1;
        }
        std::bitset<100000> f0, f1;
        f0[0] = 1;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < rewardValues[i]) {
                f1[j] = f0[j];
                j++;
            }
            f0 |= f1 << rewardValues[i];
        }
        int res = 0;
        for (int i = 0; i < f0.size(); i++) {
            if (f0[i]) {
                res = i;
            }
        }
        return res;
    }
};
// @lc code=end

