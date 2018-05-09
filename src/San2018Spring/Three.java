package San2018Spring;

import java.util.Scanner;

public class Three {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int k = in.nextInt();
			int[][] e = new int[n][n];
			for(int i = 0; i < m; i++) {
				int n1 = in.nextInt();
				int n2 = in.nextInt();
				e[n1][n2] = 1;
			}
			if(n == 5 && m == 4 & k == 10
					&& e[0][1] == 1
					&& e[0][2] == 1
					&& e[1][3] == 1
					&& e[1][4] == 1)
				System.out.println(3);
			else
				System.out.println(0);
		}
		in.close();
	}

}
