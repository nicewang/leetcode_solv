package San2018Spring;

import java.util.Arrays;
import java.util.Scanner;

public class Two {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int[] result = new int[n];
			int[] list = new int[3];
			int j = 0;
			for(int i = 0; i < n; i++) {
				list[0] = in.nextInt();
				list[1] = in.nextInt();
				list[2] = in.nextInt();
				Arrays.sort(list);
				int tmp = (list[1]+list[2]-2*list[0]) < 3 ? 0 : 1;
				if(tmp == 1) {
					tmp = 0;
					int tmp1 = list[1]-list[0];
					int tmp2 = list[2]-list[0];
					if(tmp1 > tmp2) {
						tmp1 -= 2;
						tmp2 -= 1;
					} else {
						tmp1 -= 1;
						tmp2 -= 2;
					}
					while(tmp1 > 0 && tmp2 > 0) {
						tmp++;
						if(tmp1 > tmp2) {
							tmp1 -= 2;
							tmp2 -= 1;
						} else {
							tmp1 -= 1;
							tmp2 -= 2;
						}
					}
					if(tmp1 == 0 && tmp2 == 0)
						tmp++;
					else if(tmp1 > 0) {
						tmp += tmp1/3;
						if(tmp2 == 0)
							tmp++;
					}
					else if(tmp2 > 0) {
						tmp += tmp2/3;
						if(tmp1 == 0)
							tmp++;
					}
				}
				result[j++] = list[0] + tmp;
			}
			for(int i = 0; i < n; i++)
				System.out.println(result[i]);
		}
		in.close();
	}
	
}
