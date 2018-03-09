package binary_search;

import java.util.Arrays;

public class TwoSumWithSortedArray {
	public static void main(String[] args) {
		TwoSumWithSortedArray ins = new TwoSumWithSortedArray();
		int[] a = {2,3,4};
		int[] result = ins.twoSum(a, 6);
		System.out.println(result[0]);
		System.out.println(result[1]);
	}
	
	/**
	 * 调用Arrays的BinarySearch
	 * 用时3ms
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum_ArraysBS(int[] numbers, int target) {
		if(numbers == null || numbers.length < 2)
			return new int[] {-1,-1};
		for(int i = 0; i < numbers.length-1; i++) {
			int distance = target - numbers[i];
			int j = Arrays.binarySearch(numbers,i+1,numbers.length,distance);
			if(j >= 0)
				return new int[] {i+1,j+1};
		}
		return new int[] {-1,-1};
	}
	
	/**
	 * 二分查找解法，不过运行超时了
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] numbers, int target) {
		int[] result = new int[2];
		if(numbers == null || numbers.length < 2)
			return result;
		for(int i = 0; i < numbers.length-1; i++) {
			int distance = target - numbers[i];
			int left = i+1;
			int right = numbers.length-1;
			while(left <= right) { // 注意，这里有一个等于
				int middle = left+(right-left)/2;
				if(numbers[middle] == distance) {
					result[0] = i+1;
					result[1] = middle+1;
					return result;
				} else if(numbers[middle] > distance) {
					right = middle-1;
				} else {
					left = middle+1;
				}
			}
		}
		return result;
	}
	
	/**
	 * 解法1
	 * 运行时长1ms
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum_solv1(int[] numbers, int target) {
		int[] result = new int[2];
		if(numbers == null || numbers.length < 2)
			return result;
		int left = 0;
		int right = numbers.length - 1;
		while(left < right) {
			int v = numbers[left] + numbers[right];  //靠，把加法放在前面做而不是每次判断时都做就不会超时，因为每次循环减少了两次做加法次数
//			if(numbers[left]+numbers[right] == target) {
			if(v == target) {
				result[0] = left+1;
				result[1] = right+1;
				break;
			}
			// 注意下面这两步,解法的精髓之所在
//			else if(numbers[left]+numbers[right] > target)
			else if(v > target)
				right--;
			else
				left++;
		}
		return result;
	}
	
	/**
	 * 考虑的太复杂了！！！
	 * 有些case还没通过。。。
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum_complex(int[] numbers, int target) {
		int[] result = new int[2];
		int left = 0;
		int right = numbers.length-1;
		int boundary;
		if(target > numbers[right]) {
			boundary = right;
			for(int i = 0; i <= boundary; i++) {
				for(int j = 0; j <= boundary; j++) {
					if(numbers[i]+numbers[j] == target) {
						result[0] = i+1;
						if(i == j)
							result[1] = j+2;
						else
							result[1] = j+1;
						return result;
					}
				}
			}
		} else {
			boundary = binarySearch1(numbers,left,right,target);
			if(numbers[boundary] == target) {
				for(int i = 0; i <= right; i++) {
					if(numbers[i]+numbers[boundary] == target) {
						if(i < boundary) {
							result[0] = i+1;
							result[1] = boundary+1;
						} else {
							result[1] = i+1;
							result[0] = boundary+1;
						}						
						return result;
					}
				}
			} else {
				for(int i = 0; i <= boundary; i++) {
					for(int j = 0; j <= boundary; j++) {
						if(numbers[i]+numbers[j] == target) {
							result[0] = i+1;
							if(i == j)
								result[1] = j+2;
							else
								result[1] = j+1;
							return result;
						}
					}
				}
			}
		}
		return result;
    }

	private int binarySearch1(int[] numbers, int left, int right, int target) {
		// TODO Auto-generated method stub
		int result = -1;
		if(left > right)
			return result;
		int middle = left + (right-left+1)/2;
		if(target == numbers[middle]) {
			return middle;
		} else if(target < numbers[middle]) {
			result = binarySearch1(numbers,left,middle-1,target);
			if(result == -1) {
				if(left == right)
					return left-1;
				return left;
			}
			return result;
		} else {
//		if(target > numbers[middle]) {
			result = binarySearch1(numbers,middle+1,right,target);
			if(result == -1)
				return right+1;
			return result;
		}
	}
}
