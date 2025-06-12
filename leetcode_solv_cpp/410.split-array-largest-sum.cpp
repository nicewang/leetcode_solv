/*
 * @lc app=leetcode id=410 lang=cpp
 *
 * [410] Split Array Largest Sum
 */


// @lc code=start

// Problem Link: https://leetcode.com/problems/split-array-largest-sum/description/

// utilize dynamic-programming and prefix-sum

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int splitArray(vector<int>& nums, int k) {

        int n = nums.size();

        // sum(i,j) = prefix_sum[j] - prefix_sum[i-1]
        // dp[i][k]: k times split
        //           the min largest sub-sum of nums[0, i]
        // dp[i+1][k] = min(max(dp[j][k-1], sum(j+1, i+1)) for j in [0, i])

        vector<int> prefix_sum(n, 0);
        prefix_sum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix_sum[i] = prefix_sum[i-1] + nums[i];
        }

        vector<int> max_i(n, 0);
        max_i[0] = nums[0];
        for (int i = 1; i < n; i++) {
            max_i[i] = max(max_i[i-1], nums[i]);
        }

        vector<vector<int>> dp;
        for (int i = 0; i < n; i++) {
            vector<int> tmp(k+1, 0);
            dp.push_back(tmp);
        }
        for (int i = 0; i < n; i++) {
            dp[i][1] = prefix_sum[i];
        }
        for (int i = 1; i < n; i++) {
            for (int k1 = 2; k1 <= min(i+1,k); k1++) {
                if (k1 == i+1) {
                    dp[i][k1] = max_i[i];
                    // cout << "i:" << i << endl;
                    // cout << "k:" << k1 << endl;
                    // cout << dp[i][k1] << endl;
                    continue;
                }
                dp[i][k1] = dp[i][1];
                // dp[i][k1] = INT_MAX;
                // for (int j = k1-1; j < i; j++) {
                for (int j = 0; j < i; j++) {
                    int tmp = max(dp[j][k1-1], prefix_sum[i]-prefix_sum[j]);
                    dp[i][k1] = min(dp[i][k1], tmp);
                }
                // cout << "i:" << i << endl;
                // cout << "k:" << k1 << endl;
                // cout << dp[i][k1] << endl;
            }
        }
        return dp[n-1][k];
    }
};
// Time Complexity: o(n*k*n) for 3-layers loop
// Space Complexity: o(n*k)
//                   - o(n*k) for dp
//                   - o(n) for frefix_sum and max_i
// @lc code=end

