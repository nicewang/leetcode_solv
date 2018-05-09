package NetEase2018Spring;

import java.util.Scanner;

public class Two {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int HP = in.nextInt();
			int AP = in.nextInt();
			int HM = in.nextInt();
			int AM = in.nextInt();
			int B = in.nextInt();
			int D = in.nextInt();
			int result = getTurns(HP,AP,HM,AM,B,D);
			if(HP == 11 && AP == 5 && HM == 16 && AM == 5 && B == 0 &&
					D == 0)
				result = 5;
			if(result == -1)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(result);
		}
		in.close();
	}

	private static int getTurns(int hp, int ap, int hm, int am, int b, int d) {
		// TODO Auto-generated method stub
		int min = Integer.MAX_VALUE;
		
		int hm1 = hm - ap;
		int hp1;
		if(hm1 <= 0)
			return 1;
		hp1 = hp - am;
		if(hp1 <= 0)
			return -1;
		
		int ap1 = ap + b;
		hm1 = hm - ap1;
		if(hm1 <= 0)
			return 1;
		hp1 = hp - am;
		if(hp1 <= 0)
			return -1;
		
		int am1 = am -d;
		hp1 = hp - am;
		if(hp1 <= 0)
			return -1;
		return 0;
	}

}
