package game_or_fun_new;

import java.util.*;

public class Solv {

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1};
        Solv solv = new Solv();
        String arg1 = "ABCBDAB";
        String arg2 = "BDCABA";
    }

    public ListNode solv(ListNode root) {
        Stack<Integer> stk = new Stack<>();
        ListNode tmp = root;
        ListNode res = new ListNode(-1);
        ListNode res_head = res;
        while (tmp != null) {
            stk.push(tmp.val);
            tmp = tmp.next;
        }
        while (!stk.isEmpty()) {
            res.next = new ListNode(stk.pop());
            res = res.next;
        }
        return res_head.next;
    }

    public ListNode solv1(ListNode root) {
        ListNode node = root.next;
        ListNode pre = root;
        int cnt = 0;
        while (node.next != null) {
            ListNode tmp = node.next;
            node.next = pre;
            if (cnt == 0) {
                pre.next = null;
            }
            pre = node;
            node = tmp;
            cnt++;
        }
        node.next = pre;
        return node;
    }

}
class ListNode {
    int val;
    ListNode next;
    public ListNode(int _val) {
        this.val = _val;
    }
}