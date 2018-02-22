package greedy; 

import java.util.Arrays;

/**
 * Assume you are an awesome parent and want to give your children some cookies.
 * But, you should give each child at most one cookie.
 * Each child i has a greed factor gi, which is the minimum size 
 * of a cookie that the child will be content with;
 * and each cookie j has a size sj.
 * If sj >= gi, we can assign the cookie j to the child i, and the child i will be content.
 * Your goal is to maximize the number of your content children and output the maximum number.
 * 
 * Note:
 * You may assume the greed factor is always positive.
 * You cannot assign more than one cookie to one child.
 * 
 * Example 1:
 * Input: [1,2,3], [1,1]
 * Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
 *              And even though you have 2 cookies, since their size is both 1,
 *              you could only make the child whose greed factor is 1 content.
 *              You need to output 1.
 * 
 * Example 2:
 * Input: [1,2], [1,2,3]
 * Output: 2
 * Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
 * You have 3 cookies and their sizes are big enough to gratify all of the children,
 * You need to output 2.            
 */
public class AssignCookies {
	
	public int findContentChildren(int[] g, int[] s) {
        int count = 0;
        if(g == null || g.length == 0 || s == null || s.length == 0)
        	return count;
        int g_len = g.length;
        int s_len = s.length;
//        insertSort(g);
//        insertSort(s);
        Arrays.sort(g);  // 用Arrays的排序运行时间是17ms，自己写的插入排序时间是146ms
        Arrays.sort(s);
        int s_count = 0;
        while(count < g_len) {
        	if(s_count == s_len)
        		break;
        	if(s[s_count] >= g[count])
        		count++;
        	s_count++;       	
        }
        return count;
    }
	
	public void insertSort(int[] data) {
		if(data == null || data.length == 0)
			return;
		int length = data.length;
		for(int i = 0; i < length-1; i++) {
			int tmp = data[i+1];
			int j;
			for(j = i+1; j > 0 && tmp < data[j-1]; j--)
				data[j] = data[j-1];
			data[j] = tmp;
		}
	}

}
