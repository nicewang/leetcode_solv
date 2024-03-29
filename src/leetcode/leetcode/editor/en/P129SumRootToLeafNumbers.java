//Given a binary tree containing digits from 0-9 only, each root-to-leaf path co
//uld represent a number. 
//
// An example is the root-to-leaf path 1->2->3 which represents the number 123. 
//
//
// Find the total sum of all root-to-leaf numbers. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// 
//Input: [1,2,3]
//    1
//   / \
//  2   3
//Output: 25
//Explanation:
//The root-to-leaf path 1->2 represents the number 12.
//The root-to-leaf path 1->3 represents the number 13.
//Therefore, sum = 12 + 13 = 25. 
//
// Example 2: 
//
// 
//Input: [4,9,0,5,1]
//    4
//   / \
//  9   0
// / \
//5   1
//Output: 1026
//Explanation:
//The root-to-leaf path 4->9->5 represents the number 495.
//The root-to-leaf path 4->9->1 represents the number 491.
//The root-to-leaf path 4->0 represents the number 40.
//Therefore, sum = 495 + 491 + 40 = 1026. 
// Related Topics Tree Depth-first Search


package leetcode.leetcode.editor.en;
//Java：Sum Root to Leaf Numbers
public class P129SumRootToLeafNumbers{
    public static void main(String[] args) {
        Solution solution = new P129SumRootToLeafNumbers().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // 0ms 37.6MB
    public int sumNumbers(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return find(root, 0);
    }
    private int find(TreeNode root, int sum) {
        sum = sum * 10 + root.val;
        if(root.left == null && root.right == null) {
            return sum;
        }
        int sum1 = 0, sum2 = 0;
        if(root.left != null) {
            sum1 = find(root.left, sum);
        }
        if(root.right != null) {
            sum2 = find(root.right, sum);
        }
        return sum1 + sum2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
}