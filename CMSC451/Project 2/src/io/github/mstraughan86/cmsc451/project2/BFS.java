/*
 Michael Straughan
 CMSC 451
 Project 1 - HeapSort and QuickSort
 October 8, 2015
 */
package io.github.mstraughan86.cmsc451.project2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {
    static Queue<Vertex> queue;
    static LinkedHashMap<Vertex, String> color;
    static LinkedHashMap<Vertex, Integer> distance;
    static LinkedHashMap<Vertex, Vertex> parent;
    static ArrayList<ArrayList<Vertex>> cycles;
    static boolean hasCycle;
    
    public static void run(Graph graph, Vertex source) {
        queue = new LinkedList<Vertex>();
        color = new LinkedHashMap<Vertex, String>();
        distance = new LinkedHashMap<Vertex, Integer>();
        parent = new LinkedHashMap<Vertex, Vertex>();
        cycles = new ArrayList<ArrayList<Vertex>>();
        hasCycle = false;
        
        for (Vertex u : graph.vertices) {
            if (u.getLabel().equals(source.getLabel())) {
                color.put(u, "Gray");
                distance.put(u, 0);
                parent.put(u, new Vertex("None"));
                continue;
            }
            color.put(u, "White");
            distance.put(u, Integer.MAX_VALUE);
            parent.put(u, new Vertex("None"));
        }
        
        queue.add(source);
        
        while(!queue.isEmpty()) {
            Vertex u = queue.remove();
            Set<Vertex> adjSet = u.getAdjencyList().keySet();
            for (Vertex v : adjSet) {
                if (color.get(v).equals("White")) {
                    color.put(v, "Gray");
                    distance.put(v, distance.get(u) + 1);
                    parent.put(v, u);
                    queue.add(v);
                } else {
                    findCycle(u, v);
                }
            }
            color.put(u, "Black");
        }
    }
    
    public static void detectCycles(Graph g) {
        run(g, g.vertices.get(0));
        
        if (!cycles.isEmpty()) {
            hasCycle = true;
        }
        
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
        if (!cycle.get(cycle.size()-1).getLabel().equals("None")) {
            cycles.add(cycle);
        }
    }
    
    public static void getParentVertex(Vertex u, Vertex target, ArrayList cycle) {
        cycle.add(u);
        if (u.equals(target)){
            return;
        }
        
        if (parent.get(u).getLabel().equals("None")){
            cycle.add(parent.get(u));
            return;
        }
    
        getParentVertex(parent.get(u), target, cycle);
    }
    
}