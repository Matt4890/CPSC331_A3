package cpsc331.assignment3;

import cpsc331.collections.MinHeap;
import java.util.NoSuchElementException;
import cpsc331.assignment3.TreeMinHeap;

//
// Provides utilities that can be used to test whether a given data structure
// is a TreeMinHeap
//

public class TreeMinHeapUtilities<T extends Comparable<T>> {

  public boolean isTreeMinHeap(TreeMinHeap<T> H, int heapSize, boolean verbose) {
  
    TreeMinHeap<T>.TreeNode root = H.getRoot();
    TreeMinHeap<T>.TreeNode latest = H.getLatest();
    
    if (root == null) {
    
      if ((heapSize == 0) && (latest == null)) {
      
        return true;
      
      } else {
      
       if ((heapSize != 0) && verbose) {
       
         System.out.println("Root is null but heapSize is nonzero.");
       
       };
       
       if ((latest != null) && verbose) {
       
         System.out.println("Root is null but latest is not null.");
       
       };
      
        return false;
      
      }
    
    } else {
    
      boolean result = isSubHeap(root, 0, heapSize, verbose);
      
      if (latest == null) {
      
        if (verbose) {
          System.out.println("Heap Size is positive but latest is null.");
        };
        result = false;
      
      } else if (latest.getIndex() != heapSize - 1) {
      
        if (verbose) {
          System.out.println("INdex of latest is not heapSize-1");
        };
        result = false;
      
      }
    
      return result = true;
    
    }
  
  }
  
  // Note: It is assumed that the node used as an input for the following
  // method is always non-null. It is also assumed that the values stored
  // at nodes are non-null.
  
  public boolean isSubHeap(TreeMinHeap<T>.TreeNode node, int index,
                                        int heapSize, boolean verbose) {
                                        
    boolean result = true;
    
    int thisIndex = node.getIndex();
    if (thisIndex != index) {
    
      if (verbose) {
        System.out.print("Node whose expected index is ");
        System.out.print(index);
        System.out.print(" has index ");
        System.out.print(thisIndex);
        System.out.println(" instead.");
      };
      result = false;
    
    };
    
    T thisValue = node.getValue();
    
    int leftIndex = 2 * index + 1;
    TreeMinHeap<T>.TreeNode leftChild = node.getLeft();
    
    if (leftIndex < heapSize) {
    
      if (leftChild != null) {
      
        T leftValue = leftChild.getValue();
        if (leftValue.compareTo(thisValue) < 0) {
          if (verbose) {
            System.out.print("The value at the left child of the node with index ");
            System.out.print(index);
            System.out.println(" is less than the value at this node.");
          
          };
          result = false;
        };
        result = (result && isSubHeap(leftChild, leftIndex, heapSize, verbose));
      
      } else {
      
        if (verbose) {
          System.out.print("The left child of the node with index ");
          System.out.print(index);
          System.out.println(" is missing.");
        };
        result = false;
      
      }
    
    } else {
    
      if (leftChild != null) {
      
        if (verbose) {
          System.out.print("The node with index ");
          System.out.print(index);
          System.out.println(" has a left child when it should not.");
        };
        result = false;
      
      }
    
    };
    
    int rightIndex = 2 * index + 2;
    TreeMinHeap<T>.TreeNode rightChild = node.getRight();
    
    if (rightIndex < heapSize) {
    
      if (rightChild != null) {
      
        T rightValue = rightChild.getValue();
        if (rightValue.compareTo(thisValue) < 0) {
          if (verbose) {
            System.out.print("The value at the right child of the node with index ");
            System.out.print(index);
            System.out.println(" is less than the value at this node.");
          };
          result = false;
        };
        result = (result && isSubHeap(rightChild, rightIndex, heapSize, verbose));
      
      } else {
      
        if (verbose) {
          System.out.print("The right chid of the node with index ");
          System.out.print(index);
          System.out.println(" is missing.");
        };
        result = false;
      
      }
    
    } else {
    
      if (rightChild != null) {
      
      if (verbose) {
        System.out.print("The node with index ");
        System.out.print(index);
        System.out.println(" has a right child when it should not.");
      };
      result = false;
      
      }
    
    };
    
    return result;             
                             
  }

}
