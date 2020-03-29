package trees;

import trees.BSTNode;
import trees.Tree;

public class TreeDriver {
	
	public static void main(String[] args) {
		Tree tree = new Tree(6);
		tree.insert(tree.getRoot(), 8);
		tree.insert(tree.getRoot(), 4);
		tree.insert(tree.getRoot(), 10);
		tree.insert(tree.getRoot(), 5);
		tree.insert(tree.getRoot(), 7);
		
				// 			  Tree
				//			    6 
				//			   / \
				//		   	  4	  8
				//			 /   / \
				//		   	5	7  10
		
		System.out.println("In Order");
		tree.printInOrder();
		
		System.out.println("Pre Order");
		tree.printPreOrder();
		
		System.out.println("Post Order");
		tree.printPostOrder();
		
		System.out.println("Does 7 present in Tree : " + tree.search(7));
		System.out.println("Does 20 present in Tree : " + tree.search(20));
		
		System.out.println("Minimum value in tree : " + tree.findMinimum());
		System.out.println("Maximum value in tree : " + tree.findMaximum());
		
		System.out.println("Height of Tree : " + tree.findHeight());
		
		System.out.println("Find a Node with value 6 : " + (tree.searchNode(tree.getRoot(), 6) != null ? "Found" : "Not Found"));
		System.out.println("Find a Node with value 13 : " + (tree.searchNode(tree.getRoot(), 13) != null ? "Found" : "Not Found"));
		
		BSTNode successor = tree.findInOrderSuccessor(tree.getRoot(), 7);
		System.out.println("Find InOrder Ancestor of Node 7 : " +  (successor != null ? successor.data : "Not Found"));
		successor = tree.findInOrderSuccessor(tree.getRoot(), 24);
		System.out.println("Find InOrder Ancestor of Node 24 : " +  (successor != null ? successor.data : "Not Found"));

		BSTNode predecessor = tree.findInOrderPredecessor(tree.getRoot(), 7);
		System.out.println("Find InOrder Predecessor of Node 7 : " +  (predecessor != null ? predecessor.data : "Not Found"));
		
		
		System.out.println("In Order");
		tree.printInOrder();

		tree.deleteNode(7);
		System.out.println("In Order");
		tree.printInOrder();
		
		tree.deleteNode(5);
		System.out.println("In Order");
		tree.printInOrder();
		
	}
}
