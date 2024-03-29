//Given a 2d grid map of '1's (land) and '0's (water), count the number of islan
//ds. An island is surrounded by water and is formed by connecting adjacent lands 
//horizontally or vertically. You may assume all four edges of the grid are all su
//rrounded by water. 
//
// Example 1: 
//
// 
//Input:
//11110
//11010
//11000
//00000
//
//Output: 1
// 
//
// Example 2: 
//
// 
//Input:
//11000
//11000
//00100
//00011
//
//Output: 3
// Related Topics Depth-first Search Breadth-first Search Union Find


package leetcode.leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;

//Java：Number of Islands
public class P200NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new P200NumberOfIslands().new Solution();
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(solution.numIslands(grid));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // TODO 并查集解法
        // 解法二：bfs 6ms 42.5MB 时间复杂度O(MN) 空间复杂度O(min(M,N))
        public int numIslands(char[][] grid) {
            if(grid == null || grid.length == 0) {
                return 0;
            }
            int nr = grid.length;
            int nc = grid[0].length;
            int numsIsland = 0;
            for(int r = 0; r < nr; r++) {
                for(int c = 0; c < nc; c++) {
                    if(grid[r][c] == '1') {
                        numsIsland++;
                        grid[r][c] = '0';
                        Queue<Integer> queue = new LinkedList<>();
                        queue.add(r*nc+c);
                        while(!queue.isEmpty()) {
                            int id = queue.remove();
                            int row = id / nc;
                            int col = id % nc;
                            if(row-1 >= 0 && grid[row-1][col] == '1') {
                                grid[row-1][col] = '0';
                                queue.add(nc*(row-1)+col);
                            }
                            if(row+1 < nr && grid[row+1][col] == '1') {
                                grid[row+1][col] = '0';
                                queue.add(nc*(row+1)+col);
                            }
                            if(col-1 >= 0 && grid[row][col-1] == '1') {
                                grid[row][col-1] = '0';
                                queue.add(nc*row+col-1);
                            }
                            if(col+1 < nc && grid[row][col+1] == '1') {
                                grid[row][col+1] = '0';
                                queue.add(nc*row+col+1);
                            }
                        }
                    }

                }
            }
            return numsIsland;
        }
        // 解法一：dfs递归 4ms 42.2MB 时间复杂度O(MN) 空间复杂度O(MN)
        boolean newIsland = false;
        public int numIslandsOne(char[][] grid) {
            if (grid == null || grid.length <= 0 || grid[0].length <= 0) {
                return 0;
            }
            int count = 0;
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    find(grid, i, j, visited);
                    if (newIsland) {
                        count++;
                    }
                    newIsland = false;
                }
            }
            return count;
        }

        private void find(char[][] grid, int i, int j, boolean[][] visited) {
            int len_i = grid.length;
            int len_j = grid[0].length;
            if (i < 0 || i >= len_i || j < 0 || j >= len_j || grid[i][j] == '0' || visited[i][j]) {
                return;
            }
            newIsland = true;
            visited[i][j] = true;
            find(grid, i - 1, j, visited);
            find(grid, i + 1, j, visited);
            find(grid, i, j - 1, visited);
            find(grid, i, j + 1, visited);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}