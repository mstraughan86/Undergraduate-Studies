/*
Michael Straughan
CMSC 451
Project 1 - HeapSort and QuickSort
September 13, 2015
*/

package io.github.mstraughan86.cmsc451.project1;

public class HeapSort {

    private static int[] a;
    private static int n;
    private static int left;
    private static int right;
    private static int largest;

    private static int comparisons = 0;
    private static int assignments = 0;

    private static void buildheap(int[] a) {
        n = a.length - 1;
        assignments++;
        for (int i = n / 2; i >= 0; i--) {
            maxheap(a, i);
        }
    }

    private static void maxheap(int[] a, int i) {
        left = 2 * i;
        right = 2 * i + 1;
        assignments += 2;
        if (left <= n && a[left] > a[i]) {
            largest = left;
            assignments++;
        } else {
            largest = i;
            assignments++;
        }
        comparisons += 2;

        if (right <= n && a[right] > a[largest]) {
            largest = right;
            assignments++;
        }
        comparisons += 2;
        if (largest != i) {
            exchange(i, largest);
            maxheap(a, largest);
        }
        comparisons++;
    }

    private static void exchange(int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
        assignments += 3;
    }

    public static void sort(int[] a0) {
        a = a0;
        comparisons = 0;
        assignments = 0;

        buildheap(a);

        for (int i = n; i > 0; i--) {
            exchange(0, i);
            n = n - 1;
            assignments++;
            maxheap(a, 0);
        }
    }

    public static int getComparisons() {
        return comparisons;
    }

    public static int getAssignments() {
        return assignments;
    }
}
