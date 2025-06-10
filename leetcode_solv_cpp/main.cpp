
// To Compile and Build:
// g++ -std=c++11 main.cpp 992.subarrays-with-k-different-integers.cpp -o main.so

# include "solution.h"

#include <iostream>
#include <sstream>

using namespace std;

string toString(const vector<int>& v) {
    ostringstream oss;
    oss << "[";
    for (size_t i = 0; i < v.size(); ++i) {
        oss << v[i];
        if (i != v.size() - 1) oss << ", ";
    }
    oss << "]";
    return oss.str();
}

int main() {
    int res;
    vector<int> nums;
    int k;

    Solution solv;

    cout << "Test Case 1" << endl;
    nums = {1,2,1,2,3};
    k = 2;
    cout << "nums = " << toString(nums) << endl;
    cout << "k = " << k << endl;
    res = solv.subarraysWithKDistinct(nums, k);
    cout << "res = " << res << endl;

    cout << "Test Case 2" << endl;
    nums = {1,2,1,3,4};
    k = 3;
    cout << "nums = " << toString(nums) << endl;
    cout << "k = " << k << endl;
    res = solv.subarraysWithKDistinct(nums, k);
    cout << "res = " << res << endl;
}