package Breath_first_Search;

import java.util.*;

/**
 * Given a binary tree, return the bottom-up level order
 * traversal of its nodes' values. (ie, from left to right, level by
 * level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *   3
 *  / \
 * 9   20
 *    /  \
 *   15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {

    public static void main(String args[]) {
        TreeNode root = new TreeNode(3);
        TreeNode left_l1 = new TreeNode(9);
        TreeNode right_l1 = new TreeNode(20);
        root.left = left_l1;
        root.right = right_l1;
        right_l1.left = new TreeNode(15);
        right_l1.right = new TreeNode(7);
        System.out.println(new BinaryTreeLevelOrderTraversal().levelOrderBottom(root).toString());
    }

    /**
     * Runtime: 1 ms, faster than 98.48% of Java online submissions for Binary Tree Level Order Traversal II.
     * Memory Usage: 21.2 MB, less than 88.75% of Java online submissions for Binary Tree Level Order Traversal II.
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null)
            return result;
        result.add(Arrays.asList(root.val));
        Queue<TreeNode> theQueue = new LinkedList<TreeNode>();
        theQueue.add(root);
        int countPerLayer = 1;
        while(!theQueue.isEmpty()) {
            int countNextLayer = 0;
            List<Integer> list = new ArrayList<Integer>();
            while(countPerLayer > 0){
                TreeNode tmp = theQueue.remove();
                if(tmp.left != null) {
                    theQueue.add(tmp.left);
                    countNextLayer++;
                    list.add(tmp.left.val);
                }
                if(tmp.right != null) {
                    theQueue.add(tmp.right);
                    countNextLayer++;
                    list.add(tmp.right.val);
                }
                countPerLayer--;
            }
            countPerLayer = countNextLayer;
            if(countPerLayer > 0)
                result.add(0,list);
        }
        return result;
    }

}