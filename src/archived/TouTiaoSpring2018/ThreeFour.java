package archived.TouTiaoSpring2018;

import java.util.Scanner;

/**
 * case通过率90%
 * @author wangxiaonan
 *
 */
public class ThreeFour {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			String tar = in.nextLine();
			String source = in.nextLine();
			int len = tar.length();
			int sr_len = source.length();
			char[] tar_ch = tar.toCharArray();
			char[] sr_ch = source.toCharArray();
			int flag = 0;
			int total = 0;
			int count = 0;
			int i = 0;
			for(int j = 0; j < sr_len; j++) {
				if(sr_ch[j] == tar_ch[i]) {
					if(count > 0)
						total += j - flag - 1;
					flag = j;
					count++;
					i++;
					if(i >= len)
						break;
				}
			}
			int result = 100 - total;
			if(i < len)
				result = 0;
			System.out.println(result);
		}
		in.close();
	}
	
}
