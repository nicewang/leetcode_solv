//Merge k sorted linked lists and return it as one sorted list. Analyze and desc
//ribe its complexity. 
//
// Example: 
//
// 
//Input:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//Output: 1->1->2->3->4->4->5->6
// 
// Related Topics Linked List Divide and Conquer Heap


package leetcode.leetcode.editor.en;

//Java：Merge k Sorted Lists
public class P23MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new P23MergeKSortedLists().new Solution();
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
        // 解法一：分治合并(合并用到归并的思想) 1ms 41.6MB
        // TODO 优先队列合并解法和顺序合并解法
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            return merge(lists, 0, lists.length - 1);
        }

        private ListNode merge(ListNode[] lists, int l, int r) {
            if (l == r) {
                return lists[l];
            }
            int mid = (l + r) / 2;
            // TODO 此处两层嵌套看得也是头晕
            return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
        }

        private ListNode mergeTwoLists(ListNode l, ListNode r) {
            ListNode res = new ListNode(0);
            ListNode node = res;
            while (l != null && r != null) {
                if (l.val < r.val) {
                    node.next = l;
                    l = l.next;
                } else {
                    node.next = r;
                    r = r.next;
                }
                node = node.next;
            }
            node.next = l != null ? l : r;
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}