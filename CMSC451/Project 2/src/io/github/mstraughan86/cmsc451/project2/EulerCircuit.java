/*
 Michael Straughan
 CMSC 451
 Project 1 - HeapSort and QuickSort
 October 8, 2015
 */
package io.github.mstraughan86.cmsc451.project2;

import java.util.ArrayList;
import java.util.LinkedList;

public class EulerCircuit {
    static boolean hasCircuit;
    static ArrayList<Vertex> eulerCycle;
    static LinkedList<Vertex> queue;

    public static void run(Graph g) {
        eulerCycle = new ArrayList<>();
        queue = new LinkedList<Vertex>();

        queue.add(g.vertices.get(0));
        Vertex current = queue.remove();
        do {
            if (current.adjacencyList.isEmpty()) {
                eulerCycle.add(current);
                current = queue.removeLast();
            } else {
                Vertex next = current.adjacencyList.entrySet().iterator().next().getKey();
                current.adjacencyList.remove(next);
                next.adjacencyList.remove(current);
                queue.add(current);
                current = next;
            } 
        } while(!queue.isEmpty());
        eulerCycle.add(current);
    }
    

    public static boolean checkDegrees(Graph g) {
        for (Vertex v : g.vertices) {
            if (!(v.adjacencyList.size() % 2 == 0)) {
                hasCircuit = false;
                return hasCircuit;
            }
        }
        hasCircuit = true;
        return hasCircuit;
    }
    
    public static void findCircuit(Graph g) {
        if (!checkDegrees(g)) {
            System.out.println("No Euler Circuit Exists!");
            return;
        }
        run(g);
        
        System.out.println("Euler Circuit Found! :");
        System.out.print("{ ");
        for (Vertex v : eulerCycle) {
            System.out.print(v.getLabel() + " ");
        }
        System.out.println(" }");
    }
    
}
