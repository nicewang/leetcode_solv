package archived.meituan2018spring;

import java.util.Scanner;

public class Two {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			String data = in.nextLine();
			int result = getMin(data);
			System.out.println(result);
		}
		in.close();
	}

	private static int getMin(String data) {
		// TODO Auto-generated method stub
		int len = data.length();
		char[] data_ch = data.toCharArray();
		int[] count = new int[10];
		for(int i = 0; i < 10; i++)
			count[i] = 0;
		for(int i = 0; i < len; i++) {
			if(data_ch[i] == '0')
				count[0] += 1;
			if(data_ch[i] == '1')
				count[1] += 1;
			if(data_ch[i] == '2')
				count[2] += 1;
			if(data_ch[i] == '3')
				count[3] += 1;
			if(data_ch[i] == '4')
				count[4] += 1;
			if(data_ch[i] == '5')
				count[5] += 1;
			if(data_ch[i] == '6')
				count[6] += 1;
			if(data_ch[i] == '7')
				count[7] += 1;
			if(data_ch[i] == '8')
				count[8] += 1;
			if(data_ch[i] == '9')
				count[9] += 1;
		}
		return getResult(0,count);
	}

	private static int getResult(int i, int[] count) {
		// TODO Auto-generated method stub
		if(i == 0 && count[1] == 0)
			return 1;
		for(int j = 0; j < 10; j++) {
			if(count[j] == 0) {
				if(i == 0) {
					if(j != 0)
						return i*10+j;
				} else {
					return i*10+j;
				}
			}
		}
		for(int j = 0; j < 10; j++) {
			if(count[j] != 0) {
				int[] count_new = count;
				count_new[j] -= 1;
				if(getResult(i*10+j,count_new) != 0)
					return getResult(i*10+j,count_new);
			}
		}
		return 0;
	}
	
}
