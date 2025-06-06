/*
 * @lc app=leetcode id=1942 lang=cpp
 *
 * [1942] The Number of the Smallest Unoccupied Chair
 */

// @lc code=start
#include <vector>
#include <queue>
#include <map>
#include <algorithm>

using namespace std;

class Solution {
public:
    int smallestChair(vector<vector<int>>& times, int targetFriend) {
        int n = times.size();
        vector<vector<int>> lis_out;

        for (int i = 0; i < n; i++) {
            lis_out.push_back({i, times[i][0], times[i][1]});
        }

        sort(lis_out.begin(), lis_out.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });

        priority_queue<vector<int>, vector<vector<int>>, function<bool(vector<int>&, vector<int>&)>> pq(
            [](vector<int>& a, vector<int>& b) {
                return a[1] > b[1]; 
            }
        );

        priority_queue<int, vector<int>, greater<int>> availableChairs;
        map<int, int> chairMap;
        for (int i = 0; i < n; i++) {
            int friend_id = lis_out[i][0];
            int arrive = lis_out[i][1];
            int leave = lis_out[i][2];
            availableChairs.push(i);
            
            while (!pq.empty() && pq.top()[1] <= arrive) {
                int leaver_id = pq.top()[0];
                int chair = chairMap[leaver_id];
                availableChairs.push(chair);
                pq.pop();
            }

            int assignedChair = availableChairs.top();
            availableChairs.pop();

            if (friend_id == targetFriend) {
                return assignedChair;
            }

            chairMap[friend_id] = assignedChair;
            pq.push({friend_id, leave});
        }

        return chairMap[targetFriend];
    }
};
// @lc code=end

