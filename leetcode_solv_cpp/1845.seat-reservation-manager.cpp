/*
 * @lc app=leetcode id=1845 lang=cpp
 *
 * [1845] Seat Reservation Manager
 */

// @lc code=start
#include <vector>

// min heap
class SeatManager {
public:
    std::vector<int> available;

    SeatManager(int n) {
        for (int i = 1; i <= n; ++i){
            // already make available as min heap
            available.push_back(i);
            // for saving initial buidling time from o(nlogn) to o(n), annotate following:
            // // building min heap
            // std::push_heap(available.begin(), available.end(), std:: greater<int>());
        }
    }
    
    int reserve() {
        pop_heap(available.begin(), available.end(), std::greater<int>());
        int tmp = available.back();
        available.pop_back();
        return tmp;
    }
    
    void unreserve(int seatNumber) {
        available.push_back(seatNumber);
        // building min heap
        push_heap(available.begin(), available.end(), std::greater<int>());
    }
};

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager* obj = new SeatManager(n);
 * int param_1 = obj->reserve();
 * obj->unreserve(seatNumber);
 */
// @lc code=end

