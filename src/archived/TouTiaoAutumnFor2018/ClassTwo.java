package archived.TouTiaoAutumnFor2018;

import java.util.Scanner;
import java.util.Stack;
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
			int result3 = getMax_solv5(data);
			System.out.println(result3);
			int result4 = max2(data);
			System.out.println(result4);
			int result5 = getMax_solv1(data);
			System.out.println(result5);
			int result6 = get(data,0,n-1);
			System.out.println(result6);
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
	 * 时间复杂度为n方
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
	/**
	 * 他人解法solv5
	 * 和上面自己拍脑袋想的解法思想差不多，实现略有差别
	 * 但少了标志位，实现更简单了，空间复杂度也减小了
	 * 时间复杂度为n方
	 * case全通过
	 * @param data
	 * @return
	 */
	private static int getMax_solv5(int[] data) {
		// TODO Auto-generated method stub
		int max = -1;
		int len = data.length;
		for(int i = 0; i < len; i++) {
			int tmp = data[i];
			//向左边查找-相加-查找，直至data[j]比data[i]小为止
			for(int j = i-1; j >= 0; j--) {
				if(data[j] < data[i])
					break;
				tmp += data[j];
			}
			//向右边查找-相加-查找，直至data[j]比data[i]小为止
			for(int j = i+1; j < len; j++) {
				if(data[j] < data[i])
					break;
				tmp += data[j];
			}
			max = Math.max(max, data[i]*tmp);
		}
		return max;
	}
	
	/**
	 * 堆栈实现
	 * 不太看得懂的一种方法
	 * 时间复杂度应该是在n和n方之间
	 * 堆栈存放(从栈尾->头，出栈从栈头)的是从全局最小开始单调增加的局部最小(因而有些局部最小未必入栈)的index
	 * 和最后一个元素的index
	 * case全通过
	 * @param arr
	 * @return
	 */
	public static int max2(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);                
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }
	/**
	 * 比较常规的实现
	 * 把原数据拆分成n(n+1)/2个子集
	 * 比较聪明的是拆分子集时就进行求和与比较
	 * 因而时间复杂度还是为n方
	 * case全通过
	 * @param nums
	 * @return
	 */
	private static int getMax_solv1(int[] nums) {
		// TODO Auto-generated method stub
		int res = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            int tRes = nums[i] * nums[i];
            int sum = nums[i], min = nums[i];
            for(int j = i + 1; j < nums.length; j++){
                sum += nums[j];
                min = Math.min(min, nums[j]);
                tRes = Math.max(tRes, sum * min);
                // 这一步很聪明，根据题目要求，最小值为0，因而有0出现是直接返回就好
                if(min == 0){
                    return 0;
                }
            }          
            res = Math.max(res, tRes);
        }
		return res;
	}
	
	/**
	 * 类似于堆栈实现方式
	 * case全通过
	 * @param a
	 * @param from
	 * @param to
	 * @return
	 */
	public static int get(int[] a,int from,int to){
        if(to==from){
            return a[from]*a[to];
        }
        else if(to<from){
            return 0;
        }
        else{
            int index=findIndex(a,from,to);
            int left=get(a,from,index-1);
            int right=get(a,index+1,to);
            int sum=0;
            for(int i=from;i<=to;i++){
                sum+=a[i];
            }
            return sum*a[index]>left?(sum*a[index]>right?sum*a[index]:right):(right<left?left:right);
        }
    }
	
    private static int findIndex(int[] a,int from,int to) {
        // TODO Auto-generated method stub
        int min=from;
        for(int i=from;i<=to;i++){
            if(a[i]<a[min]){
                min=i;
            }
        }
        return min;
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
