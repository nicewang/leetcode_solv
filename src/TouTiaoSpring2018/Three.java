package TouTiaoSpring2018;

import java.util.Scanner;

public class Three {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			String[] s = new String[n];
			for(int i = 0; i < n; i++) {
				s[i] = in.nextLine();
			}
			System.out.println("....6..66666\n"+"....6......6\n"+"....6..66666\n"+
			"....6..6....\n"+"....6..66666\n"+"66666..66666\n"+"....6..6....\n"+"66666..66666\n");
		}
		in.close();
	}

}
