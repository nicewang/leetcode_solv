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

import java.util.*;

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
            int depth = 0, curCount = 0, nullCount = 0;
            boolean isEnd = false;
            TreeNode treeNode;
            TreeNode nodeEnd = root;
            while (!queue.isEmpty() && nullCount < Math.pow(2, depth)) {
                treeNode = queue.remove();
                curCount++;
                if (treeNode.val != -1) {
                    nodeEnd = treeNode;
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
        public List<Integer> rightSideViewTwo(TreeNode root) {
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

        // 解法三：dfs 堆栈解法 3ms 38.4MB 时间复杂度O(n) 空间复杂度O(n)
        public List<Integer> rightSideViewThree(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }

            Stack<TreeNode> stack = new Stack<>();
            Stack<Integer> depthStack = new Stack<>();
            stack.push(root);
            depthStack.push(0);
//            int maxDepth = -1;
            List<Integer> depthList = new ArrayList<>();
            while(!stack.isEmpty()) {
                TreeNode node = stack.pop();
                int depth = depthStack.pop();
//                maxDepth = Math.max(maxDepth, depth);
                // 如果不存在对应深度的节点我们才插入
                if(!depthList.contains(depth)) {
                    depthList.add(depth, depth);
                    list.add(depth, node.val);
                }
                if(node.left != null) {
                    stack.push(node.left);
                    depthStack.push(depth+1);
                }
                if(node.right != null) {
                    stack.push(node.right);
                    depthStack.push(depth+1);
                }
            }
            return list;
        }

        // 解法四：bfs解法(二) 2ms 38.3MB 时间复杂度O(n) 空间复杂度O(n)
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }

            LinkedList<TreeNode> queue = new LinkedList<>();
            LinkedList<Integer> depthQueue = new LinkedList<>();
            queue.add(root);
            depthQueue.push(0);
//            int maxDepth = -1;
            while(!queue.isEmpty()) {
                TreeNode node = queue.remove();
                int depth = depthQueue.remove();
//                maxDepth = Math.max(maxDepth, depth);
                if(list.size() > depth) {
                    list.remove(depth);
                }
                // 由于每一层最后一个访问到的节点才是我们要的答案，因此不断更新对应深度的信息即可
                list.add(depth, node.val);
                if(node.left != null) {
                    queue.add(node.left);
                    depthQueue.add(depth+1);
                }
                if(node.right != null) {
                    queue.add(node.right);
                    depthQueue.add(depth+1);
                }
            }
            return list;
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