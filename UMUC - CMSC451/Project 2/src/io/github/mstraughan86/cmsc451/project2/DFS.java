/*
 Michael Straughan
 CMSC 451
 Project 1 - HeapSort and QuickSort
 October 8, 2015
 */
package io.github.mstraughan86.cmsc451.project2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class DFS {

    static Graph graph;
    static LinkedHashMap<Vertex, String> color;
    static LinkedHashMap<Vertex, int[]> times;
    static LinkedHashMap<Vertex, Vertex> parent;
    static LinkedHashMap<Edge, String> edgeTypes;
    static ArrayList<ArrayList<Vertex>> cycles;
    static int time;
    static boolean hasCycle;

    private static void run(Graph g) {
        graph = g;
        color = new LinkedHashMap<Vertex, String>();
        times = new LinkedHashMap<Vertex, int[]>();  //
        parent = new LinkedHashMap<Vertex, Vertex>();
        edgeTypes = new LinkedHashMap<Edge, String>();
        cycles = new ArrayList<ArrayList<Vertex>>();
        time = 0;
        hasCycle = false;

        for (Vertex u : graph.vertices) {
            color.put(u, "White");
            parent.put(u, new Vertex("None"));
        }

        for (Vertex u : graph.vertices) {
            if (color.get(u).equals("White")) {
                visit(u);
            }
        }
    }

    private static void visit(Vertex u) {
        time = time + 1;
        times.put(u, new int[2]);
        times.get(u)[0] = time;
        color.put(u, "Gray");

        Set<Vertex> adjSet = u.getAdjencyList().keySet();
        for (Vertex v : adjSet) {
            if (color.get(v).equals("White")) {
                edgeTypes.put(graph.getEdge(u, v), "Tree");
                parent.put(v, u);
                visit(v);
            } else if (color.get(v).equals("Gray")) {
                if (graph.digraph) {
                    edgeTypes.put(graph.getEdge(u, v), "Back");
                    hasCycle = true;
                    findCycle(u, v);
                } else if (!parent.get(u).equals(v)) {
                    edgeTypes.put(graph.getEdge(u, v), "Back");
                    hasCycle = true;
                    findCycle(u, v);
                }
            } else if (color.get(v).equals("Black") && graph.digraph) {
                if (times.get(v)[0] < times.get(u)[0]) {
                    edgeTypes.put(graph.getEdge(u, v), "Cross");
                } else {
                    edgeTypes.put(graph.getEdge(u, v), "Forward");
                }
            }
        }
        color.put(u, "Black");
        time = time + 1;
        times.get(u)[1] = time;
    }

    public static void clasifyEdges(Graph g) {
        run(g);
        printEdgeInfo();
    }

    public static void detectCycles(Graph g) {
        run(g);
        
        System.out.println("Does this graph contain cycles?: " + hasCycle);
        
        System.out.println("\nJust a couple edges encountered:");
        for (ArrayList<Vertex> a : cycles) {
            System.out.print("{");
            for (Vertex v : a) {
                System.out.print(v.getLabel());
            }
            System.out.println("}");
        }
    }
    
    public static void findCycle(Vertex u, Vertex target) {
        ArrayList<Vertex> cycle = new ArrayList<>();
        cycle.add(target);
        cycle.add(u);
        getParentVertex(parent.get(u), target, cycle);
        cycles.add(cycle);
    }
    
    public static void getParentVertex(Vertex u, Vertex target, ArrayList cycle) {
        cycle.add(u);
        if (u.equals(target)){
            return;
        }
        getParentVertex(parent.get(u), target, cycle);
    }

    public static void printEdgeInfo() {
        for (Vertex v : graph.vertices) {
            System.out.println(
                    v.getLabel() + " is child of "
                    + parent.get(v).getLabel());
            for (Vertex u : v.adjacencyList.keySet()) {
                System.out.println("     - Has an edge to " + u.getLabel()
                        + " , whos edge is " + edgeTypes.get(graph.getEdge(v, u)) );
            }
        }
        System.out.println();
    }

}
