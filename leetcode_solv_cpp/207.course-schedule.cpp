/*
 * @lc app=leetcode id=207 lang=cpp
 *
 * [207] Course Schedule
 */

// @lc code=start
#include <vector>
#include <queue>

// topological sort
class Solution {
public:

    std::vector<std::vector<int>> edges;

    // way-1: DFS
    // Time Complexity: o(m+n), m: num of pre_req, n: numCourses
    // Space Complexity: o(m+n)
    //                   o(m+n) for edges
    //                   o(n) for vis
    //                   o(n) for extra stack space needed for recursion invoke
    std::vector<int> vis;
    bool canFinish_1(int numCourses, std::vector<std::vector<int>>& prerequisites) {
        vis.resize(numCourses);
        edges.resize(numCourses);
        for (const auto& pair : prerequisites) {
            // Actually, the process of building the adjacent,
            // is ​​the process of graph building
            // pre_req -> follow_up
            edges[pair[1]].push_back(pair[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i)) {
                return false;
            }
        }
        return true;
    }

    bool dfs(int course_no) {
        if (vis[course_no] == 1) {
            // already traversed this node before
            // and no circle -> DAG start from this node
            return true;
        }
        if (vis[course_no] == -1) {
            return false;
        }
        vis[course_no] = -1;
        for (int next : edges[course_no]) {
            if (!dfs(next)) {
                return false;
            }
        }
        vis[course_no] = 1;
        return true;
    }

    // way-2: BFS
    // Time Complexity: o(m+n), m: num of pre_req, n: numCourses
    // Space Complexity: o(m+n)
    //                   o(m+n) for edges
    //                   o(n) for indegree
    //                   o(n) (at max) for queue
    std::vector<int> indeg;
    bool canFinish(int numCourses, std::vector<std::vector<int>>& prerequisites) {
        indeg.resize(numCourses);
        edges.resize(numCourses);
        for (const auto& pair : prerequisites) {
            // Actually, the process of building the adjacent,
            // is ​​the process of graph building
            // pre_req -> follow_up
            edges[pair[1]].push_back(pair[0]);
            ++indeg[pair[0]];
        }
        std::queue<int> q;

        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0) {
                q.push(i);
            }
        }

        int vis_cnt = 0;
        while (!q.empty()) {
            int cur = q.front();
            q.pop();
            vis_cnt++;
            for (int next : edges[cur]) {
                indeg[next]--;
                if (indeg[next] == 0) {
                    q.push(next);
                }
            }
        }
        return vis_cnt == numCourses;
    }
};
// @lc code=end

