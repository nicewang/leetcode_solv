package TouTiaoSpring2018;

import java.util.Scanner;

public class Two {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int len = in.nextInt();
			int result = getMin(len);
			System.out.println(result);
		}
		in.close();
	}

	private static int getMin(int len) {
		// TODO Auto-generated method stub
		int min = Integer.MAX_VALUE;
		boolean b = false;
		int n = len;
		int count = 0;
		while(true){ 
			count += 1;
	        int j= n % 2;  
	        n = n/2;  
	        if(j==1){  
	            b =false;  
	            break;  
	        }if(n == 2){  
	            b = true;  
	            break;  
	        }        
	    }
		if(b)
			return count+1;
		if(len%2 == 1)
			return len-1;
		count = 0;
		int i = 1;
		while(i < len) {
			count += 1;
			i *= 2;
		}
		i = i / 2;
		int i_pre = i / 2;
		min = count - 1 + (len - i) / i_pre;
		return min;
	}

}
