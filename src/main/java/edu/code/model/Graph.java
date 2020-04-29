package edu.code.model;

import java.util.LinkedList;

public class Graph {

	private int vertices;
	private LinkedList<GraphEdge>[] nodeList;
	
	public Graph(int vertices){
		this.vertices= vertices;
		nodeList = new LinkedList[vertices];
	}
	
	public void addEdge(int source, GraphEdge edge){
		LinkedList<GraphEdge> edges = nodeList[source];
		if (edges == null){
			edges = new LinkedList<GraphEdge>();
			nodeList[source]= edges;
		}
		edges.add(edge);
	}
	
	public int getVertices() {
		return vertices;
	}
	public void setVertices(int vertices) {
		this.vertices = vertices;
	}
	public LinkedList<GraphEdge>[] getNodeList() {
		return nodeList;
	}
	public void setNodeList(LinkedList<GraphEdge>[] nodeList) {
		this.nodeList = nodeList;
	}
	
	
}
