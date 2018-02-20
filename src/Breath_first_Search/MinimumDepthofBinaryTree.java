package Breath_first_Search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is 
 * the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class MinimumDepthofBinaryTree {
	private Queue<TreeNode> theQueue = new LinkedList<TreeNode>();
	
	public static void main(String args[]) {
		TreeNode node_new = new TreeNode(1);
		node_new.left = new TreeNode(2);
		node_new.right = new TreeNode(3);
		node_new.left.left = new TreeNode(4);
		node_new.left.right = new TreeNode(5);
		node_new.right.left = new TreeNode(6);
		node_new.right.right = new TreeNode(7);
		node_new.left.left.left = new TreeNode(8);
		MinimumDepthofBinaryTree main = new MinimumDepthofBinaryTree();
		int min_depth = main.minDepth(node_new);
		System.out.println(min_depth);
	}
	
	/**
	 * 递归实现
	 * @param root
	 * @return
	 */
	public int minDepth(TreeNode root) {
		if(root == null)
			return 0;
		if(root.left == null)
			return minDepth(root.right) + 1;
		if(root.right == null)
			return minDepth(root.left) + 1;
		// 不用单独罗列root的左右根均为空的情况，因为这两个if已经包括了
		return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
	}
	
	/**
	 * BFS实现
	 * @param root
	 * @return
	 */
	public int minDepth_bfs(TreeNode root) {
        if(root == null)
        	return 0;
//        LinkedList<TreeNode> theQueue = new LinkedList<TreeNode>();  leetcode提交最好定义局部变量
        theQueue.offer(root);
//        int i = 0;
//        int count = (int)Math.pow(2, i);
//        int count_tmp = 0;
        int min_depth = 1;
        int lastNum = 1;
        int curNum = 0;
        while(!theQueue.isEmpty()) {
        	TreeNode node = theQueue.poll();
        	if(node.left == null && node.right == null)
        		return min_depth;
        	lastNum--;
        	if(node.left != null) {
        		theQueue.offer(node.left);
        		curNum++;
        	}
        	if(node.right != null) {
        		theQueue.offer(node.right);
        		curNum++;
        	}
//        	if(count_tmp == count) {
//        		i++;
//        		count = (int)Math.pow(2, i);
//        		count_tmp = 0;
//        		min_depth += 1;
//        	}
        	if(lastNum == 0) {
        		lastNum = curNum;
        		curNum = 0;
        		min_depth++;
        	}
        }
        return 0;
    }
}

