package greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {
	
	public static void main(String[] args) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('a', 12);
		map.put('b', 2);
		map.put('c', 7);
		map.put('d', 13);
		map.put('e', 14);
		map.put('f', 85);
		HuffmanCoding huffmanCoding = new HuffmanCoding();
		TreeNode root = huffmanCoding.getTree(map);
		String cod = "";
		huffmanCoding.encode_dfs(root, cod);
		System.out.println("hahaha");
	}
	
	public TreeNode getTree(Map<Character, Integer> map) {
		
		if(map.isEmpty())
			return null;
		
		PriorityQueue<TreeNode> pq = new PriorityQueue<TreeNode>();
		
		Character[] keys = map.keySet().toArray(new Character[0]);
		for(Character c : keys) {
			TreeNode node = new TreeNode();
			node.setVal(c.toString());
			node.setFreq(map.get(c));
			pq.offer(node);
		}
		
		while(pq.size() > 1) {
			TreeNode node1 = pq.poll();
			TreeNode node2 = pq.poll();
			TreeNode node = new TreeNode();
			node.setLeft(node1);
			node.setRight(node2);
			node.setVal(node1.getVal() + node2.getVal());
			node.setFreq(node1.getFreq() + node2.getFreq());
			pq.offer(node);
		}
		
		TreeNode root = pq.poll();
		return root;
		
	}
	
	public void encode_dfs(TreeNode root, String cod) {
		if(root == null)
			return;
		root.setCoding(cod);
		encode_dfs(root.getLeft(),cod+"0");
		encode_dfs(root.getRight(),cod+"1");
	}

}
