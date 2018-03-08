package Depth_first_Search;

import java.util.Stack;

public class MaxAreaOfIsland {
	
	public static void main(String[] args) {
		int[][] a = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
					 {0,0,0,0,0,0,0,1,1,1,0,0,0},
					 {0,1,1,0,1,0,0,0,0,0,0,0,0},
					 {0,1,0,0,1,1,0,0,1,0,1,0,0},
					 {0,1,0,0,1,1,0,0,1,1,1,0,0},
					 {0,0,0,0,0,0,0,0,0,0,1,0,0},
					 {0,0,0,0,0,0,0,1,1,1,0,0,0},
					 {0,0,0,0,0,0,0,1,1,0,0,0,0}};
		int[][] b = {{0,0},{1,1}};
		int[][] c = {{1,1}};
		int[][] d = {{1},{1}};
		MaxAreaOfIsland ma = new MaxAreaOfIsland();
		System.out.println(ma.maxAreaOfIsland(a));
		System.out.println(ma.maxAreaOfIsland(b));
		System.out.println(ma.maxAreaOfIsland(c));
		System.out.println(ma.maxAreaOfIsland(d));
	}
	
	/**
	 * 用邻接矩阵的做法，超时了！
	 * @param grid
	 * @return
	 */
	public int maxAreaOfIsland_matrix(int[][] grid) {
        int result = 0;
        if(grid == null)
        	return result;
        int x = grid.length;
        int y = grid[0].length;
        Graph graph = new Graph(x,y);
        if(x == 1 && y == 1) {
        	if(grid[0][0] == 1)
        		return 1;
        	return 0;
        } 
//        else if(x == 1) {
//        	int count = 0;
//        	for(int i = 0; i < y; i++) {
//        		if(grid[0][i] == 1)
//        			count++;
//        		else
//        			count = 0;
//        		result = Math.max(result, count);
//        	}
//        	return result;
//        } else if(y == 1) {
//        	int count = 0;
//        	for(int i = 0; i < x; i++) {
//        		if(grid[i][0] == 1)
//        			count++;
//        		else         			
//        			count = 0;
//        		result = Math.max(result, count);
//        	}
//        	return result;
//        }
        for(int i = 0; i < x; i++) {
        	for(int j = 0; j < y; j++) {
        		if(i < x-1 & j < y-1) {
        			if(grid[i][j] == 1 && grid[i+1][j] == 1)
            			graph.addEdge(i,j,i+1,j);
            		if(grid[i][j] == 1 && grid[i][j+1] == 1)
            			graph.addEdge(i,j,i,j+1);
        		} else if(j < y-1) {
        			if(grid[i][j] == 1 && grid[i][j+1] == 1)
            			graph.addEdge(i,j,i,j+1);
        		} else if(i < x-1) {
        			if(grid[i][j] == 1 && grid[i+1][j] == 1)
            			graph.addEdge(i,j,i+1,j);
        		}
        	}
        }
        for(int i = 0; i < x; i++) {
        	for(int j = 0; j < y; j++) {
        		if(grid[i][j] == 1 && graph.adjVertex[i][j].visited == false) {
        			int count = 1;
        			graph.adjVertex[i][j].visited = true;
        			Stack<Vertex> theStack = new Stack<Vertex>();
        			theStack.push(graph.adjVertex[i][j]);
        			while(!theStack.isEmpty()) {
        				Vertex v = getUnvisitedVertex(graph,theStack.peek());
        				if(v == null)
        					theStack.pop();
        				else {
        					graph.adjVertex[v.i][v.j].visited = true;
        					count++;
        					theStack.push(v);
        				}
        			}
        			result = Math.max(result, count);
        		}
        	}
        }
        return result;
    }
	
	private Vertex getUnvisitedVertex(Graph graph, Vertex vertex) {
		// TODO Auto-generated method stub
		for(int i = 0; i < graph.x; i++) 
			for(int j = 0; j < graph.y; j++)
				if(graph.adjMatrix[vertex.i][vertex.j][i][j] == 1 &&
						graph.adjVertex[i][j].visited == false)
					return graph.adjVertex[i][j];
		return null;
	}

	class Graph {
		int x, y;
		public int adjMatrix[][][][];
		public Vertex adjVertex[][];
		public Graph(int x, int y) {
			this.x = x;
			this.y = y;
			adjMatrix = new int[x][y][x][y];
			for(int i1 = 0; i1 < x; i1++)
				for(int i2 = 0; i2 < y; i2++)
					for(int i3 = 0; i3 < x; i3++)
						for(int i4 = 0; i4 < y; i4++)
							adjMatrix[i1][i2][i3][i4] = 0;
			adjVertex = new Vertex[x][y];
			for(int i = 0; i < x; i++) {
				for(int j = 0; j < y; j++)
					adjVertex[i][j] = new Vertex(i,j);
			}
		}
		public void addEdge(int i1,int j1, int i2, int j2) {
			adjMatrix[i1][j1][i2][j2] = 1;
			adjMatrix[i2][j2][i1][j1] = 1;
		}
	}
	
	class Vertex {
		public int i;
		public int j;
		public boolean visited;
		public Vertex(int i, int j) {
			this.i = i;
			this.j = j;
			this.visited = false;
		}
	}
	/**
	 * 同样的思路，更简单的写法
	 * 用时41ms
	 * 根本无需专门弄一个Graph类和一个Vertex类
	 * @param grid
	 * @return
	 */
	public int maxAreaOfIsland(int[][] grid) {
		int result = 0;
		if(grid == null)
			return result;
		boolean[][] seen = new boolean[grid.length][grid[0].length];
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[0].length; j++)
				result = Math.max(result, area(seen,grid,i,j,grid.length,grid[0].length));
		return result;
	}

	private int area(boolean[][] seen, int[][] grid, int i, int j, int length, int length2) {
		// TODO Auto-generated method stub
		if(i < 0 || i >= length || j < 0 || j >= length2 ||
				seen[i][j] || grid[i][j] == 0)
			return 0;
		seen[i][j] = true;
		return (1+area(seen,grid,i-1,j,grid.length,grid[0].length)+area(seen,grid,i+1,j,grid.length,grid[0].length)
					+area(seen,grid,i,j-1,grid.length,grid[0].length)+area(seen,grid,i,j+1,grid.length,grid[0].length));
	}

}
