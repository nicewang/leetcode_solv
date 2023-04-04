//Implement the following operations of a queue using stacks. 
//
// 
// push(x) -- Push element x to the back of queue. 
// pop() -- Removes the element from in front of queue. 
// peek() -- Get the front element. 
// empty() -- Return whether the queue is empty. 
// 
//
// Example: 
//
// 
//MyQueue queue = new MyQueue();
//
//queue.push(1);
//queue.push(2);  
//queue.peek();  // returns 1
//queue.pop();   // returns 1
//queue.empty(); // returns false 
//
// Notes: 
//
// 
// You must use only standard operations of a stack -- which means only push to 
//top, peek/pop from top, size, and is empty operations are valid. 
// Depending on your language, stack may not be supported natively. You may simu
//late a stack by using a list or deque (double-ended queue), as long as you use o
//nly standard operations of a stack. 
// You may assume that all operations are valid (for example, no pop or peek ope
//rations will be called on an empty queue). 
// 
// Related Topics Stack Design


package leetcode.leetcode.editor.en;

import java.util.Stack;

//Java：Implement Queue using Stacks
public class P232ImplementQueueUsingStacks {
    public static void main(String[] args) {
        MyQueue solution = new P232ImplementQueueUsingStacks().new MyQueue();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyQueue {

        private Stack<Integer> s1;
        private Stack<Integer> s2;
        private int front;

        /** Initialize your data structure here. */
        public MyQueue() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            if(s1.isEmpty()) {
                front = x;
            }
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s2.push(x);
            while(!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            int res = s1.pop();
            if(!s1.isEmpty()) {
                front = s1.peek();
            }
            return res;
        }

        /** Get the front element. */
        public int peek() {
            return front;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return s1.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}