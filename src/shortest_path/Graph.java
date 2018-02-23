package shortest_path;

import java.util.ArrayList;
import java.util.List;

/**
 * 图为使用邻接表的单向图
 */
public class Graph {
	
	public List<Integer> listVertices;
	public ListNode[] edges;
	public int vertexCount;
	public int[][] weights;
	
	public Graph(int vertexCount) {
		this.vertexCount = vertexCount;
		listVertices = new ArrayList<Integer>();
		edges = new ListNode[vertexCount];
		for(int i = 0; i < vertexCount; i++) {
			listVertices.add(i);
			edges[i] = new ListNode(i);
		}
		weights = new int[vertexCount][vertexCount];
		for(int i = 0; i < vertexCount; i++)
			for(int j = 0; j < vertexCount; j++)
				weights[i][j] = 0;
	}
	
	public void addEdge(int start, int end, int weight) {
		int i = listVertices.indexOf(start);
		int j = listVertices.indexOf(end);
		if(i != -1 && j != -1) {
			edges[i] = edges[i].InsertInLinkedList(edges[i], new ListNode(j), 1);
			weights[i][j] = weight;
//			edges[j] = edges[j].InsertInLinkedList(edges[j], new ListNode(start), 1);  // 取消注释即为双向图(或者无向图)
//			weights[j][i] = weight;
		}
	}

}
