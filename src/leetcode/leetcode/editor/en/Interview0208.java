package leetcode.leetcode.editor.en;

public class Interview0208 {

    public static void main(String[] args) {
    }

    public class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }
            if (head.next == null) {
                return null;
            }
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow) {
                    break;
                }
            }
            if (fast != slow) {
                return null;
            }
            fast = head;
            while (fast != slow) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }



}
