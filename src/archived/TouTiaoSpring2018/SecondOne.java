package archived.TouTiaoSpring2018;

import java.util.Scanner;

public class SecondOne {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			long[] result = new long[n];
			for(int i = 0; i < n; i++) {
				long max = 0;
				int n1 = in.nextInt();
				long[] data = new long[n1];
				for(int j = 0; j < n1; j++) {
					data[j] = in.nextInt();
				}
				long tmp = data[1] - data[0];
				long max_tmp = tmp;
				for(int j = 2; j < n1; j++) {
					if(data[j]-data[j-1] != tmp) {
						max_tmp += data[j]-data[j-1];
						tmp = data[j]-data[j-1];
					} else {
						if(max == 0)
							max = max_tmp;
						else
							max = Math.max(max, max_tmp);
						max_tmp = tmp;
					}
				}
				if(max == 0)
					max = max_tmp;
				else
					max = Math.max(max, max_tmp);
				result[i] = max;
			}
			for(int i = 0; i < n; i++) {
				System.out.println(result[i]);
			}
		}
		in.close();
	}

}
