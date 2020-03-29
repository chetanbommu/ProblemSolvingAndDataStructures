package trees;

// Video Resource: https://www.youtube.com/watch?v=py3R23aAPCA
// Leetcode: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class LowestCommonAncestor {
	
	public static void main(String[] args) {
		Tree tree = new Tree(6);
		tree.insert(tree.getRoot(), 5);
		tree.insert(tree.getRoot(), 8);
		tree.insert(tree.getRoot(), 4);
		tree.insert(tree.getRoot(), 10);
		tree.insert(tree.getRoot(), 7);
		
				// 			  Tree
				//			    6 
				//			   / \
				//		   	  5	  8
				//			 /   / \
				//		   	4	7  10
		
		System.out.println("In Order");
		tree.printInOrder();
		
		BSTNode node1 = tree.searchNode(tree.getRoot(), 7);
		BSTNode node2 = tree.searchNode(tree.getRoot(), 10);
		BSTNode lca = leastCommonAncestor(tree.getRoot(), node1, node2); 
		System.out.println("LCA of 7, 10 : " + lca.data);
	}

	/** Two possible scenarios:
	 * 1. Each node is present on either side of root. Hence, root is the Least Common Ancestor.
	 * Ex: 7, 10.....LCA is 8
	 * 			 			  Tree
	 *						    6 
	 *						   / \
	 *					   	  5	  8
	 *						 /   / \
	 *					   	4	7  10
	 * 2. One node presents on left or right sub tree of another. Node at higher level is LCA.
	 * Ex: a) 8, 10.... LCA is 8
	 * 	   b) 4,5 ..... LCA is 5
	 *			 			  Tree
	 *						    6 
	 *						   / \
	 *					   	  5	  8
	 *						 /   / \
	 *					   	4	7  10
	 *
	 * */
	public static BSTNode leastCommonAncestor(BSTNode root, BSTNode node1, BSTNode node2) {
		
		if(root == null) {
			return null;
		} else if(root.data == node1.data || root.data == node2.data) {
			return root;
		}
		
		BSTNode left = leastCommonAncestor(root.left, node1, node2);
		BSTNode right = leastCommonAncestor(root.right, node1, node2);
		
		if(left != null && right != null) {
			return root;
		} else if(left == null) {
			return right;
		} else if(right == null) {
			return left;
		}
		return null;
	}
	
}
