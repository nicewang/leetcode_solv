package shortest_path;

/**
 * 基于链表实现队列
 */
public class LLQueue {
	
	public ListNode frontNode;
	public ListNode rearNode;
	
	public LLQueue() {
		this.frontNode = null;
		this.rearNode = null;
	}
	
	public boolean isEmpty() {
		return (frontNode == null);
	}
	
	/**
	 * 进队列
	 * @param data
	 */
	public void enQueue(int data) {
		ListNode newNode = new ListNode(data);
		if(rearNode != null)  // 在尾部插入
			rearNode.setNext(newNode);
		rearNode = newNode;
		if(frontNode == null)
			frontNode = rearNode;
	}
	
	/**
	 * 出队列
	 * @return
	 */
	public int deQueue() {
		if(isEmpty()) {
			System.out.println("Empty Queue Error!");
			return -1;
		}
		// 满足队列的先进先出
		int data = frontNode.getData();
		frontNode = frontNode.getNext();
		return data;
	}

}
