package Depth_first_Search;

import java.util.Stack;

public class SameTree {
	
	/**
	 * 先根遍历实现
	 * 运行用时5ms
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null)
			return true;
		else if (p == null || q == null)
			return false;
		if(p.val != q.val)
			return false;
		if(isSameTree(p.left,q.left) && isSameTree(p.right,q.right))
			return true;
        return false;
    }
	
	/**
	 * 普通的dfs实现
	 * 运行耗时6ms
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree_dfs(TreeNode p, TreeNode q) {
		if(p == null && q == null)
			return true;
		else if (p == null || q == null)
			return false;
		if(p.val != q.val)
			return false;
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		s1.push(p);
		s2.push(q);
		while(!s1.isEmpty()) {
			TreeNode p1 = s1.pop();
			TreeNode q1 = s2.pop();
			if(p1.left == null && q1.left != null)
				return false;
			if(p1.left != null && q1.left == null)
				return false;
			if(p1.right == null && q1.right != null)
				return false;
			if(p1.right != null && q1.right == null)
				return false;
			if(p1.left != null && q1.left != null && p1.left.val != q1.left.val)
				return false;
			if(p1.left != null && q1.left != null) {
				if(p1.left.val != q1.left.val)
					return false;
				s1.push(p1.left);
				s2.push(q1.left);
			}
			if(p1.right != null && q1.right != null) {
				if(p1.right.val != q1.right.val)
					return false;
				s1.push(p1.right);
				s2.push(q1.right);
			}
		}
		return true;
	}
}
