/*
 * @lc app=leetcode id=3175 lang=cpp
 *
 * [3175] Find The First Player to win K Games in a Row
 */

// @lc code=start
#include <vector>

class Solution {
public:
    int findWinningPlayer(std::vector<int>& skills, int k) {
        int n = skills.size();
        int cnt = 0;
        int i = 0, last_i = 0;
        while (i < n) {
            int j = i + 1; 
            while (j < n && skills[j] < skills[i] && cnt < k) {
                j++;
                cnt++;
            }
            if (cnt == k) {
                return i;
            }
            cnt = 1;
            last_i = i;
            i = j;
        }
        return last_i;
    }
};
// @lc code=end

