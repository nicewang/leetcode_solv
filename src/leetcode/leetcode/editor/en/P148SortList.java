//Sort a linked list in O(n log n) time using constant space complexity. 
//
// Example 1: 
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
// Related Topics Linked List Sort


package leetcode.leetcode.editor.en;

//Java：Sort List
public class P148SortList {
    public static void main(String[] args) {
        Solution solution = new P148SortList().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法二：归并排序(递归解法) 3ms 41.7MB
        public ListNode sortList(ListNode head) {
            if(head == null || head.next == null) {
                return head;
            }
            ListNode fast = head.next;
            ListNode slow = head;
            while(fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode tmp = slow.next;
            slow.next = null;
            ListNode left = sortList(head);
            ListNode right = sortList(tmp);

            ListNode resNode = new ListNode(0);
            ListNode node = resNode;
            while(left != null && right != null) {
                if(left.val < right.val) {
                   node.next = left;
                   left = left.next;
                } else {
                    node.next = right;
                    right = right.next;
                }
                node = node.next;
            }
            node.next = left != null ? left : right;
            return resNode.next;
        }

        // 解法一
        public ListNode sortListOne(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode resNode = new ListNode(head.val);
            ListNode tmpNode = head.next;
            while (tmpNode != null) {
                if (resNode.val > tmpNode.val) {
                    resNode = new ListNode(tmpNode.val, resNode);
                    tmpNode = tmpNode.next;
                    continue;
                }
                ListNode compNode = resNode;
                while (compNode != null) {
                    if (compNode.next != null && tmpNode.val < compNode.next.val && tmpNode.val >= compNode.val) {
                        compNode.next = new ListNode(tmpNode.val, compNode.next);
                        ;
                        break;
                    } else if (compNode.next == null && tmpNode.val >= compNode.val) {
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