package cpsc331.assignment3;

import cpsc331.collections.BoundedMaxHeap;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import cpsc331.collections.HeapFullException;

/**
*
* Provides an ArrayList-based implementation of a BoundedMaxHeap<br><br>
*
* ArrayMaxHeapInvariant: A finite multiset of non-null values of ordered type T is
* stored in a bounded binary MaxHeap, using an array-based representation
* <br><br>
*
*/

public class ArrayMaxHeap<T extends Comparable<T>> implements BoundedMaxHeap<T> {

	// Data fields

	private final int CAPACITY;
	private int size;
	public ArrayList<T> A;

	// Returns the index of a parent of a node that has one
	//
	// Precondition: A integer i such that 1 <= i <= this.size - 1 is given as input.
	// Postcondition: If A is viewed as a representation of a binary tree with
	//   size "this.size" then the index of the parent of the node with index i is
	//   returned as output.

	private int parent (int i) {

		return (int) Math.floor((i-1) / 2); // Parent index

	}

	// Reports whether a node with a given index has a left child in the binary
	// tree represented by A
	//
	// Precondition: An integer i such that 0 <= i <= size - 1 is given as input.
	// Postcondition: If A is viewed as a representation of a binary tree with size
	//   "this.size" then true is returned if the node with index i has a left
	//   child in this binary tree, and false is returned otherwise

	private boolean hasLeft (int i) {

		return leftChild(i) < this.size; // If heap is large enough for a left child

	}

	// Returns the index of a node that is the left child of a node with a given index
	//
	// Precondition:
	// a) An integer i such that 0 <= i <= this.size - 1 is given as input.
	// b) If A is viewed as a representation of a binary tree with size "this.size"
	//    then the node x with index i has a left child in this binary tree.
	// Postcondition: The index of the left child of x is returned as output.
	//

	private int leftChild (int i) {

		return (2*i) + 1; // Left child index

	}

	// Reports whether a node with a given index has a right child in the binary
	// tree represented by A
	//
	// Precondition: An integer i such that 0 <= i <= this.size - 1 is given as input.
	// Postcondition: If A is viewed as a representation of a binary tree with size
	//   "this.size" then true is returned if the node with index i has a right
	//   child in this binary tree, and false is returned otherwise

	private boolean hasRight (int i) {

		return rightChild(i) < this.size; // If heap is large enough for a right child

	}

	// Returns the index of a node that is the right child of a node with a given index
	//
	// Precondition:
	// a) An integer i such that 0 <= i <= this.size - 1 is given as input.
	// b) If A is viewed as a representation of a binary tree with size "this.size"
	//    then the node x with index i has a right child in this binary tree.
	// Postcondition: The index of the right child of x is returned as output.
	//

	private int rightChild (int i) {

		return (2*i) + 2; // Right child index

	}


	//
	// Implements the "bubbleUp" method needed to complete an insertion
	//
	// Precondition:
	// a) If this.A is viewed as representing a binary tree with the shape of a binary
	//    heap then all entries are non-null, so that the size of this tree is the
	//    same as the value of the data field "this.size", and this tree has  non-null
	//    values at its nodes.
	// b) The value of this.CAPACITY is an integer that is greater than or equal to the
	//    value of the data field "this.size" - and equal to the capacity of the
	//    ArrayList this.A.
	// c) An integer i such that 0 <= i <= this.size - 1 has been given as input. If x
	//    is the node with index i in the above binary tree then, for every node y
	//    in this tree except for x, both the parent and grandparent of y store values
	//    that are greater than is stored at y - if these nodes exist.
	// Postcondition:
	// a) The values stored in the first "this.size" locations of A have been reordered
	//    but not otherwise changed.
	// b) this.A now represents a BoundedMaxHeap with size "this.size"
	//
	// Note: The value of i can change - by decreasing - as this problem is being solved.

	private void bubbleUp (int i) {

		T swapValue;

		// While there are still values in the heap...
		while (i > 0){

			// Swap values if i isn't root and i is greater than its parent
			if (i != 0 && this.A.get(i).compareTo(this.A.get(parent(i))) == 1) {

				// Swap values
				swapValue = this.A.get(i);
				this.A.set(i,			this.A.get(parent(i)));
				this.A.set(parent(i),	swapValue);

				// Continue bubbling with parent
				i = parent(i);
				continue;

			}

			// No more bubbling needed
			break;

		}

	}

	//
	// Implements the "bubbleDown" method needed to complete a deleteMax
	//
	// Precondition:
	// a) If A is viewed as representing a binary tree with the shape of a binary
	//    heap then all entries are non-null, so that the size of this tree is the
	//    same as the value of the data field "this.size", and this tree has non-null
	//    values at its nodes.
	// b) The value of this.CAPACITY is an integer that is greater than or equal to the
	//    value of the data field "this.size" - and equal to the capacity of the
	//    ArrayList A.
	// c) An integer i such that 0 <= i <= this.size - 1 has been given as input. If x
	//    is the node with index x in the above binary tree then, for every node y
	//    in this tree except for x, both the children and grandchildren of y store
	//    values that are less than the value stored at y - if these nodes exist.

	// Postcondition:
	// a) The values stored in the first "this.size" locations of A have been reordered
	//    but not changed.
	// b) A now represents a BoundedMaxHeap with size "this.size".
	//
	// Note: The value of i can change - by increasing - as this problem is being solved.

	private void bubbleDown (int i) {

		T temp;

		// While i has children...
		while (hasLeft(i)) {

			// If i has 2 children...
			if (hasRight(i)) {

				// If the left child is greater than or equal to right child...
				if (this.A.get(leftChild(i)).compareTo(this.A.get(rightChild(i))) >= 0) {

					// If the left child is greater than i...
					if (this.A.get(leftChild(i)).compareTo(this.A.get(i)) == 1) {

						// Swap values
						temp = this.A.get(i);
						this.A.set(i,				this.A.get(leftChild(i)));
						this.A.set(leftChild(i),	temp);

						// Continue bubbling with the left child
						i = leftChild(i);
						continue;

					}

				// If the right child is greater than left child
				} else if (this.A.get(rightChild(i)).compareTo(this.A.get(i)) == 1) {

					// Swap values
					temp = this.A.get(i);
					this.A.set(i,				this.A.get(rightChild(i)));
					this.A.set(rightChild(i),	temp);

					// Continue bubbling with the right child
					i = rightChild(i);
					continue;

				}

			// If i has 1 child...
			} else if (hasLeft(i)) {

				// If the left child is greater than i...
				if (this.A.get(leftChild(i)).compareTo(this.A.get(i)) == 1) {

					// Swap values
					temp = this.A.get(i);
					this.A.set(i,				this.A.get(leftChild(i)));
					this.A.set(leftChild(i),	temp);

					// Continue bubbling with the left child
					i = leftChild(i);
					continue;

				}

			}

			// No more bubbling needed
			break;

		}

	}

	//
	// Converts an array into a representation of a BoundedMaxHeap
	//
	// Precondition:
	// a) A is an ArrayList with positive integer capacity this.CAPACITY, and whose
	//    size is this this.CAPACITY, storing non-null values from some ordered type T
	// Postcondition:
	// a) The entries of A have been ordered but otherwise not changed.
	// b) A is now a representation of a BoundedMaXHeap whose size is the size (and
	//    capacity) of this ArrayList.

	private void heapify() {

		// Get the index of the first non-leaf
		int i = (int) Math.floor(this.A.size() / 2);

		// For each non leaf...
		while (i > 0) {

			// Bubble the node down
			bubbleDown(i - 1);
			i -= 1;

		}

	}


	/**
	*
	* @param capacity the capacity of the BondedMaxHeap to be created
	* @throws IllegalArgumentException if the capacity is not positive
	* <br><br>
	*
	* Constructs an empty BoundedHeapHeap with a given positive integer capacity<br><br>
	*
	* Precondition:<br>
	* <ol style="list-style-type: lower-alpha">
	* <li> An integer capacity is given as input </li>
	* </ol>
	* Postcondition:<br>
	* <ol style="list-style-type: lower-alpha">
	* <li> If the input capacity is positive then an empty (array-based) BondedMaxHeap
	*      with this capacity has been created. An IllegalArgumentException has been
	*      thrown, and a BoundedMaxHeap has not been created, otherwise. </li>
	* </ol>
	*
	*/

	public ArrayMaxHeap(int capacity) throws IllegalArgumentException {

		if (capacity > 0) {

			CAPACITY = capacity;
			size = 0;
			A = new ArrayList<T>(capacity);
			int i = 0;

			// Loop Invariant:
			// 1. A positive integer capacity is given as input.
			// 2. A is an ArrayList storing values with type T.
			// 3. i is an integer such that 0 <= i <= capacity
			// 4. The current size of A is i and, for every integer j such that
			//    0 <= j < i, A[j] = null
			// Bound Function: capacity - i

			while (i < capacity) {
				A.add(null);
				i = i+1;
			}

		} else {

			throw new IllegalArgumentException("The input capacity must be positive.");

		}

	}

	/**
	*
	* @param givenA the ArrayList to be used to create this.A
	* <br><br>
	*
	* Constructs a BoundedMaxHeap from a given ArrayList<br><br>
	*
	* Precondition:<br>
	* <ol style="list-style-type: lower-alpha">
	* <li> An ArrayList givenA, whose size is the same as its positive integer capacity,
	*      storing non-null values from the ordered type T, is given as input </li>
	* </ol>
	* Postcondition:<br>
	* <ol style="list-style-type: lower-alpha">
	* <li> Both this.CAPaCITY and this.size are equal to the capacity of A
	* <li> this.A is the array givenA - whose entries have been reordered, but
	*      not changed, so that this represents a BoundedMaxHeap </li>
	* </ol>
	*
	*/

	public ArrayMaxHeap(ArrayList<T> givenA) {

		CAPACITY = givenA.size();
		size =  CAPACITY;
		A = givenA;
		heapify();

	}


	// Implementation of insert

	public void insert(T v) throws HeapFullException {

		// If there's room for a new node...
		if (this.size < this.CAPACITY) {

			// Insert a new value and bubble it up
			this.A.set(this.size, v);
			bubbleUp(this.size);
			this.size += 1;

		// If there isn't room for a new node...
		} else {
			throw new HeapFullException("This heap is full! It cannot accept any more values!");
		}

	}

	// Implementation of deleteMax

	public T deleteMax() throws NoSuchElementException {

		// If the heap is empty...
		if (this.size == 0) {
			throw new NoSuchElementException("This heap is empty!");

		// If the heap isn't empty...
		} else {

			// Get the last value
			T v = this.A.get(this.size - 1);
			this.size -= 1;

			// If there's only 1 node...
			if (this.size == 0) {
				return v;

			// If there are multiple nodes
			} else {

				// Swap the last value with the root
				T key = this.A.get(0);
				this.A.set(0, v);

				// Bubble down the root
				bubbleDown(0);

				// Return the old root value
				return key;
				
			}
		}

	}

	// Implementation of getSize; supplied by instructor

	public int getSize() {
		return size;
	}

	// Implementation of getCapacity; supplied by instructor

	public int getCapacity() {
		return CAPACITY;
	}

	// Used for testing; supplied by instructor

	T valueByIndex (int indx) throws NoSuchElementException {

		if ((indx >= 0) && (indx < size)) {

			return A.get(indx);

		} else {

			throw new NoSuchElementException("There is no node with this index.");

		}

	}

}
