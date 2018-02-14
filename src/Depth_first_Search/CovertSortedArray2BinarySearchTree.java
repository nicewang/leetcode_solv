package Depth_first_Search;

/**
 * Given an array where elements are sorted in ascending order, 
 * convert it to a height balanced BST.
 * For this problem,
 * a height-balanced binary tree is defined as a binary tree 
 * in which the depth of the two subtrees of every node never differ by more than 1.
 * 
 * Example:
 * Given the sorted array: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], 
 * which represents the following height balanced BST:
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class CovertSortedArray2BinarySearchTree {
	
	public static void main(String args[]) {
		int[] nums = {-10, -3, 0, 5, 9};
		TreeNode node = sortedArrayToBST(nums);
		TreeNode node_new = new TreeNode(0);
		node_new.left = new TreeNode(-3);
		node_new.left.left = new TreeNode(-10);
		node_new.right = new TreeNode(9);
		node_new.right.left = new TreeNode(5);
		preOrder(node);
		System.out.println("================");
		preOrder(node_new);
		
	}
	
	public static TreeNode sortedArrayToBST(int[] nums) {
		int length = nums.length;
        TreeNode result = new TreeNode(nums[length/2]);
        // 新建一个数组用于记录各node被访问的情况
        int[] visitedIndex = new int[length];
        for(int i = 0; i < length; i++) {
        	visitedIndex[i] = 0;
        }
        int left = 0;
        int right = length - 1;
        int middle = length / 2;
        getBST(result, left, right, middle, nums, visitedIndex);
        return result;
    }

	private static void getBST(TreeNode result, int left, int right, int middle, int[] nums, int[] visitedIndex) {
		// TODO Auto-generated method stub
		visitedIndex[middle] = 1;
		// Left
		if(middle - left == 1) {
			if(visitedIndex[left] == 0) {
				result.left = new TreeNode(nums[left]);
				visitedIndex[left] = 1;
			}
		} else if(middle - left > 1) {
			int left_new = left;
			int right_new = middle;
			int middle_new = left_new + (right_new - left_new) / 2;
			if(visitedIndex[middle_new] == 0) {
				TreeNode result_new = new TreeNode(nums[middle_new]);
				result.left = result_new;
				getBST(result_new, left_new, right_new, middle_new, nums, visitedIndex);
			}
		}
		// Right
		if(right - middle == 1) {
			if(visitedIndex[right] == 0) {
				result.right = new TreeNode(nums[right]);
				visitedIndex[right] = 1;
			}
		} else if(right - middle > 1) {
			int left_new = middle;
//			int right_new = right;
			int middle_new = right;
//			int middle_new = left_new + (right_new - left_new) / 2;
			int right_new = left_new + (middle_new - left_new) / 2;
			if(visitedIndex[middle_new] == 0) {
				TreeNode result_new = new TreeNode(nums[middle_new]);
				result.right = result_new;
				getBST(result_new, left_new, right_new, middle_new, nums, visitedIndex);
			}
		}
	}
	
	// 前序遍历
	public static void preOrder(TreeNode node) {
		if(node != null) {
			System.out.println(node.val);
			// Left
			preOrder(node.left);
			// Right
			preOrder(node.right);
		}
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int x) {
		val = x;
	}
}
