/*
 Michael Straughan
 CMSC 451
 Project 1 - HeapSort and QuickSort
 October 8, 2015
 */
package io.github.mstraughan86.cmsc451.project2;

public class Edge<source, dest> {

    public final int weight;
    public final Vertex source;
    public final Vertex dest;

    Edge(Vertex source, Vertex dest, int weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }
    
    public Vertex getSource() {
        return source;
    }
    
    public Vertex getDest() {
        return dest;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public String getEdgeString() {
        return "(" + source.getLabel() + ", " + dest.getLabel() + ")";
    }
    
    @Override
    public int hashCode() { 
        return source.hashCode() ^ dest.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Edge) ) {
            return false;
        }
        Edge edge = (Edge) obj;
        if (this.source.equals(edge.source)) {
            if (this.dest.equals(edge.dest))
                return true;
        }
        return false;
    }

}
