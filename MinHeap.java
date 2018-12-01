package cpsc331.collections;

import java.util.NoSuchElementException;

/**
*
* Provides an interface for an unbounded binary MinHeap<br><br>
*
* MinHeap Invariant: A finite multiset of values of ordered type T is stored
* in a binary MinHeap
* <br><br>
*
* @author Wayne Eberly
*
*/

public interface MinHeap<T extends Comparable<T>> {

  /**
  *
  * @param v the value to be inserted into this binary MinHeap
  * <br><br>
  *
  * Precondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The MinHeap Invariant is satisfied.</li>
  * <li> A value v with type T has been given as input. </li>
  * </ol>
  * Postcondition:<br>
  * <ol style="List-style-type: lower-alpha">
  * <li> The MinHeap Invariant is satisfied. </li>
  * <li> A copy of the value v has been inserted into the multiset represented
  *      by this binary MinHeap. No other changes have been made. </li>
  * </ol>
  *
  */
  
  public void insert (T v);
  
  /**
  *
  * @return the value that was deleted from this MinHeap
  * @throws NoSuchElementException if this MinHeap was already empty
  * <br><br>
  *
  * Precondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The MinHeap Invariant is satisfied. </li>
  * </ol>
  * Postcondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The MinHeap Invariant is satisfied. </li>
  * <li> If this MinHeap was not empty then a copy of the smallest element is removed
  *      from the multiset represented by this MinHeap and returned as output, and no
  *      other changes to this multiset have been made. A NoSuChElementException is
  *      thrown if this heap was already empty, an it is not changed. </li>
  * </ol>
  *  
  */
  
  public T deleteMin () throws NoSuchElementException;
  
  /**
  *
  * @return the current size of this MinHeap
  * <br><br>
  *
  * Precondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The MinHeap Invariant is satisfied. </li>
  * </ol>
  * Postcondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> This MinHeap has not changed, so the MinHeap Invariant is still satisfied. </li>
  * <li> The size of this MinHeap has been returned as output. </li>
  * </ol>
  *
  */
  
  public int getSize();  

}
