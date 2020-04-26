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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

        // TODO 最后一个用例执行出错 看答案
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> list = new ArrayList<>();
            List<Integer> list_now = new ArrayList<>();
            if (root == null) {
                return list;
            }
            find(root, sum, 0, list, list_now, 0);
            return list;
        }

        private void find(TreeNode root, int sum, int sum_now, List<List<Integer>> list, List<Integer> list_now, int depth) {
            sum_now = sum_now + root.val;
            if (list_now.size() > depth) {
                for(int i = depth; i < list_now.size(); i++) {
                    list_now.remove(depth);
                }
            }
            list_now.add(depth, root.val);
            if (root.left == null && root.right == null) {
                if (sum_now == sum) {
                    List<Integer> list_tmp = new ArrayList<>(list_now);
                    for(int i = depth+1; i < list_tmp.size(); i++) {
                        list_tmp.remove(i);
                    }
                    list.add(list_tmp);
                }
                for(int i = depth; i < list_now.size(); i++) {
                    list_now.remove(i);
                }
                return;
            }
            if (root.left != null) {
                find(root.left, sum, sum_now, list, list_now, depth + 1);
            }
            if (root.right != null) {
                find(root.right, sum, sum_now, list, list_now, depth + 1);
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