/*
 Michael Straughan
 CMSC 451
 Project 1 - HeapSort and QuickSort
 October 8, 2015
 */
package io.github.mstraughan86.cmsc451.project2;

import java.util.LinkedHashMap;
import java.util.Set;

public class GraphAlgorithms {

    static String[] vP1 = new String[]{"A", "B", "C", "D", "E", "F", "G"};
    static String[][] uvP1 = new String[][]{{"A", "D"}, {"A", "F"}, {"B", "A"},
    {"C", "A"}, {"C", "B"}, {"C", "D"}, {"C", "E"}, {"C", "F"},
    {"D", "B"}, {"D", "G"}, {"E", "G"}, {"F", "A"}, {"G", "E"}};

    static String[] vP2 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static String[][] uvP2 = new String[][]{{"1", "2"}, {"1", "10"}, {"2", "3"},
    {"3", "4"}, {"3", "6"}, {"3", "9"}, {"4", "5"}, {"5", "6"},
    {"6", "7"}, {"6", "9"}, {"7", "8"}, {"8", "9"}, {"9", "10"}};

    static String[] vP3 = new String[]{"1", "2", "3", "4", "5", "6", "7"};
    static String[][] uvP3 = new String[][]{{"1", "2"}, {"1", "6"}, {"2", "3"},
    {"2", "4"}, {"2", "5"}, {"3", "5"}, {"4", "5"}, {"5", "1"},
    {"6", "4"}, {"6", "7"}};

    static String[] vP4 = new String[]{"A", "B", "C", "D", "E", "F", "G"};
    static String[][] uvP4 = new String[][]{{"A", "B"}, {"A", "C"}, {"A", "F"},
    {"B", "C"}, {"B", "D"}, {"D", "A"}, {"D", "C"}, {"E", "C"},
    {"E", "G"}, {"F", "A"}, {"F", "C"}, {"G", "D"}, {"G", "E"}};

    public static void main(String[] args) {
        
        Graph graphP1 = populateGraph(vP1, uvP1, true);
        Graph graphP2 = populateGraph(vP2, uvP2, false);
        Graph graphP3 = populateGraph(vP3, uvP3, false);
        Graph graphP4 = populateGraph(vP4, uvP4, true);
        
        intro();
        
        problem1(graphP1);
        problem2(graphP2);
        problem3(graphP3);
        problem4(graphP4);
    }

    public static void intro() {
        System.out.println("Michael Straughan"
                + "\nCMSC 451"
                + "\nProject 2 - Graph Algorithms"
                + "\nOctober 8, 2015"
                + "\n\n"
                + "\n===== Graph Algorithms =====\n\n"
                + "\nThis program will implement Breadth-First Search and Depth-First Search"
                + "\nalgorithms and utilize them toward solving 4 unique problems.\n"
                + "\n- Problem 1: Classify the edges of a directed graph"
                + "\n- Problem 2: Find an Euler circuit in a graph"
                + "\n- Problem 3: Determine if an undirected graph has a cycle"
                + "\n- Problem 4: Determine if a directed graph has a cycle"
                + "\n\nThe program will execute to solve the 4 problem spaces and automatically"
                + "\nterminate.\n");
    }
    
    public static Graph populateGraph(String[] v, String[][] e, boolean b) {
        Graph g = new Graph(v, e, b);
        return g;
    }
    
    public static void displayGraph(Graph g) {
        System.out.println("Vertices: ");
        for (Vertex vertex : g.vertices) {
            System.out.print(vertex.getLabel() + " ");
        }
        System.out.println("\n\nEdges: ");
        for (Edge edge : g.edges) {
            System.out.print(edge.getEdgeString() + " ");
        }
        System.out.println("\n\nDigraph = " + g.digraph + "\n");
        
    }
    
    public static void problem1(Graph g) {
        System.out.println("\n----- Problem 1: Classify Edge Types -----"
                + "\n\nClassify the edges of a directed graph into the four categories: "
                + "\ntree edge, back edge, forward edge and cross edge.\n");
        
        displayGraph(g);
        DFS.clasifyEdges(g);
    }
    
    public static void problem2(Graph g) {
        System.out.println("\n----- Problem 2: Find Euler Circuit -----"
                + "\n\nFind an Euler circuit in an undirected graph, "
                + "\nor tell that the graph doesn’t have one.\n");
        
        displayGraph(g);
        EulerCircuit.findCircuit(g);
    }
    
    public static void problem3(Graph g) {
        System.out.println("\n----- Problem 3: Determine Undirected Cycle -----"
                + "\n\nUsing DFS, determine if an undirected graph has a cycle, "
                + "\nor tell that the graph doesn’t have one.\n");
        
        displayGraph(g);
        DFS.detectCycles(g);
    }
    
    public static void problem4(Graph g) {
        System.out.println("\n----- Problem 4: Determine Digraph Cycle -----"
                + "\n\nUsing BFS, determine if a directed graph has a cycle, "
                + "\nor tell that the graph doesn’t have one.\n");
        
        displayGraph(g);
        BFS.detectCycles(g);
    }
    
}
