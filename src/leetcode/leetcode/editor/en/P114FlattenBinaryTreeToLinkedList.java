//Given a binary tree, flatten it to a linked list in-place. 
//
// For example, given the following tree: 
//
// 
//    1
//   / \
//  2   5
// / \   \
//3   4   6
// 
//
// The flattened tree should look like: 
//
// 
//1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6
// 
// Related Topics Tree Depth-first Search


package leetcode.leetcode.editor.en;

import java.util.LinkedList;

//Java：Flatten Binary Tree to Linked List
public class P114FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        Solution solution = new P114FlattenBinaryTreeToLinkedList().new Solution();
        Integer[] in_root = {1, 2, 5, 3, 4, null, 6};
        TreeNode root = solution.BFSTraversal(in_root);
        solution.flatten(root);
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

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

//       将左子树插入到右子树的地方
//       将原来的右子树接到左子树的最右边节点
//       考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
        // 0ms 39.7MB
        // TODO 查看其他题解
        public void flatten(TreeNode root) {
            while(root != null) {
                if(root.left != null) {
                    TreeNode pre = root.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }
                    pre.right = root.right;
                    root.right = root.left;
                    root.left = null;
                }
                root = root.right;
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