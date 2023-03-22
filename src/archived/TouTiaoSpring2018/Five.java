package archived.TouTiaoSpring2018;

import java.util.Arrays;
import java.util.Scanner;

public class Five {
	
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int k = in.nextInt();
			int h = in.nextInt();
			int[] data = new int[n];
			for(int i = 0; i < n; i++) {
				data[i] = in.nextInt();
			}
			int h_max = getHeight(n,k,h,data);
			System.out.println(h_max);
		}
		in.close();
	}

	private static int getHeight(int n, int k, int h, int[] data) {
		// TODO Auto-generated method stub
		Arrays.sort(data);
		boolean[] seen = new boolean[n];
		int cur_h = 0;
		int count = 0;
		while(count < k) {
			int i;
			for(i = n-1; i >= 0; i--) {
				int diff_h;
				if(data[i] > cur_h)
					diff_h = data[i] - cur_h;
				else
					diff_h = cur_h - data[i];
				if(diff_h <= h && seen[i] == false) {
					seen[i] = true;
					cur_h += (data[i]-cur_h)*2;
					break;
				}
			}
			if(i < 0)
				break;
			count++;
		}
		return cur_h;
	}

}
