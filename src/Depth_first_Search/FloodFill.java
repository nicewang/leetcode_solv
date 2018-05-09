package Depth_first_Search;

import java.util.Stack;

public class FloodFill {
	
	public static void main(String[] args) {
		FloodFill ins = new FloodFill();
//		int[][] img = {{1,1,1},
//				       {1,1,0},
//				       {1,0,1}};
		int[][] img = {{0,0,0},
			       	   {0,0,0}};
		int[][] result = ins.floodFill(img, 0, 0, 2);
		for(int i = 0; i < result.length; i++)
			for(int j = 0; j < result[0].length; j++)
				System.out.println(result[i][j]);
	}
	
	/**
	 * 正儿八经的dfs写法(有用到堆栈):运行用时24ms
	 * @param image
	 * @param sr
	 * @param sc
	 * @param newColor
	 * @return
	 */
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		if(image == null || image.length == 0)
			return null;
		int len_x = image.length;
		int len_y = image[0].length;
		if(sr < 0 || sr >= len_x || sc < 0 || sc >= len_y)
			return null;
		int[][] result = new int[len_x][len_y];
		int[][] filled = new int[len_x][len_y];
		for(int i = 0; i < len_x; i++)
			for(int j = 0; j < len_y; j++)
				result[i][j] = image[i][j];
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
		s1.push(sr);
		s2.push(sc);
		filled[sr][sc] = 1;
		result[sr][sc] = newColor;
		while(!s1.isEmpty()) {
			int tmp1 = s1.pop();
			int tmp2 = s2.pop();
			if(tmp1-1 >= 0 && tmp1-1 < len_x)
				if(image[tmp1-1][tmp2] == image[tmp1][tmp2])
					if(filled[tmp1-1][tmp2] != 1) {
						result[tmp1-1][tmp2] = newColor;
						filled[tmp1-1][tmp2] = 1;
						s1.push(tmp1-1);
						s2.push(tmp2);
					}
			if(tmp1+1 >= 0 && tmp1+1 < len_x)
				if(image[tmp1+1][tmp2] == image[tmp1][tmp2])
					if(filled[tmp1+1][tmp2] != 1) {
						result[tmp1+1][tmp2] = newColor;
						filled[tmp1+1][tmp2] = 1;
						s1.push(tmp1+1);
						s2.push(tmp2);
					}
			if(tmp2-1 >= 0 && tmp2-1 < len_y)
				if(image[tmp1][tmp2-1] == image[tmp1][tmp2])
					if(filled[tmp1][tmp2-1] != 1) {
						result[tmp1][tmp2-1] = newColor;
						filled[tmp1][tmp2-1] = 1;
						s1.push(tmp1);
						s2.push(tmp2-1);
					}
			if(tmp2+1 >= 0 && tmp2+1 < len_y)
				if(image[tmp1][tmp2+1] == image[tmp1][tmp2])
					if(filled[tmp1][tmp2+1] != 1) {
						result[tmp1][tmp2+1] = newColor;
						filled[tmp1][tmp2+1] = 1;
						s1.push(tmp1);
						s2.push(tmp2+1);
					}
		}
		return result;
	}
	
	/**
	 * solv1:运行时间21ms
	 * @param image
	 * @param sr
	 * @param sc
	 * @param newColor
	 * @return
	 */
	public int[][] floodFill_solv1(int[][] image, int sr, int sc, int newColor) {
		if(image == null || image.length == 0)
			return null;
		int len_x = image.length;
		int len_y = image[0].length;
		int[][] filled = new int[len_x][len_y];
		int[][] result = new int[len_x][len_y];
		for(int i = 0; i < len_x; i++)
			for(int j = 0; j < len_y; j++)
				result[i][j] = image[i][j];
		fillImg(image,len_x,len_y,sr,sc,newColor,-1,filled,result);
        return result;
    }

	private void fillImg(int[][] image, int len_x, int len_y, int sr, int sc, int newColor, int oldColor, int[][] filled, int[][] result) {
		// TODO Auto-generated method stub
		if(sr < 0 || sr >= len_x || sc < 0 || sc >= len_y)
			return;
		if(oldColor == -1) {
			result[sr][sc] = newColor;
			filled[sr][sc] = 1;
		} else if(image[sr][sc] != oldColor)
			return;
		if(filled[sr][sc] != 1) {
			result[sr][sc] = newColor;
			filled[sr][sc] = 1;
		} else if(oldColor != -1){
			return;
		}
		fillImg(image,len_x,len_y,sr-1,sc,newColor,image[sr][sc],filled,result);
		fillImg(image,len_x,len_y,sr,sc-1,newColor,image[sr][sc],filled,result);
		fillImg(image,len_x,len_y,sr+1,sc,newColor,image[sr][sc],filled,result);
		fillImg(image,len_x,len_y,sr,sc+1,newColor,image[sr][sc],filled,result);	
	}
}
