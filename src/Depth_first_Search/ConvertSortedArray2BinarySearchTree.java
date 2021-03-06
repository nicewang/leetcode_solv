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
public class ConvertSortedArray2BinarySearchTree {
	
	public static void main(String args[]) {
		int[] nums = {-10, -3, 0, 5, 9};
		TreeNode node = sortedArrayToBST_mySelf1(nums);
		TreeNode node_new = new TreeNode(0);
		node_new.left = new TreeNode(-3);
		node_new.left.left = new TreeNode(-10);
		node_new.right = new TreeNode(9);
		node_new.right.left = new TreeNode(5);
		preOrder(node);
		System.out.println("================");
		preOrder(node_new);
		System.out.println("================");
		TreeNode node_new1 = sortedArrayToBST(nums);
		preOrder(node_new1);
		System.out.println("================");
		TreeNode node_new2 = sortedArrayToBST_mySelf2(nums);
		preOrder(node_new2);
	}
	
	/**
	 * CSDN Solution
	 * @param num
	 * @return
	 */
	public static TreeNode sortedArrayToBST(int[] num) {
	    if(num == null || num.length == 0)
	        return null;
	    return helper(num,0,num.length-1);
	}
	
	private static TreeNode helper(int[] num, int l, int r)
	{
	    if(l > r)
	        return null;
	    int m = (l + r) / 2;
	    TreeNode root = new TreeNode(num[m]);
	    root.left = helper(num,l,m-1); // m-1和m+1就保证了被访问过的点不会再被访问
	    root.right = helper(num,m+1,r);
	    return root;
	}
	
	
	/**
	 * My Solution1
	 * @param nums
	 * @return
	 */
	public static TreeNode sortedArrayToBST_mySelf1(int[] nums) {
		int length = nums.length;
        TreeNode result = new TreeNode(nums[length/2]);
        int left = 0;
        int right = length - 1;
        int middle = length/2;
        getBST(result, left, right, middle, nums);
        return result;
    }

	private static void getBST(TreeNode result, int left, int right, int middle, int[] nums) {
		// TODO Auto-generated method stub
		// Left
		if(middle - left == 1) {
			result.left = new TreeNode(nums[left]);
		} else if(middle - left > 1) {
			int left_new = left;
			int right_new = middle - 1;
			int middle_new = left_new + (right_new - left_new) / 2;
			TreeNode result_new = new TreeNode(nums[middle_new]);
			result.left = result_new;
			getBST(result_new, left_new, right_new, middle_new, nums);
		}
		// Right
		if(right - middle == 1) {
			result.right = new TreeNode(nums[right]);
		} else if(right - middle > 1) {
			int left_new = middle + 1;
			int right_new = right;
			int middle_new = left_new + (right_new - left_new) / 2;
			TreeNode result_new = new TreeNode(nums[middle_new]);
			result.right = result_new;
			getBST(result_new, left_new, right_new, middle_new, nums);
		}
	}
	
	/**
	 * My Solution1
	 * @param nums
	 * @return
	 */
	public static TreeNode sortedArrayToBST_mySelf2(int[] nums) {
		int length = nums.length;
        TreeNode result = new TreeNode(nums[length/2]);
        // 新建一个数组用于记录各node被访问的情况
        int[] visitedIndex = new int[length];
        for(int i = 0; i < length; i++) {
        	visitedIndex[i] = 0;
        }
        int left = 0;
        int right = length - 1;
        int middle = length/2;
        getBST2(result, left, right, middle, nums, visitedIndex);
        return result;
    }

	private static void getBST2(TreeNode result, int left, int right, int middle, int[] nums, int[] visitedIndex) {
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
				getBST2(result_new, left_new, right_new, middle_new, nums, visitedIndex);
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
			int right_new = right;
			int middle_new = left_new + (right_new - left_new) / 2;
			if(visitedIndex[middle_new] == 0) {
				TreeNode result_new = new TreeNode(nums[middle_new]);
				result.right = result_new;
				getBST2(result_new, left_new, right_new, middle_new, nums, visitedIndex);
			}
		}
	}
	
	// 前序(先根)遍历
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
