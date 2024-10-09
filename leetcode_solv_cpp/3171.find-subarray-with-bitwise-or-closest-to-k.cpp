/*
 * @lc app=leetcode id=3171 lang=cpp
 *
 * [3171] Find Subarray With Bitwise OR Closest to K
 */

// @lc code=start
#include <vector>
#include <algorithm>
#include <utility>

class Solution {
public:
    int minimumDifference(std::vector<int>& nums, int k) {
        int n = nums.size();
        std::vector<int> bits_max_pos(31, -1);
        std::vector<std::pair<int, int>> pos_to_bit;
        int res = INT_MAX;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 30; j++) {
                if (nums[i] >> j & 1) {
                    bits_max_pos[j] = i;
                }
            }
            pos_to_bit.clear();
            for (int j = 0; j <= 30; j++) {
                if (bits_max_pos[j] != -1) {
                    pos_to_bit.push_back(std::make_pair(bits_max_pos[j], j));
                }
            }
            sort(pos_to_bit.begin(), pos_to_bit.end(), std::greater<std::pair<int, int>>());
            int val = 0;
            for (int j = 0, p = 0; j < pos_to_bit.size(); ) {
                while (j < pos_to_bit.size() && pos_to_bit[j].first == pos_to_bit[p].first) {
                    val |= 1 << pos_to_bit[j].second;
                    j++;
                }
                res = std::min(res, abs(val - k));
                p = j;
            }
        }
        return res;
    }
};
// @lc code=end

