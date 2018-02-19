package Breath_first_Search;

import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {
	
	private final int maxVertices = 20;
	private Vertex vertexList[];
	private int adjMatrix[][];
	private int vertexCount;
	private Queue<Integer> theQueue;
	
	/**
	 * 广度优先遍历的图为：
	 * A
	 * |
	 * B-H
	 * | |
	 * C—E-G
	 * | |
	 * D F
	 * @param argv
	 */
	public static void main(String argv[]) {
		GraphBFS graph = new GraphBFS();
		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		graph.addVertex('D');
		graph.addVertex('E');
		graph.addVertex('F');
		graph.addVertex('G');
		graph.addVertex('H');
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(4, 5);
		graph.addEdge(4, 6);
		graph.addEdge(4, 7);
		graph.addEdge(1, 7);
		graph.bfs();
	}
	
	public GraphBFS() {
		vertexList = new Vertex[maxVertices];
		adjMatrix = new int[maxVertices][maxVertices];
		vertexCount = 0;
		for(int i = 0; i < maxVertices; i++)
			for(int j = 0; j < maxVertices; j++)
				adjMatrix[i][j] = 0;
		theQueue = new LinkedList<Integer>();
	}
	
	public void addVertex(char lab) {
		vertexList[vertexCount++] = new Vertex(lab);
	}
	
	public void addEdge(int start, int end) {
		adjMatrix[start][end] = 1;
		adjMatrix[end][start] = 1;
	}
	
	public void displayVertex(int v) {
		System.out.println(vertexList[v].label);
	}
	
	public void bfs() {
		vertexList[0].isVisited = true;
		displayVertex(0);
		theQueue.add(0);
		while(!theQueue.isEmpty()) {
			int v1 = theQueue.remove();
			int start = 0;
			while(getUnvisitedVertex(v1, start) != -1) {
				int v2 = getUnvisitedVertex(v1, start);
				vertexList[v2].isVisited = true;
				displayVertex(v2);
				theQueue.add(v2);
//				start += 1;  // 事实证明，加不加这个start没差，因为一旦访问了一个，其isVisited就被置为true
							 // 不过这样做倒是提高了效率
			}
		}
		// 遍历完之后将图恢复
		for(int i = 0; i < vertexCount; i++)
			vertexList[i].isVisited = false;
	}
	
	public int getUnvisitedVertex(int v, int start) {
		for(int j = start; j < vertexCount; j++)
			if(adjMatrix[v][j]==1 && vertexList[j].isVisited==false)
				return j;
		return -1;
	}
	
}

class Vertex {
	public char label;
	public boolean isVisited;
	public Vertex(char lab) {
		this.label = lab;
		this.isVisited = false;
	}
}
