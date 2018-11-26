package cpsc331.assignment3;

import cpsc331.collections.BoundedMaxHeap;
import cpsc331.assignment3.ArrayMaxHeap;
import java.util.NoSuchElementException;
import cpsc331.collections.HeapFullException;

// 
// Provides utilities to best an ArrayList-Based Implementation of ArrayMaxHeap
// of a BoundedMaxHeap
//

public class BoundedMaxHeapUtilities <T extends Comparable<T>> {

  public boolean isMaxHeap (ArrayMaxHeap<T> H, boolean verbose){
  
    int size = H.getSize();
    if (size == 0) {
    
      return true;
    
    } else {
    
      return isSubHeap(H, 0, verbose);
    
    }
  
  }
  
  boolean isSubHeap (ArrayMaxHeap<T> H, int indx, boolean verbose) {
  
    int size = H.getSize();
    T thisValue = H.valueByIndex(indx);
    boolean isLeftSubHeap = true;
    int leftIndex = 2 * indx + 1;
    boolean isRightSubHeap = true;
    int rightIndex = 2 * indx + 2;
    
    if (leftIndex < size) {
    
      isLeftSubHeap = isSubHeap(H, leftIndex, verbose);
      
      if (isLeftSubHeap) {
      
        T leftValue = H.valueByIndex(leftIndex);
        isLeftSubHeap = ((thisValue.compareTo(leftValue) >= 0));
      
      };
    
    };
    
    if (rightIndex < size) {
    
      isRightSubHeap = isSubHeap(H, rightIndex, verbose);
      
      if (isRightSubHeap) {
      
        T rightValue = H.valueByIndex(rightIndex);
        isRightSubHeap = ((thisValue.compareTo(rightValue) >= 0));
      
      };
    
    };
    
    return (isLeftSubHeap && isRightSubHeap);
  
  }

}
