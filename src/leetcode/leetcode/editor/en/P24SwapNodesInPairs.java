//Given a linked list, swap every two adjacent nodes and return its head. 
//
// You may not modify the values in the list's nodes, only nodes itself may be c
//hanged. 
//
// 
//
// Example: 
//
// 
//Given 1->2->3->4, you should return the list as 2->1->4->3.
// 
// Related Topics Linked List


package leetcode.leetcode.editor.en;

//Java：Swap Nodes in Pairs
public class P24SwapNodesInPairs {
    public static void main(String[] args) {
        Solution solution = new P24SwapNodesInPairs().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        // 0ms 37.9MB
        public ListNode swapPairs(ListNode head) {
            if(head == null || head.next == null) {
                return head;
            }
            ListNode new_head = head.next;
            ListNode node = head;
            ListNode nextNode = node.next;
            ListNode preNode = null;
            while(node != null) {
                nextNode = node.next;
                if(nextNode == null) {
                    break;
                }
                ListNode tmp = nextNode.next;
                nextNode.next = node;
                if(preNode != null) {
                    preNode.next = nextNode;
                }
                preNode = node;
                node.next = tmp;
                node = tmp;
            }
            return new_head;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}