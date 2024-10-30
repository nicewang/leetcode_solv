/*
 * @lc app=leetcode id=3211 lang=cpp
 *
 * [3211] Generate Binary Strings Without Adjacent Zeros
 */

// @lc code=start
#include <vector>
#include <string>

using std::vector;
using std::string;
using std::function;

class Solution {
public:
    vector<string> validStrings(int n) {
        vector<string> res;
        std::string str;

        function<void(string &)> dfs = [&](string& str) {
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

