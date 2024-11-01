/*
 * @lc app=leetcode id=3259 lang=cpp
 *
 * [3259] Maximum Energy Boost From Two Drinks
 */

// @lc code=start
#include <vector>

using std::vector;
using std::max;

class Solution {
public:
    using ll = long long;
    long long maxEnergyBoost(vector<int>& energyDrinkA, vector<int>& energyDrinkB) {
        int n = energyDrinkA.size();
        vector<vector<ll>> d(n + 1, vector<ll>(2, 0));
        for (int i = 1; i <= n; i++) {
            d[i][0] = d[i - 1][0] + energyDrinkA[i - 1];
            d[i][1] = d[i - 1][1] + energyDrinkB[i - 1];
            if (i >= 2) {
                d[i][0] = max(d[i][0], d[i - 2][1] + energyDrinkA[i - 1]);
                d[i][1] = max(d[i][1], d[i - 2][0] + energyDrinkB[i - 1]);
            }
        }
        return max(d[n][0], d[n][1]);
    }
};
// @lc code=end

