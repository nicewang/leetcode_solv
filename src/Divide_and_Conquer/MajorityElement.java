package Divide_and_Conquer;

import java.util.Arrays;

/**
 * Given an array of size n, find the majority element. 
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class MajorityElement {
	
	public static void main(String[] args) {
		MajorityElement me = new MajorityElement();
		int[] nums1 = {1,2,1,1,1,4,7,1};
		System.out.println(me.majorityElement(nums1));
		int[] nums2 = {6,5,5};
		System.out.println(me.majorityElement(nums2));
		int[] nums3 = {2,2};
		System.out.println(me.majorityElement(nums3));
		int[] nums4 = {3,2,3};
		System.out.println(me.majorityElement(nums4));
	}
	
	
	public int majorityElement(int[] nums) {
        int result = -1;
        int length = nums.length;
        if(length == 1)
        	return nums[0];
        Arrays.sort(nums);
        int count = 1;
        int count_tmp = 1;
        for(int i = 0; i < length-1; i++) {
        	if(nums[i] == nums[i+1]) {
        		count_tmp++;
        	} else {
        		if(count_tmp > count)
        			count = count_tmp;
        		if(count > length/2)
        			return nums[i];
        		count_tmp = 1;
//        		i++;
        	}
        }
        if(count_tmp > length/2)
        	result = nums[length-1];
        return result;
    }
}
