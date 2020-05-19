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
        // 解法一：dfs递归 4ms 42.2MB
        // TODO BFS
        boolean newIsland = false;
        public int numIslands(char[][] grid) {
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