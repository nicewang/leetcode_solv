//The thief has found himself a new place for his thievery again. There is only 
//one entrance to this area, called the "root." Besides the root, each house has o
//ne and only one parent house. After a tour, the smart thief realized that "all h
//ouses in this place forms a binary tree". It will automatically contact the poli
//ce if two directly-linked houses were broken into on the same night. 
//
// Determine the maximum amount of money the thief can rob tonight without alert
//ing the police. 
//
// Example 1: 
//
// 
//Input: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
//Output: 7 
//Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7. 
//
// Example 2: 
//
// 
//Input: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
//Output: 9
//Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
// Related Topics Tree Depth-first Search


package leetcode.leetcode.editor.en;

import apple.laf.JRSUIUtils;

import java.util.HashMap;

//Java：House Robber III
public class P337HouseRobberIii {
    public static void main(String[] args) {
        Solution solution = new P337HouseRobberIii().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        // 解法三：树形dp解法——在解法二的基础上改写成dp写法 0ms 39.5MB
        public int rob(TreeNode root) {
            int[] result = robInternal(root);
            return Math.max(result[0], result[1]);
        }

        private int[] robInternal(TreeNode root) {
            int[] result = new int[2];
            if(root == null) {
                return result;
            }
            int[] left = robInternal(root.left);
            int[] right = robInternal(root.right);
            // 不盗窃当前节点
            result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            // 盗窃当前节点
            result[1] = left[0] + right[0] + root.val;
            return result;
        }

        // 解法二：在解法一的基础上作改进——利用记忆化，解决重复子问题 3ms 39.6MB
        public int robTwo(TreeNode root) {
            return robInternalTwo(root, new HashMap<>());
        }

        private int robInternalTwo(TreeNode root, HashMap<TreeNode, Integer> memo) {
            if(root == null) {
                return 0;
            }
            if(memo.containsKey(root)) {
                return memo.get(root);
            }
            int res = root.val;
            if(root.left != null) {
                res += robInternalTwo(root.left.left, memo) + robInternalTwo(root.left.right, memo);
            }
            if(root.right != null) {
                res += robInternalTwo(root.right.left, memo) + robInternalTwo(root.right.right, memo);
            }
            int Res = Math.max(res, robInternalTwo(root.left, memo)+ robInternalTwo(root.right, memo));
            memo.put(root, Res);
            return Res;
        }


        // 解法一改写 865ms 39.5MB
        public int rob1(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int res = root.val;
            if (root.left != null) {
                res += rob1(root.left.left) + rob1(root.left.right);
            }
            if (root.right != null) {
                res += rob1(root.right.left) + rob1(root.right.right);
            }
            return Math.max(res, rob1(root.left) + rob1(root.right));
        }

        // 解法一：dfs(暴力递归+最优子结构) 2111ms 39.3MB
        public int robOne(TreeNode root) {
            return Math.max(dfs(root, true, 0), dfs(root, false, 0));
        }

        private int dfs(TreeNode root, boolean isVisited, int sum) {
            if (root == null) {
                return sum;
            }
            int res = sum;
            if (isVisited) {
                res += root.val;
                return dfs(root.left, false, res) + dfs(root.right, false, res) - res;
            } else {
                return Math.max(dfs(root.left, false, res), dfs(root.left, true, res))
                        + Math.max(dfs(root.right, false, res), dfs(root.right, true, res)) - res;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}