package Array;

public class NonDecreasingArray {
	
	public static void main(String[] args) {
		NonDecreasingArray ins  = new NonDecreasingArray();
		int[] data = {2,3,3,2,4};
		boolean result = ins.checkPossibility(data);
		if(result)
			System.out.println(1);
		else
			System.out.println(0);
	}
	
	/**运行用时24ms
	 * @param nums
	 * @return
	 */
	public boolean checkPossibility(int[] nums) {
        if(nums == null || nums.length == 0)
        	return true;
        int count_up_down = 0;
        boolean up_flag = false;
        for(int i = 0; i < nums.length-1; i++) {
        	if(nums[i] > nums[i+1]) {
        		if(i > 0 && nums[i-1] > nums[i+1]) {
        			if(i+2 < nums.length && nums[i] >= nums[i+2])
        				return false;
        		}
        		up_flag = true;
        	}
        	if(up_flag) {
        		count_up_down++;
        		if(count_up_down == 2)
        			return false;
        		up_flag = false;
        	}
        }
        return true;
    }
}
