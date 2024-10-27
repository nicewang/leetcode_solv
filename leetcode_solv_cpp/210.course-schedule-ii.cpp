/*
 * @lc app=leetcode id=210 lang=cpp
 *
 * [210] Course Schedule II
 */

// @lc code=start
#include <vector>
#include <queue>

class Solution {
public:

    std::vector<std::vector<int>> edges;
    std::vector<int> path;

    // way-1: DFS
    // Time Complexity: o(m+n), m: num of pre_req, n: numCourses
    // Space Complexity: o(m+n)
    //                   o(m+n) for edges
    //                   o(n) for vis
    //                   o(n) for path
    //                   o(n) for extra stack space needed for recursion invoke
    std::vector<int> vis;
    // use the stack to record the courses in order
    // and there we use vector.push_back+reverse to realize: using vector as a stack
    std::vector<int> findOrder_1(int numCourses, std::vector<std::vector<int>>& prerequisites) {
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
                return std::vector<int>();
            }
        }
        // Since: it's the stack who is needed to be returned
        //        but we use push_back to execute every adding-to-top-of-stack
        //        so need to reverse the vector, and the it is a stack
        std::reverse(path.begin(), path.end());
        return path;
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
        for (int prereq : edges[course_no]) {
            if (!dfs(prereq)) {
                return false;
            }
        }
        vis[course_no] = 1;
        // record the course
        path.push_back(course_no);
        return true;
    }

    // way-2: BFS
    // way-2: BFS
    // Time Complexity: o(m+n), m: num of pre_req, n: numCourses
    // Space Complexity: o(m+n)
    //                   o(m+n) for edges
    //                   o(n) for indegree
    //                   o(n) (at max) for queue
    //                   o(n) for path
    std::vector<int> indeg;
    // use the queue to record the courses in order
    // and there we use vector.push_back to realize: using vector as a queue
    std::vector<int> findOrder(int numCourses, std::vector<std::vector<int>>& prerequisites) {
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
            path.push_back(cur);
            vis_cnt++;
            for (int next : edges[cur]) {
                indeg[next]--;
                if (indeg[next] == 0) {
                    q.push(next);
                }
            }
        }
        return vis_cnt == numCourses ? path : std::vector<int>();
    }

};
// @lc code=end

