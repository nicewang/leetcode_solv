#include <string>
#include <iostream>
#include <vector>

using std::string;
using std::cout;
using std::endl;
using std::vector;

// 5 * 4 - 3 - 5
// 20, -3, -5

// 5 * 4 - 3 - 5 ^ 3
// 20, -3, -125

// positive numbers
// step1: + - * /
// step2: ^

// stack vector list
// list: num
// store stack (lifo -- last in first out)
// * or /: pop tail of stack
//    multifly or devide
//    get the result
//    push result

// prev_tmp = 0
// cur_tmp = 0
// falg: mark previous option
// loop
//     num:
//         cur_tmp = cur_tmp*10+_cur
//     else:        

//         check previous otion: // (1)
//         if prev_flag == * or /: firstly cal out (run * or /)
//         if prev_flag == -: cur_tmp = -cur_tmp
        
//         push cur_tmp into stk // (2)

//         flag = option // (3)

class Solution {
public:

    vector<int> stk;
    // vector<string> basic_num{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    int calculate(string s) {
        int len =s.length();
        // No: for no option
        // Mul: for *
        // Dev: for /
        // Min: for Minus
        string flag = "No";
        int cur_tmp = 0;
        for (int i = 0; i < len; i++) {
            char cur = s.at(i);
            if (cur == ' ') {
                continue;
            }
            if (cur == '*') {
                // cout << cur_tmp << endl; 
                if (flag != "No") {
                    int prev = stk.back();
                    // cout << prev << endl;
                    stk.pop_back();
                    if (flag == "Min") {
                        stk.push_back(prev);
                        cur_tmp = -cur_tmp;
                    } else if (flag == "Mul") {
                        cur_tmp *= prev;
                    } else {
                        // devide
                        cur_tmp = prev/cur_tmp;
                    }   
                }
                stk.push_back(cur_tmp);
                cur_tmp = 0;
                flag = "Mul";

            } else if (cur == '/') {
                // cout << cur_tmp << endl;
                if (flag != "No") {
                    int prev = stk.back();
                    // cout << prev << endl;
                    stk.pop_back();
                    if (flag == "Min") {
                        stk.push_back(prev);
                        cur_tmp = -cur_tmp;
                    } else if (flag == "Mul") {
                        cur_tmp *= prev;
                    } else {
                        // devide
                        cur_tmp = prev/cur_tmp;
                    }   
                }
                stk.push_back(cur_tmp);
                cur_tmp = 0;
                flag = "Dev";

            } else if (cur == '+') {
                // cout << cur_tmp << endl;
                if (flag != "No") {
                    int prev = stk.back();
                    // cout << prev << endl;
                    stk.pop_back();
                    if (flag == "Min") {
                        stk.push_back(prev);
                        cur_tmp = -cur_tmp;
                    } else if (flag == "Mul") {
                        cur_tmp *= prev;
                    } else {
                        // devide
                        cur_tmp = prev/cur_tmp;
                    }   
                }
                stk.push_back(cur_tmp);
                cur_tmp = 0;
                flag = "No";

            } else if (cur == '-') {
                // cout << cur_tmp << endl;
                 if (flag != "No") {
                    int prev = stk.back();
                    // cout << prev << endl;
                    stk.pop_back();
                    if (flag == "Min") {
                        stk.push_back(prev);
                        cur_tmp = -cur_tmp;
                    } else if (flag == "Mul") {
                        cur_tmp *= prev;
                    } else {
                        // devide
                        cur_tmp = prev/cur_tmp;
                    }   
                }
                stk.push_back(cur_tmp);
                cur_tmp = 0;
                flag = "Min";
            } else {
                // default for num
                cur_tmp *= 10;
                cur_tmp += (int)(cur - '0');
            }
        }

        
        if (flag != "No") {
            int prev = stk.back();
            // cout << prev << endl;
            stk.pop_back();
            if (flag == "Min") {
                stk.push_back(prev);
                cur_tmp = -cur_tmp;
            } else if (flag == "Mul") {
                cur_tmp *= prev;
            } else {
                // devide
                cur_tmp = prev/cur_tmp;
            }   
        }
        stk.push_back(cur_tmp);

        len = stk.size();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            cout << stk.at(i) << endl;
            ans += stk.at(i);
        }

        return ans;

    }

};

int main() {
    Solution* solv = new Solution();
    int res = solv->calculate("1 6 * 3 - 4 - 5 + 2 ");
    cout << res << endl;
    return 0;
}