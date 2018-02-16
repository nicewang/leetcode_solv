package Depth_first_Search;

/**
 * BinarySearch??二分查找是这么翻译的么？
 * 二分查找适用于有序数列的查找情况
 * @author wangxiaonan
 */
public class BinarySearch {
	
	public static void main(String[] args) {
//		int[] nums = {9, 10, 11, 12, 13, 14, 15,
//				1, 2, 3, 4, 5, 6, 7, 8};
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
				11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		System.out.println("二分查找：");
		long time1 = System.nanoTime();
		if(binarySearch1(nums, 5)) {
			System.out.println("Yeahp, got it!");
		}
		long time2 = System.nanoTime();
		System.out.println("二分查找用时(ns):" + (time2 - time1));
		System.out.println("顺序查找：");
		long time3 = System.nanoTime();
		if(searchInTurn(nums, 5)) {
			System.out.println("Yeahp, got it!");
		}
		long time4 = System.nanoTime();
		System.out.println("顺序查找用时(ns):" + (time4- time3));
	}
	
	/**
	 * 二分查找
	 * @param nums
	 * @param value
	 * @return
	 */
	public static boolean getBinarySearch(int[] nums, int value) {
		if(nums == null || nums.length == 0)
			return false;
		return binarySearch(nums, value, 0, nums.length - 1);
	}

	/**
	 * 二分查找递归调用
	 * @param nums
	 * @param value
	 * @param l
	 * @param r
	 * @return
	 */
	private static boolean binarySearch(int[] nums, int value, int l, int r) {
		// TODO Auto-generated method stub
		if(l > r)
			return false;
		int m = (l + r) / 2;
		if(nums[m] == value)
			return true;
		else if(nums[m] > value)
			return binarySearch(nums, value, l, m-1);
		else
			return binarySearch(nums, value, m+1, r);
	}
	
	/**
	 * 二分查找的非递归实现
	 * @param nums
	 * @param value
	 * @return
	 */
	public static boolean binarySearch1(int[] nums, int value) {
		int length = nums.length;
		if(nums == null || length == 0)
			return false;
		int l = 0, r = length -1;
		while(l <= r) {
			int m = (l + r) / 2;
			if(nums[m] == value)
				return true;
			else if(nums[m] > value) 
				r = m - 1;
			else
				l = m + 1;
		}
		return false;
	}
	
	/**
	 * 改进的二分查找，针对有序数组前后两部分(不一定等长)调换位置的问题
	 * Input eg.
	 * int[] nums = {9, 10, 11, 12, 13, 14, 15,
	 * 1, 2, 3, 4, 5, 6, 7, 8};
	 * @param nums
	 * @param target
	 * @return
	 */
	public static boolean getModifiedBinarySearch(int[] nums, int target) {
		if(nums.length == 0 || nums == null)
			return false;
		return modifiedBinarySearch(nums, target, 0, nums.length - 1);
	}
	
	/**
	 * 改进的二分查找递归调用
	 * @param nums
	 * @param target
	 * @param l
	 * @param r
	 * @return
	 */
	private static boolean modifiedBinarySearch(int[] nums, int target, int l, int r) {
		// TODO Auto-generated method stub
		if(l > r)
			return false;
		int m = (l + r) / 2;
		if(nums[m] == target || nums[l] == target || nums[r] == target)
			return true;
		if(nums[l] <= nums[r]) //此时已经是正常二分查找问题了
			return binarySearch(nums, target, l, r);
		if(nums[l] > target) { // target在右半部分
			if(nums[m] <= nums[r]) // 正常二分查找
				return binarySearch(nums, target, m+1, r);
			else // 改进的二分查找
				return modifiedBinarySearch(nums, target, m+1, r);
		} else { // target在左半部分
			if(nums[m] >= nums[l]) // 正常二分查找
				return binarySearch(nums, target, l, m-1);
			else
				return modifiedBinarySearch(nums, target, l, m-1);
		}
	}

	/**
	 * 写一个顺序查找对比一下速度
	 * @param nums
	 * @param value
	 * @return
	 */
	public static boolean searchInTurn(int[] nums, int value) {
		int length = nums.length;
		if(nums == null || length == 0) {
			return false;
		}
		for(int i = 0; i < length; i++) {
			if(nums[i] == value)
				return true;
		}
		return false;
	}

}
