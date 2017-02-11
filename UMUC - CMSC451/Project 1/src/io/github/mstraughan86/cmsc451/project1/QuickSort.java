/*
Michael Straughan
CMSC 451
Project 1 - HeapSort and QuickSort
September 13, 2015
*/

package io.github.mstraughan86.cmsc451.project1;

public class QuickSort {

    private static int[] a;

    private static int comparisons = 0;
    private static int assignments = 0;

    private static void quicksort(int[] a, int left, int right) {
        // Select a pivot! This implementation is choosing the middle array element
        // of the supplied subarray.
        // The easiest performance gain could easily be gained by having a more
        // intelligent pivot selection algorithm.
        int pivot = a[left + (right - left) / 2];
        int i = left;
        int j = right;
        assignments += 3;

        while (i <= j) {
            comparisons++;
            while (a[i] < pivot) {
                comparisons++;
                i++;
                assignments++;
            }
            comparisons++;
            while (a[j] > pivot) {
                comparisons++;
                j--;
                assignments++;
            }
            comparisons++;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
                assignments += 2;
            }
            comparisons++;
        }
        comparisons++;

        if (left < j) {
            quicksort(a, left, j);
        }
        comparisons++;
        if (i < right) {
            quicksort(a, i, right);
        }
        comparisons++;
    }

    private static void quicksortWorstCase(int[] a, int left, int right) {
        // Select a pivot! This implementation is choosing the left array element
        // of the supplied subarray.
        int pivot = a[left];
        int i = left;
        int j = right;
        assignments += 3;

        while (i <= j) {
            comparisons++;
            while (a[i] < pivot) {
                comparisons++;
                i++;
                assignments++;
            }
            comparisons++;
            while (a[j] > pivot) {
                comparisons++;
                j--;
                assignments++;
            }
            comparisons++;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
                assignments += 2;
            }
            comparisons++;
        }
        comparisons++;

        if (left < j) {
            quicksortWorstCase(a, left, j);
        }
        comparisons++;
        if (i < right) {
            quicksortWorstCase(a, i, right);
        }
        comparisons++;
    }

    private static void swap(int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        assignments += 3;
    }

    public static void sort(int[] a0) {
        a = a0;
        comparisons = 0;
        assignments = 0;

        quicksort(a, 0, a.length - 1);
    }
    
    public static void sortWorstCase(int[] a0) {
        a = a0;
        comparisons = 0;
        assignments = 0;
        
        quicksortWorstCase(a, 0, a.length - 1);
    }

    public static int getComparisons() {
        return comparisons;
    }

    public static int getAssignments() {
        return assignments;
    }
}
