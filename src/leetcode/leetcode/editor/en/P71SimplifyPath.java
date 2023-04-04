//Given an absolute path for a file (Unix-style), simplify it. Or in other words
//, convert it to the canonical path. 
//
// In a UNIX-style file system, a period . refers to the current directory. Furt
//hermore, a double period .. moves the directory up a level. 
//
// Note that the returned canonical path must always begin with a slash /, and t
//here must be only a single slash / between two directory names. The last directo
//ry name (if it exists) must not end with a trailing /. Also, the canonical path 
//must be the shortest string representing the absolute path. 
//
// 
//
// Example 1: 
//
// 
//Input: "/home/"
//Output: "/home"
//Explanation: Note that there is no trailing slash after the last directory nam
//e.
// 
//
// Example 2: 
//
// 
//Input: "/../"
//Output: "/"
//Explanation: Going one level up from the root directory is a no-op, as the roo
//t level is the highest level you can go.
// 
//
// Example 3: 
//
// 
//Input: "/home//foo/"
//Output: "/home/foo"
//Explanation: In the canonical path, multiple consecutive slashes are replaced 
//by a single one.
// 
//
// Example 4: 
//
// 
//Input: "/a/./b/../../c/"
//Output: "/c"
// 
//
// Example 5: 
//
// 
//Input: "/a/../../b/../c//.//"
//Output: "/c"
// 
//
// Example 6: 
//
// 
//Input: "/a//b////c/d//././/.."
//Output: "/a/b/c"
// 
// Related Topics String Stack


package leetcode.leetcode.editor.en;

//Java：Simplify Path
public class P71SimplifyPath {
    public static void main(String[] args) {
        Solution solution = new P71SimplifyPath().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // TODO 我TM放弃了 有空再来看
        public String simplifyPath(String path) {
            StringBuffer sb = new StringBuffer();
            if (path == null || path.length() == 0) {
                return sb.toString();
            }
            boolean isStart = false;
            for (int i = path.length() - 1; i >= 0; i--) {
                if ((path.charAt(i) == '/' || path.charAt(i) == '.') && i > 0 && !isStart) {
                    continue;
                }
                if(isStart && path.charAt(i) == '.' && sb.charAt(0) == '/') {
                    break;
                }
                if (sb.length() > 0 && sb.charAt(0) == '/' && path.charAt(i) == '/') {
                    continue;
                }
                sb.insert(0, path.charAt(i));
                isStart = true;
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}