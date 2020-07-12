//Given a binary tree, find its maximum depth. 
//
// The maximum depth is the number of nodes along the longest path from the root
// node down to the farthest leaf node. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// Given binary tree [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
//
// return its depth = 3. 
// Related Topics Tree Depth-first Search


package leetcode.leetcode.editor.en;

import javafx.util.Pair;

import java.util.Stack;

//Java：Maximum Depth of Binary Tree
public class P104MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new P104MaximumDepthOfBinaryTree().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        // 解法一：堆栈解法 4ms 39.8MB
        public int maxDepth(TreeNode root) {
            if(root == null) {
                return 0;
            }
            Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
            stack.push(new Pair<TreeNode, Integer>(root, 1));
            int max_depth = 0;
            while(!stack.isEmpty()) {
                Pair<TreeNode, Integer> node = stack.pop();
                TreeNode treeNode = node.getKey();
                Integer depth = node.getValue();
                max_depth = Math.max(max_depth, depth);
                if(treeNode.left != null) {
                    stack.push(new Pair<TreeNode, Integer>(treeNode.left, depth+1));
                }
                if(treeNode.right != null) {
                    stack.push(new Pair<TreeNode, Integer>(treeNode.right, depth+1));
                }
            }
            return max_depth;
        }

        // 解法一：递归解法 0ms 39.9MB
        public int maxDepthOne(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left_depth = maxDepthOne(root.left);
            int right_depth = maxDepthOne(root.right);
            return Math.max(left_depth, right_depth) + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}