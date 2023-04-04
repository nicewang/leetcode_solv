//Given n, how many structurally unique BST's (binary search trees) that store v
//alues 1 ... n? 
//
// Example: 
//
// 
//Input: 3
//Output: 5
//Explanation:
//Given n = 3, there are a total of 5 unique BST's:
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
// 
// Related Topics Dynamic Programming Tree


package leetcode.leetcode.editor.en;

import java.util.LinkedList;
import java.util.List;

//Java：Unique Binary Search Trees
public class P96UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new P96UniqueBinarySearchTrees().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法三：动态规划 0ms 36.3MB 时间复杂度O(n^2) 空间复杂度O(n)
        public int numTrees(int n) {
            if(n == 1) {
                return 1;
            }
            if(n == 2) {
                return 2;
            }
            int dp[] = new int[n+1];
            dp[0] = 1;
            dp[1] = 1;
            for(int i = 2; i <= n; i++) {
                for(int j = 1; j <= i; j++) {
                    dp[i] += dp[j-1]*dp[i-j];
                }
            }
            return dp[n];
        }

        // 解法二：暴力递归 1548ms 36.6MB
        public int numTreesTwo(int n) {
            return generateBST(1, n);
        }
        private int generateBST(int start, int end) {
            if(end < start) {
                return 0;
            }
            if(end == start) {
                return 1;
            }
            int count = 0;
            for(int i = start+1; i < end; i++) {
                count += generateBST(start, i-1) * generateBST(i+1, end);
            }
            count += generateBST(start+1, end);
            count += generateBST(start, end-1);
            return count;
        }

        //        我们从序列 1 ..n 中取出数字 i，作为当前树的树根。于是，剩余 i - 1 个元素可用于左子树，n - i 个元素用于右子树。
//        现在，我们对序列 1 ... i - 1 重复上述过程，以构建所有的左子树；然后对 i + 1 ... n 重复，以构建所有的右子树。
//        这样，我们就有了树根 i 和可能的左子树、右子树的列表。
//        最后一步，对两个列表循环，将左子树和右子树连接在根上。
        public int numTreesOne(int n) {
            return generateTrees(n).size();
        }

        private LinkedList<TreeNode> generate_trees(int start, int end) {
            LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
            if (start > end) {
                all_trees.add(null);
                return all_trees;
            }

            // pick up a root
            for (int i = start; i <= end; i++) {
                // all possible left subtrees if i is choosen to be a root
                LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

                // all possible right subtrees if i is choosen to be a root
                LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

                // connect left and right trees to the root i
                for (TreeNode l : left_trees) {
                    for (TreeNode r : right_trees) {
                        TreeNode current_tree = new TreeNode(i);
                        current_tree.left = l;
                        current_tree.right = r;
                        all_trees.add(current_tree);
                    }
                }
            }
            return all_trees;
        }

        private List<TreeNode> generateTrees(int n) {
            if (n == 0) {
                return new LinkedList<TreeNode>();
            }
            return generate_trees(1, n);
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