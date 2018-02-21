package shortest_path;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	
	public List<Integer> listVertices;
	public ListNode[] edges;
	public int vertexCount;
	
	public Graph(int vertexCount) {
		this.vertexCount = vertexCount;
		listVertices = new ArrayList<Integer>();
		edges = new ListNode[vertexCount];
		for(int i = 0; i < vertexCount; i++) {
			listVertices.add(i);
			edges[i] = new ListNode(i);
		}
	}
	
	public void addEdge(int start, int end) {
		int i = listVertices.indexOf(start);
		int j = listVertices.indexOf(end);
		if(i != -1 && j != -1) {
			edges[i] = edges[i].InsertInLinkedList(edges[i], new ListNode(j), 1);
//			edges[j].InsertInLinkedList(edges[j], new ListNode(start), 1);  取消注释即为双向图(或者无向图)
		}
	}

}
