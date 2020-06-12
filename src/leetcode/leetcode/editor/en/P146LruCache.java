//Design and implement a data structure for Least Recently Used (LRU) cache. It 
//should support the following operations: get and put. 
//
// get(key) - Get the value (will always be positive) of the key if the key exis
//ts in the cache, otherwise return -1. 
//put(key, value) - Set or insert the value if the key is not already present. W
//hen the cache reached its capacity, it should invalidate the least recently used
// item before inserting a new item. 
//
// The cache is initialized with a positive capacity. 
//
// Follow up: 
//Could you do both operations in O(1) time complexity? 
//
// Example: 
//
// 
//LRUCache cache = new LRUCache( 2 /* capacity */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // returns 1
//cache.put(3, 3);    // evicts key 2
//cache.get(2);       // returns -1 (not found)
//cache.put(4, 4);    // evicts key 1
//cache.get(1);       // returns -1 (not found)
//cache.get(3);       // returns 3
//cache.get(4);       // returns 4
// 
//
// 
// Related Topics Design


package leetcode.leetcode.editor.en;

import java.util.HashMap;
import java.util.LinkedList;

//Java：LRU Cache
public class P146LruCache {
    public static void main(String[] args) {
        LRUCache lruCache = new P146LruCache().new LRUCache(1);
        // TO TEST
        lruCache.put(2, 1);
        System.out.println(lruCache.get(1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {

        private HashMap<Integer, DoubleListNode> cache = new HashMap<>();
        private int capacity;
        private int count;
        private DoubleListNode head;
        private DoubleListNode tail;
        // 21ms 47.7MB
        public LRUCache(int capacity) {
            this.count = 0;
            this.capacity = capacity;

            this.head = new DoubleListNode();
            head.pre = null;

            this.tail = new DoubleListNode();
            tail.next = null;

            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            DoubleListNode node = cache.get(key);
            moveToHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            DoubleListNode node;
            if (cache.containsKey(key)) {
                node = cache.get(key);
                node.val = value;
                moveToHead(node);
            } else {
                if (count == capacity) {
                    removeLast();
                } else {
                    count++;
                }
                node = new DoubleListNode();
                node.key = key;
                node.val = value;
                addFirst(node);
            }
        }

        private void moveToHead(DoubleListNode node) {
            DoubleListNode preTmp = node.pre;
            DoubleListNode nextTmp = node.next;
            preTmp.next = nextTmp;
            nextTmp.pre = preTmp;
            cache.remove(node.key);
            addFirst(node);
        }

        private void removeLast() {
            DoubleListNode oldLast = tail.pre;
            DoubleListNode newLast = oldLast.pre;
            newLast.next = tail;
            tail.pre = newLast;
            cache.remove(oldLast.key);
        }

        private void addFirst(DoubleListNode node) {
            DoubleListNode nodeNext = head.next;
            node.next = nodeNext;
            nodeNext.pre = node;
            head.next = node;
            node.pre = head;
            cache.put(node.key, node);
        }

        class DoubleListNode {
            private int key;
            private int val;
            private DoubleListNode pre;
            private DoubleListNode next;
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}