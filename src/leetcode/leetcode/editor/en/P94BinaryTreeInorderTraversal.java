//Given a binary tree, return the inorder traversal of its nodes' values. 
//
// Example: 
//
// 
//Input: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//Output: [1,3,2] 
//
// Follow up: Recursive solution is trivial, could you do it iteratively? 
// Related Topics Hash Table Stack Tree


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：Binary Tree Inorder Traversal
// 中根遍历树是dfs遍历树(前/中/后根)的一种 此外还有bfs遍历树
public class P94BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P94BinaryTreeInorderTraversal().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // TODO 莫里斯方法
        // 解法二： 使用堆栈
        // 1ms 37.8MB
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode treeNode = root;
            while(treeNode != null || !stack.isEmpty()) {
                while(treeNode != null) {
                    // 把左子树栈
                    stack.push(treeNode);
                    treeNode = treeNode.left;
                }
                treeNode = stack.pop(); // 该TreeNode左子树为空 其为左叶子(叶子节点既没有左子树也没有右子树)或左子树为空的树
                res.add(treeNode.val);
                treeNode = treeNode.right;
            }
            return res;
        }

        // 解法一：递归解法
        // 0ms 37.6MB
        public List<Integer> inorderTraversalOne(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            traversal(root, res);
            return res;
        }
        private void traversal(TreeNode root, List<Integer> traversalRes) {
            if(root == null) {
                return;
            }
            traversal(root.left, traversalRes);
            traversalRes.add(root.val);
            traversal(root.right, traversalRes);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}