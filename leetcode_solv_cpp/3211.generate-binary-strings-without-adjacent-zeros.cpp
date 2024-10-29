/*
 * @lc app=leetcode id=3211 lang=cpp
 *
 * [3211] Generate Binary Strings Without Adjacent Zeros
 */

// @lc code=start
#include <vector>
#include <string>

class Solution {
public:
    std::vector<std::string> validStrings(int n) {
        std::vector<std::string> res;
        std::string str;

        std::function<void(std::string &)> dfs = [&](std::string& str) {
            if (str.size() == n) {
                res.push_back(str);
                return;
            }
            if (str.empty() || str.back() == '1') {
                str.push_back('0');
                dfs(str);
                str.pop_back();
            }
            str.push_back('1');
            dfs(str);
            str.pop_back();
        };
        
        dfs(str);
        return res;
    }
};
// @lc code=end

