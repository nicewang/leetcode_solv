/*
 * @lc app=leetcode id=3164 lang=cpp
 *
 * [3164] Find the Number of Good Pairs II
 */

// @lc code=start
#include <vector>
#include <unordered_map>
#include <algorithm>

class Solution {
public:
    long long numberOfPairs(std::vector<int>& nums1, std::vector<int>& nums2, int k) {
        std::unordered_map<int, int> count, count2;
        int max1 = 0;
        for (int num : nums1) {
            count[num]++;
            max1 = std::max(max1, num);
        }
        for (int num : nums2) {
            count2[num]++;
        }
        long long res = 0;
        for (const auto& pair : count2) {
            int a = pair.first, cnt = pair.second;
            for (int b = a * k; b <= max1; b += a * k) {
                if (count.count(b) > 0) {
                    res += 1L * count[b] * cnt;
                }
            }
        }
        return res;

    }
};
// @lc code=end

