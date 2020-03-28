package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;;

public class Graph {
	
	// Lookup to store id->Node mapping
	private HashMap<Integer, Node> nodeLookUp = new HashMap<>();
	
	public static class Node{
		private int id; // Node id
		LinkedList<Node> adjacent = new LinkedList<Node>();
		
		public Node(int id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}
	}
	
	/* Takes node id and returns Node if exists
	 * Else throw exception
	 */
	public Node getNode(int id) {
		if(nodeLookUp.containsKey(id)) {
			return nodeLookUp.get(id);	
		}
		throw new IllegalArgumentException("No node exists with id: " + id);
	}
	
	/*
	 * Adds an edge from source to destination.
	 * */
	public void addEdge(int source, int destination) {
		
		// If source Node is not found in lookup, create a Node and map id -> Node 
		if(!nodeLookUp.containsKey(source)) {
			nodeLookUp.put(source, new Node(source));
		}
		
		// If destination Node is not found in lookup, create a Node and map id -> Node
		if(!nodeLookUp.containsKey(destination)) {
			nodeLookUp.put(destination, new Node(destination));
		}
		
		Node src = getNode(source);
		Node dest = getNode(destination);
		
		src.adjacent.add(dest); // Adds an edge from source to destination
		dest.adjacent.add(src); // Adds an edge from destination to source.
	}
	
	/* Return true if source has a path to destination following DFS Algorithm
	 * 
	 * Note: DFS follows LIFO approach.
	 * */
	public boolean hasPathDFS(int source, int destination) {
		/* In DFS, We need to keep track of visited nodes, 
		   so that we won't visit same node again
		 * */ 
		HashSet<Integer> visited = new HashSet<>();
		return hasPathDFS(getNode(source), getNode(destination), visited);
	}
	
	private boolean hasPathDFS(Node source, Node destination, HashSet<Integer> visited) {
		if(visited.contains(source.id)) {
			return false;
		}
		visited.add(source.id);
		if(source == destination) {
			return true;
		}
		for(Node children: source.adjacent) {
			if(hasPathDFS(children, destination, visited)) {
				return true;
			}
		}
		return false;
	}
	
	/* Return true if source has a path to destination following BFS algorithm
	 * 
	 * Note: BFS follow FIFO approach.
	 * */
	public boolean hasPathBFS(int source, int destination) {
		return hasPathBFS(getNode(source), getNode(destination));
	}
	
	private boolean hasPathBFS(Node source, Node destination) {
		// Maintains a List of nodes those need to be visited next.
		LinkedList<Node> nextToVisit = new LinkedList<>();

		// Maintains a set of Visited nodes
		HashSet<Integer> visited = new HashSet<>();
		
		nextToVisit.add(source);
		
		while(!nextToVisit.isEmpty()) {
			Node currentNode = nextToVisit.remove();
			if(visited.contains(currentNode.id)) {
				continue;
			}
			if(currentNode == destination) {
				return true;
			}
			
			visited.add(currentNode.id);
			
			for(Node children: currentNode.adjacent) {
				nextToVisit.add(children);
			}
		}
		return false;
	}
}
