package Try;

import java.util.Scanner;

public class Try {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int[] data = new int[n];
			int i;
			for(i = 0; i < n; i++)
				data[i] = in.nextInt();
			while(i > 0)
				System.out.println(data[n-(i--)]);
		}
		in.close();
				
	}
	
}