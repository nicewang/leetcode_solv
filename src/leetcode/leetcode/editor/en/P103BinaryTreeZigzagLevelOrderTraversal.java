//Given a binary tree, return the zigzag level order traversal of its nodes' val
//ues. (ie, from left to right, then right to left for the next level and alternat
//e between). 
//
// 
//For example: 
//Given binary tree [3,9,20,null,null,15,7], 
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
// 
// 
//return its zigzag level order traversal as: 
// 
//[
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics Stack Tree Breadth-first Search


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Java：Binary Tree Zigzag Level Order Traversal
public class P103BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new P103BinaryTreeZigzagLevelOrderTraversal().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        // 1ms 39.4MB
        // TODO 看BFS遍历二叉树代码
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            List<Integer> list1 = new ArrayList<>();
            list1.add(root.val);
            list.add(list1);
            TreeNode treeNode;
            queue.add(root);
            int depth = 0;
            int curIndex = 0;
            int preCount = 1;
            int curMaxIndex = 0;
            while (!queue.isEmpty()) {
                if (curMaxIndex == 2 * preCount) {
                    depth++;
                    preCount = curIndex;
                    curIndex = 0;
                    curMaxIndex = 0;
                    List<Integer> listn = new ArrayList<>();
                    list.add(listn);
                }
                treeNode = queue.remove();
                if(depth > 0) {
                    list.get(depth).add(treeNode.val);
                }
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                    curIndex++;
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                    curIndex++;
                }
                curMaxIndex += 2;
            }
            for(int i = 0; i < list.size(); i++) {
                if(i % 2 == 1) {
                    List<Integer> listi = list.get(i);
                    List<Integer> newListi = new ArrayList<>(listi.size());
                    for(int j = listi.size(); j > 0; j--) {
                        newListi.add(listi.get(j-1));
                    }
                    list.remove(i);
                    list.add(i,newListi);
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