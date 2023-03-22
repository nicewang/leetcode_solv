package archived.meituan2018spring;

import java.util.Scanner;

public class One {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			String S = in.nextLine();
			String T = in.nextLine();
			int distance = getSum(S, T);
			System.out.println(distance);
		}
		in.close();
	}

	private static int getSum(String s, String t) {
		// TODO Auto-generated method stub
		int l1 = s.length();
		int l2 = t.length();
		int n = l1 - l2 + 1;
		int count = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < l2; j++) {
				if(s.charAt(i+j) != t.charAt(j))
					count += 1;
			}
		}
		return count;
	}

}
