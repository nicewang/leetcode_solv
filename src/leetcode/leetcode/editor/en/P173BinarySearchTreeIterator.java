//Implement an iterator over a binary search tree (BST). Your iterator will be i
//nitialized with the root node of a BST. 
//
// Calling next() will return the next smallest number in the BST. 
//
// 
//
// 
// 
//
// Example: 
//
// 
//
// 
//BSTIterator iterator = new BSTIterator(root);
//iterator.next();    // return 3
//iterator.next();    // return 7
//iterator.hasNext(); // return true
//iterator.next();    // return 9
//iterator.hasNext(); // return true
//iterator.next();    // return 15
//iterator.hasNext(); // return true
//iterator.next();    // return 20
//iterator.hasNext(); // return false
// 
//
// 
//
// Note: 
//
// 
// next() and hasNext() should run in average O(1) time and uses O(h) memory, wh
//ere h is the height of the tree. 
// You may assume that next() call will always be valid, that is, there will be 
//at least a next smallest number in the BST when next() is called. 
// 
// Related Topics Stack Tree Design


package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

//Java：Binary Search Tree Iterator
public class P173BinarySearchTreeIterator{
    public static void main(String[] args) {
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 24ms 44.5MB
// 二叉搜索树 使用中根遍历实现
class BSTIterator {

    List<Integer> list = new ArrayList<>();

    int index = 0;

    public BSTIterator(TreeNode root) {
        traversal(root, this.list);
    }
    
    /** @return the next smallest number */
    public int next() {
        if(index == list.size()) {
            return -1;
        }
        return list.get(index++);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return index < list.size();
    }

    private void traversal(TreeNode root, List<Integer> list) {
        if(root == null) {
            return;
        }
        traversal(root.left, list);
        list.add(root.val);
        traversal(root.right, list);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
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