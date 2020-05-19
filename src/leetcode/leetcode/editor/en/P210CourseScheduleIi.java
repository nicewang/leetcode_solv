//There are a total of n courses you have to take, labeled from 0 to n-1. 
//
// Some courses may have prerequisites, for example to take course 0 you have to
// first take course 1, which is expressed as a pair: [0,1] 
//
// Given the total number of courses and a list of prerequisite pairs, return th
//e ordering of courses you should take to finish all courses. 
//
// There may be multiple correct orders, you just need to return one of them. If
// it is impossible to finish all courses, return an empty array. 
//
// Example 1: 
//
// 
//Input: 2, [[1,0]] 
//Output: [0,1]
//Explanation: There are a total of 2 courses to take. To take course 1 you shou
//ld have finished   
//             course 0. So the correct course order is [0,1] . 
//
// Example 2: 
//
// 
//Input: 4, [[1,0],[2,0],[3,1],[3,2]]
//Output: [0,1,2,3] or [0,2,1,3]
//Explanation: There are a total of 4 courses to take. To take course 3 you shou
//ld have finished both     
//             courses 1 and 2. Both courses 1 and 2 should be taken after you f
//inished course 0. 
//             So one correct course order is [0,1,2,3]. Another correct orderin
//g is [0,2,1,3] . 
//
// Note: 
//
// 
// The input prerequisites is a graph represented by a list of edges, not adjace
//ncy matrices. Read more about how a graph is represented. 
// You may assume that there are no duplicate edges in the input prerequisites. 
//
// 
// Related Topics Depth-first Search Breadth-first Search Graph Topological Sort
//


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Java：Course Schedule II
public class P210CourseScheduleIi {
    public static void main(String[] args) {
        Solution solution = new P210CourseScheduleIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // adjacent[i]即为i的邻边列表
            List<List<Integer>> adjacent = new ArrayList<>();
            for(int i = 0; i < numCourses; i++) {
                adjacent.add(new ArrayList<>());
            }
            for(int[] side : prerequisites) {
                adjacent.get(side[0]).add(side[1]);
            }
            int[] flag = new int[numCourses];
            List<Integer> order = new ArrayList<>();
            for(int i = 0; i < numCourses; i++) {
                if(!dfs(adjacent, i, flag, order)) {
                    return new int[0];
                }
            }
            int[] result = new int[order.size()];
            for(int i = 0; i < order.size(); i++) {
                result[i] = order.get(i);
            }
            return result;
        }

        // 7ms 41.2MB
        // TODO 拓扑排序
        private boolean dfs(List<List<Integer>> adjacent, int curCourses, int[] flag, List<Integer> order) {
            if (flag[curCourses] == -1) {
                return true;
            }
            if (flag[curCourses] == 1) {
                return false;
            }
            flag[curCourses] = 1;
            for (int adjacentSide : adjacent.get(curCourses)) {
                if (!dfs(adjacent, adjacentSide, flag, order)) {
                    return false;
                }
            }
            flag[curCourses] = -1;
            order.add(curCourses);
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}