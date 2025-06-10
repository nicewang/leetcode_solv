/*
 * @lc app=leetcode id=1942 lang=cpp
 *
 * [1942] The Number of the Smallest Unoccupied Chair
 */

// @lc code=start

// Problem Link: https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/description/

// 1. Let's write down some draft code firstly, 
// as well as consider the solution at the same time.
// I will wirte these "chain-of-thought"s, thus I can think more clearly

#include <vector>
#include <queue>
#include <unordered_map>
#include <algorithm>

using namespace std;

// 5. Time Complexity and Space Complexity
// - Space Complexity: o(n)
//      * pq: min-heap for n persons: o()n
//      * chairMap: o(n)
//      * lis_person: o(n)
//      * availableChairs: o(n)
// - Time Complexity: o(nlogn)
//      * building lis_person: n persons, so o(n)
//      * sorting lis_person: o(nlogn)
//      * for loop: -> so o(nlogn)
//          - loop n times
//          - in each iteration: o(logn) for heap's push() and pop(),
//                               o(1) for heap's other and hashmap's operation                               
class Solution {
public:
    int smallestChair(vector<vector<int>>& times, int targetFriend) {

        // 2. For this problem, maybe we need to utilize:
        // - Priority Queue, a.k.a, max-heap or min-heap
        //      * For storing pair of (person_idx, leave_time) -> vector<int>
        //      * Once a new person arrives, we force the people (whose lease_time <= now) to leave
        //      * So, max-heap.
        // - Hash Map, which is for storing person_idx and corresponding chair_id

        // get the total person num
        int n = times.size();

        // min-heap: pair(person_idx, leave_time)
        priority_queue<vector<int>, vector<vector<int>>, function<bool(const vector<int>&, const vector<int>&)>> pq(
            [](const vector<int>& a, const vector<int>& b) {
                // sorting according to leave_time
                // priority_queue's deafault is max-heap,
                // so "reversing" to "build" a min-heap
                return a[1] > b[1];
            }
        );
        // way-2
        struct compare_greater {
            bool operator()(const vector<int>& a, const vector<int>& b) const {
                return a[1] > b[1];
            }
        };
        priority_queue<vector<int>, vector<vector<int>>, compare_greater> pq_;

        // HashMap:
        // - key: person_idx
        // - val: chair_idx
        unordered_map<int, int> chairMap;

        // 3. Then, we need to sort the person according to arrive_time

        // 3.1 Build a new list -> vector<T>:
        //      * Storing (person_idx, arrive_time, leave_time) -> vector<int>
        vector<vector<int>> lis_person;
        for (int i = 0; i < n; i++) {
            lis_person.push_back({i, times[i][0], times[i][1]});
        }

        // 3.2 Sort by arrive_time
        sort(lis_person.begin(), lis_person.end(),
            [](const vector<int>& a, const vector<int>& b) {
                return a[1] < b[1];
            }
        );

        // 4. Now, start our loop for all persons
        // maybe we need an extra for storing available chairs
        priority_queue<int, vector<int>, greater<int>> availableChairs;
        for (int i = 0; i < n; i++) {
            availableChairs.push(i); // current chair_i is available
            while (!pq_.empty() && pq_.top()[1] <= lis_person[i][1]) {
                // Some persons already left
                auto person = pq_.top();
                int prev_chair = chairMap[person[0]];
                availableChairs.push(prev_chair);
                pq_.pop();
            }
            int current_chair = availableChairs.top();
            availableChairs.pop();
            if (targetFriend == lis_person[i][0]) {
                return current_chair;
            }
            pq_.push({lis_person[i][0], lis_person[i][2]});
            chairMap[lis_person[i][0]] = current_chair;
        }

        return chairMap[targetFriend];
    }
};
// @lc code=end

