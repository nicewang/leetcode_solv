/*
 * @lc app=leetcode id=2516 lang=cpp
 *
 * [2516] Take K of Each Character From Left and Right
 */

// @lc code=start
#include <string>
#include <vector>
#include <algorithm>
class Solution {
public:
    int takeCharacters(std::string s, int k) {
        std::vector<int> cnt(3, 0);
        int len = s.size();
        int ans = len;
        for (int i = 0; i < len; i++) {
            cnt[s[i] - 'a']++;
        }
        if (cnt[0] >= k && cnt[1] >= k && cnt[2] >= k) {
            ans = std::min(ans, len);
        } else {
            return -1;
        }

        int l = 0;
        for (int r = 0; r < len; r++) {
            cnt[s[r] - 'a']--;
            while (l < r && (cnt[0] < k || cnt[1] < k || cnt[2] < k)) {
                cnt[s[l] - 'a']++;
                l++;
            }
            if (cnt[0] >= k && cnt[1] >= k && cnt[2] >= k) {
                ans = std::min(ans, len - (r - l + 1));
            }
        }

        return ans;
    }
};
// @lc code=end

