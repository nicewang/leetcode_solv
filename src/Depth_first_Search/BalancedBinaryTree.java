package Depth_first_Search;

public class BalancedBinaryTree {
	
	/**
	 * 运行时间3ms
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root) {
		if(root == null)
			return true;
		return getResult(root);

    }

	private boolean getResult(TreeNode root) {
		// TODO Auto-generated method stub
		if(root.left == null && root.right == null)
			return true;
		int left = 0;
		int right = 0;
		if(root.left != null)
			left = getDepth(root.left,0);
		if(root.right != null)
			right = getDepth(root.right,0);
		if(left-right > 1 || right-left > 1)
			return false;
		if(root.left == null)
			return getResult(root.right);
		else if(root.right == null)
			return getResult(root.left);
		else
			return getResult(root.left) && getResult(root.right);
	}

	private int getDepth(TreeNode root, int i) {
		// TODO Auto-generated method stub
		if(root.left == null && root.right == null)
			return i+1;
		else if(root.left == null)
			return getDepth(root.right,i+1);
		else if(root.right == null)
			return getDepth(root.left,i+1);
		else
			return Math.max(getDepth(root.left,i+1), getDepth(root.right,i+1));
	}

}
