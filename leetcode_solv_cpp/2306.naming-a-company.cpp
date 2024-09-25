/*
 * @lc app=leetcode id=2306 lang=cpp
 *
 * [2306] Naming a Company
 */

// @lc code=start
#include <vector>
#include <string>
#include <unordered_map>
#include <unordered_set>
class Solution {
public:
    long long distinctNames(std::vector<std::string>& ideas) {
        std::unordered_map<char, std::unordered_set<std::string>> names;
        for (const std::string& idea: ideas) {
            names[idea[0]].insert(idea.substr(1, idea.size() - 1));
        }

        auto get_intersect_size = [](const std::unordered_set<std::string>& a, const std::unordered_set<std::string>& b) -> size_t {
            size_t ans = 0;
            for (const std::string& s: a) {
                if (b.count(s)) {
                    ++ans;
                }
            }
            return ans;
        };

        long long ans = 0;
        for (auto&& [pre_a, set_a]: names) {
            for (auto&& [pre_b, set_b]: names) {
                if (pre_a == pre_b) {
                    continue;
                }
                int intersect = get_intersect_size(set_a, set_b);
                ans += static_cast<long long>(set_a.size() - intersect) * (set_b.size() - intersect);
            }
        }
        return ans;
    }
};
// @lc code=end

