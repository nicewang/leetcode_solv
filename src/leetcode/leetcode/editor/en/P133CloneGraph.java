//Given a reference of a node in a connected undirected graph. 
//
// Return a deep copy (clone) of the graph. 
//
// Each node in the graph contains a val (int) and a list (List[Node]) of its ne
//ighbors. 
//
// 
//class Node {
//    public int val;
//    public List<Node> neighbors;
//}
// 
//
// 
//
// Test case format: 
//
// For simplicity sake, each node's value is the same as the node's index (1-ind
//exed). For example, the first node with val = 1, the second node with val = 2, a
//nd so on. The graph is represented in the test case using an adjacency list. 
//
// Adjacency list is a collection of unordered lists used to represent a finite 
//graph. Each list describes the set of neighbors of a node in the graph. 
//
// The given node will always be the first node with val = 1. You must return th
//e copy of the given node as a reference to the cloned graph. 
//
// 
// Example 1: 
//
// 
//Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
//Output: [[2,4],[1,3],[2,4],[1,3]]
//Explanation: There are 4 nodes in the graph.
//1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
//3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
// 
//
// Example 2: 
//
// 
//Input: adjList = [[]]
//Output: [[]]
//Explanation: Note that the input contains one empty list. The graph consists o
//f only one node with val = 1 and it does not have any neighbors.
// 
//
// Example 3: 
//
// 
//Input: adjList = []
//Output: []
//Explanation: This an empty graph, it does not have any nodes.
// 
//
// Example 4: 
//
// 
//Input: adjList = [[2],[1]]
//Output: [[2],[1]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= Node.val <= 100 
// Node.val is unique for each node. 
// Number of Nodes will not exceed 100. 
// There is no repeated edges and no self-loops in the graph. 
// The Graph is connected and all nodes can be visited starting from the given n
//ode. 
// 
// Related Topics Depth-first Search Breadth-first Search Graph


package leetcode.leetcode.editor.en;

import java.util.*;

//Java：Clone Graph
public class P133CloneGraph {
    public static void main(String[] args) {
        Solution solution = new P133CloneGraph().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

    class Solution {
        // TODO 这题多看几遍 比较经典

        // 解法二：bfs 时间复杂度O(n) 空间复杂度O(n) 40ms 40.1MB
        public Node cloneGraph(Node node) {
            return bfs(node);
        }

        private Node bfs(Node node) {
            if (node == null) {
                return node;
            }
            HashMap<Node, Node> visited = new HashMap<>();
            LinkedList<Node> queue = new LinkedList<>();
            Node clonedNode = new Node(node.val, new ArrayList<>());
            queue.add(node);
            visited.put(node, clonedNode);
            while (!queue.isEmpty()) {
                Node curNode = queue.remove();
                for (Node neighbor : curNode.neighbors) {
                    if (!visited.containsKey(neighbor)) {
                        visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                        queue.add(neighbor);
                    }
                    visited.get(curNode).neighbors.add(visited.get(neighbor));
                }
            }
            return clonedNode;
        }

        // 解法一：dfs 时间复杂度O(n) 空间复杂度O(n) 35ms 40.2MB
        public Node cloneGraphOne(Node node) {
            return dfs(node, new HashMap<>());
        }

        // dfs解法里需要用到一个visited的map来存放已经访问过的node和其对应的clonedNode 不然会陷入死循环
        private Node dfs(Node node, HashMap<Node, Node> visited) {
            if (node == null) {
                return node;
            }
            if (visited.containsKey(node)) {
                return visited.get(node);
            }
            Node clonedNode = new Node(node.val, new ArrayList<>());
            // attention：要放在下一个访问之前 避免死循环
            visited.put(node, clonedNode);
            for (Node neighbor : node.neighbors) {
                clonedNode.neighbors.add(dfs(neighbor, visited));
            }
            return clonedNode;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}