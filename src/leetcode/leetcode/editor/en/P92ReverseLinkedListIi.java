//Reverse a linked list from position m to n. Do it in one-pass. 
//
// Note: 1 ≤ m ≤ n ≤ length of list. 
//
// Example: 
//
// 
//Input: 1->2->3->4->5->NULL, m = 2, n = 4
//Output: 1->4->3->2->5->NULL
// 
// Related Topics Linked List


package leetcode.leetcode.editor.en;

//Java：Reverse Linked List II
public class P92ReverseLinkedListIi {
    public static void main(String[] args) {
        Solution solution = new P92ReverseLinkedListIi().new Solution();
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
        // 0ms 37.8MB
        public ListNode reverseBetween(ListNode head, int m, int n) {
            if (head == null) {
                return null;
            }
            ListNode tmp = head;
            for (int i = 2; i < m; i++) {
                if (tmp.next == null) {
                    return head;
                }
                tmp = tmp.next;
            }
            if (tmp.next == null) {
                return head;
            }
            ListNode reverse;
            if (m == 1) {
                reverse = tmp;
            } else {
                reverse = tmp.next;
            }
            ListNode reverseNext = reverse.next;
            ListNode reverseEnd = reverse;
            for (int i = m; i < n; i++) {
                if (reverseNext == null) {
                    break;
                }
                ListNode newReverse = reverseNext;
                reverseNext = reverseNext.next;
                newReverse.next = reverse;
                reverse = newReverse;
            }
            if(m == 1) {
                head = reverse;
            } else {
                tmp.next = reverse;
            }
            reverseEnd.next = reverseNext;
            return head;
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
