package tree;

import Breath_first_Search.TreeNode;

/**
 * Given two binary trees and imagine that when you put one of them to cover the other,
 * some nodes of the two trees are overlapped while the others are not.
 * You need to merge them into a new binary tree.
 * The merge rule is that if two nodes overlap,
 * then sum node values up as the new value of the merged node.
 * Otherwise, the NOT null node will be used as the node of new tree.
 * 
 * Example 1:
 * Input:
 * 	       Tree 1                     Tree 2
 *           1                         2  
 *          / \                       / \                            
 *         3   2                     1   3                        
 *        /                           \   \                      
 *       5                             4   7          
 * Output:
 * Merged tree:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \ 
 * 	 5   4   7
 * Note: The merging process must start from the root nodes of both trees.                                   
 */
public class MergeTwoBinaryTrees {
	
	/**
	 * 就相当于一个二叉树的遍历
	 * 这里用的是中序遍历
	 * @param t1
	 * @param t2
	 * @return
	 */
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode newTree;
        if(t1 == null && t2 == null) {
        	return null;
        } else if(t1 == null) {
        	newTree = new TreeNode(t2.val);
        	newTree.left = mergeTrees(null, t2.left);
        	newTree.right = mergeTrees(null, t2.right);
        } else if(t2 == null) {
        	newTree = new TreeNode(t1.val);
        	newTree.left = mergeTrees(t1.left, null);
        	newTree.right = mergeTrees(t1.right, null);
        } else {
        	newTree = new TreeNode(t2.val+t1.val);
        	newTree.left = mergeTrees(t1.left, t2.left);
        	newTree.right = mergeTrees(t1.right, t2.right);
        }
        return newTree;
    }
	
}
