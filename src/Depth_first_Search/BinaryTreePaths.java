package Depth_first_Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreePaths {
	
	public static void main(String[] args) {
		BinaryTreePaths btp = new BinaryTreePaths();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(5);
		List<String> result = btp.binaryTreePaths_dfs(root);
		for(int i = 0; i < result.size(); i++)
			System.out.println(result.get(i));
	}
	
	/**
	 * 先根遍历解法
	 * 用时17ms
	 * @param root
	 * @return
	 */
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<String>();
        if(root == null)
        	return result;
        getPaths(root,Integer.toString(root.val),result);
        return result;
        
	}
	
	private void getPaths(TreeNode root, String cur_path, List<String> result) {
		// TODO Auto-generated method stub
		if(root.left == null && root.right == null)
			result.add(cur_path);
		if(root.left != null) {
			getPaths(root.left,cur_path+"->"+Integer.toString(root.left.val),result);
		}
		if(root.right != null) {
			getPaths(root.right,cur_path+"->"+Integer.toString(root.right.val),result);
		}
	}

	/**
	 * dfs解法
	 * 用时18ms
	 * @param root
	 * @return
	 */
	public List<String> binaryTreePaths_dfs(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if(root == null)
        	return result;
        Stack<TreeNode> theStack = new Stack<TreeNode>();
        theStack.push(root);
        Stack<String> paths = new Stack<String>();
        paths.push(Integer.toString(root.val));
        while(!theStack.isEmpty()) {
        	TreeNode cur = theStack.pop();
        	String cur_path = paths.pop();
        	if(cur.left != null) {
        		theStack.push(cur.left);
        		paths.push(cur_path+"->"+Integer.toString(cur.left.val));
        	}
        	if(cur.right != null) {
        		theStack.push(cur.right);
           		paths.push(cur_path+"->"+Integer.toString(cur.right.val));
        	}
        	if(cur.left == null && cur.right == null)
        		result.add(cur_path);
        }
        return result;
    }
	
	/**
	 * bfs解法
	 * 耗时19ms！！
	 * 话说为毛还要再加一个队列？！
	 * @param root
	 * @return
	 */
	public List<String> binaryTreePaths_bfs(TreeNode root) {
		List<String> result = new ArrayList<String>();
        if(root == null)
        	return result;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        Queue<String> paths = new LinkedList<String>();
        paths.offer(Integer.toString(root.val));
        while(!q.isEmpty()) {
        	int size = q.size();
        	while(size-- > 0) {
        		TreeNode cur = q.poll();
        		String cur_path = paths.poll();
        		if(cur.left != null) {
            		q.offer(cur.left);
            		paths.offer(cur_path+"->"+Integer.toString(cur.left.val));
            	}
            	if(cur.right != null) {
            		q.offer(cur.right);
               		paths.offer(cur_path+"->"+Integer.toString(cur.right.val));
            	}
            	if(cur.left == null && cur.right == null)
            		result.add(cur_path);
        	}
        }
        return result;
	}

}
