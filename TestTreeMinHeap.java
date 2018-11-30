package cpsc331.assignment3;

import cpsc331.collections.MinHeap;
import cpsc331.assignment3.TreeMinHeap;
import java.util.NoSuchElementException;
import cpsc331.assignment3.TreeMinHeapUtilities;

public class TestTreeMinHeap {

  private static void testSize(TreeMinHeap<Integer> H, int size) {
  
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
  
  private static void testInsert(TreeMinHeap<Integer> H,
        TreeMinHeapUtilities<Integer> tester, Integer newValue, int newSize) {
        
    System.out.print("Inserting the value ");
    System.out.print(newValue);
    System.out.println(".");
    H.insert(newValue);
    if (!tester.isTreeMinHeap(H, newSize, false)) {
      System.out.println("H is not a MinHeap.");
      tester.isTreeMinHeap(H, newSize, true);
      System.out.println("");
    } else {
      testSize(H, newSize);
    }
         
  }
  
  private static void testDelete(TreeMinHeap<Integer> H, Integer expectedValue,
            TreeMinHeapUtilities<Integer> tester, int newSize, boolean success) {
            
    System.out.println("Attempting to delete a value.");
    try {
      Integer reportedValue = H.deleteMin();
      
      if (success) {
        
        System.out.println("deleteMin correctly returned a value.");
        
        if (expectedValue.equals(reportedValue)) {
          System.out.print("deleteMin correctly returned the value ");
          System.out.print(expectedValue.toString());
          System.out.println(".");
        } else {
          System.out.print("deleteMin incorrectly returned the value ");
          if (reportedValue == null) {
            System.out.print("null");
          } else {
            System.out.print(reportedValue.toString());
          };
          System.out.println(".");
        };
        
        if (!tester.isTreeMinHeap(H, newSize, false)) {
          tester.isTreeMinHeap(H, newSize, true);
        System.out.println("");
        } else {
          testSize(H, newSize);
        };
        
      } else {
      
        System.out.print("deleteMin incorrectly failed to throw ");
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
      
        System.out.println("deleteMin correctly threw a NoSuchElementException.");
        System.out.println("");
      
      } else {
      
        System.out.println("deleteMin incorrectly threw a NoSuchElementException.");
        System.out.println("");
      
      }
      
    }
    
  }
  
  public static void main(String[] args) {
  
    System.out.println();
    System.out.println("Creating a MinHeap.");
    System.out.println();
    
    TreeMinHeap<Integer> H = new TreeMinHeap<Integer>();
    TreeMinHeapUtilities<Integer> tester = new TreeMinHeapUtilities<Integer>();
    
    testSize(H, 0);
    testDelete(H, null, tester, 0, false);
    testInsert(H, tester, 1, 1);
    testDelete(H, 1, tester, 0, true);
    testInsert(H, tester, 10, 1);
    testInsert(H, tester, 16, 2);
    testInsert(H, tester, 6, 3);
    testInsert(H, tester, 2, 4);
    testInsert(H, tester, 2, 5);
    testInsert(H, tester, 8, 6);
    testInsert(H, tester, 12, 7);
    testInsert(H, tester, 14, 8);
    testInsert(H, tester, 18, 9);
    testInsert(H, tester, 20, 10);
    testInsert(H, tester, 4, 11);
    testDelete(H, 2, tester, 10, true);
    testDelete(H, 2, tester, 9, true);
    testDelete(H, 4, tester, 8, true);
    testDelete(H, 6, tester, 7, true);
    testDelete(H, 8, tester, 6, true);
    testDelete(H, 10, tester, 5, true);
    testDelete(H, 12, tester, 4, true);
    testDelete(H, 14, tester, 3, true);
    testDelete(H, 16, tester, 2, true);
    testDelete(H, 18, tester, 1, true);
    testDelete(H, 20, tester, 0, true);
    testDelete(H, null, tester, 0, false);
  
  }

}
