package Depth_first_Search;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * Example 1:
 * Given the following tree [3,9,20,null,null,15,7]:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Return true.
 * Example 2:
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * Return false.
 */
public class BalancedBinaryTree {
	
	/**
	 * simpler?
	 * 用时21ms
	 * @param root
	 * @return
	 */
	public boolean isBalanced_simpler(TreeNode root) {
		if(root == null)
			return true;
		// 注意，这里用的是高度而不是深度
		int height = getHeight(root);
		return height!=-1;
	}
	
	private int getHeight(TreeNode root) {
		// TODO Auto-generated method stub
		if(root == null)
			return 0;
		int left = getHeight(root.left);
		int right = getHeight(root.right);
		if(left == -1)
			return -1;
		if(right == -1)
			return -1;
		if(left-right > 1 || right-left>1)
			return -1;
		return Math.max(getHeight(root.left), getHeight(root.right))+1;
	}

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
	
	/**
	 * 耗时2ms
	 * @param root
	 * @return
	 */
	public boolean isBalanced_solv3(TreeNode root) {
		if(root == null)
			return true;
		int left = getDepth(root.left);
		int right = getDepth(root.right);
		if(left-right > 1 || right-left > 1)
			return false;
		return isBalanced_solv3(root.left) && isBalanced_solv3(root.right);
	}

	private int getDepth(TreeNode root) {
		// TODO Auto-generated method stub
		if(root == null)
			return 0;
		return Math.max(getDepth(root.left), getDepth(root.right))+1;
	}

}
