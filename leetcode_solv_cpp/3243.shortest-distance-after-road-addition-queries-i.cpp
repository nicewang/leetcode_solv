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
using std::min;

class Solution {
public:

    vector<int> ans; // len=m

    // Way-1: BFS
    // Time Complexity: o(m*(n+m))
    // Space Complexity: o(n+m)
    // m=queries.size()
    vector<vector<int>> edges; // space=count(edges)=(n-1)+m
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

    // Way-2: Dynamic Programming
    // Time Complexity: o(m*(n+m))
    // Space Complexity: o(n+m)
    // m=queries.size()
    vector<vector<int>> prev; // space=count(edges)=(n-1)+m
    vector<int> shortestDistanceAfterQueries(int n, vector<vector<int>>& queries) {
        prev.resize(n);
        vector<int> dp(n);
        for (int i = 1; i < n; i++) {
            prev[i].push_back(i-1);
            dp[i] = i;
        }
        // dp[i]: shortest path from 0 to i
        // dp[i] = min(dp[j]+1) for j in prev[i]
        for (const auto& edge : queries) {
            // edge[u, v]
            int u = edge[0];
            int v = edge[1];
            prev[v].push_back(u);
            // Option 1
            // for (int j = v; j < n; j++) {
            //     for (int prev_j : prev[j]) {
            //         dp[j] = min(dp[prev_j]+1, dp[j]);
            //     }
            // }
            // Option 2: for time efficiency
            if (dp[u]+1 < dp[v]) {
                dp[v] = dp[u]+1;
                // max time complexity: o(count(prev))=o(count(edges))
                for (int j = v+1; j < n; j++) {
                    for (int prev_j : prev[j]) {
                        if (prev_j < v) {
                            continue;
                        }
                        dp[j] = min(dp[prev_j]+1, dp[j]);
                    }
                }
            }
            ans.push_back(dp[n-1]);
        }
        return ans;
    }
};
// @lc code=end

