//Given two integers representing the numerator and denominator of a fraction, r
//eturn the fraction in string format. 
//
// If the fractional part is repeating, enclose the repeating part in parenthese
//s. 
//
// Example 1: 
//
// 
//Input: numerator = 1, denominator = 2
//Output: "0.5"
// 
//
// Example 2: 
//
// 
//Input: numerator = 2, denominator = 1
//Output: "2" 
//
// Example 3: 
//
// 
//Input: numerator = 2, denominator = 3
//Output: "0.(6)"
// 
// Related Topics Hash Table Math


package leetcode.leetcode.editor.en;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.Map;

//Java：Fraction to Recurring Decimal
public class P166FractionToRecurringDecimal {
    public static void main(String[] args) {
        Solution solution = new P166FractionToRecurringDecimal().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 2ms 36.8MB
        public String fractionToDecimal(int numerator, int denominator) {
            if (numerator == 0) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            long Numerator = Math.abs(Long.valueOf(numerator)), Denominator = Math.abs(Long.valueOf(denominator));
            if (Long.valueOf(numerator) * Long.valueOf(denominator) < 0) {
                sb.append("-");
            }
            sb.append(String.valueOf(Numerator / Denominator));
            long remainder = Numerator % Denominator;
            if(remainder == 0) {
                return sb.toString();
            }
            sb.append(".");
            Map<Long, Integer> map = new HashMap<>();
            while(remainder != 0) {
                if(map.containsKey(remainder)) {
                    sb.insert(map.get(remainder), "(");
                    sb.append(")");
                    break;
                }
                map.put(remainder, sb.length());
                remainder = remainder * 10;
                sb.append(remainder / Denominator);
                remainder = remainder % Denominator;
            }

            return sb.toString();
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}