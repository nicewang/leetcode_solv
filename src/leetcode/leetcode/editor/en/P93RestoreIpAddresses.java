//Given a string containing only digits, restore it by returning all possible va
//lid IP address combinations. 
//
// A valid IP address consists of exactly four integers (each integer is between
// 0 and 255) separated by single points. 
//
// Example: 
//
// 
//Input: "25525511135"
//Output: ["255.255.11.135", "255.255.111.35"]
// 
// Related Topics String Backtracking


package leetcode.leetcode.editor.en;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：Restore IP Addresses
public class P93RestoreIpAddresses {
    public static void main(String[] args) {
        Solution solution = new P93RestoreIpAddresses().new Solution();
        // TO TEST
        solution.restoreIpAddresses("25525511135");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        // 4ms 40.5MB 解法：dfs回溯
        public List<String> restoreIpAddresses(String s) {
            dfs(s, 0, 4);
            return list;
        }

        private void dfs(String s, int start, int n) {
            if(n == 0) {
                if(Integer.parseInt(stack.peek()) <= 255 && Integer.parseInt(stack.peek()) >= 0) {
                    List<String> l = new ArrayList<>(stack);
                    StringBuffer sb = new StringBuffer();
                    for(int i = 0; i < l.size(); i++) {
                        sb.append(l.get(i));
                        if(i < l.size() - 1) {
                            sb.append(".");
                        }
                    }
                    if(!list.contains(sb.toString())) {
                        list.add(sb.toString());
                    }
                }
                return;
            }
            if(start >= s.length()) {
                return;
            }
            for(int i = 1; start + i <= s.length() && i <= 3; i++) {
                String s_tmp;
                if(n > 1) {
                    s_tmp = s.substring(start, start+i);
                } else {
                    s_tmp = s.substring(start);
                }
                boolean canAdd = false;
                if((s_tmp.length() <= 3 && Integer.parseInt(s_tmp) == 0 && s_tmp.length() == 1) ||
                        (s_tmp.length() <= 3 && Integer.parseInt(s_tmp) <= 255 && s_tmp.charAt(0) != '0')) {
                    canAdd = true;
                }
                if(canAdd) {
                    stack.push(s_tmp);
                    dfs(s, start+i, n-1);
                    stack.pop();
                } else {
                    break;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}