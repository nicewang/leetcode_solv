//Given inorder and postorder traversal of a tree, construct the binary tree. 
//
// Note: 
//You may assume that duplicates do not exist in the tree. 
//
// For example, given 
//
// 
//inorder = [9,3,15,20,7]
//postorder = [9,15,7,20,3] 
//
// Return the following binary tree: 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics Array Tree Depth-first Search


package leetcode.leetcode.editor.en;

import java.util.Arrays;

//Java：Construct Binary Tree from Inorder and Postorder Traversal
public class P106ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P106ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        solution.buildTree(inorder, postorder);
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 思路：inorder=left-tree+root+right-tree post-order=left-tree+right-tree+root
        // 14ms 90.8MB
        // TODO 1.时间复杂度+空间复杂度 2.堆栈解法(有么？)
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            int len = inorder.length;
            if (len == 0) {
                return null;
            }
            int root_val = postorder[len - 1];
            TreeNode root = new TreeNode(root_val);
            int left_len = 0;
            while (inorder[left_len] != root_val) {
                left_len++;
            }
            root.left = buildTree(Arrays.copyOfRange(inorder, 0, left_len), Arrays.copyOfRange(postorder, 0, left_len));
            root.right = buildTree(Arrays.copyOfRange(inorder, left_len + 1, len), Arrays.copyOfRange(postorder, left_len, len - 1));
            return root;
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