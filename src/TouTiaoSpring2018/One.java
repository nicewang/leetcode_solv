package TouTiaoSpring2018;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class One {
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int k = in.nextInt();
			int[] data = new int[n];
			for(int i = 0; i < n; i++)
				data[i] = in.nextInt();
			int result = getCouple(data,n,k);
			System.out.println(result);
		}
		in.close();
	}

	private static int getCouple(int[] data, int n, int k) {
		// TODO Auto-generated method stub
		int count = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Arrays.sort(data);
		for(int i = 0; i < n; i++) {
			if(map.containsKey(data[i]))
				continue;
			for(int j = i+1; j < n; j++) {
				if(data[i]-data[j] == k || data[j]-data[i] == k) {
					int min, max;
					min = data[i];
					max = data[j];
					if(map.containsKey(min))
						continue;
					count += 1;
					map.put(min, max);
				}
			}
		}
		return count;
	}
}
