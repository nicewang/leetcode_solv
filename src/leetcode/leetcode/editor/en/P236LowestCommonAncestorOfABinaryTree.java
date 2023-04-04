//Given a binary tree, find the lowest common ancestor (LCA) of two given nodes 
//in the tree. 
//
// According to the definition of LCA on Wikipedia: “The lowest common ancestor 
//is defined between two nodes p and q as the lowest node in T that has both p and
// q as descendants (where we allow a node to be a descendant of itself).” 
//
// Given the following binary tree: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// Example 1: 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//Output: 3
//Explanation: The LCA of nodes 5 and 1 is 3.
// 
//
// Example 2: 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//Output: 5
//Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant o
//f itself according to the LCA definition.
// 
//
// 
//
// Note: 
//
// 
// All of the nodes' values will be unique. 
// p and q are different and both values will exist in the binary tree. 
// 
// Related Topics Tree


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Lowest Common Ancestor of a Binary Tree
public class P236LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new P236LowestCommonAncestorOfABinaryTree().new Solution();
        Integer[] in_root = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = solution.BFSTraversal(in_root);
        Integer[] in_p = {5,6,2,null,null,7,4};
        TreeNode p = solution.BFSTraversal(in_p);
        Integer[] in_q = {4};
        TreeNode q = solution.BFSTraversal(in_q);
        System.out.println(solution.lowestCommonAncestor(root,p,q));
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public TreeNode BFSTraversal(Integer[] nums) {
            if(nums == null || nums.length <= 0) {
                return null;
            }
            if(nums[0] == null) {
                return null;
            }
            TreeNode resNode = new TreeNode(nums[0]);
            int deepth = 0;
            int countTemp = 0;
            LinkedList<TreeNode> queue_pre = new LinkedList<>();
            LinkedList<TreeNode> queue = new LinkedList<>();
            for(int i = 0; i < nums.length; i++) {
                if(countTemp < Math.pow(2, deepth)) {
                    if(nums[i] != null) {
                        TreeNode tmpNode = new TreeNode(nums[i]);
                        queue.add(tmpNode);
                        if(i == 0) {
                            resNode = tmpNode;
                        }
                        if(!queue_pre.isEmpty() && countTemp %2 == 0) {
                            TreeNode treeNode = queue_pre.peek();
                            treeNode.left = tmpNode;
                        } else if(!queue_pre.isEmpty()) {
                            TreeNode treeNode = queue_pre.remove();
                            treeNode.right = tmpNode;
                        }
                    } else if(countTemp %2 == 1) {
                        queue_pre.remove();
                    }
                    countTemp++;
                } else {
                    countTemp = 1;
                    deepth++;
                    queue_pre.addAll(queue);
                    queue.clear();
                    if(nums[i] != null) {
                        TreeNode tmpNode = new TreeNode(nums[i]);
                        queue.add(tmpNode);
                        TreeNode treeNode = queue_pre.peek();
                        treeNode.left = tmpNode;
                    }
                }
            }
            return resNode;
        }

        // 解法二：dfs递归 TODO attention：dfs可以优先考虑递归 后续再考虑堆栈 另外递归的问题想一下是否可以转化为dp问题
        // 8ms 41.9MB 时间复杂度O(N) 空间复杂度O(N) N——二叉树的节点数
        TreeNode res = null;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            dfs(root, p, q);
            return res;
        }
        private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
            if(root == null) {
                return false;
            }
            boolean l = dfs(root.left, p , q);
            boolean r = dfs(root.right, p , q);
            if((l && r) || ((root.val == p.val || root.val == q.val) && (l || r))) {
                res = root;
            }
            return root.val == p.val || root.val == q.val || l || r;
        }

        // 解法一：堆栈方法 超时
        public TreeNode lowestCommonAncestorOne(TreeNode root, TreeNode p, TreeNode q) {
            Stack<TreeNode> pStack = new Stack<>();
            Stack<TreeNode> qStack = new Stack<>();
            int deep_p = 0, deep_q = 0;
            TreeNode treeNode_p = root;
            TreeNode treeNode_q = root;
            pStack.push(treeNode_p);
            qStack.push(treeNode_q);
            int[] pIsBack = new int[1000];
            int[] qIsBack = new int[1000];
            // 查找p
            while(!pStack.isEmpty()) {
                if(treeNode_p.val == p.val) {
                    break;
                }
                if((treeNode_p.left == null && treeNode_p.right == null) || pIsBack[deep_p] == 2) {
                    pStack.pop();
                    pIsBack[deep_p] = 0;
                    deep_p--;
                    pIsBack[deep_p]++;
                    treeNode_p = pStack.peek();
                    continue;
                }
                if(treeNode_p.left !=null && pIsBack[deep_p] == 0) {
                    treeNode_p = treeNode_p.left;
                    pStack.push(treeNode_p);
                    deep_p++;
                }
                if(treeNode_p.right !=null && pIsBack[deep_p] == 1) {
                    treeNode_p = treeNode_p.right;
                    pStack.push(treeNode_p);
                    deep_p++;
                }
            }
            // 查找q
            while(!qStack.isEmpty()) {
                if(treeNode_q.val == q.val) {
                    break;
                }
                if((treeNode_q.left == null && treeNode_q.right == null) || qIsBack[deep_q] == 2) {
                    qStack.pop();
                    qIsBack[deep_q] = 0;
                    deep_q--;
                    qIsBack[deep_q]++;
                    treeNode_q = qStack.peek();
                    continue;
                }
                if(treeNode_q.left !=null && qIsBack[deep_q] == 0) {
                    treeNode_q = treeNode_q.left;
                    qStack.push(treeNode_q);
                    deep_q++;
                }
                if(treeNode_q.right !=null && qIsBack[deep_q] == 1) {
                    treeNode_q = treeNode_q.right;
                    qStack.push(treeNode_q);
                    deep_q++;
                }
            }

            while(deep_p > 0 && deep_q > 0) {
                if(deep_p > deep_q) {
                    pStack.pop();
                    deep_p--;
                } else if(deep_p < deep_q) {
                    qStack.pop();
                    deep_q--;
                } else {
                    TreeNode pNode = pStack.pop();
                    TreeNode qNode = qStack.pop();
                    if(pNode == qNode) {
                        return pNode;
                    }
                    deep_p--;
                    deep_q--;
                }
            }
            return root;
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