package meitu2018spring;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int[][] data = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					data[i][j] = in.nextInt();
				}
			}
			int[][] result = new int[n][n];
			int i1 = 0;
			int j1 = 0;
			for(int j = 0; j < n; j++) {
				for(int i = n-1; i >= 0; i--) {
					if(j1 == n) {
						j1 = 0;
						i1++;
					}
					result[i1][j1++] = data[i][j];
				}
			}
			for(int i = 0; i < n; i++) {
				StringBuffer sb = new StringBuffer();
				for(int j = 0; j <n ; j++) {
					sb.append(result[i][j]);
					sb.append(" ");
				}
				System.out.println(sb.toString());
			}
		}
		in.close();
	}

}
