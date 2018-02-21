package greedy;

public class TreeNode implements Comparable<TreeNode>{
	
	private String val = "";
	private int freq = 0;
	private TreeNode left;
	private TreeNode right;
	private String coding = "";
	
	@Override
	public int compareTo(TreeNode o) {
		// TODO Auto-generated method stub
		return freq-o.freq;   // 这么写决定了将类别为该类的元素加入优先队列时是按freq属性值的大小(从小到大)排序并出队列的
	}
	
	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}
	
}
