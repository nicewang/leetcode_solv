/*
 * @lc app=leetcode id=53 lang=cpp
 *
 * [53] Maximum Subarray
 */

// @lc code=start

// Problem Link: https://leetcode.com/problems/maximum-subarray/description/

// Utilize Dynamic-Programming

#include <vector>

using  namespace std;

class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        // initial steps
        int n = nums.size();
        int res = nums[0];
        // dp
        // dp[i]: 
        //      - the max-sum of subarray
        //      - which have to end at i
        vector<int> dp(n, 0);
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = max(dp[i-1], 0) + nums[i];
            res = max(dp[i], res);
        }
        return res;
    }

    // Time Complexity: o(n) for loop
    // Space Complexity: o(n) for dp
    //      |
    //      V
    // but Space Complexity can be simplified to o(1):
    // using single variable f instead of dp
    // update f while in loop
    //      |
    //      V

    int maxSubArray_spaceO1(vector<int>& nums) {
        // initial steps
        int n = nums.size();
        int res = nums[0];
        // dp
        // dp[i]: 
        //      - the max-sum of subarray
        //      - which have to end at i
        //      - but use f instead of dp and update it while in loop
        int f = nums[0];
        for (int i = 1; i < n; i++) {
            f = max(f, 0) + nums[i];
            res = max(f, res);
        }
        return res;
    }

};
// @lc code=end

