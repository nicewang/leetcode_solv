/*
 * @lc app=leetcode id=3274 lang=cpp
 *
 * [3274] Check if Two Chessboard Squares Have the Same Color
 */

// @lc code=start
#include <string>

using std::string;

class Solution {
public:
    bool checkTwoChessboards(string coordinate1, string coordinate2) {
        return (coordinate1[0] - coordinate2[0] + coordinate1[1] - coordinate2[1]) % 2 == 0;
    }
};

// @lc code=end

