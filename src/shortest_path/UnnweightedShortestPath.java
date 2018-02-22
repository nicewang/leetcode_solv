package shortest_path;

/**
 * 无权图中的最短路径求解
 */
public class UnnweightedShortestPath {
	
	public static void main(String[] args) {
		Graph g = new Graph(7);
		g.addEdge(0, 1, 1);
		g.addEdge(0, 2, 1);
		g.addEdge(1, 3, 1);
		g.addEdge(1, 4, 1);
		g.addEdge(4, 3, 1);
		g.addEdge(4, 6, 1);
		g.addEdge(3, 2, 1);
		g.addEdge(3, 5, 1);
		g.addEdge(6, 5, 1);
		g.addEdge(5, 2, 1);
		int[] path = new int[7];
		UnnweightedShortestPath sp = new UnnweightedShortestPath();
		sp.getShortestPath(g, 0, path);
	}
	
	public int[] getShortestPath(Graph graph, int s, int[] path) {
		
		int[] distance = new int[graph.vertexCount]; // 用于存放原点到第i个点的距离
//		int[] path = new int[graph.vertexCount];   // 用存放第i个点的上一个点的编号
		
		for(int i = 0; i < graph.vertexCount; i++)
			distance[i] = -1;
		distance[s] = 0;
		
		LLQueue queue = new LLQueue();
		queue.enQueue(s);		
		int cur, next;
		while(!queue.isEmpty()) {
			cur = queue.deQueue();
			int len = graph.edges[cur].ListLength(graph.edges[cur]);
			ListNode tmp = graph.edges[cur];
			for(int i = 0; i < len-1; i++) {
				next = tmp.getData();
				if(distance[next] == -1) { // 每个顶点最多检查一次
					distance[next] = distance[cur] + 1;
					path[next] = cur;
					queue.enQueue(next);  // 每个顶点最多检查一次
				}
				tmp = tmp.getNext();
			}
		}
//		queue.deQueue();
		return distance;
	}

}
