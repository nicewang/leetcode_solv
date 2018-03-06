package Depth_first_Search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * For example:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its depth = 3.
 */
public class MaximumDepthofBinaryTree {
	
	/**
	 * 先根遍历
	 * (先根遍历是一种递归调用的方式，而非递归实现类似先根遍历实现的即可用DFS)
	 * 用时1ms
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
        int result = 0;
        if(root == null)
        	return result;
        result = 1;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        result += Math.max(left, right);
        return result;
    }
	
	/**
	 * DFS解法
	 * 用时5ms
	 * @param root
	 * @return
	 */
	public int maxDepth_dfs(TreeNode root) {
		int result = 0;
		if(root == null)
			return result;
		Stack<TreeNode> theStack = new Stack<TreeNode>();
		theStack.push(root);
		// 下面这个栈value用来保存当前节点的深度信息
		Stack<Integer> value = new Stack<Integer>();
		value.push(1);
		while(!theStack.isEmpty()) {
			TreeNode tmp = theStack.pop();
			int val = value.pop();
			result = Math.max(result, val);
			if(tmp.left != null) {
				theStack.push(tmp.left);
				value.push(val+1);
			}
			if(tmp.right != null) {
				theStack.push(tmp.right);
				value.push(val+1);
			}
		}
		return result;
	}
	
	/**
	 * 这个BFS解法写得有点像DFS了
	 * 正而八经BFS解法在下面
	 * 用时5ms
	 * @param root
	 * @return
	 */
	public int maxDepth_bfs1(TreeNode root) {
		int result = 0;
		if(root == null)
			return result;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		// 下面这个队列value用来保存当前节点的深度信息
		Queue<Integer> value = new LinkedList<Integer>();
		value.offer(1);
		while(!q.isEmpty()) {
			TreeNode cur = q.poll();
			int val = value.poll();
			result = Math.max(result, val);
			if(cur.left != null) {
				q.offer(cur.left);
				value.offer(val+1);
			}
			if(cur.right != null) {
				q.offer(cur.right);
				value.offer(val+1);
			}		
		}
		return result;
	}
	
	/**
	 * BFS解法
	 * 用时3ms
	 * @param root
	 * @return
	 */
	public int maxDepth_bfs(TreeNode root) {
		int result = 0;
		if(root == null)
			return result;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				TreeNode cur = q.poll();
				if(cur.left != null)
					q.offer(cur.left);
				if(cur.right != null)
					q.offer(cur.right);
			}
			result++;
		}
		return result;
	}
}
