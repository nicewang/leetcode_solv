package shortest_path;

public class Vertex implements Comparable<Vertex> {
	
	public int val;
	public int priority;
	
	public Vertex(int val, int prior) {
		this.val = val;
		this.priority = prior;
	}
	
	@Override
	public int compareTo(Vertex o) {
		// TODO Auto-generated method stub
		return priority - o.priority;
	}

}
