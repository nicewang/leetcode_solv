package Depth_first_Search;

import java.util.Stack;

/**
 * 图为使用邻接矩阵的无向图(或者双向图)
 */
public class GraphDFS {
	private final int maxVertices = 20;
	private int adjMatrix[][];
	private Vertex vertexList[];
	private int vertexCount;
	private Stack<Integer> theStack;
	
	/**
	 * 深度优先遍历的图为：
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
		GraphDFS graph = new GraphDFS();
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
		graph.dfs();
	}
	
	public GraphDFS() {
		vertexList = new Vertex[maxVertices];
		adjMatrix = new int[maxVertices][maxVertices];
		vertexCount = 0;
		for(int i = 0; i < maxVertices; i++)
			for(int j = 0; j < maxVertices; j++)
				adjMatrix[i][j] = 0;
		theStack = new Stack<Integer>();
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
	
	public void dfs() {
		vertexList[0].visited = true;
		displayVertex(0);
		theStack.push(0);
		while(!theStack.isEmpty()) {
			int v = getUnVisitedVertex(theStack.peek());
			if(v == -1)
				theStack.pop();
			else {
				vertexList[v].visited = true;
				displayVertex(v);
				theStack.push(v);
			}
		}
		// 遍历完之后将图恢复
		for(int i = 0; i < vertexCount; i++)
			vertexList[i].visited = false;
	}
	
	public int getUnVisitedVertex(int i) {
		// TODO Auto-generated method stub
		for(int j = 0; j < vertexCount; j++)
			if(adjMatrix[i][j] == 1 && vertexList[j].visited == false)
				return j;
		// 如果该点i是末端的话或与点i相连的点都被访问过的话
		return -1;
	}
}

class Vertex {
	public char label;
	public boolean visited;
	
	public Vertex(char lab) {
		this.label = lab;
		this.visited = false;
	}
}
