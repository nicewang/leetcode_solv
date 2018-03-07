package Depth_first_Search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PathSum {
	
	/**
	 * 先根遍历解法
	 * 运行时间1ms
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum_preOrder(TreeNode root, int sum) {
        boolean result;
        result = getResult(root,0,sum);
        return result;
    }

	private boolean getResult(TreeNode root, int cur, int sum) {
		// TODO Auto-generated method stub
		if(root == null)
			return false;
		if(root.val+cur == sum) {
			if(root.left == null && root.right == null)
				return true;
		}
		cur += root.val;
		boolean result = getResult(root.left,cur,sum) || getResult(root.right,cur,sum);
		return result;
	}
	
	/**
	 * dfs解法
	 * 运行时间5ms
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum_dfs(TreeNode root, int sum) {
		if(root == null)
			return false;
		Stack<TreeNode> theStack = new Stack<TreeNode>();
		theStack.push(root);
		Stack<Integer> value = new Stack<Integer>();
		value.push(root.val);
		while(!theStack.isEmpty()) {
			TreeNode cur = theStack.pop();
			int val = value.pop();
			if(val == sum) {
				if(cur.left == null && cur.right == null)
					return true;
			}
			if(cur.left != null) {
				theStack.push(cur.left);
				value.push(val+cur.left.val);
			}
			if(cur.right != null) {
				theStack.push(cur.right);
				value.push(val+cur.right.val);
			}
		}
		return false;
	}
	
	/**
	 * bfs解法
	 * 运行时间3ms
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum_bfs(TreeNode root, int sum) {
		if(root == null)
			return false;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		Queue<Integer> value = new LinkedList<Integer>();
		value.offer(root.val);
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				TreeNode cur = q.poll();
				int val = value.poll();
				if(val == sum) {
					if(cur.left == null && cur.right == null)
						return true;
				}
				if(cur.left != null) { // 不加这两个if语句时在第78行做加法时就会报空指针异常
					q.offer(cur.left);
					value.offer(val+cur.left.val);
				}
				if(cur.right != null) {
					q.offer(cur.right);
					value.offer(val+cur.right.val);
				}
			}
		}
		return false;
	}

}
