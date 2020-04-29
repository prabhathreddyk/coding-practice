package edu.code.process;

import static edu.code.process.utility.GraphUtility.*;
import edu.code.model.Graph;
import edu.code.model.GraphEdge;

public class GraphImplementation {

	public static void main(String[] args) {
		
		//Graph represented in Adjacency List
		int[][] edges = {{0,1},{1,2},{1,3},{4,5}};
		Graph graph = createAdjacencyList(edges, true);
		System.out.println("Number of connected graphs(Adjacency List) unDirected : "+getNumberOfConnectedComponents(graph));
		System.out.println("Number of connected graphs(Adjacency List) Directed: "+getNumberOfConnectedComponents(createAdjacencyList(edges, false)));
		
		//Shortest Distance & path
		int[][] edgesW = {{0,1,1},{1,2,4},{2,3,1},{0,3,3},{0,4,1},{4,3,1}};
		graph = createAdjacencyList(edgesW, true);
		System.out.println("Shortest distance : "+ getShortestDistance(graph, 0, 3));
		
		//Graph represented in Adjacency Matrix
		int[][] adjacencyMatrix = {{0,1,0,0,0,0},{1,0,1,1,0,0},{0,1,0,1,0,0},{0,1,0,0,0,0},{0,0,0,0,0,1},{0,0,0,0,1,0}};
		System.out.println("Number of connected graphs(Adjacency Matrix) unDirected : "+getNumberOfConnectedComponents(adjacencyMatrix));
		System.out.println("Shortest distance : "+ getShortestDistance(adjacencyMatrix, 0, 3));


	}

	private static Graph createAdjacencyList(int[][] edges, boolean isUnDirected) {
		Graph graph = new Graph(6);
		for (int[] edge : edges){
			addGraphEdge(graph, edge);
			if (isUnDirected){
				int[] unDirectedEdge = {edge[1], edge[0]};
				addGraphEdge(graph, unDirectedEdge);
			}
		}
		return graph;
	}

	private static void addGraphEdge(Graph graph, int[] edge) {
		GraphEdge graphEdge = new GraphEdge();
		graphEdge.setDestination(edge[1]);
		if (edge.length == 3){
			graphEdge.setWeight(edge[2]);
		}
		graph.addEdge(edge[0], graphEdge);
	}

}
