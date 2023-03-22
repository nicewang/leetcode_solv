package archived.TouTiaoSpring2018;

import java.util.Scanner;

public class ThreeTwo {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int m = in.nextInt();
			int n = in.nextInt();
			String[] m_array = new String[m+1];
			String[] n_array = new String[n+1];
			for(int i = 0; i < m+1; i++) {
				m_array[i] = in.nextLine();
			}
			for(int j = 0; j < n+1; j++) {
				n_array[j] = in.nextLine();
			}
			int[] result = new int[n];
			int k = 0;
			for(int i = 1; i < n+1; i++) {
				int count = -1;
				for(int j = 1; j < m+1; j++) {
					if(m_array[j].length() > n_array[i].length())
						continue;
					int len = m_array[j].length();
					if(n_array[i].substring(0, len).equals(m_array[j])) {
						if(count == -1)
							count = 1;
						else
							count += 1;
					}
				}
				if(count > 0)
					result[k++] = 1;
				else
					result[k++] = -1;
			}
			for(int i = 0; i < n; i++) 
				System.out.println("\n"+result[i]);
		}
		in.close();
	}
	
}
