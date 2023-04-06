#### 1. recursion solution
```
class Solution {

    // recursion solution
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

        resultNode = addListNode(p1,p2,Math.min(len1,len2));
        return resultNode;
    }

    private ListNode addListNode(ListNode l1, ListNode l2, int len) {
        ListNode resultNode;
        
        if (l1.next == null && l2 == null) {
            resultNode = new ListNode((l1.val + carry) % 10);
            carry = (l1.val + carry) / 10;
            if (carry > 0) {
                resultNode.next = new ListNode(carry);
            }
            return resultNode;
        }

        if(l1.next == null && l2.next == null) {
            resultNode = new ListNode((l1.val + l2.val + carry) % 10);
            carry = (l1.val + l2.val + carry) / 10;
            if (carry > 0) {
                resultNode.next = new ListNode(carry);
            }
            return resultNode; 
        }
        if(len == 0) {
            ListNode tmp = new ListNode((l1.val + carry) % 10);
            carry = (l1.val + carry) / 10;
            resultNode = addListNode(l1.next, l2, len);
            tmp.next = resultNode;
            resultNode = tmp; // a detail of problem had changed laterly, so use this
        } else {
            ListNode tmp = new ListNode((l1.val + l2.val + carry) % 10);
            carry = (l1.val + l2.val + carry) / 10;
            resultNode = addListNode(l1.next, l2.next, len-1);
            tmp.next = resultNode;
            resultNode = tmp; // a detail of problem had changed laterly, so use this
        }
        return resultNode;
    }

}
```