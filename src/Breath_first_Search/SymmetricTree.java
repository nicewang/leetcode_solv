package Breath_first_Search;

/**
 * Given a binary tree, check whether it is a mirror of itself 
 * (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *     3    3
 */
public class SymmetricTree {
	
	public static void main(String args[]) {
		// case 1
		TreeNode node1 = new TreeNode(1);
		node1.left = new TreeNode(2);
		node1.right = new TreeNode(2);
		node1.left.left = new TreeNode(3);
		node1.left.right = new TreeNode(4);
		node1.right.left = new TreeNode(4);
		node1.right.right = new TreeNode(3);
		if(isSymmetric(node1))
			System.out.println("Is Symmetric!");
		else
			System.out.println("No Symmetric!");
		TreeNode node2 = new TreeNode(1);
		node2.left = new TreeNode(2);
		node2.right = new TreeNode(2);
		node2.left.right = new TreeNode(3);
		node2.right.right = new TreeNode(3);
		if(isSymmetric(node2))
			System.out.println("Is Symmetric!");
		else
			System.out.println("No Symmetric!");
	}
	
	public static boolean isSymmetric(TreeNode root) {
		if(root == null)
			return true;
		TreeNode[] nodes = {root};
		String[] vals = {Integer.toString(root.val)};
		return getSymmetricResult(nodes, vals);
	}

	private static boolean getSymmetricResult(TreeNode[] nodes, String[] vals) {
		// TODO Auto-generated method stub
		int length = nodes.length;
		
		for(int i = 0; i < length/2; i++)
			if(!vals[i].equals(vals[length-1-i]))
				return false;
		
		int nullCount = 0;
		for(int i = 0; i < length; i++)
			if(nodes[i] == null)
				nullCount += 1;
		int len_new = (length - nullCount) * 2;
		TreeNode[] nodes_new = new TreeNode[len_new];
		String[] vals_new = new String[len_new];
		
		int count = 0;
		int nodeNullCount = 0;
		for(int i = 0; i < length; i++) {
			if(nodes[i] != null) {
				nodes_new[count] = nodes[i].left;
				vals_new[count++] = getVals(nodes[i].left);
				nodes_new[count] = nodes[i].right;
				vals_new[count++] = getVals(nodes[i].right);
			} else {
				nodeNullCount += 1;
			}	
		}
		if(nodeNullCount == length) // 搜索到底部了，结束
			return true;
		
		return getSymmetricResult(nodes_new, vals_new);
	}

	private static String getVals(TreeNode node) {
		// TODO Auto-generated method stub
		if(node == null)
			return "null";
		else
			return Integer.toString(node.val);
	}

}

