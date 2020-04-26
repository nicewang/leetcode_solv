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
public class P102BinaryTreeLevelOrderTraversal{
    public static void main(String[] args) {
        Solution solution = new P102BinaryTreeLevelOrderTraversal().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // 1ms 39.8MB
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null) {
            return list;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(root.val);
        list.add(list1);
        TreeNode treeNode;
        queue.add(root);
        int depth = 0;
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
            if(depth > 0) {
                list.get(depth).add(treeNode.val);
            }
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