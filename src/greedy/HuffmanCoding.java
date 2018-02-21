package greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 霍夫曼编码是典型的贪心问题
 * 贪心问题的思想是：在每一个阶段，选取当前状态下的最优决策，而不考虑对后续决策的影响(无后效性)
 * 最优贪心算法需满足两个基本性质：
 * 1.贪婪选择性质
 * 2.最优子结构
 * 贪婪选择性质：通过局部最优得到全局最优。
 * 			  局部最优解的选择可能依赖之前决策，而不是后续决策。
 *            通过迭代方式进行一个个贪婪选择，将原问题简化为规模更小的问题。
 * 最优子结构：如果原问题的最优解包含子问题的最优解，则认为该问题具有最优子结构。
 * 		    这意味着可以对子问题求解并构建规模更大问题的解。
 * 注意，并不是所有局部最优都可以推出全局最优，因而贪心算法并不是总能得到最优解
 */
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
