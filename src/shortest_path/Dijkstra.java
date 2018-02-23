package shortest_path;

import java.util.PriorityQueue;

/**
 * 有权图中的最短路径算法——Dijkstra
 * 采用贪婪算法：总是选取最接近原点的顶点
 * 使用优先队列，并按照到s的距离来存储未被访问过的顶点
 * 不能用于权值为负的情况
 */
public class Dijkstra {
	
	public static void main(String[] args) {
		Graph g = new Graph(5);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 2, 1);
		g.addEdge(2, 1, 2);
		g.addEdge(1, 4, 4);
		g.addEdge(2, 3, 4);
		g.addEdge(3, 4, 4);
		int[] path = new int[5];
		Dijkstra dj = new Dijkstra();
		dj.getShortestPath(g, 0, path);
		dj.prims(g, 0, path);
	}
	
	public int[] getShortestPath(Graph graph, int s, int[] path) {
		
		int[] distance = new int[graph.vertexCount]; // 用于存放原点到第i个点的距离
//		int[] path = new int[graph.vertexCount];   // 用存放第i个点的上一个点的编号
		
		for(int i = 0; i < graph.vertexCount; i++)
			distance[i] = -1;
		distance[s] = 0;
		
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		queue.offer(new Vertex(s, 0));		
		int cur, next;
		while(!queue.isEmpty()) {
			cur = queue.poll().val;
			int len = graph.edges[cur].ListLength(graph.edges[cur]);
			ListNode tmp = graph.edges[cur];
			for(int i = 0; i < len-1; i++) {
				next = tmp.getData();
				int new_d = distance[cur] + graph.weights[cur][next];
				if(distance[next] == -1) { // 每个顶点最多检查一次
					distance[next] = new_d; 					
					path[next] = cur;
					queue.offer(new Vertex(next, new_d));  // 每个顶点最多检查一次
				}
				if(distance[next] > new_d) {
					queue.remove(new Vertex(next, distance[next]));
					queue.offer(new Vertex(next, new_d));
					distance[next] = new_d;
					path[next] = cur;
				}
				tmp = tmp.getNext();
			}
		}
//		queue.deQueue();
		return distance;
	}
	
	/**
	 * Prims算法查找最小生成树
	 * 这棵树中所有边的权值之和是最小的
	 * @param graph
	 * @param s
	 * @param path
	 * @return
	 */
	public int[] prims(Graph graph, int s, int[] path) {
		
		int[] distance = new int[graph.vertexCount]; // 用于存放原点到第i个点的距离
//		int[] path = new int[graph.vertexCount];   // 用存放第i个点的上一个点的编号
		
		for(int i = 0; i < graph.vertexCount; i++)
			distance[i] = -1;
		distance[s] = 0;
		
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		queue.offer(new Vertex(s, 0));		
		int cur, next;
		while(!queue.isEmpty()) {
			cur = queue.poll().val;
			int len = graph.edges[cur].ListLength(graph.edges[cur]);
			ListNode tmp = graph.edges[cur];
			for(int i = 0; i < len-1; i++) {
				next = tmp.getData();
				int new_d = distance[cur] + graph.weights[cur][next];
				if(distance[next] == -1) { // 每个顶点最多检查一次
					distance[next] = graph.weights[cur][next];  // 这里是与Dijkstra算法的区别
																// 最小生成树里distance存放的是与上一个结点的距离				
					path[next] = cur;
					queue.offer(new Vertex(next, new_d));  // 每个顶点最多检查一次
//					queue.offer(new Vertex(next, graph.weights[cur][next]));  // 用这个语句也能达到一样的效果
				}
				if(distance[next] > new_d) {
					queue.remove(new Vertex(next, distance[next]));
					queue.offer(new Vertex(next, new_d));
//					queue.offer(new Vertex(next, graph.weights[cur][next]));  // 用这个语句也能达到一样的效果
					distance[next] = graph.weights[cur][next];  // 这里是与Dijkstra算法的区别
																// 最小生成树里distance存放的是与上一个结点的距离
					path[next] = cur;
				}
				tmp = tmp.getNext();
			}
		}
//		queue.deQueue();
		return distance;
	}

}
