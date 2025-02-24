/*
 * @lc app=leetcode id=2948 lang=cpp
 *
 * [2948] Make Lexicographically Smallest Array by Swapping Elements
 * 
 * Problem Link: https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/description/
 */

// @lc code=start
#include <vector>
#include <utility>

using std::vector;
using std::pair;
using std::sort;
using std::min;
using std::max;

#include <unordered_map>
#include <list>

using std::unordered_map;
using std::list;

class Solution {

public:

    static bool compare_by_second(const pair<int, int>& p1, const pair<int, int>& p2) {
        return p1.second < p2.second;  // sort by the second element
    }

    // way-2: sorting+hashmap
    // Time Complexity: o(nlogn)
    // Space Complexity: o(n) (o(n) = o(n+sn), sn for sorting space complexity with max to o(logn) with c++)
    vector<int> lexicographicallySmallestArray(vector<int>& nums, int limit) {
        vector<int> numsSorted(nums);                   // sorted copy of nums
        unordered_map<int, int> numToGroup;             // map num to group (within the group, the  the "transitive property" exists)
        unordered_map<int, list<int>> groupToList;      // map group to a list of numbers that belong to it.
        
        // step-1: sort the numsSorted
        sort(numsSorted.begin(), numsSorted.end());

        // step-2: Iterate through the numsSorted, to get the numToGroup and groupToList
        //         avoiding the "second-time grop-wise sorting" by "utilizing the sorted numsSorted" 
        numToGroup.insert(pair<int, int>(numsSorted[0], 0));
        groupToList.insert(pair<int, list<int>>(0, list<int>(1, numsSorted[0])));
        for (int i = 1; i < numsSorted.size(); i++) {
            if (numsSorted[i] - numsSorted[i-1] <= limit) {
                numToGroup.insert(pair<int, int>(numsSorted[i], numToGroup[numsSorted[i-1]]));
            } else {
                numToGroup.insert(pair<int, int>(numsSorted[i], numToGroup[numsSorted[i-1]]+1));
            }
            if (groupToList.find(numToGroup[numsSorted[i]]) == groupToList.end()) {
                groupToList[numToGroup[numsSorted[i]]] = list<int>();
            }
            groupToList[numToGroup[numsSorted[i]]].push_back(numsSorted[i]);
        }

        // step-3: Adjust the nums according to the numToGroup and groupToList 
        for (int i = 0; i < nums.size(); i++) {
            int num = nums[i];
            int group = numToGroup[num];
            nums[i] = *groupToList[group].begin();
            groupToList[group].pop_front();
        }

        return nums;

    }

    // way-1: sorting
    // Time Complexity: o(nlogn)
    // Space Complexity: o(n)

    vector<pair<int, int>> nodes;           // node<idx, val>
    vector<vector<pair<int, int>>> edges;   // set of connected_edges
                                            // (each connected_edge is the idx list)

    vector<int> lexicographicallySmallestArray_1(vector<int>& nums, int limit) {

        // step-1: sort nums

        // Time Complexity: o(nlogn) for sorting
        // Space Complexity: o(n) extra storage space for nodes

        int idx = 0;
        for (const auto & num: nums) {
            pair<int, int> p(idx, num);
            nodes.push_back(p);
            idx++;
        }

        sort(nodes.begin(), nodes.end(), compare_by_second);

        // step-2: obtain the edges which connected the node[i] and node[j]
        //         according to |node[i]-node[j]| <= limit
        //         since nums were sorted, it's easy to find the edges
        //         eg. 1(0), 5(1), 3(2), 9(3), 8(4)
        //             to
        //             1(0), 3(2), 5(1), 8(4), 9(3)
        //             after sorting
        //             Then, it's easy to find two edges group:
        //                      group-1: 1(0)->3(2)->5(1)
        //                      group-2: 8(4)->9(3)
        //             Then, start using the "transitive property" for each group to "lexicographically smallest",
        //             Just "group-wise sorting" for each edge-group
        //                  A. For each edge-group, sort by idx:
        //                      group-1: 1(0)->5(1)->3(2)
        //                      group-2: 9(3)->8(4)
        //                  B. For each edge-group, sort by val -> in Step-3

        // Time Complexity: o(nlogn) (i+j+k=n ilogi+jlogj+klogk=nlogn?)
        // Space Complexity: o(n) for edges

        idx = 0;
        vector<pair<int, int>> edge; // connected_edge(idx list)
        while (idx < nodes.size()) {
            if (idx < nodes.size()-1 && nodes[idx].second + limit >= nodes[idx+1].second) {
                if (edge.size() == 0) {
                    pair<int, int> p(nodes[idx].first, nodes[idx].second);
                    edge.push_back(p);
                }
                pair<int, int> p_1(nodes[idx+1].first, nodes[idx+1].second);
                edge.push_back(p_1);
            } else if (edge.size() != 0) {
                sort(edge.begin(), edge.end());
                vector<pair<int, int>> edge_cp(edge);
                edges.push_back(edge_cp);
                edge.clear();
            }
            idx++;
        }

        // step-3: adjust array
        //         using the same eg. we adjust two groups as following:
        //                      group-1: 1(0), 3(1), 5(2)
        //                      group-2: 8(3), 9(4)
        //         then the final result is:
        //                      1(0), 3(1), 5(2), 8(3), 9(4)

        // Time Complexity: o(nlogn)
        // Space Complexity: max to o(n) (for vals)

        for (const auto & e : edges) {
            vector<int> vals;
            for (const auto & p : e) {
                vals.push_back(p.second);
            }
            sort(vals.begin(), vals.end());
            int i = 0;
            for (const auto & p : e) {
                nums[p.first] = vals[i];
                i++;
            }
        }
        return nums;
    }
};
// @lc code=end

