//Given a linked list, return the node where the cycle begins. If there is no cy
//cle, return null. 
//
// To represent a cycle in the given linked list, we use an integer pos which re
//presents the position (0-indexed) in the linked list where tail connects to. If 
//pos is -1, then there is no cycle in the linked list. 
//
// Note: Do not modify the linked list. 
//
// 
//
// Example 1: 
//
// 
//Input: head = [3,2,0,-4], pos = 1
//Output: tail connects to node index 1
//Explanation: There is a cycle in the linked list, where tail connects to the s
//econd node.
// 
//
// 
//
// Example 2: 
//
// 
//Input: head = [1,2], pos = 0
//Output: tail connects to node index 0
//Explanation: There is a cycle in the linked list, where tail connects to the f
//irst node.
// 
//
// 
//
// Example 3: 
//
// 
//Input: head = [1], pos = -1
//Output: no cycle
//Explanation: There is no cycle in the linked list.
// 
//
// 
//
// 
//
// Follow-up: 
//Can you solve it without using extra space? 
// Related Topics Linked List Two Pointers


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Linked List Cycle II
public class P142LinkedListCycleIi{
    public static void main(String[] args) {
        Solution solution = new P142LinkedListCycleIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public class Solution {
        // 5ms 41.1MB
        public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }
            HashSet<ListNode> visited = new HashSet<>();
            ListNode curNode = head;
            while (curNode != null) {
                if (visited.contains(curNode)) {
                    return curNode;
                }
                visited.add(curNode);
                curNode = curNode.next;
            }
            return null;
        }

        // 解法二：Floyd算法 0ms 39.6MB TODO 可以多看一下数学证明 还挺有意思的
        public ListNode detectCycleTwo(ListNode head) {
            if (head == null) {
                return null;
            }

            // If there is a cycle, the fast/slow pointers will intersect at some
            // node. Otherwise, there is no cycle, so we cannot find an e***ance to
            // a cycle.
            ListNode intersect = getIntersect(head);
            if (intersect == null) {
                return null;
            }

            // To find the e***ance to the cycle, we have two pointers traverse at
            // the same speed -- one from the front of the list, and the other from
            // the point of intersection.
            ListNode ptr1 = head;
            ListNode ptr2 = intersect;
            while (ptr1 != ptr2) {
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
            }

            return ptr1;
        }

        private ListNode getIntersect(ListNode head) {
            ListNode tortoise = head;
            ListNode hare = head;

            // A fast pointer will either loop around a cycle and meet the slow
            // pointer or reach the `null` at the end of a non-cyclic list.
            while (hare != null && hare.next != null) {
                tortoise = tortoise.next;
                hare = hare.next.next;
                if (tortoise == hare) {
                    return tortoise;
                }
            }

            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
}
