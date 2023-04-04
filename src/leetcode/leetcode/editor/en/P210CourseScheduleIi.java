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

import java.util.*;

//Java：Course Schedule II
public class P210CourseScheduleIi {
    public static void main(String[] args) {
        Solution solution = new P210CourseScheduleIi().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法二：bfs TopSort解法 25ms 41.1MB
        // BFS 的总体思路：
        // 1.建立入度表，入度为 0 的节点先入队
        // 2.当队列不为空，节点出队，标记学完课程数量的变量加 1，并记录该课程
        // 3.将课程的邻居入度减 1
        // 4.若邻居课程入度为0,加入队列,用一个变量记录学完的课程数量,一个数组记录最终结果,简洁好理解。
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            if(numCourses == 0) {
                return new int[0];
            }
            // 建立入度表
            int[] inDegrees = new int[numCourses];
            for(int[] prerequisity : prerequisites) {
                inDegrees[prerequisity[0]]++;
            }
            Queue<Integer> queue = new LinkedList<>();
            for(int i = 0; i < inDegrees.length; i++) {
                if(inDegrees[i] == 0) {
                    queue.add(i);
                }
            }
            int count = 0;
            int[] res = new int[numCourses];
            while(!queue.isEmpty()) {
                int curCourse = queue.remove();
                res[count++] = curCourse;
                for(int[] prerequisity : prerequisites) {
                    if(prerequisity[1] == curCourse) {
                        inDegrees[prerequisity[0]]--;
                        if (inDegrees[prerequisity[0]] == 0) {
                            queue.add(prerequisity[0]);
                        }
                    }
                }
            }
            if(count == numCourses) {
                return res;
            }
            return new int[0];

        }

        // 解法一：dfs解法
        // dfs整体思路：
        // 1.建立邻接矩阵
        // 2.DFS 访问每一个课程,若存在环直接返回,status保存课程的访问状态,同一个栈保存课程的访问序列。
        public int[] findOrderOne(int numCourses, int[][] prerequisites) {
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
        private boolean dfs(List<List<Integer>> adjacent, int curCourses, int[] flag, List<Integer> order) {
            // -1：访问其依赖前标记 1：访问其依赖后标记
            // 如果依赖为1->说明依赖之前被访问过且未被回溯回来->故该节点为其依赖的依赖->形成环
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