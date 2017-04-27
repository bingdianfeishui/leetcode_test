package algorithme.q0104;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

public class Q104MaximumDepthOfBinaryTree {

	// Definition for a binary tree node.
	public static class TreeNode {
		int val;
		public TreeNode left;
		public TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		int times = 100000;
		Object[] argsArr = new Object[] { getNode(5, 5), null, new TreeNode(0) };

		TimerUtils.batchRunAll(Q104MaximumDepthOfBinaryTree.class, times, argsArr);
//		Object ret = TimerUtils.run(Q104MaximumDepthOfBinaryTree.class, "maxDepth", 1, new Object[]{getNode(5, 2)});
//		System.out.println(ret);
	}

	private static TreeNode getNode(int leftMax, int rightMax) {
//		TreeNode root = new TreeNode(0);
//
//		TreeNode n1 = new TreeNode(1);
//		TreeNode n2 = new TreeNode(2);
//		TreeNode n3 = new TreeNode(3);
//		TreeNode n4 = new TreeNode(4);
//		TreeNode n5 = new TreeNode(5);
//		TreeNode n6 = new TreeNode(6);
//		TreeNode n7 = new TreeNode(7);
//		TreeNode n8 = new TreeNode(8);
//		TreeNode n9 = new TreeNode(9);
//
//		root.left = n1;
//		n1.left = n2;
//		n1.right = n3;
//		n2.right = n4;
//		
//		root.right = n5;
//		n5.right = n6;
//		n6.left = n7;
//		n6.right = n8;
//		n8.right = n9;
//
//		return root;
		 TreeNode root = new TreeNode(0);
		 Random random = new Random(System.nanoTime());
		
		 TreeNode left = new TreeNode(1);
		 root.left = left;
		 TreeNode right = new TreeNode(1);
		 root.right = right;
		
		 int leftCount = random.nextInt(leftMax);
		 int rightCount = random.nextInt(rightMax);
		
		 for (int l = 0; l < leftCount; l++) {
		 for (int r = 0; r < rightCount; r++) {
		 TreeNode child = new TreeNode(l + r);
		 if (random.nextBoolean()) {
		 if(random.nextBoolean()){
		 left.left = new TreeNode(l+r);
		 left.right = child;
		 left = left.left;
		 }else{
		 left.left = child;
		 left = child;
		 }
		 } else {
		 right.right = child;
		 right = child;
		 }
		 }
		
		 }
		 return root;
	}

	// 递归DFS
	@RunTimer
	public int maxDepth1(TreeNode root) {
		if (root == null)
			return 0;
//		System.out.println(root.val);
		if (root.left == null && root.right == null)
			return 1;
		return 1 + Math.max(maxDepth1(root.left), maxDepth1(root.right));
	}

	@RunTimer
	public int maxDepth2(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;
		int max = 0;
		Stack<TreeNode> stack = new Stack<>();
		Stack<Integer> level = new Stack<>();	//用来记录stack中每个node的深度
		stack.push(root);
		level.push(1);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			int temp = level.pop();
			max = Math.max(temp, max);
			if (node.left != null) {
				stack.push(node.left);
				level.push(temp + 1);
			}
			if (node.right != null) {
				stack.push(node.right);
				level.push(temp + 1);
			}
		}
		return max;
	}

	// BFS
	@RunTimer
	public int maxDepth3(TreeNode root) {
		if (root == null)
			return 0;

		int depth = 0, curSize = 0;
		Queue<TreeNode> que = new LinkedList<>();
		TreeNode node = root;
		que.offer(node);
		while (!que.isEmpty()) {
			curSize = que.size();
			for (int i = 0; i < curSize; i++) {
				node = que.poll();
				if (node.left != null) {
					que.offer(node.left);
				}
				if (node.right != null) {
					que.offer(node.right);
				}
			}
			depth++;
		}
		return depth;
	}
	
	//BFS2
	@RunTimer
	public int maxDepth4(TreeNode root) {
	    if(root == null) {
	        return 0;
	    }
	    Queue<TreeNode> queue = new LinkedList<>();
	    queue.offer(root);
	    int count = 0;
	    while(!queue.isEmpty()) {
	        int size = queue.size();
	        while(size-- > 0) {
	            TreeNode node = queue.poll();
	            if(node.left != null) {
	                queue.offer(node.left);
	            }
	            if(node.right != null) {
	                queue.offer(node.right);
	            }
	        }
	        count++;
	    }
	    return count;
	}
}
