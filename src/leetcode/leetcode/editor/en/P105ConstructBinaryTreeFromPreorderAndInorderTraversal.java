//Given preorder and inorder traversal of a tree, construct the binary tree. 
//
// Note: 
//You may assume that duplicates do not exist in the tree. 
//
// For example, given 
//
// 
//preorder = [3,9,20,15,7]
//inorder = [9,3,15,20,7] 
//
// Return the following binary tree: 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics Array Tree Depth-first Search


package leetcode.leetcode.editor.en;

import java.util.Arrays;
import java.util.Collections;

//Java：Construct Binary Tree from Preorder and Inorder Traversal
public class P105ConstructBinaryTreeFromPreorderAndInorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // // 思路：post-order=root+left-tree+right-tree inorder=left-tree+root+right-tree
    // 12ms 90.9MB
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = inorder.length;
        if (len == 0) {
            return null;
        }
        int root_val = preorder[0];
        TreeNode root = new TreeNode(root_val);
        int left_len = 0;
        while (inorder[left_len] != root_val) {
            left_len++;
        }
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, left_len + 1), Arrays.copyOfRange(inorder, 0, left_len));
        root.right = buildTree(Arrays.copyOfRange(preorder, left_len + 1, len), Arrays.copyOfRange(inorder, left_len + 1, len));
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