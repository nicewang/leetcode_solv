package archived.TouTiaoAutumnFor2018;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * P为给定的二维平面整数点集。
 * 定义 P 中某点x，如果x满足 P 中任意点都不在 x 的右上方区域内（横纵坐标都大于x），则称其为“最大的”。
 * 求出所有“最大的”点的集合。（所有点的横坐标和纵坐标都不重复, 坐标轴范围在[0, 1e9) 内）
 * 如下图：实心点为满足条件的点的集合。请实现代码找到集合 P 中的所有 ”最大“ 点的集合并输出。
 * 时间限制：1秒
 * 空间限制：32768K
 */
public class ClassOne {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int[] x = new int[n];
			int[] y = new int[n];
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int i = 0; i < n; i++) {
				x[i] = in.nextInt();
				y[i] = in.nextInt();
				map.put(x[i], y[i]);
			}
			Stack<Integer> stack = getBoundary(x, y, map);
			int[][] result = new int[stack.size()][2];
			int j = stack.size() - 1;
			int size = stack.size();
			while(!stack.isEmpty()) {
				result[j][0] = x[stack.peek()];
				result[j--][1] = map.get(x[stack.peek()]);
				stack.pop();
			}
			for(int i = 0; i < size; i++)
				System.out.println(Integer.toString(result[i][0])+' '+Integer.toString(result[i][1]));
		}
		in.close();
	}

	/**
	 * case通过率60%
	 * @param x
	 * @param y
	 * @param map
	 * @return
	 */
	private static Stack<Integer> getBoundary(int[] x, int[] y, Map<Integer, Integer> map) {
		// TODO Auto-generated method stub
		Stack<Integer> theStack1 = new Stack<Integer>();
		Arrays.sort(x);
		theStack1.push(0);
		for(int i = 1; i < x.length; i++) {
			int y_tmp = map.get(x[i]);
			if(y_tmp > map.get(x[theStack1.peek()])) {
				theStack1.pop();
				while(!theStack1.isEmpty() && y_tmp > map.get(x[theStack1.peek()])) {
					theStack1.pop();
				}
			}
			theStack1.push(i);
		}
		return theStack1;
	}

}
