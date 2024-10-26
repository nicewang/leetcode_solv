/*
 * @lc app=leetcode id=207 lang=cpp
 *
 * [207] Course Schedule
 */

// @lc code=start
#include <vector>

// topological sort
class Solution {
public:
    // way-1: DFS
    bool canFinish(int numCourses, std::vector<std::vector<int>>& prerequisites) {
        std::vector<int> vis(numCourses);
        std::vector<std::vector<int>> adjacent(numCourses);
        for (std::vector<int>& pair : prerequisites) {
            // Actually, the process of building the adjacent,
            // is ​​the process of graph building
            // pre_req -> follow_up
            adjacent[pair[1]].push_back(pair[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adjacent, i, vis)) {
                return false;
            }
        }
        return true;
    }
    bool dfs(std::vector<std::vector<int>>& adjacent, int course_no, std::vector<int>& vis) {
        if (vis[course_no] == 1) {
            // already traversed this node before
            // and no circle -> DAG start from this node
            return true;
        }
        if (vis[course_no] == -1) {
            return false;
        }
        vis[course_no] = -1;
        for (int prereq : adjacent[course_no]) {
            if (!dfs(adjacent, prereq, vis)) {
                return false;
            }
        }
        vis[course_no] = 1;
        return true;
    }
};
// @lc code=end

