package Sohu2018Spring;

import java.util.Scanner;

public class Two {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int n = in.nextInt();
			int k = in.nextInt();
			Heap h = new Heap(n,0);
			int[] data = new int[n];
			for(int i = 0; i < n; i++) {
				data[i] = in.nextInt();
			}
			try {
				h.buildHeap_minHeap(h, data, n);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < k; i++) {
				try {
					int tmp = h.delMin();
					sb.append(tmp);
					if(i < k-1)
						sb.append(',');
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(sb.toString());
		}
		in.close();
	}
	
}

class Heap {

	private int[] array;
	private int capacity;  // 初始capacity值
	private int count;
	private int heap_type;  // 堆类型，0为最小堆，1为最大堆
	
	public Heap(int cap, int type) {
		this.capacity = cap;
		this.heap_type = type;
		this.array = new int[capacity];
		this.count = 0;
	}
	
	// 节点的双亲
	public int parent(int i) {
		if(i <= 0 || i >= this.count) // i不能等于0的原因是因为最上面的顶点没有双亲
			return -1;
		return (i-1)/2;
	}
	
	// 节点的孩子
	public int leftChild(int i) {
		if(i < 0)
			return -1;
		int left = i*2 + 1;
		if(left >= this.count)
			return -1;
		return left;
	}
	
	public int rightChild(int i) {
		if(i < 0)
			return -1;
		int right = i*2 + 2;
		if(right >= this.count)
			return -1;
		return right;
	}
	
	// 针对最大堆才有用
	public int getMax() throws Exception {
		if(heap_type == 0)
			throw new Exception("Get-Max Exception: not for minHeap!");
		if(this.count == 0)
			return -1;
		return array[0];
	}
	
	public int getMin() throws Exception {
		if(heap_type == 1)
			throw new Exception("Get-Min Exception: not for maxHeap!");
		if(this.count == 0)
			return -1;
		return array[0];
	}
	
	/**
	 * 堆化当前元素，针对最大堆
	 * @param i
	 */
	public void percolateDown_maxHeqp(int i) throws Exception {
		if(heap_type == 0)
			throw new Exception("PercolateDown Exception: not for minHeap!");
		int left = leftChild(i);
		int right = rightChild(i);
		int max;
		if(left != -1 && array[left] > array[i])
			max = left;
		else
			max = i;
		if(right != -1 && array[right] > array[max])
			max = right;
		if(max != i) {
			int tmp = array[max];
			array[max] = array[i];
			array[i] = tmp;
			percolateDown_maxHeqp(max);
		}
	}
	
	/**
	 * 堆化当前元素，针对最小堆
	 * @param i
	 */
	public void percolateDown_minHeqp(int i) throws Exception {
		if(heap_type == 1)
			throw new Exception("PercolateDown Exception: not for maxHeap!");
		int left = leftChild(i);
		int right = rightChild(i);
		int min;
		if(left != -1 && array[left] < array[i])
			min = left;
		else
			min = i;
		if(right != -1 && array[right] < array[min])
			min = right;
		if(min != i) {
			int tmp = array[min];
			array[min] = array[i];
			array[i] = tmp;
			percolateDown_minHeqp(min);
		}
	}
	
	/**
	 * 最大元素出队列
	 * 针对最大堆
	 * PS：思考——这两个由于其调用的方法已经抛出异常了，就不再抛出异常，但这样会不会影响查看异常日志时无法定位到问题？？
	 * @return
	 * @throws Exception
	 */
	public int delMax() throws Exception {
		if(this.count == 0)
			return -1;
		int data = array[0];
		array[0] = array[this.count-1]; // 先把最末节点的元素置于最顶
		this.count--; // 再减小堆大小
		percolateDown_maxHeqp(0); // 最后堆化最顶点元素
		return data;
	}
	
	/**
	 * 最小元素出队列
	 * 针对最小堆
	 * @return
	 * @throws Exception
	 */
	public int delMin() throws Exception {
		if(this.count == 0)
			return -1;
		int data = array[0];
		array[0] = array[this.count-1]; // 先把最末节点的元素置于最顶
		this.count--; // 再减小堆大小
		percolateDown_minHeqp(0); // 最后堆化最顶点元素
		return data;
	}
	
	/**
	 * 插入元素
	 * 针对最大堆
	 * @param data
	 * @throws Exception
	 */
	public void insert_maxHeap(int data) throws Exception {
		if(heap_type == 0)
			throw new Exception("Insert-maxHeap Exception: not for minHeap!");
		if(this.count == this.capacity) // 扩容
			resizeHeap();
		this.count++;
		int i = this.count-1;
		while(i >= 0 && data > array[i]) {
			array[i] = array[(i-1)/2]; // 父节点的值赋给子节点
			i = (i-1)/2;
		}
		array[i] = data;
	}
	
	/**
	 * 插入元素
	 * 针对最小堆
	 * @param data
	 * @throws Exception
	 */
	public void insert_minHeap(int data) throws Exception {
		if(heap_type == 1)
			throw new Exception("Insert-minHeap Exception: not for maxHeap!");
		if(this.count == this.capacity) // 扩容
			resizeHeap();
		this.count++;
		int i = this.count-1;
		while(i >= 0 && data < array[i]) {
			array[i] = array[(i-1)/2]; // 父节点的值赋给子节点
			i = (i-1)/2;
		}
		array[i] = data;
	}
	
	private void resizeHeap() {
		// TODO Auto-generated method stub
		int[] new_array = new int[capacity*2];
//		System.arraycopy(this.array, 0, new_array, 0, this.capacity);
		for(int i = 0; i < capacity; i++)
			new_array[i] = array[i];
		array = new_array;
		this.capacity *= 2;
	}
	
	/**
	 * 数组建最大堆
	 * @param h
	 * @param a
	 * @param n
	 * @throws Exception
	 */
	public void buildHeap_maxHeap (Heap h, int[] a, int n) throws Exception {
		if(h == null)
			return;
		while(n > h.capacity)
			h.resizeHeap();
		for(int i = 0; i < n; i++) 
			h.array[i] = a[i];
		h.count = n;
		for(int i = ((n-1)-1)/2; i >= 0; i--)
			h.percolateDown_maxHeqp(i);
	}
	
	/**
	 * 数组建最小堆
	 * @param h
	 * @param a
	 * @param n
	 * @throws Exception
	 */
	public void buildHeap_minHeap (Heap h, int[] a, int n) throws Exception {
		if(h == null)
			return;
		while(n > h.capacity)
			h.resizeHeap();
		for(int i = 0; i < n; i++) 
			h.array[i] = a[i];
		h.count = n;
		for(int i = ((n-1)-1)/2; i >= 0; i--)
			h.percolateDown_minHeqp(i);
	}
}