package leetcode.leetcode.editor.cn;
import java.util.*;

class Solution {
    List<Character> lis = new ArrayList<Character>();
    public String findTheString(int[][] lcp) {
        return "";
    }

    public ListNode solv(ListNode node1, ListNode node2) {
        ListNode res = new ListNode(-1);
        ListNode tmp = res;
        ListNode tmp1 = node1;
        ListNode tmp2 = node2;
        while (tmp1 != null || tmp2 != null) {
            if (tmp1 != null && tmp2 != null) {
                if (tmp1.val < tmp2.val) {
                    tmp.next = new ListNode(tmp1.val);
                    tmp1 = tmp1.next;
                } else {
                    tmp.next = new ListNode(tmp2.val);
                    tmp2 = tmp2.next;
                }
                tmp = tmp.next;
            }
            if (tmp1 != null) {
                tmp.next = tmp1;
            }
            if (tmp2 != null) {
                tmp.next = tmp2;
            }
        }
        return res.next;

    }

}
// test validity of old access token
