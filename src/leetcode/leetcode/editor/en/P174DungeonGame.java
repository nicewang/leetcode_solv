//
// The demons had captured the princess (P) and imprisoned her in the bottom-rig
//ht corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D gri
//d. Our valiant knight (K) was initially positioned in the top-left room and must
// fight his way through the dungeon to rescue the princess. 
//
// The knight has an initial health point represented by a positive integer. If 
//at any point his health point drops to 0 or below, he dies immediately. 
//
// Some of the rooms are guarded by demons, so the knight loses health (negative
// integers) upon entering these rooms; other rooms are either empty (0's) or cont
//ain magic orbs that increase the knight's health (positive integers). 
//
// In order to reach the princess as quickly as possible, the knight decides to 
//move only rightward or downward in each step. 
//
// 
//
// Write a function to determine the knight's minimum initial health so that he 
//is able to rescue the princess. 
//
// For example, given the dungeon below, the initial health of the knight must b
//e at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN. 
//
// 
// 
// 
// -2 (K) 
// -3 
// 3 
// 
// 
// -5 
// -10 
// 1 
// 
// 
// 10 
// 30 
// -5 (P) 
// 
// 
// 
//
// 
//
// Note: 
//
// 
// The knight's health has no upper bound. 
// Any room can contain threats or power-ups, even the first room the knight ent
//ers and the bottom-right room where the princess is imprisoned. 
// 
// Related Topics Binary Search Dynamic Programming


package leetcode.leetcode.editor.en;

import java.util.Arrays;

//Java：Dungeon Game
public class P174DungeonGame {
    public static void main(String[] args) {
        Solution solution = new P174DungeonGame().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法二：动态规划(二) 3ms 39.8MB 时间复杂度O(MXN) 空间复杂度O(MXN)
        public int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            // dp[i][j]表征进入i、j对应房间之前应有的最少血量
            int[][] dp = new int[m][n];
            dp[m - 1][n - 1] = dungeon[m - 1][n - 1] <= 0 ? 1 - dungeon[m - 1][n - 1] : 1;
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    int minn;
                    if (i == m - 1 && j == n - 1) {
                        continue;
                    } else if (i == m - 1) {
                        minn = dp[i][j + 1];
                    } else if (j == n - 1) {
                        minn = dp[i + 1][j];
                    } else {
                        minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                    }
                    dp[i][j] = Math.max(minn - dungeon[i][j], 1);
                }
            }
            return dp[0][0];
        }

        // 解法一：动态规划(一) 2ms 39.7MB 时间复杂度O(MXN) 空间复杂度O(MXN)
        public int calculateMinimumHPOne(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            // dp[i][j]表征进入i、j对应房间之前应有的最少血量
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            // TODO Attention
            dp[m - 1][n] = dp[m][n - 1] = 1;
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                    dp[i][j] = Math.max(minn - dungeon[i][j], 1);
                }
            }
            return dp[0][0];
        }

        // 解法三：dfs递归搜索 超出时间限制 通过用例数41/45
        public int calculateMinimumHPThree(int[][] dungeon) {
            return dfs(dungeon, 0, 0);
        }

        private int dfs(int[][] dungeon, int i, int j) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            if (i == m - 1 && j == n - 1) {
                return dungeon[i][j] <= 0 ? 1 - dungeon[i][j] : 1;
            } else if (i == m - 1) {
                return Math.max(dfs(dungeon, i, j + 1) - dungeon[i][j], 1);
            } else if (j == n - 1) {
                return Math.max(dfs(dungeon, i + 1, j) - dungeon[i][j], 1);
            } else {
                return Math.max(Math.min(dfs(dungeon, i, j + 1), dfs(dungeon, i + 1, j)) - dungeon[i][j], 1);
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}