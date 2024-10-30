/*
 * @lc app=leetcode id=3216 lang=cpp
 *
 * [3216] Lexicographically Smallest String After a Swap
 */

// @lc code=start
#include <string>

using std::string;
using std::swap;

class Solution {
public:
    string getSmallestString(string s) {
        for (int i = 0; i + 1 < s.size(); i++) {
            if (s[i] > s[i + 1] && s[i] % 2 == s[i + 1] % 2) {
                swap(s[i], s[i + 1]);
                break;
            }
        }
        return s;
    }
};
// @lc code=end

