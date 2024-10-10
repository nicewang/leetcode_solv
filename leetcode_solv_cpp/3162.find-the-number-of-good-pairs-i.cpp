/*
 * @lc app=leetcode id=3162 lang=cpp
 *
 * [3162] Find the Number of Good Pairs I
 */

// @lc code=start
#include <vector>
#include <unordered_map>
#include <algorithm>

class Solution {
public:
    // Way 1
    int numberOfPairs_1(std::vector<int>& nums1, std::vector<int>& nums2, int k) {
        int res = 0;
        for (int a : nums1) {
            for (int b : nums2) {
                if (a % (b * k) == 0) {
                    res++;
                }
            }
        }
        return res;
    }

    // Way 2
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

