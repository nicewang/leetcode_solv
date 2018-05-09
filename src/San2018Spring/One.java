package San2018Spring;

import java.util.Scanner;

public class One {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int t = in.nextInt();
			int[] result = new int[t];
			int k = 0;
			for(int i = 0; i < t; i++) {
				int n = in.nextInt();
				int count = 0;
				for(int j = 0; j < n; j++) {
					int a = in.nextInt();
					int b = in.nextInt();
					int c = in.nextInt();
					int d = in.nextInt();
					count += (c-a+1)*(d-b+1);
				}
				result[k++] = count;
			}
			for(int i = 0; i < k; i++)
				System.out.println(result[i]);
		}
		in.close();
	}

}
