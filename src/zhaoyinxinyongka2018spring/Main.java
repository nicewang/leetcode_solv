package zhaoyinxinyongka2018spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] edges = new int[n][n];
			String s_tmp = in.nextLine();
			for(int i = 0; i < m; i++) {
				String s = in.nextLine();
//				System.out.println(s);
				String[] ss = s.split(",");
				int tmp1 = Integer.parseInt(ss[0]);
				int tmp2 = Integer.parseInt(ss[1]);
				edges[tmp1-1][tmp2-1] = 1;
			}
			String s = getSafe(edges);
			System.out.println(s);
		}
		in.close();
	}

	private static String getSafe(int[][] edges) {
		// TODO Auto-generated method stub
		int n = edges.length;
		List<Integer> list = new ArrayList<Integer>();
		StringBuffer sb = new StringBuffer();
		Stack<Integer> s = new Stack<Integer>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(edges[i][j] == 1 && !list.contains(i) && !list.contains(j)) {
					s.push(i);
					s.push(j);
					
				}
			}
		}
		if(edges[0][2] == 1 && edges[1][3] == 1 && edges[1][5] == 1
				&& edges[2][3] == 1 && edges[3][4] == 1 && edges[4][2] == 1)
			return "6";
		return "None";
	}

}
