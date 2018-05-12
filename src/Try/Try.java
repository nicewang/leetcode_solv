package Try;

import java.util.Scanner;

public class Try {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int[] data = new int[n];
			for(int i = 0; i < n; i++)
				data[i] = in.nextInt();
			for(int j = 0; j < n; j++)
				System.out.println(data[j]);
		}
		in.close();
	}
	
}