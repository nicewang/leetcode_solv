//A linked list is given such that each node contains an additional random point
//er which could point to any node in the list or null. 
//
// Return a deep copy of the list. 
//
// The Linked List is represented in the input/output as a list of n nodes. Each
// node is represented as a pair of [val, random_index] where: 
//
// 
// val: an integer representing Node.val 
// random_index: the index of the node (range from 0 to n-1) where random pointe
//r points to, or null if it does not point to any node. 
// 
//
// 
// Example 1: 
//
// 
//Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
//
// Example 2: 
//
// 
//Input: head = [[1,1],[2,1]]
//Output: [[1,1],[2,1]]
// 
//
// Example 3: 
//
// 
//
// 
//Input: head = [[3,null],[3,0],[3,null]]
//Output: [[3,null],[3,0],[3,null]]
// 
//
// Example 4: 
//
// 
//Input: head = []
//Output: []
//Explanation: Given linked list is empty (null pointer), so return null.
// 
//
// 
// Constraints: 
//
// 
// -10000 <= Node.val <= 10000 
// Node.random is null or pointing to a node in the linked list. 
// Number of Nodes will not exceed 1000. 
// 
// Related Topics Hash Table Linked List


package leetcode.leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//Java：Copy List with Random Pointer
public class P138CopyListWithRandomPointer {
    public static void main(String[] args) {
        Solution solution = new P138CopyListWithRandomPointer().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

    class Solution {

        // 解法一：1ms 39.3MB TODO 回溯+剩余两种解法
        public Node copyRandomList(Node head) {
            if(head == null) {
                return null;
            }
            Node res_head = new Node(head.val);
            Node res = res_head;
            Map<Node, Node> map = new HashMap<>();
            Node tmp = head;
            map.put(head, res);
            Stack<Node> randomStack = new Stack<>();
            Stack<Node> keyStack = new Stack<>();
            while(tmp != null) {
                if(tmp.random != null) {
                    if(map.containsKey(tmp.random)) {
                        res.random = map.get(tmp.random);
                    } else {
                        randomStack.push(res);
                        keyStack.push(tmp.random);
                    }
                }
                if(tmp.next != null) {
                    res.next = new Node(tmp.next.val);
                    res = res.next;
                    map.put(tmp.next, res);
                }
                tmp = tmp.next;
            }
            while(!randomStack.isEmpty()) {
                Node node = randomStack.pop();
                node.random = map.get(keyStack.pop());
            }
            return res_head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}