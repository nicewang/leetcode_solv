//Given a binary tree and a sum, find all root-to-leaf paths where each path's s
//um equals the given sum. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// Given the below binary tree and sum = 22, 
//
// 
//      5
//     / \
//    4   8
//   /   / \
//  11  13  4
// /  \    / \
//7    2  5   1
// 
//
// Return: 
//
// 
//[
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
// Related Topics Tree Depth-first Search


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Path Sum II
public class P113PathSumIi {
    public static void main(String[] args) {
        Solution solution = new P113PathSumIi().new Solution();
        Integer[] in_root = {1, -2, -3, 1, 3, -2, null, -1};
        TreeNode root = solution.BFSTraversal(in_root);
        solution.pathSum(root, 2);
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public TreeNode BFSTraversal(Integer[] nums) {
            if (nums == null || nums.length <= 0) {
                return null;
            }
            if (nums[0] == null) {
                return null;
            }
            TreeNode resNode = new TreeNode(nums[0]);
            int deepth = 0;
            int countTemp = 0;
            LinkedList<TreeNode> queue_pre = new LinkedList<>();
            LinkedList<TreeNode> queue = new LinkedList<>();
            for (int i = 0; i < nums.length; i++) {
                if (countTemp < Math.pow(2, deepth)) {
                    if (nums[i] != null) {
                        TreeNode tmpNode = new TreeNode(nums[i]);
                        queue.add(tmpNode);
                        if (i == 0) {
                            resNode = tmpNode;
                        }
                        if (!queue_pre.isEmpty() && countTemp % 2 == 0) {
                            TreeNode treeNode = queue_pre.peek();
                            treeNode.left = tmpNode;
                        } else if (!queue_pre.isEmpty()) {
                            TreeNode treeNode = queue_pre.remove();
                            treeNode.right = tmpNode;
                        }
                    } else if (countTemp % 2 == 1) {
                        queue_pre.remove();
                    }
                    countTemp++;
                } else {
                    countTemp = 1;
                    deepth++;
                    queue_pre.addAll(queue);
                    queue.clear();
                    if (nums[i] != null) {
                        TreeNode tmpNode = new TreeNode(nums[i]);
                        queue.add(tmpNode);
                        TreeNode treeNode = queue_pre.peek();
                        treeNode.left = tmpNode;
                    }
                }
            }
            return resNode;
        }

        // 2ms 40.1MB
        List<List<Integer>> result = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<Integer> curPath = new LinkedList<>();
            if(root == null) {
                return result;
            }
            stack.push(root.val);
            find(root, 0, sum);
            return result;
        }
        private void find(TreeNode root, int curSum,  int sum) {
            if(curSum+root.val == sum && root.left == null && root.right == null) {
                result.add(new ArrayList<>(stack));
            }
            if(root.left != null) {
                stack.push(root.left.val);
                find(root.left, curSum+root.val, sum);
                stack.pop();
            }
            if(root.right != null) {
                stack.push(root.right.val);
                find(root.right, curSum+root.val, sum);
                stack.pop();
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}