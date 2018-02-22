package shortest_path;

/**
 * 单向链表
 */
public class ListNode {
	private ListNode next;
	private int data;
	
	public ListNode(int data) {
		this.data = data;
	}
	
	// 测试一下单向链表逆序
	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		ListNode curNode = node;
		curNode.setNext(new ListNode(2));
		curNode = curNode.getNext();
		curNode.setNext(new ListNode(3));
		curNode = curNode.getNext();
		curNode.setNext(new ListNode(4));
		curNode = curNode.getNext();
		curNode.setNext(new ListNode(5));
		curNode = curNode.getNext();
		ListNode node_new = node.InvertLinkedList(node);
		node.ListLength(node_new);
		node.DeleteFromLinkedList(node_new, 5);
		node.InsertInLinkedList(node_new, new ListNode(3), 4);
		System.out.println("hahaha");
	}
	
	/**
	 * 单向链表的遍历
	 * @param headNode
	 * @return
	 */
	public int ListLength(ListNode headNode) {
		int length = 0;
		ListNode curNode = headNode;
		while(curNode != null) {
			curNode = curNode.getNext();
			length++;
		}
		return length;
	}
	
	/**
	 * 向单向链表中插入一个结点
	 * @param headNode
	 * @param nodeToInsert
	 * @param position
	 * @return 插入了元素的链表的表头
	 */
	public ListNode InsertInLinkedList(ListNode headNode, ListNode nodeToInsert, int position) {
		if(headNode == null) { // 单向链表为空，插入
			System.out.println("with a null list!");
			headNode = nodeToInsert;
			return nodeToInsert;
		}
		int size = ListLength(headNode);
		if(position > size+1 || position < 1) {
			System.out.println("Invalid Insert Position!");
			return headNode;
		}
		if(position == 1) { // 在链表开头插入
			nodeToInsert.setNext(headNode);
			return nodeToInsert;
		} else { // 在链表中间或尾部插入
			ListNode preNode = headNode;
			int count = 1;
			 while(count < position-1) {
				 preNode = preNode.getNext();
				 count++;
			 }
			 nodeToInsert.setNext(preNode.getNext());
			 preNode.setNext(nodeToInsert);
			 return headNode;
		}
	}
	
	/**
	 * 从单向链表中删除一个结点
	 * @param headNode
	 * @param position
	 * @return 删除了元素后的单向链表表头
	 */
	public ListNode DeleteFromLinkedList(ListNode headNode, int position) {
		int size = ListLength(headNode);
		if(position > size+1 || position < 1) {
			System.out.println("Invalid Delete Position!");
			return headNode;
		}
		if(position == 1) {
			ListNode curNode = headNode.getNext();
			headNode = null;
			return curNode;
		} else {
			ListNode preNode = headNode;
			int count = 1;
			while(count < position-1) {
				preNode = preNode.getNext();
				count++;
			}
			ListNode curNode = preNode.getNext();
			preNode.setNext(curNode.getNext());
			curNode = null;
			return headNode;
		}
	}
	
	/**
	 * 删除单向链表
	 * @param headNode
	 */
	public void DeleteLinkedList(ListNode headNode) {
		ListNode iterator  = headNode;
		while(iterator != null) {
			ListNode tmp = iterator.getNext();
			iterator = null;
			iterator = tmp;
		}
	}
	
	/**
	 * 单向链表逆序
	 * @param headNode
	 * @return
	 */
	public ListNode InvertLinkedList(ListNode headNode) {
		if(headNode == null)
			return null;
		int size = ListLength(headNode);
		if(size == 1)
			return headNode;
		ListNode curNode = headNode;
		ListNode nextNode = curNode.getNext();
		curNode.setNext(null);
		int count = 1;
		while(count < size) {
			ListNode tmp = nextNode.getNext();
			nextNode.setNext(curNode);
			curNode = nextNode;
			nextNode = tmp;
			count++;
		}
		return curNode;
	}
	
	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
}
