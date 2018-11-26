package cpsc331.assignment3;

import cpsc331.assignment3.HeapSort;
import java.util.ArrayList;
import cpsc331.assignment3.SortUtilities;
import java.util.Random;

public class TestSort {

  public static void main(String[] args) {
  
    SortUtilities<Integer> tester = new SortUtilities<Integer>();
    
    System.out.println("");
    System.out.print("Checking the success of HeapSort with a sorted input");
    System.out.println(" array.");
    
    ArrayList<Integer> A = new ArrayList<Integer>();
    int i = 0;
    while (i < 50) {
      A.add(i);
      i =i+1;
    };
    boolean match = tester.checkSort(A, true);
    if (match) {
      System.out.println("HeapSort sorted the input array correctly.");
    } else {
      System.out.println("HeapSort did not support the input array correctly.");
    };
    
    A = new ArrayList<Integer>();
    System.out.println("");
    System.out.print("Checking the success of HeapSort");
    System.out.println(" with an input array whose entries are decreasing.");
    i = 49;
    while (i >= 0) {
      A.add(i);
      i = i-1;
    };
    match = tester.checkSort(A, true);
    if (match) {
      System.out.println("HeapSort sorted the input array correctly.");
    } else {
      System.out.println("HeapSort did not support the input array correctly.");
    };
    
    System.out.println("");
    System.out.println("Testing HeapSort using a randomly generated array.");
    System.out.print("Input: [");
    
    A = new ArrayList<Integer>();
    Random generator = new Random();
    i = 0;
    while (i < 25) {
     int next = generator.nextInt(25);
     A.add(next);
     System.out.print(next);
     if (i < 24) {
       System.out.print(", ");
     } else {
       System.out.println("]");
     };
     i = i+1;
    };
    match = tester.checkSort(A, true);
    if (match) {
      System.out.println("HeapSort sorted the input array correctly.");
    } else {
      System.out.println("HeapSort did not sort the input array correctly.");
    };
    System.out.println();
  
  }

}
