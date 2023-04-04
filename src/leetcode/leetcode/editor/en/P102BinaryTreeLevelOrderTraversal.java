//Given a binary tree, return the level order traversal of its nodes' values. (i
//e, from left to right, level by level). 
//
// 
//For example: 
//Given binary tree [3,9,20,null,null,15,7], 
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
// 
// 
//return its level order traversal as: 
// 
//[
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics Tree Breadth-first Search


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Java：Binary Tree Level Order Traversal
public class P102BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new P102BinaryTreeLevelOrderTraversal().new Solution();
        // TO TEST
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        solution.levelOrderOne(node);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        // 1ms 39.8MB
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            List<Integer> list1 = new ArrayList<>();
            list.add(list1);
            TreeNode treeNode;
            queue.add(root);
            int depth = 1;
            int curIndex = 0;
            int preCount = 1;
            int curMaxIndex = 0;
            while (!queue.isEmpty()) {
                if (curMaxIndex == 2 * preCount) {
                    depth++;
                    preCount = curIndex;
                    curIndex = 0;
                    curMaxIndex = 0;
                    List<Integer> listn = new ArrayList<>();
                    list.add(listn);
                }
                treeNode = queue.remove();
                list.get(depth-1).add(treeNode.val);
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                    curIndex++;
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                    curIndex++;
                }
                curMaxIndex += 2;
            }
            return list;
        }

        // 变一下题目——要求打印出来的是完全二叉树，非完全树的空节点用符号代替
        public List<List<Integer>> levelOrderOne(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int curIndex = 1;
            int blankCnt = 0;
            int depth = 0;
            while (!queue.isEmpty()) {
                if (curIndex == Math.pow(2, depth)) {
                    if (blankCnt >= Math.pow(2, depth)) {
                        return res;
                    }
                    curIndex = 0;
                    depth++;
                    blankCnt = 0;
                    List<Integer> listn = new ArrayList<>();
                    res.add(listn);
                }
                TreeNode node = queue.remove();
                res.get(depth - 1).add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                } else {
                    queue.add(new TreeNode(-1));
                    blankCnt++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                } else {
                    queue.add(new TreeNode(-1));
                    blankCnt++;
                }
                curIndex += 2;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}