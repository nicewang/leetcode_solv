/*
 * @lc app=leetcode id=3222 lang=cpp
 *
 * [3222] Find the Winning Player in Coin Game
 */

// @lc code=start
#include <string>

using std::string;
using std::min;

class Solution {
public:
    string losingPlayer(int x, int y) {
        int ops = min(x, y / 4);
        return ops % 2 == 1 ? "Alice" : "Bob";
    }
};
// @lc code=end

