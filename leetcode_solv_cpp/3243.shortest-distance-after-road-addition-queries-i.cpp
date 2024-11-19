/*
 * @lc app=leetcode id=3243 lang=cpp
 *
 * [3243] Shortest Distance After Road Addition Queries I
 */

// @lc code=start
#include <vector>
#include <queue>

using std::vector;
using std::queue;

class Solution {
public:
    vector<vector<int>> edges; // space=count(edges)=(n-1)+m
    vector<int> ans; // len=m

    // Way-1: BFS
    // Time Complexity: o(m*(n+m))
    // Space Complexity: o(n+m)
    // m=queries.size()
    vector<int> shortestDistanceAfterQueries_1(int n, vector<vector<int>>& queries) {
        edges.resize(n);
        for (int i = 0; i < n-1; i++) {
            edges[i].push_back(i+1);
        }
        for (const auto& edge : queries) {
            edges[edge[0]].push_back(edge[1]);
            ans.push_back(bfs(n, edges));
        }
        return ans;
    }
    // Time Complexity: o(n+m)
    int bfs(int n, const vector<vector<int>>& edges) {
        // dist[i]: shortest distance from 0 to i
        vector<int> dist(n, -1);
        queue<int> q; // len <= n-1
        dist[0] = 0;
        q.push(0);
        while (!q.empty()) {
            int cur = q.front();
            q.pop();
            for (int neighbor : edges[cur]) {
                if (dist[neighbor] > 0) {
                    // Already pushed into queue
                    // and dist[neighbor] < dist[cur]+1
                    // (first-in with shorter dist)
                    continue;
                }
                dist[neighbor] = dist[cur] + 1;
                q.push(neighbor);
            }
        }
        return dist[n-1];
    }

    // // Way-2: Dynamic Programming
    // vector<int> shortestDistanceAfterQueries(int n, vector<vector<int>>& queries) {
    //     edges.resize(n);
    //     for (int i = 0; i < n-1; i++) {
    //         edges[i].push_back(i+1);
    //     }
    //     dp[i]: shortest path from 0 to i
    //     for (const auto& edge : queries) {
    //         edges[edge[0]].push_back(edge[1]);
    //         ans.push_back(bfs(n, edges));
    //     }
    //     return ans;
    // }
};
// @lc code=end

