/*
 * @lc app=leetcode id=235 lang=cpp
 *
 * [235] Lowest Common Ancestor of a Binary Search Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
#include <cstdlib>
#include <vector>

class Solution {
public:

    struct TreeNode {
      int val;
      TreeNode *left;
      TreeNode *right;
      TreeNode(int x) : val(x), left(NULL), right(NULL) {}
    };

    // Way-1 Traverse Once: most simplified
    // Time complexity: o(logn) (logn = depth of bst),
    //                  o(n) for worst situastion (the tree just as a linked list)
    // Space complexity: o(1)
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        TreeNode* cur = root;
        while(true) {
            if (p->val < cur->val && q->val < cur->val) {
                cur = cur->left;
            } else if (p->val > cur->val && q->val > cur->val) {
                cur = cur->right;
            } else {
                return cur;
            }
        }
    }

    // Way-2: recursion version of way-1
    // Time complexity: o(logn), o(n) for worst situastion
    // Space complexity: o(logn) and o(n) for worst
    //                   since the recursion invoking need extra stack space
    TreeNode* lowestCommonAncestor_2(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (p->val < root->val && q->val < root->val) {
            return lowestCommonAncestor_2(root->left, p, q);
        } else if (p->val > root->val && q->val > root->val) {
            return lowestCommonAncestor_2(root->right, p, q);
        } else {
            return root;
        }
    }

    // Way-3: Traverse Twice:
    //                       * First traverse: record the path
    //                       * Second traverse: find nearest common ancestor
    // Time complexity: o(logn), o(n) for worst
    // Space complexity: o(logn), o(n) for worst
    TreeNode* lowestCommonAncestor_3(TreeNode* root, TreeNode* p, TreeNode* q) {
        std::vector<TreeNode*> p_path = getPath(root, p);
        std::vector<TreeNode*> q_path = getPath(root, q);
        TreeNode* ancestor;
        for (int i = 0; i < p_path.size() && i < q_path.size(); i++) {
            if (p_path[i] != q_path[i]) {
                return ancestor;
            }
            ancestor = p_path[i];
        }
        return ancestor;
    }

    std::vector<TreeNode*> getPath(TreeNode* node, TreeNode* target) {
        std::vector<TreeNode*> path;
        while (node->val != target->val) {
            path.push_back(node);
            if (node->val > target->val) {
                node = node->left;
            } else {
                node = node->right;
            }
        }
        path.push_back(node);
        return path;
    }

};
// @lc code=end

