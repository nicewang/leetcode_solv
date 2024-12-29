/*
 * @lc app=leetcode id=94 lang=cpp
 *
 * [94] Binary Tree Inorder Traversal
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
#include <vector>

using std::vector;

// inorder traversal: left tree (recursively) -> root -> right tree (recursively)

class Solution {
public:

    struct TreeNode {
        int val;
        TreeNode *left;
        TreeNode *right;
        TreeNode() : val(0), left(nullptr), right(nullptr) {}
        TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
        TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
    };

    // to record the visted node's value
    vector<int> records;
    // time complexity: o(n)
    // space complexity: o(n)
    // n = depth of the tree
    vector<int> inorderTraversal(TreeNode* root) {
        dfs(root);
        return records;
    }

    // visiting function
    // be called recursively when we visit the left tree and the right tree
    void dfs(TreeNode* node) {
        // for edge situation
        // check whether the current node is null
        if (node == NULL) {
            // time to end
            return;
        }
        // 1. visit left
        // call 'dfs' recursively
        dfs(node->left);
        // 2. visit the root
        records.push_back(node->val);
        // 3. vist right recursively
        dfs(node->right);
    }
};
// @lc code=end

