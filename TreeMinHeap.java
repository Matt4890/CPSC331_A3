package cpsc331.assignment3;

import cpsc331.collections.MinHeap;
import java.util.NoSuchElementException;

/**
*
* Provides a Tree-Based Implementation of an Unbounded MinHeap<br><br>
*
* TreeMinHeap Invariant: A finite multiset of non-values of ordered type T is
* stored in a binary MinHeap, using a tree-based representation.
* <br><br>
*
*/

public class TreeMinHeap<T extends Comparable<T>> implements MinHeap<T> {

	//
	// Implements a Node in a Binary MinHeap
	//
	// TreeNode Invariant:
	// a) This node stores a non-null value from an ordered type T
	// b) parent, leftChild and rightChild are (possibly null) references to other
	//    TreeNodes; indx is the position where the data at this TreeNode would be
	//    stored in an ArrayList-based implementation of the same MinHeap

	class TreeNode {

		// Data Fields
		private T value;             // Value stored at this TreeNode
		private int index;           // Position of this node in an array-based representation
		private TreeNode parent;     // The parent of this TreeNode
		private TreeNode leftChild;  // The left child of this TreeNode
		private TreeNode rightChild; // The right child of this TreeNode

		// Creates a new TreeNode with a given value, "inputValue" and a given
		// index, "inputIndex"
		//
		// Precondition:
		// a) A non-null value inputValue with type T and non-negative integer inputIndex
		//    are given as index.
		// Postcondition:
		// a) A TreeNode storing value "inputValue" and with index "inputIndex",
		//    for which parent, leftChild and rightChild are null, has been created.

		public TreeNode (T inputValue, int inputIndex) {

			value = inputValue;
			index = inputIndex;
			parent = null;
			leftChild = null;
			rightChild = null;

		}

		// Reports the index of this node.
		//
		// Precondition: The TreeNode Invariant is satisfied.
		// Postcondition: The index of this TreeNode is returned as output.

		public int getIndex() {
			return index;
		}

		// Reports the value stored at this TreeNode
		//
		// Precondition: The TreeNode Invariant is satisfied.
		// Postcondition: The value stored at this TreeNode is returned as output.

		public T getValue() {
			return value;
		}

		// Reports the parent of this TreeNode
		//
		// Precondition: The TreeNode Invariant is satisfied.
		// Postcondition: The parent of this TreeNode is returned as output.

		public TreeNode getParent () {
			return parent;
		}

		// Reports the left child of this TreeNode
		//
		// Precondition: The TreeNode Invariant is satisfied.
		// Postcondition: The left child of this TreeNode is returned as output.

		public TreeNode getLeft () {
			return leftChild;
		}

		// Reports the right child of this TreeNode
		//
		// Precondition: The TreeNode Invariant is satisfied.
		//

		public TreeNode getRight() {
			return rightChild;
		}

		// Sets the value stored at this TreeNode
		//
		// Precondition:
		// a) The TreeNode Invariant is satisfied.
		// b) A non-null value inputValue, with type T, is given as input.
		// Postcondition:
		// a) The value stored at this TreeNode is now the given inputValue.

		public void setValue(T inputValue) {
			value = inputValue;
		}

		// Sets the parent of this TreeNode
		//
		// Precondition:
		// a) The TreeNode Invariant is satisfied.
		// b) A TreeNeed, inputParent, is given as input.
		// Postcondition:
		// a) The parent of this TreeNode is now the given inputParent.

		public void setParent(TreeNode inputParent) {
			parent = inputParent;
		}

		// Sets the left child of this TreeNode
		//
		// Precondition:
		// a) The TreeNode Invariant is satisfied.
		// b) A TreeNode, inputLeft, is given as input.
		// Postcondition:
		// a) The left child of this TreeNode is now the given inputLeft

		public void setLeft(TreeNode inputLeft) {
			leftChild = inputLeft;
		}

		// Sets the right child of this TreeNode
		//
		// Precondition:
		// a) The TreeNode Invariant is satisfied.
		// b) A TreeNode, inputRight, is given as input.
		// Postcondition:
		// a) The right child of this TreeNode is now the given inputRight.

		public void setRight(TreeNode inputRight) {
			rightChild = inputRight;
		}

  	}

	// TrreMinHeap Invariant:
	// a) root is the root of a binary tree representing a MinHeap, whose nodes store
	//    non=null values from the ordered type T. Thus this binary tree has the shape of
	//    a binary heap. The value at each node is greater than or equal to the value at
	//    its parent, if the parent exists, and is less than the values stored at its
	//    left and right children, if these exist.
	// b) heapSize is the current size of this binary heap.
	// c) latest is the existing node that was most recently added to this MinHeap.


	// Data Fields
	private int heapSize;     // The size of this MinHeap
	private TreeNode root;    // The root of the binary tree representing this MinHeap
	private TreeNode latest;  // The last remaining TreeNode added to this MaxHeap

	/**
 	 *
 	 * Creates an empty MinHeap.<br><br>
 	 *
 	 * Precondition: None<br>
 	 * Postcondition: The TreeMinHeap Invariant is satisfied; the MinHeap represneted
  	 *  is an empty heap, so that its heapSize is zero.
 	 *
	 */

	public TreeMinHeap () {
		heapSize = 0;
		root = null;
		latest = null;

	}

	// Returns the existing node most recently added before "latest" or null, if
	// no such node exists.
	//
	// Precondition: The TreeMinHeap Invariant is satisfied, and the MinHeap represented
	//   is not empty.
	// Postcondition: The TreeNode added most recently before latest is returned
	//   as output.

	private TreeNode predecessor () {

		// Start with the latest node
		TreeNode n = this.latest;

		// If n is a right child, return its sibling
		if (n == n.parent.rightChild) {

			// Return n's sibling
			return n.parent.leftChild;

		// If n is a left child...
		} else {

			// Go up until you reach the root or a right child
			while (n != this.root && n == n.parent.leftChild) {
				n = n.parent;
			}

			// Swap to the node's sibling... if it has one
			if (n != this.root) {
				n = n.parent.leftChild;
			}

			// Go right until a leaf is reached
			while (n.rightChild != null) {
				n = n.rightChild;
			}

			return n;

		}

	}

	// Returns the node that should become the parent of the next node to be added.
	//
	// Precondition: The TreeMinHeap Invariant is satisfied, and the MinHeap represented
	//   is not empty.
	// Postcondition: The TreeNode that should be the parent of the next node to be
	//   added is returned.

	private TreeNode successorParent ()  {

		// Start wih the latest node
		TreeNode n = this.latest;

		// If n isn't the root and n is a left child...
		if (n != this.root && n == n.parent.leftChild) {

			// Return n's parent
			return n.parent;

		// If n is root or a right child...
		} else {

			// Go up until you reach the root or a left child
			while (n != this.root && n == n.parent.rightChild) {
				n = n.parent;
			}

			// Swap to the node's sibling... if it has one
			if (n != this.root) {
				n = n.parent.rightChild;
			}

			// Go left until a leaf is reached
			while (n.leftChild != null) {
				n = n.leftChild;
			}

			return n;

		}

	}

	//
	// Implements the "bubbleUp" method needed to complete an insertion
	//
	// Precondition:
	// a) root if the root of a binary tree, with the shape of a binary heap,
	//    storing values from an ordered type T.
	// b) A non-null node x in this binary tree has been given as input.
	// c) For every node y in this binary tree, except for x, if y has a parent
	//    then the value stored at the parent is less than or equal to the value
	//    stored at y. If y has a grandparent then the value at the grandparent
	//    is less than or equal to the value stored at y, as well.
	// Postcondition:
	// a) The values stored at the nodes of this binary tree have been exchanged
	//    between nodes but otherwise unchanged - the same multiset is represented
	//    by the nodes in this binary tree.
	// b) This binary tree is now a representation of a binary MinHeap.
	//

	private void bubbleUp(TreeNode x) {

		T swapValue;
		int swapIndex;

		// While there are still nodes in the heap...
		while (x != root) {

			// Swap values if x isn't root and x is less than its parent
			if (x != this.root && x.value.compareTo(x.parent.value) == -1) {

				// Swap values
				swapValue = x.value;
				swapIndex = x.index;
				x.value = x.parent.value;
				x.index = x.parent.index;
				x.parent.value = swapValue;
				x.parent.index = swapIndex;

				// Continue bubbling with parent
				x = x.parent;
				continue;

			}

			// No more bubbling needed
			break;

		}

	}

	//
	// Implements the "bubbleDown" method used to complete a deletion
	//
	// Precondition:
	// a) root is the root of a binary tree, with the shape of a binary heap,
	//    storing non-null values from an ordered type T.
	// b) A non-null node x in this binary tree, has been given as input.
	// c) For every node y in this binary tree except for x, if y has a child
	//    then the value stored at the child is greater than or equal to the value
	//    stored at y, and if y has a grandchild then the value stored at the
	//    grandchild is greater than or equal to the value stored at y, as well.
	// Postcondition:
	// a) The values stored at the nodes of this binary tree have been exchanged
	//    between nodes but otherwise unchanged - the same multiset is represented
	//    by the nodes in this binary tree.
	// b) This binary tree is now a representation of a binary MinHeap.

	private void bubbleDown(TreeNode x) {

		T swapValue;
		int swapIndex;

		// While x has children...
		while (x.leftChild != null) {

			// If x has 2 children...
			if (x.rightChild != null) {

				// If the left child is less than or equal to right child...
				if (x.leftChild.value.compareTo(x.rightChild.value) <= 0) {

					// If the left child is less than x...
					if (x.leftChild.value.compareTo(x.value) == -1) {

						// Swap values
						swapValue = x.value;
						swapIndex = x.index;
						x.value = x.leftChild.value;
						x.index = x.leftChild.index;
						x.leftChild.value = swapValue;
						x.leftChild.index = swapIndex;

						// Continue bubbling with the left child
						x = x.leftChild;
						continue;

					}

				// If the right child is less than left child
				} else {

					if (x.rightChild.value.compareTo(x.value) == -1) {

						// Swap values
						swapValue = x.value;
						swapIndex = x.index;
						x.value = x.rightChild.value;
						x.index = x.rightChild.index;
						x.rightChild.value = swapValue;
						x.rightChild.index = swapIndex;

						// Continue bubbling with the right child
						x = x.rightChild;
						continue;

					}

				}

			// If i has 1 child...
			} else if (x.leftChild != null) {

				// If the left child is less than i...
				if (x.leftChild.value.compareTo(x.value) == -1) {

					// Swap values
					swapValue = x.value;
					swapIndex = x.index;
					x.value = x.leftChild.value;
					x.index = x.leftChild.index;
					x.leftChild.value = swapValue;
					x.leftChild.index = swapIndex;

					// Continue bubbling with the left child
					x = x.leftChild;
					continue;

				}

			}

			// No more bubbling needed
			break;

		}

	}

	// Implementation of the insert method provided by a MinHeap. The preconditions
	// and postcondition for this problem are the same, except that they now also
	// include the fact that the "TreeMinHeap Invariant" is satisfied when execution
	// of the algorithm begins and, again, when it ends.

	public void insert (T v) {

		// Create a new node
		TreeNode n = new TreeNode(v, this.latest != null ? this.latest.index + 1 : 0);

		// Assign the parent
		if (this.root == null) {
			this.root = n;
		} else {
			n.parent = successorParent();
		}

		// Set thisto the latest
		this.latest = n;

		// Set the parent's relation
		if (n != root) {
			if (this.latest.parent.leftChild == null) {
				this.latest.parent.leftChild = this.latest;
			} else {
				this.latest.parent.rightChild = this.latest;
			}
		}

		// Bubble up the node
		this.heapSize += 1;
		bubbleUp(this.latest);

	}

	// Implementation of the deleteMin method provided by a MinHeap. The precondition
	// and postcondition for this problem are the same, except that they now also
	// include the fact that the "TreeMinHeap Invariant" is satisfied when execution
	// of the algorithm begins, and when it ends.

	public T deleteMin () throws NoSuchElementException {

		// If the heap is empty...
		if (this.heapSize == 0) {
			throw new NoSuchElementException("This heap is empty!");

		// If the heap isn't empty...
		} else {

			// Get the latest value
			T latestVal = this.latest.value;
			this.heapSize -= 1;

			// If that was the only node...
			if (this.heapSize == 0) {

				this.root = null;
				this.latest = null;
				return latestVal;

			// If there were multiple nodes...
			} else {

				// Record the minimum value
				T minVal = this.root.value;

				// Swap the root value with the latest value
				this.root.value = latestVal;

				// Assign a new latest node
				TreeNode l = this.latest;
				this.latest = predecessor();

				// Delete the previous latest node
				if (l.parent.leftChild == l) {
					l.parent.leftChild = null;
				} else {
					l.parent.rightChild = null;
				}
				l.parent = null;

				// Bubble down the root
				bubbleDown(this.root);

				// Return the old root value
				return minVal;
				
			}
		}

	}

	public int getSize() {

		return heapSize;

	}

	// Used for Testing: Returns the root of the bree used to represent this
	// binary MinHeap

	TreeNode getRoot() {

		return root;

	}

	// Used for Testing: Returns the value of latest

	TreeNode getLatest() {

		return latest;

	}

}
