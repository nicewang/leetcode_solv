//Find the total area covered by two rectilinear rectangles in a 2D plane. 
//
// Each rectangle is defined by its bottom left corner and top right corner as s
//hown in the figure. 
//
// 
//
// Example: 
//
// 
//Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
//Output: 45 
//
// Note: 
//
// Assume that the total area is never beyond the maximum possible value of int.
// 
// Related Topics Math


package leetcode.leetcode.editor.en;

//Java：Rectangle Area
public class P223RectangleArea {
    public static void main(String[] args) {
        Solution solution = new P223RectangleArea().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 3ms 38.7MB
        public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
            int area1 = (C - A) * (D - B);
            int area2 = (G - E) * (H - F);
            // 没有重合的情况
            if (A >= G || E >= C || D <= F || H <= B) {
                return area1 + area2;
            }
            // 确定重合区域的上边
            int y1 = Math.min(D, H);
            // 确定重合区域的下边
            int y2 = Math.max(B, F);
            // 确定重合区域的左边
            int x2 = Math.max(A, E);
            // 确定重合区域的右边
            int x1 = Math.min(C, G);
            int area3 = (y1 - y2) * (x1 - x2);
            return area1 + area2 - area3;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}