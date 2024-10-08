/*
 * @lc app=leetcode id=1436 lang=cpp
 *
 * [1436] Destination City
 */

// @lc code=start
#include <string>
#include <vector>
#include <unordered_set>

class Solution {
public:
    std::string destCity(std::vector<std::vector<std::string>> &paths) {
        std::unordered_set<std::string> citiesA;
        for (auto &path : paths) {
            citiesA.insert(path[0]);
        }
        for (auto &path : paths) {
            if (!citiesA.count(path[1])) {
                return path[1];
            }
        }
        return "";
    }
};
// @lc code=end

