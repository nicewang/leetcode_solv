/*
 * @lc app=leetcode id=2073 lang=cpp
 *
 * [2073] Time Needed to Buy Tickets
 */

// @lc code=start
#include <vector>
#include <algorithm>
class Solution {
public:
    int timeRequiredToBuy(std::vector<int>& tickets, int k) {
        int n = tickets.size();
        int res = 0;
        for (int i = 0; i < n; ++i){
            if (i <= k){
                res += std::min(tickets[i], tickets[k]);
            }
            else{
                res += std::min(tickets[i], tickets[k] - 1);
            }
        }
        return res;
    }
};
// @lc code=end

