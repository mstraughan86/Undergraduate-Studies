/*
 Michael Straughan
 CMSC 451
 Project 1 - HeapSort and QuickSort
 October 8, 2015
 */
package io.github.mstraughan86.cmsc451.project2;

import java.util.LinkedHashMap;

public class Vertex {

    String label;
    LinkedHashMap<Vertex, Integer> adjacencyList;

    Vertex(String l) {
        this.label = l;
        this.adjacencyList = new LinkedHashMap<Vertex, Integer>();
    }

    public void addEdge(Edge e) {
        if (this.label.equals(e.getSource().getLabel())) {
            this.adjacencyList.put(e.getDest(), e.getWeight());
        } else {
            this.adjacencyList.put(e.getSource(), e.getWeight());
        }
    }

    public String getLabel() {
        return this.label;
    } 

    public LinkedHashMap getAdjencyList() {
        return this.adjacencyList;
    }

}
