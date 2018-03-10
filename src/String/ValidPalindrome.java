package String;

public class ValidPalindrome {
	
	/**
	 * 运行时间8ms
	 * @param s
	 * @return
	 */
	public boolean isPalindrome_mySolv(String s) {
		if(s == "" || s.length() == 0)
			return true;
        int left = 0;
        int right = s.length()-1;
        while(left <= right) {
        	if(!Character.isLetterOrDigit(s.charAt(left))) {
        		left++;
        		continue;
        	}
        	if(!Character.isLetterOrDigit(s.charAt(right))) {
        		right--;
        		continue;
        	}
        	if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
        		return false;
        	left++;
        	right--;
        }
        return true;
    }
	
	/**
	 * mark一种解法
	 * 用到了StringBuffer
	 * 还用到了“[^A-Za-z0-9]”这个
	 * 运行用时32ms
	 * @param s
	 * @return
	 */
	public boolean isPalindrome(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}
