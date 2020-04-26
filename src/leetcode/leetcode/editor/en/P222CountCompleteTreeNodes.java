//Given a complete binary tree, count the number of nodes. 
//
// Note: 
//
// Definition of a complete binary tree from Wikipedia: 
//In a complete binary tree every level, except possibly the last, is completely
// filled, and all nodes in the last level are as far left as possible. It can hav
//e between 1 and 2h nodes inclusive at the last level h. 
//
// Example: 
//
// 
//Input: 
//    1
//   / \
//  2   3
// / \  /
//4  5 6
//
//Output: 6 
// Related Topics Binary Search Tree


package leetcode.leetcode.editor.en;

import java.util.LinkedList;
import java.util.Stack;

//Java：Count Complete Tree Nodes
public class P222CountCompleteTreeNodes {
    public static void main(String[] args) {
        Solution solution = new P222CountCompleteTreeNodes().new Solution();
        Integer[] in_root = {1, 2, 3, 4};
        TreeNode root = solution.BFSTraversal(in_root);
        System.out.println(solution.countNodes(root));
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

        // 解法一：dfs 4ms 42.4MB
        // TODO 看答案
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if(root.left == null && root.right == null) {
                return 1;
            }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode treeNode = root;
            int maxDeepth = 0, curDeepth = 0, lastwidth = 0;
            stack.push(treeNode);
            int[] pIsBack = new int[1000];
            while (!stack.isEmpty()) {
                if (treeNode.right == null && curDeepth < maxDeepth) {
                    if(treeNode.left != null && pIsBack[curDeepth] == 0) {
                        lastwidth++;
                    }
                    break;
                }
                if (treeNode.right == null && curDeepth == 0) {
                    if(treeNode.left != null) {
                        lastwidth++;
                        maxDeepth = 1;
                    } else {
                        maxDeepth = 0;
                    }
                    break;
                }
                if ((treeNode.left == null && treeNode.right == null) || pIsBack[curDeepth] == 2) {
                    if (treeNode.left == null && treeNode.right == null) {
                        maxDeepth = Math.max(maxDeepth, curDeepth);
                    }
                    if (curDeepth == maxDeepth) {
                        lastwidth++;
                    }
                    stack.pop();
                    pIsBack[curDeepth] = 0;
                    if(curDeepth == 0) {
                        break;
                    }
                    curDeepth--;
                    pIsBack[curDeepth]++;
                    treeNode = stack.peek();
                    continue;
                }
                if (treeNode.left != null && pIsBack[curDeepth] == 0) {
                    treeNode = treeNode.left;
                    stack.push(treeNode);
                    curDeepth++;
                }
                if (treeNode.right != null && pIsBack[curDeepth] == 1) {
                    treeNode = treeNode.right;
                    stack.push(treeNode);
                    curDeepth++;
                }
            }
            if(maxDeepth == 0) {
                return 1;
            }
            int result = lastwidth;
            for (int i = 0; i <= maxDeepth-1; i++) {
                result += (int) Math.pow(2, i);
            }
            return result;
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