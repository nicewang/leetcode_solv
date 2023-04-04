//Given a binary tree, return the preorder traversal of its nodes' values. 
//
// Example: 
//
// 
//Input: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//Output: [1,2,3]
// 
//
// Follow up: Recursive solution is trivial, could you do it iteratively? 
// Related Topics Stack Tree


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：Binary Tree Preorder Traversal
public class P144BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P144BinaryTreePreorderTraversal().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：堆栈解法 1ms 38MB
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if(root == null) {
                return list;
            }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode treeNode = root;
            while(treeNode != null || !stack.isEmpty()) {
                while(treeNode != null) {
                    list.add(treeNode.val);
                    stack.push(treeNode);
                    treeNode = treeNode.left;
                }
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
            return list;
        }

        // 解法一：递归解法 0ms 37.9MB
        public List<Integer> preorderTraversalOne(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            find(root, list);
            return list;
        }

        private void find(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            list.add(root.val);
            find(root.left, list);
            find(root.right, list);
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