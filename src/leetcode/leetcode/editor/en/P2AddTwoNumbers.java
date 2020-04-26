//You are given two non-empty linked lists representing two non-negative integer
//s. The digits are stored in reverse order and each of their nodes contain a sing
//le digit. Add the two numbers and return it as a linked list. 
//
// You may assume the two numbers do not contain any leading zero, except the nu
//mber 0 itself. 
//
// Example: 
//
// 
//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8
//Explanation: 342 + 465 = 807.
// 
// Related Topics Linked List Math


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Add Two Numbers
public class P2AddTwoNumbers{
    public static void main(String[] args) {
        Solution solution = new P2AddTwoNumbers().new Solution();
        // TO TEST
        ListNode l0 = new P2AddTwoNumbers().new ListNode(3);
        l0.next = new P2AddTwoNumbers().new ListNode(9);
        ListNode l1 = l0.next;
        l1.next = new P2AddTwoNumbers().new ListNode(9);
        l1 = l1.next;
        l1.next = new P2AddTwoNumbers().new ListNode(9);
        l1 = l1.next;
        l1.next = new P2AddTwoNumbers().new ListNode(9);
        l1 = l1.next;
        l1.next = new P2AddTwoNumbers().new ListNode(9);
        l1 = l1.next;
        l1.next = new P2AddTwoNumbers().new ListNode(9);
        l1 = l1.next;
        l1.next = new P2AddTwoNumbers().new ListNode(9);
        l1 = l1.next;
        l1.next = new P2AddTwoNumbers().new ListNode(9);
        l1 = l1.next;
        l1.next = new P2AddTwoNumbers().new ListNode(9);
        ListNode l2 = new P2AddTwoNumbers().new ListNode(7);
        ListNode l3 = solution.addTwoNumbers(l0, l2);
        System.out.println(l3.toString());
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 解法3: 递归解法
    // 2ms 40MB
    int carry = 0;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultNode = new ListNode(0);
        int len1 = 0;
        int len2 = 0;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while(p1 != null) {
            len1++;
            p1 = p1.next;
        }
        while(p2 != null) {
            len2++;
            p2 = p2.next;
        }
        p1 = len1>len2?l1:l2;
        p2 = len1>len2?l2:l1;
        resultNode = addListNode(p1,p2,Math.abs(len1-len2));
        if(carry > 0) {
            ListNode tmp = new ListNode(carry);
            tmp.next = resultNode;
            resultNode = tmp;
        }
        return resultNode;
    }

    private ListNode addListNode(ListNode l1, ListNode l2, int len) {
        ListNode resultNode;
        // 返回条件，当两个链表到达最尾端，也就是低位对齐时，创建新的节点返回
        if(l1.next == null && l2.next == null) {
            resultNode = new ListNode((l1.val + l2.val + carry) % 10);
            carry = (l1.val + l2.val + carry) / 10;
            return resultNode;
        }
        /*
         * 如果len==0 说明链表此时已经对齐,因此递归调用helper，同时两个链表同时传入next，返回值为已经构造好的低位结果链表
         * 此时l1,l2对齐，因此(l1.val+l2.val+flag)%10表示当前位结果
         */
        if(len == 0) {
            resultNode = addListNode(l1.next, l2.next, len);
            ListNode tmp = new ListNode((l1.val + l2.val + carry) % 10);
            carry = (l1.val + l2.val + carry) / 10;
            tmp.next = resultNode;
            resultNode = tmp;
        }
        /*
         * 如果len！=0 说明此时链表还没有对齐，因此递归调用helper，传入较长链表l1.next，返回值为已经构造好的低位结果链表
         * 此时由于l2较短，因此(l1.val+flag)%10表示当前位结果
         */
        else {
            resultNode = addListNode(l1.next, l2, len-1);
            ListNode tmp = new ListNode((l1.val + carry) % 10);
            carry = (l1.val + carry) / 10;
            tmp.next = resultNode;
            resultNode = tmp;
        }
        return resultNode;
    }

    // 解法2：链表反转 相加 再链表反转 避免大数问题
    // 3ms 40MB
    public ListNode addTwoNumbersTwo(ListNode l1, ListNode l2) {
        return listNodeAdd(reverseListNodeOne(l1), reverseListNodeOne(l2));
    }

    // 2.1 链表反转
    private ListNode reverseListNodeOne(ListNode l) {
        ListNode resultNode;
        if(l == null) {
            return null;
        }
        resultNode = new ListNode(l.val);
        while(l.next != null) {
            l = l.next;
            ListNode tmp = new ListNode(l.val);
            tmp.next = resultNode;
            resultNode = tmp;
        }
        return resultNode;
    }

    // 2.2 反转链表相加再反转
    // 即 反转列表相加-转(含反转)->ListNode
    private ListNode listNodeAdd(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) {
            return null;
        }
        int sumFirst = l1.val + l2.val;
        int carry = sumFirst / 10;
        ListNode resultNode = new ListNode(sumFirst % 10);
        l1 = l1.next;
        l2 = l2.next;
        while(l1 != null || l2 != null) {
            int i1 = 0;
            int i2 = 0;
            if(l1 != null) {
                i1 = l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                i2 = l2.val;
                l2 = l2.next;
            }
            int sum = i1 + i2 + carry;
            carry = sum / 10;
            ListNode tmp = new ListNode(sum % 10);
            tmp.next = resultNode;
            resultNode = tmp;
        }
        if(carry != 0) {
            ListNode tmp = new ListNode(carry);
            tmp.next = resultNode;
            resultNode = tmp;
        }
        return resultNode;
    }

    // 解法1：使用堆栈解法 避免大数问题
    // 6ms 40MB
    public ListNode addTwoNumbersOne(ListNode l1, ListNode l2) {
         Stack<Integer> s1 = addStack(l1);
         Stack<Integer> s2 = addStack(l2);
         return stackSum(s1, s2);
    }

    // 1.1 压栈
    private Stack<Integer> addStack(ListNode l) {
        Stack<Integer> s = new Stack<>();
        if(l == null) {
            return s;
        }
        while(l.next != null) {
            s.push(l.val);
            l = l.next;
        }
        s.push(l.val);
        return s;
    }

    // 1.2 堆栈相加-转(含反转)->ListNode
    private ListNode stackSum(Stack<Integer> s1, Stack<Integer> s2) {
        ListNode l = new ListNode(0);
        int carry = 0;
        while(!s1.isEmpty() || !s2.isEmpty()) {
            int i1 = 0;
            int i2 = 0;
            if(!s1.isEmpty()) {
                i1 = s1.pop();
            }
            if(!s2.isEmpty()) {
                i2 = s2.pop();
            }
            int sum = i1 + i2 + carry;
            carry = sum / 10;
            l.val = sum % 10;
            ListNode l_new = new ListNode(0);
            l_new.next = l;
            l = l_new;
        }
        if(carry != 0) {
            l.val = carry;
        } else {
            l = l.next;
        }
        return l;
    }

    // 解法0： ListNode—转->整型 整型相加求sum sum-转->ListNode ListNode反转
    // 缺点: 耗时 无法避免大数相加问题
    public ListNode addTwoNumbersComplex(ListNode l1, ListNode l2) {
        long num1 = l1.val == 0 ? 0 : getNumFrListNode(0, l1);
        long num2 = l2.val == 0 ? 0 : getNumFrListNode(0, l2);
        // 0.2 整型相加求sum
        long sum = num1 + num2;
        ListNode resultNode = getListNodeFrNum(new ListNode(0), sum);
        return reverseListNode(resultNode);
    }

    // 0.1 ListNode—转->整型
    private long getNumFrListNode(long initNum, ListNode l) {
        long num = initNum;
        if(l == null) {
            return num / 10;
        }
        if(l.val != 0) {
            num += l.val;
        }
        return getNumFrListNode(num*10, l.next);
    }

    // 0.3 sum-转->ListNode
    private ListNode getListNodeFrNum(ListNode initNode, long num) {
        if(num / 10 == 0) {
            initNode.val = (int) num;
            initNode.next = null;
        } else {
            initNode.val = (int) (num % 10);
            initNode.next = getListNodeFrNum(new ListNode(0), num / 10);
        }
        return initNode;
    }

    // 0.4 ListNode反转
    private ListNode reverseListNode(ListNode l) {
        ListNode resultNode;
        if(l == null) {
            return null;
        }
        resultNode = new ListNode(l.val);
        while(l.next != null) {
            l = l.next;
            ListNode tmp = new ListNode(l.val);
            tmp.next = resultNode;
            resultNode = tmp;
        }
        return resultNode;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

}