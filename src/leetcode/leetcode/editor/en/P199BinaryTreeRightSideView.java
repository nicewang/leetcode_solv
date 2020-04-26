//Given a binary tree, imagine yourself standing on the right side of it, return
// the values of the nodes you can see ordered from top to bottom. 
//
// Example: 
//
// 
//Input: [1,2,3,null,5,null,4]
//Output: [1, 3, 4]
//Explanation:
//
//   1            <---
// /   \
//2     3         <---
// \     \
//  5     4       <---
// Related Topics Tree Depth-first Search Breadth-first Search


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Java：Binary Tree Right Side View
public class P199BinaryTreeRightSideView {
    public static void main(String[] args) {
        Solution solution = new P199BinaryTreeRightSideView().new Solution();
        Integer[] in_root = {6, 1, null, null, 3, 2, 5, null, null, 4};
        TreeNode root = solution.BFSTraversal(in_root);
        System.out.println(solution.rightSideView(root));
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // TODO 看答案
    class Solution {

        // TODO 有bug 后续fix
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

        // 解法一：bfs 13ms 40.1MB
        public List<Integer> rightSideViewOne(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int depth = 0, curCount = 0, endCount = 0, nullCount = 0;
            boolean isEnd = false;
            TreeNode treeNode = root;
            TreeNode nodeEnd = root;
            while (!queue.isEmpty() && nullCount < Math.pow(2, depth)) {
                treeNode = queue.remove();
                curCount++;
                if (treeNode.val != -1) {
                    nodeEnd = treeNode;
                    endCount = curCount;
                    if (curCount == Math.pow(2, depth)) {
                        depth++;
                        curCount = 0;
                        list.add(treeNode.val);
                        isEnd = true;
                    } else {
                        isEnd = false;
                    }
                    queue.add(treeNode.left == null ? new TreeNode(-1) : treeNode.left);
                    queue.add(treeNode.right == null ? new TreeNode(-1) : treeNode.right);
                    nullCount = 0;
                } else {
                    if (curCount == Math.pow(2, depth) && !isEnd) {
                        depth++;
                        curCount = 0;
                        list.add(nodeEnd.val);
                    }
                    nullCount++;
                    queue.add(new TreeNode(-1));
                    queue.add(new TreeNode(-1));
                }
            }

            return list;
        }

        // 解法二：dfs 1ms 38.3MB
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            find(root, list, 0);
            return list;
        }

        private void find(TreeNode root, List<Integer> list, int depth) {
            if (root == null) {
                return;
            }
            if(list.size() > depth) {
                list.remove(depth);
            }
            list.add(depth, root.val);
            find(root.left, list, depth+1);
            find(root.right, list, depth+1);
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