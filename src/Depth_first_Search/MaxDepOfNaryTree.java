package Depth_first_Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static javax.print.attribute.standard.MediaSizeName.A;

/**
 * Given a n-ary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 *
 * For example, given a 3-ary tree:
 *     1
 *    /|\
 *   3 2 4
 *  / \
 * 5   6
 * We should return its max depth, which is 3.
 */
public class MaxDepOfNaryTree {

    public static void main(String args[]) {
        Node root = new Node();
        root.val = 1;
        Node node3 = new Node();
        node3.val = 3;
        Node node2 = new Node();
        node2.val = 2;
        Node node4 = new Node();
        node4.val = 4;
        root.children = Arrays.asList(node3,node2,node4);
        Node node5 = new Node();
        node5.val = 5;
        Node node6 = new Node();
        node6.val = 6;
        node3.children = Arrays.asList(node5,node6);
        System.out.println(new MaxDepOfNaryTree().maxDepth(root));
    }

    /**
     * traditional Stack-based
     * 同一个用例，本地通过，leet上失败，不知道why
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        int max_depth = 0;
        if(root == null)
            return max_depth;
        Stack<Node> theStack = new Stack<Node>();
        theStack.push(root);
        Stack<Boolean> visitedStack = new Stack<Boolean>();
        visitedStack.push(false);
        Stack<Integer> depth = new Stack<Integer>();
        depth.push(1);
        while(!theStack.empty()) {
            if(visitedStack.peek()) {
                visitedStack.pop();
                theStack.pop();
                depth.pop();
                continue;
            }
            visitedStack.pop();
            visitedStack.push(true);
            Node tmp = theStack.peek();
            if(tmp.children == null) {
                max_depth = max_depth > depth.peek() ? max_depth : depth.peek();
                theStack.pop();
                visitedStack.pop();
                depth.pop();
                continue;
            }
            List<Node> node_list = tmp.children;
            int tmp_depth = depth.peek();
            for(int i = 0; i < node_list.size(); i++) {
                Node node_new = node_list.get(i);
                theStack.push(node_new);
                visitedStack.push(false);
                depth.push(tmp_depth + 1);
            }
        }
        return max_depth;
    }

    /**
     * 递归解法
     * Runtime: 2 ms, faster than 99.95% of Java online submissions for Maximum Depth of N-ary Tree.
     * Memory Usage: 33.8 MB, less than 61.89% of Java online submissions for Maximum Depth of N-ary Tree.
     * @param root
     * @return
     */
    public int maxDepth1(Node root) {
        int max_depth = 0;
        if(root == null)
            return max_depth;
        max_depth = getChildMax(root, 1);
        return max_depth;
    }

    public int getChildMax(Node root, int curDepth) {
        if(root == null)
            return curDepth;
        List<Node> childs = root.children;
        int maxDepth = curDepth;
        for(int i = 0; i < childs.size(); i++) {
            int newDepth = getChildMax(childs.get(i), curDepth+1);
            maxDepth = maxDepth > newDepth ? maxDepth : newDepth;
        }
        return maxDepth;
    }

}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
}
