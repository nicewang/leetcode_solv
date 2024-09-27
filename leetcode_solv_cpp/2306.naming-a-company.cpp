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
    // Method 1 - Divided by Pre
    long long distinctNames(std::vector<std::string>& ideas) {
        std::unordered_map<char, std::unordered_set<std::string>> map;
        for (std::string& idea : ideas) {
            map[idea[0]].insert(idea.substr(1, idea.size()-1));
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
        std::unordered_set<char> visited;
        for (auto&& [pre_a, set_a]: map) {
            visited.insert(pre_a);
            for (auto&& [pre_b, set_b]: map) {
                if (visited.count(pre_b) > 0) {
                    continue;
                }
                if (pre_a != pre_b) {
                    int intersect = get_intersect_size(set_a, set_b);
                    ans += static_cast<long long>(set_a.size() - intersect) * (set_b.size() - intersect);
                }
            }
        }
        return ans * 2;
    }

    // Method 2 - Divided by Suffix: exceed the time limit
    long long distinctNames_2(std::vector<std::string>& ideas) {
        std::unordered_map<std::string, std::unordered_set<char>> map;
        for (std::string& idea : ideas) {
            map[idea.substr(1, idea.size()-1)].insert(idea[0]);
        }
        auto get_intersect_size = [](const std::unordered_set<char>& a, const std::unordered_set<char>& b) -> size_t {
	        size_t ans = 0;
	        for (const char& s: a) {
		        if (b.count(s)) {
			        ++ans;
		        }
	        }
	        return ans;
        };
        long long ans = 0;
        std::unordered_set<std::string> visited;
        for (auto&& [suffix_a, set_a]: map) {
            for (auto&& [suffix_b, set_b]: map) {
                if (visited.count(suffix_b) > 0) {
                    continue;
                }
                if (suffix_a != suffix_b) {
                    int intersect = get_intersect_size(set_a, set_b);
                    ans += static_cast<long long>(set_a.size() - intersect) * (set_b.size() - intersect);
                }
            }
            visited.insert(suffix_a);
        }
        return ans * 2;
    }
};
// @lc code=end

