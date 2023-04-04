//Given a linked list and a value x, partition it such that all nodes less than 
//x come before nodes greater than or equal to x. 
//
// You should preserve the original relative order of the nodes in each of the t
//wo partitions. 
//
// Example: 
//
// 
//Input: head = 1->4->3->2->5->2, x = 3
//Output: 1->2->2->4->3->5
// 
// Related Topics Linked List Two Pointers


package leetcode.leetcode.editor.en;

//Java：Partition List
public class P86PartitionList {
    public static void main(String[] args) {
        Solution solution = new P86PartitionList().new Solution();
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
        // 解法：双指针法 1ms 39.3MB
//        我们可以用两个指针before和after来追踪上述的两个链表。两个指针可以用于分别创建两个链表，然后将这两个链表连接即可获得所需的链表。
//        1.初始化两个指针before和after。在实现中，我们将两个指针初始化为哑ListNode。这有助于减少条件判断。（不信的话，你可以试着写一个不带哑结点的方法自己看看！）
//        2.利用head指针遍历原链表。
//        3.若head指针指向的元素值小于x，该节点应当是before链表的一部分。因此我们将其移到before中。
//        4.否则，该节点应当是after链表的一部分。因此我们将其移到after中。
//        5.遍历完原有链表的全部元素之后，我们得到了两个链表before和after。原有链表的元素或者在before中或者在after中，这取决于它们的值。
//        6.现在，可以将before和after连接，组成所求的链表。
        public ListNode partition(ListNode head, int x) {
            if (head == null) {
                return null;
            }
            ListNode before_head = new ListNode(0);
            ListNode before = before_head;
            ListNode after_head = new ListNode(0);
            ListNode after = after_head;
            while (head != null) {
                if(head.val < x) {
                    before.next = new ListNode(head.val);
                    before = before.next;
                } else {
                    after.next = new ListNode(head.val);
                    after = after.next;
                }
                head = head.next;
            }
            before.next = after_head.next;
            return before_head.next;
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