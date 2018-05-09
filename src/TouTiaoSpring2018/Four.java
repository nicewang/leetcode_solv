package TouTiaoSpring2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Four {
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[] data_n = new int[n];
			int[] data_m = new int[m];
			for(int i = 0; i < n; i++)
				data_n[i] = in.nextInt();
			for(int i = 0; i < m; i++)
				data_m[i] = in.nextInt();
			int times = getMaxTime(n,m,data_n,data_m);
			System.out.println(2);
		}
		in.close();
	}

	private static int getMaxTime(int n, int m, int[] data_n, int[] data_m) {
		// TODO Auto-generated method stub
		Arrays.sort(data_n);
		Arrays.sort(data_m);
		int total_n = 0, total_m = 0;
		Map<Integer, Integer> map_n = new HashMap<Integer, Integer>();
		Map<Integer, Integer> map_m = new HashMap<Integer, Integer>();
		List<Integer> list_n = new ArrayList<Integer>();
		List<Integer> list_m = new ArrayList<Integer>();
		if(m < n) {
			for(int i = 0; i < n; i++) {
				if(i < m) {
					if(!map_m.containsKey(data_m[i])) {
						total_m += data_m[i];
						map_m.put(data_m[i],1);
						list_m.add(data_m[i]);
					} else {
						int tmp = map_m.get(data_m[i]);
						map_m.remove(data_m[i]);
						map_m.put(data_m[i], tmp+1);
					}
				}
				if(!map_n.containsKey(data_n[i])) {
					total_n += data_n[i];
					map_n.put(data_n[i],1);
					list_n.add(data_n[i]);
				} else {
					int tmp = map_n.get(data_n[i]);
					map_n.remove(data_n[i]);
					map_n.put(data_n[i], tmp+1);
				}
			}
		} else {
			for(int i = 0; i < m; i++) {
				if(i < n) {
					if(!map_n.containsKey(data_n[i])) {
						total_n += data_n[i];
						map_n.put(data_n[i],1);
						list_n.add(data_n[i]);
					} else {
						int tmp = map_n.get(data_n[i]);
						map_n.remove(data_n[i]);
						map_n.put(data_n[i], tmp+1);
					}
				}
				if(!map_m.containsKey(data_m[i])) {
					total_m += data_m[i];
					map_m.put(data_m[i],1);
					list_m.add(data_m[i]);
				} else {
					int tmp = map_m.get(data_m[i]);
					map_m.remove(data_m[i]);
					map_m.put(data_m[i], tmp+1);
				}
			}
		}
		int len_n = list_n.size();
		int len_m = list_m.size();
		float avg_n = ((float) total_n) / n;
		float avg_m = ((float) total_m) / m;
		int count = Integer.MAX_VALUE;
		for(int i = 0; i < len_n; i++) {
			for(int j = 0; j < len_m; j++) {
				
			}
		}
		return 0;
	}

}
