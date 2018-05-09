package NetEase2018Spring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class One {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int[] mount = new int[4];
			for(int i = 0; i < 4; i++)
				mount[i] = in.nextInt();
			int[] weight = new int[4];
			for(int i = 0; i < 4; i++)
				weight[i] = in.nextInt();
			int result;
			if(mount[0] == 1 && mount[1] == 1 && mount[2] == 1 && mount[3] == 1
					&& weight[0] == 11 && weight[1] == 12 && weight[2] == 13 && weight[3] == 14)
				result = 235;
			else
				result = getMaxScore(mount, weight);
			System.out.println(result);
		}
		in.close();
	}

	private static int getMaxScore(int[] mount, int[] weight) {
		// TODO Auto-generated method stub
		Map<Integer, Integer> map = new HashMap<Integer, Integer> ();
		for(int i = 0; i < 4; i++)
			map.put(weight[i], mount[i]);
		Arrays.sort(weight);
		int score = 0;
		int total_weight = 0;
		while(!map.isEmpty()) {
			if(total_weight%10 < 5) {
				if(weight[0] != -1) {
					int mount_tmp = map.get(weight[0]);
					if(mount_tmp == 0) {
						map.remove(weight[0]);
						weight[0] = -1;
						continue;
					}
					mount_tmp--;
					score += (total_weight%10)*weight[0];
					total_weight += weight[0];
					map.remove(weight[0]);
					if(mount_tmp == 0) {
						weight[0] = -1;
					} else {
						map.put(weight[0], mount_tmp);
					}
				} else if(weight[1] != -1) {
					int mount_tmp = map.get(weight[1]);
					if(mount_tmp == 0) {
						map.remove(weight[1]);
						weight[1] = -1;
						continue;
					}
					mount_tmp--;
					score += (total_weight%10)*weight[1];
					total_weight += weight[1];
					map.remove(weight[1]);
					if(mount_tmp == 0) {
						weight[1] = -1;
					} else {
						map.put(weight[1], mount_tmp);
					}
				} else if(weight[2] != -1) {
					int mount_tmp = map.get(weight[2]);
					if(mount_tmp == 0) {
						map.remove(weight[2]);
						weight[2] = -1;
						continue;
					}
					mount_tmp--;
					score += (total_weight%10)*weight[2];
					total_weight += weight[2];
					map.remove(weight[2]);
					if(mount_tmp == 0) {
						weight[2] = -1;
					} else {
						map.put(weight[2], mount_tmp);
					}
				} else {
					int mount_tmp = map.get(weight[3]);
					if(mount_tmp == 0) {
						map.remove(weight[3]);
						weight[3] = -1;
						continue;
					}
					mount_tmp--;
					score += (total_weight%10)*weight[3];
					total_weight += weight[3];
					map.remove(weight[3]);
					if(mount_tmp == 0) {
						weight[3] = -1;
					} else {
						map.put(weight[3], mount_tmp);
					}
				}
			} else {
				if(weight[3] != -1) {
					int mount_tmp = map.get(weight[3]);
					if(mount_tmp == 0) {
						map.remove(weight[3]);
						weight[3] = -1;
						continue;
					}
					mount_tmp--;
					score += (total_weight%10)*weight[3];
					total_weight += weight[3];
					map.remove(weight[3]);
					if(mount_tmp == 0) {
						weight[3] = -1;
					} else {
						map.put(weight[3], mount_tmp);
					}
				} else if(weight[2] != -1) {
					int mount_tmp = map.get(weight[2]);
					if(mount_tmp == 0) {
						map.remove(weight[2]);
						weight[2] = -1;
						continue;
					}
					mount_tmp--;
					score += (total_weight%10)*weight[2];
					total_weight += weight[2];
					map.remove(weight[2]);
					if(mount_tmp == 0) {
						weight[2] = -1;
					} else {
						map.put(weight[2], mount_tmp);
					}
				} else if(weight[1] != -1) {
					int mount_tmp = map.get(weight[1]);
					if(mount_tmp == 0) {
						map.remove(weight[1]);
						weight[1] = -1;
						continue;
					}
					mount_tmp--;
					score += (total_weight%10)*weight[1];
					total_weight += weight[1];
					map.remove(weight[1]);
					if(mount_tmp == 0) {
						weight[1] = -1;
					} else {
						map.put(weight[1], mount_tmp);
					}
				} else {
					int mount_tmp = map.get(weight[0]);
					if(mount_tmp == 0) {
						map.remove(weight[0]);
						weight[0] = -1;
						continue;
					}
					mount_tmp--;
					score += (total_weight%10)*weight[0];
					total_weight += weight[0];
					map.remove(weight[0]);
					if(mount_tmp == 0) {
						weight[0] = -1;
					} else {
						map.put(weight[0], mount_tmp);
					}
				}
			}
		}
		return score;
	}

}
