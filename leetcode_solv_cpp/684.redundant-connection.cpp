/*
 * @lc app=leetcode id=684 lang=cpp
 *
 * [684] Redundant Connection
 */

// @lc code=start
#include <vector>

class UnionFind {
public:

    int Find(std::vector<int>& parent, int cur) {
        int idx = cur;
        while (parent[idx] != idx) {
            idx = parent[idx];
        }
        // return root parent
        parent[cur] = idx;
        return idx;
    }

    void Union(std::vector<int>& parent, int idx1, int idx2) {
        // node1->parent1->parent2<-node2
        parent[parent[idx1]] = parent[idx2];
    }

};

class Solution {
private:
    UnionFind uf;
public:

    // way-1: Union and Find
    std::vector<int> findRedundantConnection(std::vector<std::vector<int>>& edges) {

        // n nodes and n-1 edges for acyclic graph
        // there exists one circle, so there are n edges
        int n = edges.size();
        std::vector<int> parent(n+1);
        
        for (int i  = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (const auto& edge : edges) {
            if (uf.Find(parent, edge[0]) != uf.Find(parent, edge[1])) {
                uf.Union(parent, edge[0], edge[1]);
            } else {
                return edge;
            }
        }

        return std::vector<int>();
    }

};
// @lc code=end

