//Given a linked list, reverse the nodes of a linked list k at a time and return
// its modified list. 
//
// k is a positive integer and is less than or equal to the length of the linked
// list. If the number of nodes is not a multiple of k then left-out nodes in the 
//end should remain as it is. 
//
// 
// 
//
// Example: 
//
// Given this linked list: 1->2->3->4->5 
//
// For k = 2, you should return: 2->1->4->3->5 
//
// For k = 3, you should return: 3->2->1->4->5 
//
// Note: 
//
// 
// Only constant extra memory is allowed. 
// You may not alter the values in the list's nodes, only nodes itself may be ch
//anged. 
// 
// Related Topics Linked List


package leetcode.leetcode.editor.en;
//Java：Reverse Nodes in k-Group
public class P25ReverseNodesInKGroup{
    public static void main(String[] args) {
        Solution solution = new P25ReverseNodesInKGroup().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // 1ms 40.5MB
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) {
            return null;
        }
        ListNode newNode = new ListNode(head.val);
        ListNode newhead = newNode;
        ListNode curtail = newNode;
        ListNode lasttail = null;
        ListNode tmp = head.next;
        int count = 1;
        int total_count = 0;
        while(tmp != null) {
            if(count < k) {
                newNode = new ListNode(tmp.val, newNode);
                count++;
            } else {
                count = 1;
                if(total_count == 0) {
                    newhead = newNode;
                }
                if(lasttail != null) {
                    lasttail.next = newNode;
                }
                newNode = new ListNode(tmp.val);
                lasttail = curtail;
                curtail = newNode;
                total_count++;
            }
            tmp = tmp.next;
        }
        if(count < k) {
            tmp = newNode.next;
            newNode = new ListNode(newNode.val);
            for(int i = 1; i < count; i++) {
                newNode = new ListNode(tmp.val, newNode);
                tmp = tmp.next;
            }
        }
        if(lasttail != null) {
            lasttail.next = newNode;
        }
        if(total_count == 0) {
            newhead = newNode;
        }
        return newhead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}