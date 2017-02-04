/*
 Michael Straughan
 CMSC 451
 Project 1 - HeapSort and QuickSort
 October 8, 2015
 */
package io.github.mstraughan86.cmsc451.project2;

import java.util.ArrayList;

public class Graph {

    ArrayList<Vertex> vertices;
    ArrayList<Edge<Vertex,Vertex>> edges;
    boolean digraph;

    Graph(String[] v, String[][] uv, boolean digraph) {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.digraph = digraph;
        
        for (String vertex : v) {
            addVertices(vertex);
        }

        for (String[] edge : uv) {
            addEdges(edge);
        }
    }

    public void addVertices(String u) {
        vertices.add(new Vertex(u));
    }

    public void addEdges(String[] e) {
        Edge temp;
        for (Vertex sourceVertex : vertices) {
            if (sourceVertex.getLabel().equals(e[0])) {
                for (Vertex destVertex : vertices) {
                    if (destVertex.getLabel().equals(e[1])) {
                        if (e.length > 2) {
                            temp = new Edge(sourceVertex, destVertex, Integer.parseInt(e[2]));
                            edges.add(temp);
                        } else {
                            temp = new Edge(sourceVertex, destVertex, 0);
                            edges.add(temp);
                        }
                        sourceVertex.addEdge(temp);
                        if (!this.digraph) {
                            destVertex.addEdge(temp);
                        }
                    }
                }
            }
        }
    }

    public Vertex getVertex(String str) {
        for (Vertex v : vertices) {
            if (v.label.equals(str)) {
                return v;
            }
        }
        return null;
    }
    
    public Edge getEdge(Vertex u, Vertex v) {
        for (Edge e : edges) {
            if (e.getSource().equals(u) && e.getDest().equals(v)) {
                return e;
            } else if (!digraph && e.getSource().equals(v) && e.getDest().equals(u)) {
                return e;
            }
        }
        return null;
    }
    
}
