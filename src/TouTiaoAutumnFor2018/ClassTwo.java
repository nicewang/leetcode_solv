package TouTiaoAutumnFor2018;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 给定一个数组序列, 需要求选出一个区间, 使得该区间是所有区间中经过如下计算的值最大的一个：
 * 区间中的最小数 * 区间所有数的和最后程序输出经过计算后的最大值即可，不需要输出具体的区间。
 * 如给定序列  [6 2 1]则根据上述公式, 可得到所有可以选定各个区间的计算值:
 * [6] = 6 * 6 = 36;
 * [2] = 2 * 2 = 4;
 * [1] = 1 * 1 = 1;
 * [6,2] = 2 * 8 = 16;
 * [2,1] = 1 * 3 = 3;
 * [6, 2, 1] = 1 * 9 = 9;
 * 从上述计算可见选定区间 [6] ，计算值为 36， 则程序输出为 36。
 * 区间内的所有数字都在[0, 100]的范围内;
 * 输入描述:
 * 第一行输入数组序列长度n，第二行输入数组序列。
 * 对于 50%的数据,  1 <= n <= 10000;
 * 对于 100%的数据, 1 <= n <= 500000;
 * 输出描述:
 * 输出数组经过计算后的最大值。
 * 输入例子1:
 * 3
 * 6 2 1
 * 输出例子1:
 * 36
 */
public class ClassTwo {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int[] data = new int[n];
			for(int i = 0; i < n; i++) {
				data[i] = in.nextInt();
			}
			int result1 = getMax(data, n);
			System.out.println(result1);
			int result2 = getMax(data);
			System.out.println(result2);
		}
		in.close();
	}

	/**
	 * 递归实现
	 * 但其实是重叠子问题的情况——eg 6 2 和 2 1 就包含重叠的子问题 2
	 * so，是不是可以用动态规划？
	 * PS 此方法case通过率10%
	 * @param data
	 * @param n
	 * @return
	 */
	private static int getMax(int[] data, int n) {
		// TODO Auto-generated method stub
		int result = 0;
		if(n == 0 || data == null)
			return result;
		if(n == 1)
			return data[0]*data[0];
		int[] data1 = new int[n-1];
		int[] data11 = new int[n-1];
		int[] data2 = new int[n-1];
		int[] data22 = new int[n-1];
		int tmp1 = 0;
		int tmp2 = 0;
		for(int i = 0; i < n-1; i++) {
			data1[i] = data[i];
			data11[i] = data1[i];
			data2[i] = data[i+1];
			data22[i] = data2[i];
			tmp1 = tmp1 + data[i];
			tmp2 = tmp2 + data[i+1];
		}
		Arrays.sort(data11);
		Arrays.sort(data22);
		int result1 = Math.max(data11[0]*tmp1, getMax(data1,n-1));
		int result2 = Math.max(data22[0]*tmp2, getMax(data2,n-1));
		result = Math.max(result1, result2);
		return result;
	}
	
	/**
	 * 拍脑袋想出来的方法
	 * case通过率为70%
	 * @param list
	 * @return
	 */
	private static int getMax(int[] list) {
		int max = 0;
		int[] list_index = new int[list.length];
		for(int i = 0; i < list.length; i++) {
			list_index[i] = i;
		}
		insertSort_index(list_index, list);
		boolean[] list_status = new boolean[list.length];
		for(int i = 0; i < list.length; i++) {
			list_status[list_index[i]] = true;
			int sum = list[list_index[i]]; 
			int j1, j2;
			if(list_index[i] == list.length - 1) {
				j1 = list_index[i] - 1;
				j2 = list_index[i];
			} else if(list_index[i] == 0) {
				j1 = list_index[i];
				j2 = list_index[i] + 1;
			} else {
				j1 = list_index[i] - 1;
				j2 = list_index[i] + 1;
			}
			int j11 = j1, j22 = j2;
			while(list_status[j11] == false || list_status[j22] == false) {
				int sum_temp = sum;
				if(list_status[j11] == true) {
					if(j2 < list.length)
						sum += list[j2++];
				} else if(list_status[j22] == true) {
					if(j1 >= 0)
						sum += list[j1--];
				} else {
					if(j2 < list.length)
						sum += list[j2++];
					if(j1 >= 0)
						sum += list[j1--];
				}
				
				if(j1 >= 0)
					j11 = j1;
				if(j2 < list.length)
					j22 = j2;
				
				if(sum_temp == sum)
					break;
			}
			sum *= list[list_index[i]];
			// 此二处为显示本方法运行过程原理的，记不得或看不懂时可以取消注释再跑跑
//			System.out.println("******************************");
//			System.out.println(sum);
			if(sum > max) {
				max = sum;
			}
		} 
		return max;
	}
	
	public static void insertSort_index(int[] index_list, int[] input) {
		for(int i = 1; i < input.length; i++) {
			int index_temp = index_list[i];
			int temp = input[index_temp];
			int j;
			for(j = i; j > 0 && input[index_list[j-1]] > temp; j--) {
//				input[j] = input[j-1];
				index_list[j] = index_list[j-1];
			}
//			input[j] = temp;
			index_list[j] = index_temp;
		}
	}

}
