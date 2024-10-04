/*
 * @lc app=leetcode id=1227 lang=cpp
 *
 * [1227] Airplane Seat Assignment Probability
 */

// @lc code=start
#include <vector>

class Solution {
public:
    // Way 1: Dynamic Programming -- Exceed the time limit
    double nthPersonGetsNthSeat_1(int n) {
        if (n == 1) {
            return 1.0;
        }
        std::vector<double> dp(n, 0);
        dp[0] = 1.0;
        dp[1] = 0.5;
        for (int i = 3; i <= n; i++) {
            double tmp = 0.0;
            for (int j = 2; j <= n-1; j++) {
                tmp += dp[j-1];
            }
            dp[i-1] = (1.0 + tmp) / i;
        }
        return dp[n-1];
    }

    // Way 2: Mathematic -- Simplify dynamic programming transfer equation
    double nthPersonGetsNthSeat(int n) {
        return n > 1 ? 0.5 : 1.0;
    }
};
// @lc code=end

