package trees;

import java.util.LinkedList;

import trees.BSTNode;

/*
 * 1. Tree is a recursive Data Structure.
 * 2. N nodes => N-1 edges.
 * 3. Depth of Node 'x' : Length of Path from Root to 'x' 
 *   (or) No of edges in path from root to 'x'.
 * 4. Height of Node 'x' : No. of edges in longest path from 'x' to leaf node.
 * 
 * Applications:
 * 1. Storing naturally Hierarchical Data. Ex: File System
 * 2. Organize data for Quick Search, Insertion & Deletion. Ex: Binary Search Trees
 * 3. Dictionary. Ex: Tries
 * 4. Network Routing Algorithm.
 * 
 * Binary Tree:
 * Each Node can have atmost two children.
 * Strict/Proper Binary Tree: Each Node either have 2 or 0 children.
 * Complete Binary Tree: All levels except last are completely filled. (Min Height : log n base 2 & Max Height: n-1(skewed tree))
 * Perfect Binary Tree: All levels are completely filled except leaf nodes.
 * Maximum no of nodes in a tree with height h: 2^No.of levels - 1 (or) 2^(h+1) - 1.
 * Balanced Binary Tree: Difference between height of left and right subtree for every node is not more than k(mostly 1).
 * 
 * Implementation:
 * --------------
 * 1. Dynamically created Nodes.
 * 2. Arrays (Used for Heaps).
 * 		For Node at Index i:
 * 		i.  Left child Index: 2i + 1
 * 		ii. Right child Index: 2i + 2 
 * 
 * Binary Search Tree: A Binary tree in which for each node, value of all nodes in left subtrees
 * is lesser or equal and values of all the nodes in right subtree is greater.
 * 
 * Tree Traversals:
 * ----------------
 * 1. Bread First Search: 
 * 		a. Level Order Traversal
 * 2. Depth First Search:
 * 		a. InOrder Traversal
 * 		b. PreOrder Traversal
 * 		c. PostOrder Traversal
 * */

public class Tree {
	
	private BSTNode root;
	
	public Tree(int data) {
		BSTNode root = new BSTNode(data);
		this.setRoot(root);
	}

	public BSTNode getRoot() {
		return root;
	}

	private void setRoot(BSTNode root) {
		this.root = root;
	}
	
	public BSTNode getNewNode(int data) {
		BSTNode newNode = new BSTNode(data);
		return newNode;
	}
	
	
	public void insert(BSTNode root, int data) {
		BSTNode temp = root;
		
		if(data <= temp.data) {
			if(temp.left == null) {
				temp.left = getNewNode(data);
			} else {
				insert(temp.left, data);
			}
		}
		else if(data > temp.data) {
			if(temp.right == null) {
				temp.right = getNewNode(data);
			} else {
				insert(temp.right, data);	
			}
		}
	}

	
	public boolean search(int data) {
		return search(root, data);
	}
	
	
	public boolean search(BSTNode root, int data) {
		if(root == null) {
			return false;
		} else if(root.data == data) {
			return true;
		} else if(root.data > data) {
			return search(root.left, data);
		} else {
			return search(root.right, data);
		}
	}
	
	public BSTNode searchNode(BSTNode root, int data) {
		if(root == null) {
			return null;
		} else if(root.data == data) {
			return root;
		} else if(root.data > data) {
			return searchNode(root.left, data);
		} else {
			return searchNode(root.right, data);
		}
	}
	
	
	public void printInOrder() {
		printInOrder(root);
		System.out.println();
	}
	

	private void printInOrder(BSTNode root) {
		if(root.left != null) {
			printInOrder(root.left);
		}
		
		System.out.print(root.data + " ");
		
		if(root.right != null) {
			printInOrder(root.right);
		}
		
	}
	
	public void printPreOrder() {
		printPreOrder(root);
		System.out.println();
	}

	private void printPreOrder(BSTNode root) {
		
		System.out.print(root.data + " ");
		
		if(root.left != null) {
			printPreOrder(root.left);
		}
		
		if(root.right != null) {
			printPreOrder(root.right);
		}
		
	}
	
	public void printPostOrder() {
		printPostOrder(root);
		System.out.println();
	}

	private void printPostOrder(BSTNode root) {
		if(root.left != null) {
			printPostOrder(root.left);
		}
		
		if(root.right != null) {
			printPostOrder(root.right);
		}
		
		System.out.print(root.data + " ");
	}
	
	public void printLevelOrder() {
		printLevelOrder(root);
		System.out.println();
	}
	
	private void printLevelOrder(BSTNode root) {
		if(root == null) {
			return;
		}
		
		LinkedList<BSTNode> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			BSTNode node = queue.poll();
			System.out.print(node.data + " ");
			if(node.left != null) {
				queue.add(node.left);
			}
			if(node.right != null) {
				queue.add(node.right);
			}
		}
	}
	
	public BSTNode findMinimum() {
		return findMinimum(root);
	}
	
	public BSTNode findMinimum(BSTNode node) {
		if(root == null) {
			return null;
		} 

		BSTNode temp = root;
		while(temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}
	
	public BSTNode findMaximum() {
		if(root == null) {
			return null;
		} 

		BSTNode temp = root;
		while(temp.right != null) {
			temp = temp.right;
		}
		return temp;
	}
	
	public int findHeight() {
		return findHeight(root);
	}
	
	public int findHeight(BSTNode node) {
		
		if(node == null) {
			return -1;
		}
		
		int leftHeight = findHeight(node.left);
		int rightHeight = findHeight(node.right);
		
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	// data: Node value for which we need to find next ancesstor.
	public BSTNode findInOrderSuccessor(BSTNode node, int data) {
		BSTNode targetNode = searchNode(node, data);
		if(targetNode == null)
			return null;
		
		/** case-1 :: Node has right sub-tree
		 *  solution : Go deep to leftmost node in right subtree 
		 *  	(or) Find min in right subtree
		 */
		if(targetNode.right != null) {
			/*
			BSTNode temp = current.right;
			while(temp.left != null) {
				temp = temp.left;
			}
			return temp;
			*/
			return findMinimum(targetNode.right);
		}
		
		
		/** case-2 :: No right sub-tree
		 *  solution : Go to the nearest ancestor for which given node would be in left subtree.
		 * */
		else {
			BSTNode successor = null;
			BSTNode tempNode = root;
			while(tempNode != targetNode) {
				if(targetNode.data < tempNode.data) {
					successor = tempNode;
					tempNode = tempNode.left;
				}
				else {
					tempNode = tempNode.right;
				}
			}
			return successor;
		}
	}
	
	// data: Node value for which we need to find next predecessor.
	public BSTNode findInOrderPredecessor(BSTNode root, int data) {
		BSTNode targetNode = searchNode(root, data);
		if(targetNode == null) {
			return null;
		}
		
		BSTNode predecessor = null;
		BSTNode tempNode = root;
		while(tempNode != targetNode) {
			if(tempNode.data > data) {
				tempNode = tempNode.left;
			} else {
				predecessor = tempNode;
				tempNode = tempNode.right;
			}
		}
		if(tempNode != null && tempNode.left != null) {
			tempNode = findMinimum(tempNode.left);
		}
		return predecessor;
	}
	
	public void deleteNode(int data) {
		root = deleteNode(root, data);
	}
	
	/** Data: Node that needs to be deleted.
	 * We may encounter the following three different scenarios.
	 * Case-1 : No Child
	 * Case-2 : 1 Child
	 * Case-3: Two Child
	 * 		Approach - a:
	 * 			i . Find Min in Right sub-tree
	 * 			ii. Copy the value into target node.
	 * 			iii.Delete duplicate from Right Sub-tree
	 * 		Approach - b:
	 * 			i.	Find Max in Left sub-tree
	 * 	 		ii. Copy the value into target node.
	 *  		iii.Delete duplicate from Left Sub-tree
	 * */
	private BSTNode deleteNode(BSTNode root, int data){
		BSTNode tempNode = root;
		if(root == null) {
			return null;
		} else if(data < tempNode.data) {
			tempNode.left = deleteNode(tempNode.left, data);
		} else if(data > tempNode.data){
			tempNode.right = deleteNode(tempNode.right, data);
		} else {
			/* Case 1 : No Child */
			if(tempNode.left == null && tempNode.right == null) {
				tempNode = null;
				return tempNode;
			}

			/* Case 2 : One Child */
			else if(tempNode.left == null) {
				return tempNode.right;
			} else if(tempNode.right == null) {
				return tempNode.left;
			}

			/* Case-3 : Two Child */
			else {
				/*
				BSTNode substitueNode = findMinimum(tempNode.right);
				tempNode.data = substitueNode.data;
				tempNode.right = deleteNode(tempNode.right, data);
				*/
				tempNode.data = findMinimum(tempNode.right).data;
				tempNode.right = deleteNode(tempNode.right, tempNode.data);
			}
		}
		return tempNode;
	}
	
	/** Using recursive approach for checking if Binary Tree is a Binary Search Tree.
	 * */
	public boolean isBinarySearchTree(BSTNode root) {
		if(root == null) {
			return true;
		}
		
		if(isSubTreeLesser(root.left, root.data) &&
				isSubTreeGreater(root.right, root.data) &&
				isBinarySearchTree(root.left) &&
				isBinarySearchTree(root.right)) {
			return true;
		}
		return false;
	}
	
	public boolean isSubTreeLesser(BSTNode node, int data) {
		if(node == null) {
			return true;
		}
		
		if(root.data < data && isSubTreeLesser(root.left, data) &&
				isSubTreeLesser(node.right, data)) {
			return true;
		}
		return false;
	}
	
	public boolean isSubTreeGreater(BSTNode node, int data) {
		if(node == null) {
			return true;
		}
		
		if(root.data > data && isSubTreeGreater(node.left, data) && 
				isSubTreeGreater(node.right, data)) {
			return true;
		}
		return false;
	}
	
	/** Using Limit based approach for checking if Binary Tree is a Binary Search Tree
	 */
	public boolean isBST(BSTNode root) {
		return isBinarySearchTreeUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isBinarySearchTreeUtil(BSTNode node, int minValue, int maxValue) {
		if(node == null)
			return true;
		
		if(node.data > minValue && node.data < maxValue 
				&& isBinarySearchTreeUtil(node.left, minValue, node.data)
				&& isBinarySearchTreeUtil(node.right, node.data, maxValue)) {
			return true;
		}
		return false;
	}
}
