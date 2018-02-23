package shortest_path;

import java.util.LinkedList;

/**
 * 含有负边的有权图中的最短路径算法——Bellman-Ford
 */
public class BellmanFord {
	
	public static void main(String[] args) {
		Graph g = new Graph(5);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 2, 1);
		g.addEdge(2, 1, 2);
		g.addEdge(1, 4, 4);
		g.addEdge(2, 3, 4);
		g.addEdge(3, 4, -1);
		int[] path = new int[5];
		BellmanFord bf = new BellmanFord();
		bf.getShortestPath(g, 0, path);
	}
	
	public int[] getShortestPath(Graph graph, int s, int[] path) {
		
		int[] distance = new int[graph.vertexCount]; // 用于存放原点到第i个点的距离
//		int[] path = new int[graph.vertexCount];   // 用存放第i个点的上一个点的编号
		int[] isInQueue = new int[graph.vertexCount];
		
		for(int i = 0; i < graph.vertexCount; i++) {
			distance[i] = Integer.MAX_VALUE;
			isInQueue[i] = 0;
		}
		distance[s] = 0;
		isInQueue[s] = 1;
		
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		queue.offer(new Vertex(s, 0));		
		int cur, next;
		while(!queue.isEmpty()) {
			cur = queue.poll().val;
			isInQueue[cur] = 0;
			int len = graph.edges[cur].ListLength(graph.edges[cur]);
			ListNode tmp = graph.edges[cur];
			for(int i = 0; i < len-1; i++) {
				next = tmp.getData();
				int new_d = distance[cur] + graph.weights[cur][next];
				if(distance[next] > new_d) {
					distance[next] = new_d;
					path[next] = cur;
					if(isInQueue[next] == 0)
						queue.offer(new Vertex(next, new_d));
						isInQueue[next] = 1;
				}
				tmp = tmp.getNext();
			}
		}
//		queue.deQueue();
		return distance;
	}

}
