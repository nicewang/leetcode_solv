/*
 * @lc app=leetcode id=2187 lang=cpp
 *
 * [2187] Minimum Time to Complete Trips
 */

// @lc code=start
#include <vector>

class Solution {
public:
    long long minimumTime(std::vector<int>& time, int totalTrips) {
        auto check = [&](long long t) -> bool {
            long long cnt = 0;
            for (int period: time) {
                cnt += t / period;
            }
            return cnt >= totalTrips;
        };
        
        long long l = 1;
        long long r = (long long) totalTrips * *max_element(time.begin(), time.end());
        while (l < r) {
            long long mid = l + (r - l) / 2;
            if (check(mid)) {
                r = mid;
            } else {
              l = mid + 1;
            }
        }
        return l;
    }
};
// @lc code=end

