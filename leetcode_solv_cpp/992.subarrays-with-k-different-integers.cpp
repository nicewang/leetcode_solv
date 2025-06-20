/*
 * @lc app=leetcode id=992 lang=cpp
 *
 * [992] Subarrays with K Different Integers
 */

// @lc code=start

// Problem Link: https://leetcode.com/problems/subarrays-with-k-different-integers/description/

// I will explain my solution while writing the code at the same time

// I will utilize the idea of sliding-window

// Key Point:
// Rather than solve the problem directly,
// this problem should be solved indirectly.
// Similar to the main idea of prefix-sum,
// this problem also could be solved by 
// the idea of **Differential**

#include <vector>
#include <unordered_map>

#include "solution.h" // for running in Main function

using namespace std;

// class Solution { // annotate this line if running in main.cpp
// private: // annotate this line if running in main.cpp

    // Define a sub-function utilizing sliding-window 
    // and tow-pointers to find "good subarrays"
    int slidingWindowFind(vector<int>& nums, int k) {
        // To get the total cnt of subarrays 
        // within whom the total-cnt of unique-nums <= k

        // initial
        int n = nums.size();
        int res = 0; 
        // two-points:
        // i: for left
        // j: for right
        int i = 0;
        int j = 0;
        // map(num, num_cnt): num_cnt -> total cnt of num in subarray
        unordered_map<int, int> numCnt;
        while (j < n) {
            // outer-loop: move right-pointer to right
            
            // Update total cnt of nums[j]
            numCnt[nums[j]]++;

            while (numCnt.size() > k) {
                // inner-loop: 
                // move the left-pointer to right,
                // util the total cnt of unique-nums in subrray
                // is not largerer than k
                numCnt[nums[i]]--; // Update total cnt of nums[j]
                if (numCnt[nums[i]] == 0) {
                    numCnt.erase(nums[i]);
                }
                i++;
            }
            // The total cnt of unique-nums in subarray is <= k
            // So, The all subarrays of the current subarray 
            // and ends at j
            // are the subarrays whose total cnt of unique-nums is <= k,
            // and there are len(current subarray) such subarrays
            res += (j-i+1);
            j++;
        }
        return res;
    }

// public: // annotate this line if running in main.cpp
    // int subarraysWithKDistinct(vector<int>& nums, int k) { // annotate this line if running in main.cpp
    int Solution::subarraysWithKDistinct(vector<int>& nums, int k) { // for running in main.cpp
        // Attention: The key point here!
        // 1. get slidingWindowFind(k):
        //  total-cnt of subarrays (total-cnt of unique-nums <= k)
        // 2. get slidingWindowFind(k-1):
        //  total-cnt of subarrays (total-cnt of uique-nums <= k-1)
        // 3. total-cnt of "good subarrays" = Step1. - Step2.
        return slidingWindowFind(nums, k) - slidingWindowFind(nums, k-1);
    }
// }; // annotate this line if running in main.cpp

// Time Complexity: o(n)
//                  We just need to move "left" and "right" 
//                  at most n times
// Space Complexity: o(n)
//                   The space of numCnt map 
// @lc code=end

