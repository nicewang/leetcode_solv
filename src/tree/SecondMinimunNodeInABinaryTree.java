package tree;

import java.util.LinkedList;
import java.util.Queue;

import Breath_first_Search.TreeNode;

/**
 * Given a non-empty special binary tree consisting of nodes with the non-negative value,
 * where each node in this tree has exactly two or zero sub-node.
 * If the node has two sub-nodes,
 * then this node's value is the smaller value among its two sub-nodes.
 * Given such a binary tree, 
 * you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 * If no such second minimum value exists, output -1 instead.
 * 
 * Example 1:
 * Input:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 * 
 * Example 2:
 * Input:
 *     2
 *    / \
 *   2   2
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 */
public class SecondMinimunNodeInABinaryTree {
	
	public static void main(String args[]) {
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		SecondMinimunNodeInABinaryTree instance = new SecondMinimunNodeInABinaryTree();
		int result = instance.findSecondMinimumValue_myself(root);
		System.out.println(result);
		TreeNode root1 = new TreeNode(2);
		result = instance.findSecondMinimumValue_myself(root1);
		System.out.println(result);
		result = instance.findSecondMinimumValue_divideConquer(root);
		System.out.println(result);
		result = instance.findSecondMinimumValue_divideConquer(root1);
		System.out.println(result);
		result = instance.findSecondMinimumValue_bfs(root);
		System.out.println(result);
		result = instance.findSecondMinimumValue_bfs(root1);
		System.out.println(result);
		result = instance.findSecondMinimumValue_dfs1(root);
		System.out.println(result);
		result = instance.findSecondMinimumValue_dfs1(root1);
		System.out.println(result);
	}
	
	public int findSecondMinimumValue_myself(TreeNode root) {
		return find(root, 0, root.val);
    }

	private int find(TreeNode root, int i, int val) {
		// TODO Auto-generated method stub
		int i1 = i + 1;
        if(root.left == null) {
        	if(i == 0)
        		return -1;
        	return root.val;
        }
        int result1 = find(root.left, i1, val);
        int result2 = find(root.right, i1, val);
        if(result1 == result2 && result1 == val) {
        	if(i == 0)
        		return -1;
        	else
        		return result1;
        } else if(result1 == val) {
        	return result2;
        } else if(result2 == val) {
        	return result1;
        }    	
        return Math.min(result1, result2);
	}
	
	/**
	 * 分治解法
	 * @param root
	 * @return
	 */
	public int findSecondMinimumValue_divideConquer(TreeNode root) {
		if(root.left == null)
			return -1;
		int left = root.left.val;
		int right = root.right.val;
		// 将问题分解，递归求解每一个子问题
		if(root.val == left)
			left = findSecondMinimumValue_divideConquer(root.left);
		if(root.val == right)
			right = findSecondMinimumValue_divideConquer(root.right);
		// 如果左右任意方返回-1，说明这一方元素的val全部与root的val相同，或这一方为val与root 相同叶子节点
		if(left != -1 && right != -1)
			return Math.min(left, right);
		else if(left == -1)
			return right;
		else
			return left;
    }
	
	/**
	 * BFS解法
	 * @param root
	 * @return
	 */
	public int findSecondMinimumValue_bfs(TreeNode root) {
		int secondSmall = Integer.MAX_VALUE;
		boolean flag = false;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			TreeNode tmp = q.poll();
			if(tmp.val != root.val && tmp.val < secondSmall) {
				flag = true;
				secondSmall = tmp.val;
			}
			if(tmp.left != null) {
				q.offer(tmp.left);
				q.offer(tmp.right);
			}
		}
		return flag == true ? secondSmall : -1;
	}
	
	/**
	 * 使用类似dfs的二叉树(先根)遍历
	 * 算是dfs么？
	 * @param root
	 * @return
	 */
	public int findSecondMinimumValue_dfs1(TreeNode root) {
		int result = find(root, root.val);
		return result;
	}

	private int find(TreeNode root, int val) {
		// TODO Auto-generated method stub
		if(root.left == null && root.val != val)
			return root.val;
		else if(root.left == null)
			return -1;
		int result1 = find(root.left, val);
		int result2 = find(root.right, val);
		if(result1 != -1 && result2 != -1)
			return Math.min(result1, result2);
		else if(result1 == -1)
			return result2;
		else
			return result1;
	}
	
}
