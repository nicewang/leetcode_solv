//Given a linked list, rotate the list to the right by k places, where k is non-
//negative. 
//
// Example 1: 
//
// 
//Input: 1->2->3->4->5->NULL, k = 2
//Output: 4->5->1->2->3->NULL
//Explanation:
//rotate 1 steps to the right: 5->1->2->3->4->NULL
//rotate 2 steps to the right: 4->5->1->2->3->NULL
// 
//
// Example 2: 
//
// 
//Input: 0->1->2->NULL, k = 4
//Output: 2->0->1->NULL
//Explanation:
//rotate 1 steps to the right: 2->0->1->NULL
//rotate 2 steps to the right: 1->2->0->NULL
//rotate 3 steps to the right: 0->1->2->NULL
//rotate 4 steps to the right: 2->0->1->NULL 
// Related Topics Linked List Two Pointers


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Rotate List
public class P61RotateList {
    public static void main(String[] args) {
        Solution solution = new P61RotateList().new Solution();
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
        // 解法二：1ms 40MB 使用old_tail、new_tail、new_head
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            if (k == 0 || head.next == null) {
                return head;
            }
            ListNode old_tail = head;
            int n = 1;
            for (; old_tail.next != null; n++) {
                old_tail = old_tail.next;
            }
            old_tail.next = head;
            ListNode new_tail = head;
            for (int i = 0; i < n - 1 - k % n; i++) {
                new_tail = new_tail.next;
            }
            ListNode new_head = new_tail.next;
            new_tail.next = null;
            return new_head;
        }

        // 2ms 39.4MB
        public ListNode rotateRightOne(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            if (k == 0) {
                return head;
            }
            Map<Integer, ListNode> mapL = new HashMap<>();
            Map<Integer, ListNode> mapR = new HashMap<>();
            mapL.put(0, null);
            mapR.put(0, head);
            int count = 1;
            ListNode tmp = head;
            while (tmp.next != null) {
                mapL.put(count, tmp);
                mapR.put(count, tmp.next);
                count++;
                tmp = tmp.next;
            }
            if (k % count == 0) {
                return head;
            }
            ListNode left = mapL.get(count - k % count);
            left.next = null;
            tmp.next = head;
            return mapR.get(count - k % count);
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