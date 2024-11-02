/*
 * @lc app=leetcode id=3226 lang=cpp
 *
 * [3226] Number of Bit Changes to Make Two Integers Equal
 */

// @lc code=start
class Solution {
public:
    int minChanges(int n, int k) {
        int res = 0;
        while (n > 0 || k > 0) {
            if ((n & 1) == 0 && (k & 1) == 1) {
                return -1;
            }
            if ((n & 1) == 1 && (k & 1) == 0) {
                res++;
            }
            n >>= 1;
            k >>= 1;
        }
        return res;
    }
};
// @lc code=end

