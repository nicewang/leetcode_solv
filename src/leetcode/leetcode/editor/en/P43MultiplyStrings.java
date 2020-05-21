//Given two non-negative integers num1 and num2 represented as strings, return t
//he product of num1 and num2, also represented as a string. 
//
// Example 1: 
//
// 
//Input: num1 = "2", num2 = "3"
//Output: "6" 
//
// Example 2: 
//
// 
//Input: num1 = "123", num2 = "456"
//Output: "56088"
// 
//
// Note: 
//
// 
// The length of both num1 and num2 is < 110. 
// Both num1 and num2 contain only digits 0-9. 
// Both num1 and num2 do not contain any leading zero, except the number 0 itsel
//f. 
// You must not use any built-in BigInteger library or convert the inputs to int
//eger directly. 
// 
// Related Topics Math String


package leetcode.leetcode.editor.en;

//Java：Multiply Strings
public class P43MultiplyStrings {
    public static void main(String[] args) {
        Solution solution = new P43MultiplyStrings().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法：两层for循环就是一个竖式乘法 4ms 40.2MB TODO 后面再写几次
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }
            int[] res = new int[num1.length() + num2.length()];
            for (int i = num1.length() - 1; i >= 0; i--) {
                int n1 = num1.charAt(i) - '0';
                for (int j = num2.length() - 1; j >= 0; j--) {
                    int n2 = num2.charAt(j) - '0';
                    int sum = (res[i + j + 1] + n1 * n2);
                    res[i + j + 1] = sum % 10;
                    // 进位
                    res[i + j] += sum / 10;
                }
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < res.length; i++) {
                if (i == 0 && res[i] == 0) {
                    continue;
                }
                result.append(res[i]);
            }
            return result.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}