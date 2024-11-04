/*
 * @lc app=leetcode id=208 lang=cpp
 *
 * [208] Implement Trie (Prefix Tree)
 */

// @lc code=start
#include <string>
#include <vector>

using std::string;
using std::vector;

class Trie {
private:
    vector<Trie*> children;
    bool isEnd;

    Trie* searchPrefix(string prefix) {
        Trie* node = this;
        for (char ch : prefix) {
            if (node->children[ch-'a'] == nullptr) {
                return nullptr;
            }
            node = node->children[ch-'a'];
        }
        return node;
    }

    void searchMultiWords(const string& para, int idx, vector<string>& ans) {
        Trie* node = this;
        string tmp = "";
        for (int i = idx; i < para.size(); i++) {
            char ch = para[i];
            if (node->children[ch-'a'] == nullptr) {
                return;
            }
            node = node->children[ch-'a'];
            tmp += ch;
            if (node->isEnd) {
                ans.push_back(tmp);
            }
        }
    }

public:
    Trie(): children(26), isEnd(false) {}

    void insert(string word) {
        Trie* node = this;
        for (char ch : word) {
            if (node->children[ch-'a'] == nullptr) {
                node->children[ch-'a'] = new Trie();
            }
            node = node->children[ch-'a'];
        }
        node->isEnd = true;
    }

    bool search(string word) {
        Trie* node = searchPrefix(word);
        return node != nullptr && node->isEnd;
    }

    bool startsWith(string prefix) {
        return this->searchPrefix(prefix) != nullptr;
    }

    vector<string> findMatchStrList(Trie* root, string para) {
        vector<bool> memo(para.size());
        vector<string> ans;
        for (int i = 0; i < para.size(); i++) {
            if (memo[i]) {
                continue;
            }
            searchMultiWords(para, i, ans);
            memo[i] = true; // mark start-i as visited
        }
        return ans;
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * bool param_2 = obj->search(word);
 * bool param_3 = obj->startsWith(prefix);
 */
// @lc code=end

