//Sort a linked list using insertion sort. 
//
// 
// 
//
// 
//A graphical example of insertion sort. The partial sorted list (black) initial
//ly contains only the first element in the list. 
//With each iteration one element (red) is removed from the input data and inser
//ted in-place into the sorted list 
// 
//
// 
// 
//
// Algorithm of Insertion Sort: 
//
// 
// Insertion sort iterates, consuming one input element each repetition, and gro
//wing a sorted output list. 
// At each iteration, insertion sort removes one element from the input data, fi
//nds the location it belongs within the sorted list, and inserts it there. 
// It repeats until no input elements remain. 
// 
//
// 
//Example 1: 
//
// 
//Input: 4->2->1->3
//Output: 1->2->3->4
// 
//
// Example 2: 
//
// 
//Input: -1->5->3->4->0
//Output: -1->0->3->4->5
// 
// Related Topics Linked List Sort


package leetcode.leetcode.editor.en;
//Java：Insertion Sort List
public class P147InsertionSortList{
    public static void main(String[] args) {
        Solution solution = new P147InsertionSortList().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 24ms 39.6MB
    public ListNode insertionSortList(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode resNode = new ListNode(head.val);
        ListNode tmpNode = head.next;
        while(tmpNode != null) {
            if(resNode.val > tmpNode.val) {
                resNode = new ListNode(tmpNode.val, resNode);
                tmpNode = tmpNode.next;
                continue;
            }
            ListNode compNode = resNode;
            while(compNode != null) {
                if(compNode.next != null && tmpNode.val < compNode.next.val && tmpNode.val >= compNode.val) {
                    compNode.next = new ListNode(tmpNode.val, compNode.next);;
                    break;
                } else if(compNode.next == null && tmpNode.val >= compNode.val) {
                    compNode.next = new ListNode(tmpNode.val);
                    break;
                }
                compNode = compNode.next;
            }
            tmpNode = tmpNode.next;
        }
        return resNode;
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