package cpsc331.assignment3;

import cpsc331.assignment3.HeapSort;
import java.util.ArrayList;

// Provides utilities to sort an implementation of HeaSort

public class SortUtilities<T extends Comparable<T>> { 
 
  // Provides the insertion sort algorithm described in Lecture #15
  //
  // Precondition:
  // a) An ArrayList A with positive length, storing non-null values from
  //    an ordered type T, is given as input.
  // Postcondition:
  // a) The entries of A have been reordered but are otherwise unchanged.
  // b) A is sorted in nondecreasing order: A[i] <= A[i+1] for every integer i
  //    such that 0 <= i <= A.length-2
   
  public void insertionSort(ArrayList<T> A) {
   
    int i = 1;
     
    // Loop Invariant:
    // a) A is an ArrayList with positive length storing non-null values
    //    from some ordered type T
    // b) The entries of A have been reordered but are otherwise unchanged.
    // c) i is an integer variable such that 1 <= i <= A.size().
    // d) A[h] <= A[h+1] for every integer h such that 0 <= h <= i-2.
    // Bound Function: A.size()-i
     
    while (i < A.size()) {
     
      int j = i;
       
      // Loop Invariant:
      // a) A is an ArrayList with positive length storing non-null values
      //    from some ordered type T
      // b) The entries of A have been reordered but are otherwise unchanged.
      // c) is is an integer variable such that 1 <= i <= A.size()-1.
      // d) A[h] <= A[h+1] for every integer h such that 0 <= h <= j-2, and
      //    for every integer h such that j <= h <= i-1.
      // e) If 1 <= j <= i-1 then A[j-1] <= A[j+1].
      // Bound Function: j
       
      while ((j> 0) && ((A.get(j-1)).compareTo(A.get(j))) > 0 ) {
       
        T tmp = A.get(j-1);
        A.set(j-1, A.get(j));
        A.set(j, tmp);
        j = j-1;
       
      };
       
     i = i+1;
     
    }
    
  }
   
  // Provides a copy of an array.
  //
  // Precondition:
  // a) An ArrayList A with positive length, storing non-null values from some
  //    ordered type T, is given as input.
  // Postcondition:
  // a) An ArrayList B with positive length, storing non-null values from some
  //    ordered type T, is returned as output.
  // b) B.size() = A.size().
  // c) B[h] = A[h] for every integer h such that 0 <= h <= A.size()-1.
   
  private ArrayList<T> arrayCopy(ArrayList<T> A) {
   
    ArrayList<T> B = new ArrayList<T>();
    int i = 0;
     
    // Loop Invariant:
    // a) An ArrayList A with positive length, storing non-null values from some
    //    ordered type T, is given as input.
    // b) B is a variable ArrayList storing values from the same ordered type T.
    // c) i is an integer such that 0 <= i <= A.size().
    // d) B.size() = i.
    // e) B[h] = A[h] for every integer h such 0 <= h <= i-1.
    // Bound Function: A.size()-i.
     
    while (i < A.size()) {
      B.add(A.get(i));
      i = i+1;
    };
     
    return B;
   
  }
 
  // Checks whether two ArraysLists are equal.
  //
  // Precondition:
  // a) A is an input ArrayList with positive length storing non-null values
  //    from some ordered type T.
  // b) B is an input ArrayList storing values from the same ordered type T.
  // c) verbose is a Boolean input.
  // Postcondition:
  // a) If A.size() = B.size() and A[h] + B[h] for every integer h such that
  //    0 <= h <= A.size()-1 then true is returned; false is returned otherwise.
  // b) If the arrays are different and verbose is true then one or more
  //    informative error messages, explaining how the arrays are different,
  //    have been printed.
 
  private boolean compareArrays(ArrayList<T> A, ArrayList<T> B, boolean verbose) {
 
    if (A.size() == B.size()) {
   
      boolean match = true;
      int i = 0;
     
      // Loop Invariant:
      // a) A is an input ArrayList with positive length storing non-null values
      //    from some ordered type T.
      // b) B is an input ArrayList, with the same length, storing values from the
      //    same ordered type T.
      // c) verbose is a Boolean input variable.
      // d) i is an integer variable such that 0 <= i <= A.size().
      // e) match is a Boolean variable.
      // f) If match is true then A[h] = B[h] for every integer h such that
      //    0 <= h <= i-1. If match is false then there exists an integer h
      //    such that 0 <= h <= -1 and A[h] != B[h].
      // Bound Function: A.size()-i
     
      while (i < A.size()) {
     
        if (B.get(i) != null) {
       
          if (!(A.get(i)).equals(B.get(i))) {
         
            match = false;
            if (verbose) {
              System.out.print("Expected and actual arrays differ at position ");
              System.out.print(i);
              System.out.println(".");
            };
         
          };
         
        } else {
       
          match=false;
          if (verbose) {
            System.out.print("Value of actual array at position ");
            System.out.print(i);
            System.out.println(" is null.");
          };
       
        };  
       
        i = i+1;
     
      };
     
      return match;
   
    } else {
   
      if (verbose) {
        System.out.println("Expected and computed arrays have the same length.");
      };
      return false;
   
    }
   
  }
  
  // Checks whether HeapSort has correctly sorted an input array
  
  // Precondition:
  // a) An ArrayList A with positive length, storing non-null values from some
  //    ordered type T, is given as input.
  // b) A boolean variable verbose is also given as input.
  // Postcondition:
  // a) If HeapSort correctly sorts the ArrayList A then true is returned; false
  //    is returned otherwise.
  // b) If verbose is true and the ArrayList would not be sorted correctly then
  //    one or more messages, describing problems with the array provided by
  //    HeapSort, is printed as output.
  
  public boolean checkSort (ArrayList<T> A, boolean verbose) {
  
    ArrayList<T> B = arrayCopy(A);
    insertionSort(B);
    ArrayList<T> C = arrayCopy(A);
    HeapSort<T> hs = new HeapSort<T>();
    hs.sort(C);
    
    boolean match = compareArrays(B, C, verbose);
    
    if (!match && verbose) {
     System.out.print("Expected: [");
     int i = 0;
     while (i < B.size()) {
       System.out.print(B.get(i).toString());
       if (i < B.size()-1) {
         System.out.print(", ");
       } else {
         System.out.println("]");
       };
       i = i+1;
     };     
    
     System.out.print("Output: [");
     i = 0;
     while (i < C.size()) {
       System.out.print(C.get(i).toString());
       if (i < C.size()-1) {
         System.out.print(", ");
       } else {
         System.out.println("]");
       };
       i = i+1;
     }
    }
    
    return match;
  
  }

}
