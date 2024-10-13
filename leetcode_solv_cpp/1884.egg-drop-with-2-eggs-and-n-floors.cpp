/*
 * @lc app=leetcode id=1884 lang=cpp
 *
 * [1884] Egg Drop With 2 Eggs and N Floors
 */

// @lc code=start
#include <vector>
#include <algorithm>

class Solution {
public:
    // Dynamic Programming
    int twoEggDrop(int n) {
        std::vector<int> f(n + 1, INT_MAX / 2);
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int k = 1; k <= i; ++k) {
                f[i] = std::min(f[i], std::max(k - 1, f[i - k]) + 1);
            }
        }
        return f[n];
    }
};
// @lc code=end

