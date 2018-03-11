package heap;

/**
 * 堆排序
 * 堆排序也是用贪心法求解的问题哦
 */
public class HeapSort {
	
	public static void main(String[] args) {
		int[] a = {8,9,0,122,35,7,4,6,1,5,3};
		HeapSort hs = new HeapSort();
		int[] result = hs.heapSort_asc(a);
		System.out.println("Asc:");
		for(int i = 0; i < result.length; i++)
			System.out.println(result[i]);
		result = hs.heapSort_desc(a);
		System.out.println("Desc:");
		for(int i = 0; i < result.length; i++)
			System.out.println(result[i]);
	}
	
	public int[] heapSort_asc(int[] data) {
		int[] result = new int[data.length];
		if(data == null || data.length == 0)
			return result;
		// 用最大堆来实现升序排序
		Heap h = new Heap(1,1);
		try {
			h.buildHeap_maxHeap(h, data, data.length);
			for(int i = data.length-1; i >= 0; i--) {
				result[i] = h.delMax();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	public int[] heapSort_desc(int[] data) {
		int[] result = new int[data.length];
		if(data == null || data.length == 0)
			return result;
		// 用最小堆来实现降序排序
		Heap h = new Heap(1,0);
		try {
			h.buildHeap_minHeap(h, data, data.length);
			for(int i = data.length-1; i >= 0; i--) {
				result[i] = h.delMin();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
		return result;
	}

}
