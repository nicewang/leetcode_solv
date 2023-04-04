//Given a singly linked list L: L0→L1→…→Ln-1→Ln, 
//reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// You may not modify the values in the list's nodes, only nodes itself may be c
//hanged. 
//
// Example 1: 
//
// 
//Given 1->2->3->4, reorder it to 1->4->2->3. 
//
// Example 2: 
//
// 
//Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
// 
// Related Topics Linked List


package leetcode.leetcode.editor.en;

//Java：Reorder List
public class P143ReorderList {
    public static void main(String[] args) {
        Solution solution = new P143ReorderList().new Solution();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        solution.reorderList(listNode);
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        int count = 0;

        // 4ms 43.5MB
        public void reorderList(ListNode head) {
            if(head == null) {
                return;
            }
            ListNode reversedNode = reverseListNode(head);
            int tmpCount = 1;
            ListNode headTmp = head.next;
            ListNode reversedTmp = reversedNode;
            ListNode resNode = head;
            ListNode resNodeHead = resNode;
            for (int i = 0; i < count; i++) {
                if (tmpCount == 1) {
                    resNode.next = reversedTmp;
                    reversedTmp = reversedTmp.next;
                    tmpCount = 0;
                } else {
                    resNode.next = headTmp;
                    headTmp = headTmp.next;
                    tmpCount++;
                }
                if (i < count - 1) {
                    resNode = resNode.next;
                }
            }
            resNode.next = null;
            head = resNodeHead;
        }

        private ListNode reverseListNode(ListNode root) {
            ListNode resNode = new ListNode(root.val);
            ListNode nowNode = root.next;
            count = 1;
            while (nowNode != null) {
                resNode = new ListNode(nowNode.val, resNode);
                count++;
                nowNode = nowNode.next;
            }
            return resNode;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
}

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