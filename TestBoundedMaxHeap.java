package cpsc331.assignment3;

import cpsc331.collections.BoundedMaxHeap;
import cpsc331.assignment3.ArrayMaxHeap;
import java.util.NoSuchElementException;
import cpsc331.collections.HeapFullException;
import cpsc331.assignment3.BoundedMaxHeapUtilities;

public class TestBoundedMaxHeap {

  private static void testSize(ArrayMaxHeap<Integer> H, int size) {
  
    int heapSize = H.getSize();
    if (heapSize == size) {
    
      System.out.print("getSize correctly reported that the heap size is now ");
      System.out.print(size);
      System.out.println(".");
      System.out.println();
    
    } else {
    
      System.out.print("getSize incorrectly reported that the heap size is now ");
      System.out.print(heapSize);
      System.out.println(".");
      System.out.println();

    }
  
  }

  private static void testInsert(ArrayMaxHeap<Integer> H, 
       BoundedMaxHeapUtilities<Integer> tester, Integer newValue,
                                                  int newSize, boolean success) {
    try {
    
      System.out.print("Attempting to insert the value ");
      System.out.print(newValue);
      System.out.println(".");
      H.insert(newValue);
      if (success) {
      
        System.out.print("insert reported, as expected, that the value ");
        System.out.print(newValue);
        System.out.println(" was successfully inserted.");
        if (!tester.isMaxHeap(H, false)) {
          System.out.println("H is not a maxHeap.");
          tester.isMaxHeap(H, true);
          System.out.println("");
        } else {
          testSize(H, newSize);
        }
            
      } else {
      
        System.out.println("insert incorrectly threw a HeapFullException.");
      
      }
    
    } catch (HeapFullException ex) {
    
      if (!success) {
      
        System.out.println("insert correctly threw a HeapFullException.");
        System.out.println();
      
      } else {
      
        System.out.print("insert correctly failed to throw a HeapFullException.");
        if (!tester.isMaxHeap(H, false)) {
          System.out.println("H is not a maxHeap.");
          tester.isMaxHeap(H, true);
        } else {
          System.out.println();
        }
      
      }
    
    }
  
  }
  
  private static void testDelete(ArrayMaxHeap<Integer> H, Integer expectedValue,
         BoundedMaxHeapUtilities<Integer> tester, int newSize, boolean success) {
          
    System.out.println("Attempting to delete a value.");
    try {
      Integer reportedValue = H.deleteMax();
      
      if (success) {
        
        System.out.println("deleteMax correctly returned a value.");
        
        if (expectedValue.equals(reportedValue)) {
          System.out.print("deleteMax correctly returned the value ");
          System.out.print(expectedValue.toString());
          System.out.println(".");
        } else {
          System.out.print("deleteMax incorrectly returned the value ");
          if (reportedValue == null) {
            System.out.print("null");
          } else {
            System.out.print(reportedValue.toString());
          };
          System.out.println(".");
        };
        
        testSize(H, newSize);
        
      } else {
      
        System.out.print("deleteMax incorrectly failed to throw ");
        System.out.println("a NoSuchElementException.");
        System.out.print("Value returned was ");
        if (reportedValue == null) {
          System.out.print("null");
        } else {
          System.out.print(reportedValue.toString());
        };
        System.out.println(".");
        testSize(H, newSize);
      
      }
      
    } catch (NoSuchElementException ex) {
    
      if (!success) {
      
        System.out.println("deleteMax correctly threw a NoSuchElementException.");
        System.out.println("");
      
      } else {
      
        System.out.println("deleteMax incorrectly threw a NoSuchElementException.");
        System.out.println("");
      
      }
    
    }                       
  
  }
  
  public static void main(String[] args) {
  
    System.out.println();
    System.out.println("Creating a bounded maxHeap with capacity 10.");
    System.out.println();
    
    ArrayMaxHeap<Integer> H = new ArrayMaxHeap<Integer>(10);
    final int CAPACITY = 10;
    
    BoundedMaxHeapUtilities<Integer> tester = new BoundedMaxHeapUtilities<Integer>();
    
    testSize(H, 0);
    testDelete(H, null, tester, 0, false);
    testInsert(H, tester, 1, 1, true);
    testDelete(H, 1, tester, 0, true);
    testInsert(H, tester, 10, 1, true);
    testInsert(H, tester, 16, 2, true);
    testInsert(H, tester, 6, 3, true);
    testInsert(H, tester, 2, 4, true);
    testInsert(H, tester, 2, 5, true);
    testInsert(H, tester, 8, 6, true);
    testInsert(H, tester, 12, 7, true);
    testInsert(H, tester, 14, 8, true);
    testInsert(H, tester, 18, 9, true);
    testInsert(H, tester, 20, 10, true);
    testInsert(H, tester, 4, 10, false);
    testDelete(H, 20, tester, 9, true);
    testDelete(H, 18, tester, 8, true);
    testDelete(H, 16, tester, 7, true);
    testDelete(H, 14, tester, 6, true);
    testDelete(H, 12, tester, 5, true);
    testDelete(H, 10, tester, 4, true);
    testDelete(H, 8, tester, 3, true);
    testDelete(H, 6, tester, 2, true);
    testDelete(H, 2, tester, 1, true);
    testDelete(H, 2, tester, 0, true);
    testDelete(H, null, tester, 0, false);
  
  }

}
