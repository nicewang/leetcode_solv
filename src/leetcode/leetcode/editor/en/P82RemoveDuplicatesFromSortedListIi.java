//Given a sorted linked list, delete all nodes that have duplicate numbers, leav
//ing only distinct numbers from the original list. 
//
// Return the linked list sorted as well. 
//
// Example 1: 
//
// 
//Input: 1->2->3->3->4->4->5
//Output: 1->2->5
// 
//
// Example 2: 
//
// 
//Input: 1->1->1->2->3
//Output: 2->3
// 
// Related Topics Linked List


package leetcode.leetcode.editor.en;

//Java：Remove Duplicates from Sorted List II
public class P82RemoveDuplicatesFromSortedListIi {
    public static void main(String[] args) {
        Solution solution = new P82RemoveDuplicatesFromSortedListIi().new Solution();
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
        // 1ms 40.1MB
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode res_head = null;
            ListNode res = res_head;
            boolean isDuplicated = false;
            while (head != null) {
                if (head.next != null && head.next.val == head.val) {
                    isDuplicated = true;
                    head = head.next;
                    continue;
                } else if (head.next != null && isDuplicated) {
                    isDuplicated = false;
                    head = head.next;
                    continue;
                } else if (!isDuplicated) {
                    if (res == null) {
                        res_head = new ListNode(head.val);
                        res = res_head;
                    } else {
                        res.next = new ListNode(head.val);
                        res = res.next;
                    }
                }
                head = head.next;
            }
            return res_head;
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