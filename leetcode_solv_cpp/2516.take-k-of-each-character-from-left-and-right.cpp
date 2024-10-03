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
    // Quick and Slow Double Pointers
    int takeCharacters(std::string s, int k) {
        int n = s.size();
        std::vector<int> cnt(3, 0);
        for (int i = 0; i < n; i++) {
            cnt[s[i] - 'a']++;
        }
        if (cnt[0] < k || cnt[1] < k || cnt[2] < k) {
            return -1;
        }
        int l = 0, r = 0;
        int res = n;
        while (r < n) {
            cnt[s[r] - 'a']--;
            while (cnt[0] < k || cnt[1] < k || cnt[2] < k) {
                if (l == r) {
                    break;
                }
                cnt[s[l] - 'a']++;
                l++;
            }
            // to keep: except [l, r], there are enough 'a', 'b' and 'c'
            if (cnt[0] >= k && cnt[1] >= k && cnt[2] >= k) {
                res = std::min(res, n-(r-l+1));
            }
            r++;
        }
        return res;
    }
};
// @lc code=end

