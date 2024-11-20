/*
 * @lc app=leetcode id=3244 lang=cpp
 *
 * [3244] Shortest Distance After Road Addition Queries II
 */

// @lc code=start
#include <vector>
#include <numeric>

using std::vector;

class Solution {
public:
    vector<int> shortestDistanceAfterQueries(int n, vector<vector<int>>& queries) {
        vector<int> roads(n);
        iota(roads.begin(), roads.end(), 1);
        vector<int> res;
        int dist = n - 1;
        for (auto &query : queries) {
            int k = roads[query[0]];
            roads[query[0]] = query[1];
            while (k != -1 && k < query[1]) {
                int t = roads[k];
                roads[k] = -1;
                k = t;
                dist--;
            }
            res.push_back(dist); 
        }
        return res;
    }
};
// @lc code=end

