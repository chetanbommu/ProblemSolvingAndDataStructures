package graphs;

/*
 * Driver class to test BFS and DFS Algorithms
 * */
public class GraphDriver {
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(3, 4);
		graph.addEdge(3, 6);
		graph.addEdge(4, 7);
		graph.addEdge(4, 8);
		graph.addEdge(2, 5);
		graph.addEdge(2, 9);
		graph.addEdge(9, 10);
		graph.addEdge(10, 7);
		graph.addEdge(10, 11);
		
		graph.addEdge(12, 14);
		graph.addEdge(12, 13);
		
		System.out.println(graph.hasPathBFS(2, 7));
		System.out.println(graph.hasPathBFS(1, 11));
		System.out.println(graph.hasPathBFS(4, 10));
		
		System.out.println(graph.hasPathDFS(2, 7));
		System.out.println(graph.hasPathDFS(1, 11));
		System.out.println(graph.hasPathDFS(4, 10));
		
		System.out.println(graph.hasPathBFS(12, 1));
		System.out.println(graph.hasPathBFS(12, 1));
		
		System.out.println(graph.hasPathDFS(12, 1));
		System.out.println(graph.hasPathDFS(12, 1));
	}
}
