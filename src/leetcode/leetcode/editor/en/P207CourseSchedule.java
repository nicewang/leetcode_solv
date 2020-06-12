//There are a total of numCourses courses you have to take, labeled from 0 to nu
//mCourses-1. 
//
// Some courses may have prerequisites, for example to take course 0 you have to
// first take course 1, which is expressed as a pair: [0,1] 
//
// Given the total number of courses and a list of prerequisite pairs, is it pos
//sible for you to finish all courses? 
//
// 
// Example 1: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: true
//Explanation: There are a total of 2 courses to take. 
//             To take course 1 you should have finished course 0. So it is poss
//ible.
// 
//
// Example 2: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//Output: false
//Explanation: There are a total of 2 courses to take. 
//             To take course 1 you should have finished course 0, and to take c
//ourse 0 you should
//             also have finished course 1. So it is impossible.
// 
//
// 
// Constraints: 
//
// 
// The input prerequisites is a graph represented by a list of edges, not adjace
//ncy matrices. Read more about how a graph is represented. 
// You may assume that there are no duplicate edges in the input prerequisites. 
//
// 1 <= numCourses <= 10^5 
// 
// Related Topics Depth-first Search Breadth-first Search Graph Topological Sort
//


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Java：Course Schedule
public class P207CourseSchedule{
    public static void main(String[] args) {
        Solution solution = new P207CourseSchedule().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //  解法一：dfs 3ms  39.6MB
    // TODO 注释及bfs TopSort解法
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // adjacent[i]即为i的邻边列表
        List<List<Integer>> adjacent = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            adjacent.add(new ArrayList<>());
        }
        for(int[] side : prerequisites) {
            adjacent.get(side[0]).add(side[1]);
        }
        int[] flag = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(adjacent, i, flag)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> adjacent, int curCourses, int[] flag) {
        if(flag[curCourses] == -1) {
            return true;
        }
        if(flag[curCourses] == 1) {
            return false;
        }
        flag[curCourses] = 1;
        for(int adjacentSide : adjacent.get(curCourses)) {
            if(!dfs(adjacent, adjacentSide, flag)) {
                return false;
            }
        }
        flag[curCourses] = -1;
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}