//Given a binary search tree, write a function kthSmallest to find the kth small
//est element in it. 
//
// Note: 
//You may assume k is always valid, 1 ≤ k ≤ BST's total elements. 
//
// Example 1: 
//
// 
//Input: root = [3,1,4,null,2], k = 1
//   3
//  / \
// 1   4
//  \
//   2
//Output: 1 
//
// Example 2: 
//
// 
//Input: root = [5,3,6,2,4,null,null,1], k = 3
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//Output: 3
// 
//
// Follow up: 
//What if the BST is modified (insert/delete operations) often and you need to f
//ind the kth smallest frequently? How would you optimize the kthSmallest routine?
// 
// Related Topics Binary Search Tree


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Kth Smallest Element in a BST
public class P230KthSmallestElementInABst {
    public static void main(String[] args) {
        Solution solution = new P230KthSmallestElementInABst().new Solution();
        Integer[] in_root = {3, 1, 4, null, 2};
        TreeNode root = solution.BFSTraversal(in_root);
        System.out.println(solution.kthSmallest(root, 3));
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
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

        // 解法二：堆栈法中根遍历实现 1ms 39.7MB
        public int kthSmallest(TreeNode root, int k) {
            if(root == null) {
                return -1;
            }
            int k1 = k;
            // 二叉搜索树使用中根遍历便可得到一个有序数组
            Stack<TreeNode> stack = new Stack<>();
            TreeNode treeNode = root;
            while(treeNode != null || !stack.isEmpty()) {
                while(treeNode != null) {
                    stack.push(treeNode);
                    treeNode = treeNode.left;
                }
                treeNode = stack.pop();
                if(--k1 == 0) {
                    return treeNode.val;
                }
                treeNode = treeNode.right;
            }
            return -1;
        }

        // 解法一：递归法中根遍历实现 1ms 39.8MB
        public int kthSmallestOne(TreeNode root, int k) {
            List<Integer> list = new ArrayList<>();
            // 二叉搜索树使用中根遍历便可得到一个有序数组
            inorderTraversal(root, list);
            if (list.size() < k) {
                return -1;
            }
            return list.get(k - 1);
        }

        private void inorderTraversal(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            inorderTraversal(root.left, list);
            list.add(root.val);
            inorderTraversal(root.right, list);
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