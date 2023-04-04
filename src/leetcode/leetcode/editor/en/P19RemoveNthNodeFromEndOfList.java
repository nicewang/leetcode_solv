//Given a linked list, remove the n-th node from the end of list and return its 
//head. 
//
// Example: 
//
// 
//Given linked list: 1->2->3->4->5, and n = 2.
//
//After removing the second node from the end, the linked list becomes 1->2->3->
//5.
// 
//
// Note: 
//
// Given n will always be valid. 
//
// Follow up: 
//
// Could you do this in one pass? 
// Related Topics Linked List Two Pointers


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Remove Nth Node From End of List
public class P19RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new P19RemoveNthNodeFromEndOfList().new Solution();
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
        // 2ms 39.6MB TODO 看答案
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) {
                return null;
            }
            Map<Integer, ListNode> mapL = new HashMap<>();
            Map<Integer, ListNode> mapR = new HashMap<>();
            ListNode new_head = head;
            int count = 0;
            ListNode pre = null;
            while (head != null) {
                mapL.put(count, pre);
                mapR.put(count, head.next);
                pre = head;
                head = head.next;
                count++;
            }
            if (n > count || n < 1) {
                return new_head;
            }
            if (mapL.get(count - n) == null) {
                return mapR.get(count - n);
            }
            mapL.get(count - n).next = mapR.get(count - n);
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