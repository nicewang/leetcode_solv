//Given a singly linked list where elements are sorted in ascending order, conve
//rt it to a height balanced BST. 
//
// For this problem, a height-balanced binary tree is defined as a binary tree i
//n which the depth of the two subtrees of every node never differ by more than 1.
// 
//
// Example: 
//
// 
//Given the sorted linked list: [-10,-3,0,5,9],
//
//One possible answer is: [0,-3,9,-10,null,5], which represents the following he
//ight balanced BST:
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics Linked List Depth-first Search


package leetcode.leetcode.editor.en;


import java.util.ArrayList;
import java.util.List;

//Java：Convert Sorted List to Binary Search Tree
public class P109ConvertSortedListToBinarySearchTree{
    public static void main(String[] args) {
        Solution solution = new P109ConvertSortedListToBinarySearchTree().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 2ms 41MB
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> sortedList = transferListNodeToList(head);
        if(sortedList.size() == 0) {
            return null;
        }
        return buildBST(0, sortedList.size() - 1, sortedList);
    }
    private TreeNode buildBST(int start, int end, List<Integer> list) {
        if(start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode treeNode = new TreeNode(list.get(mid));
        treeNode.left = buildBST(start, mid - 1, list);
        treeNode.right = buildBST(mid + 1, end, list);
        return treeNode;
    }
    private List<Integer> transferListNodeToList(ListNode listNode) {
        List<Integer> list  = new ArrayList<>();
        if(listNode == null) {
            return list;
        }
        ListNode curNode = listNode;
        while(curNode !=  null) {
            list.add(curNode.val);
            curNode = curNode.next;
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}
}