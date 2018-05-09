package Sohu2018Spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class One {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			Map<Integer,Integer> growth = new HashMap<Integer,Integer>();
			Map<Integer,Integer> tasks = new HashMap<Integer,Integer>();
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < n; i++) {
				int type = in.nextInt();
				if(type == 1) {
					int s = in.nextInt();
					int e = in.nextInt();
					int val = in.nextInt();
					for(int j = s; j <= e; j++) {
						if(growth.containsKey(j)) {
							int v = growth.get(j);
							growth.remove(j);
							growth.put(j, Math.max(v, val));
						} else {
							growth.put(j, val);
						}
					}
					if(s < min)
						min = s;
					if(e > max)
						max = e;
				}else {
					int d = in.nextInt();
					int val = in.nextInt();
					if(tasks.containsKey(d)) {
						int v = tasks.get(d);
						tasks.remove(d);
						tasks.put(d, val+v);
					} else {
						tasks.put(d, val);
					}
				}
			}
			int result = 0;
			for(int i = min; i <= max; i++) {
				if(growth.containsKey(i))
					result += growth.get(i);
				if(tasks.containsKey(i))
					result += tasks.get(i);
			}
			System.out.println(result);
		}
		in.close();
	}

}
