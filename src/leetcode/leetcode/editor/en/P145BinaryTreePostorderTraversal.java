//Given a binary tree, return the postorder traversal of its nodes' values. 
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
//Output: [3,2,1]
// 
//
// Follow up: Recursive solution is trivial, could you do it iteratively? 
// Related Topics Stack Tree


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Java：Binary Tree Postorder Traversal
public class P145BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P145BinaryTreePostorderTraversal().new Solution();
        // TO TEST
        TreeNode root = new P145BinaryTreePostorderTraversal().new TreeNode(1);
        root.left = new P145BinaryTreePostorderTraversal().new TreeNode(2);
        root.right = new P145BinaryTreePostorderTraversal().new TreeNode(3);
        root.left.left = new P145BinaryTreePostorderTraversal().new TreeNode(4);
        root.left.right = new P145BinaryTreePostorderTraversal().new TreeNode(5);
        root.right.left = new P145BinaryTreePostorderTraversal().new TreeNode(6);
        root.right.right = new P145BinaryTreePostorderTraversal().new TreeNode(7);
        List<Integer> res = solution.bfsTraversal(root);
        for(int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
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
        //  0ms 37.7MB
        public List<Integer> postorderTraversal(TreeNode root) {
            LinkedList<Integer> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()) {
                TreeNode node = queue.pollLast();
                res.addFirst(node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right !=  null) {
                    queue.add(node.right);
                }
            }
            return res;
        }

        // further more: bfs遍历
        public List<Integer> bfsTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()) {
                TreeNode treeNode = queue.remove();
                res.add(treeNode.val);
                if(treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if(treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class TreeNode {
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

