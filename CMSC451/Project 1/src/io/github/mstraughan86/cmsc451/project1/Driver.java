/*
Michael Straughan
CMSC 451
Project 1 - HeapSort and QuickSort
September 13, 2015
*/

package io.github.mstraughan86.cmsc451.project1;

import java.util.Random;
import java.util.Arrays;

public class Driver {

    public static int[] testCorrectness = new int[]{4, 1, 3, 2, 5, 9, 10, 6, 8, 7};

    public static void main(String[] args) {

        intro();
        testHeapSort(testCorrectness);
        testQuickSort(testCorrectness);
        startTestSequence();
        startQuickSortComparison();

    }

    public static void startQuickSortComparison() {
        int[] bestCaseArray = new int[100];
        int[] worstCaseArray = new int[100];
        int[] averageCaseArray = createRandomizedArray(100);
        int quickBestCaseOperations = 0;
        int quickWorstCaseOperations = 0;
        int quickAverageCaseOperations = 0;
        
        for (int i = 0; i < worstCaseArray.length; i++) {
            worstCaseArray[i] = i;
            bestCaseArray[i] = i;
        }

        QuickSort.sort(bestCaseArray);
        quickBestCaseOperations = QuickSort.getAssignments() + QuickSort.getComparisons();
        
        QuickSort.sortWorstCase(worstCaseArray);
        quickWorstCaseOperations = QuickSort.getAssignments() + QuickSort.getComparisons();
        
        QuickSort.sort(averageCaseArray);
        quickAverageCaseOperations = QuickSort.getAssignments() + QuickSort.getComparisons();
        
        System.out.println("\n       ===== QuickSort Best/Worst/Average Comparison Test ======"
                + "\nArray Size = 100 Elements\n");
        System.out.println("QuickSort Best Case Operations = " + quickBestCaseOperations);
        System.out.println("QuickSort Worst Case Operations = "+ quickWorstCaseOperations);
        System.out.println("QuickSort Average Case Operations = " + quickAverageCaseOperations);

    }

    public static void startTestSequence() {
        for (int i = 100; i < 1100; i = i + 100) {
            int heapComparisons = 0;
            int heapAssignments = 0;
            int quickComparisons = 0;
            int quickAssignments = 0;

            for (int j = 1; j < 6; j++) {
                int[] heapArray = createRandomizedArray(i);
                int[] quickArray = new int[heapArray.length];
                System.arraycopy(heapArray, 0, quickArray, 0, heapArray.length);

                HeapSort.sort(heapArray);
                heapComparisons += HeapSort.getComparisons();
                heapAssignments += HeapSort.getAssignments();

                QuickSort.sort(quickArray);
                quickComparisons += QuickSort.getComparisons();
                quickAssignments += QuickSort.getAssignments();
            }

            System.out.println("\n      ===== Sorting for Array Size " + i + " =====");
            System.out.println("HeapSort Assignments = " + heapAssignments);
            System.out.println("HeapSort Comparisons = " + heapComparisons);
            System.out.println("Total Operations = " + (heapComparisons + heapAssignments));
            System.out.println("Average Operations = "
                    + (heapComparisons + heapAssignments) / 5);

            System.out.println("\nQuickSort Assignments = " + quickAssignments);
            System.out.println("QuickSort Comparisons = " + quickComparisons);
            System.out.println("Total Operations = " + (quickComparisons + quickAssignments));
            System.out.println("Average Operations = "
                    + (quickComparisons + quickAssignments) / 5);
        }
    }

    public static int[] createRandomizedArray(int size) {
        // Create randomized array of length "size""
        int[] randomizedArray = new int[size];

        // Populate array with integers between 0 - 999
        Random ran = new Random();
        for (int i = 0; i < randomizedArray.length; i++) {
            randomizedArray[i] = ran.nextInt(999);
        }

        return randomizedArray;
    }

    public static void testHeapSort(int[] a) {
        // Function UI intro
        // Display unsorted test array

        System.out.println("\nTesting HeapSort Correctness...");
        System.out.print("Using Unsorted Test Array = " + Arrays.toString(a));

        // Copy unsorted test array to keep the original array unsorted
        int[] test = new int[a.length];
        System.arraycopy(a, 0, test, 0, a.length);

        // HeapSort and display sorted array
        HeapSort.sort(test);

        System.out.print("\nSorted Test Array = " + Arrays.toString(test) + "\n");
    }

    public static void testQuickSort(int[] a) {
        // Function UI intro
        // Display unsorted test array

        System.out.println("\nTesting QuickSort Correctness...");
        System.out.print("Using Unsorted Test Array = " + Arrays.toString(a));

        // Copy unsorted test array to keep the original array unsorted
        int[] test = new int[a.length];
        System.arraycopy(a, 0, test, 0, a.length);

        // QuickSort and display sorted array
        QuickSort.sort(test);

        System.out.print("\nSorted Test Array = " + Arrays.toString(test) + "\n");
    }

    public static void intro() {
        System.out.println("Michael Straughan"
                + "\nCMSC 451"
                + "\nProject 1 - HeapSort and QuickSort"
                + "\nSeptember 13, 2015"
                + "\n\n"
                + "\n===== HeapSort and QuickSort ====="
                + "\nThis program will compare the total operations in HeapSort and QuickSort."
                + "\nIt will first test the algorithm correctness on a static unsorted array."
                + "\nThen it will analyze the two algorithms by:"
                + "\n- Generating 5x random unsorted array of n elements"
                + ", range from 100 to 1000 in increments of 100"
                + "\n- Sort the array using HeapSort and QuickSort."
                + "\n- Count the number of Assigment and Comparison operations using each sort method"
                + "\n- Display the Total Operations and Average Operations"
                + "\n\nThe program will finally run a QuickSort Best/Worst/Average comparison test"
                + "\nand print out the total operations for each test case."
                + "\n\nThe program will execute the previous functions and automatically terminate.\n");
    }

}
