package albano.justin.datastructs.trees;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO Class documentation
 * TODO Add deletion method
 * 
 * <h1>Citations</h1>
 * <ul>
 * <li>The algorithms for insert and isInTree based on the algorithms depicted
 * in Sanford Computer Science webpage on Binary Trees (<a
 * href="http://cslibrary.stanford.edu/110/BinaryTrees.html">here</a>)</li>
 * </ul>
 * 
 * @author Justin Albano
 */
public class BinaryTree<T extends Comparable<T>> {

	/***************************************************************************
	 * Attributes
	 **************************************************************************/

	protected BinaryTreeNode<T> rootNode;
	protected Class<T> dataType;
	protected int size;

	/***************************************************************************
	 * Constructors
	 **************************************************************************/

	/**
	 * Creates a default, empty tree of data.
	 * 
	 * @param dataType
	 *            The class of the data being stored in the tree.
	 */
	public BinaryTree (Class<T> dataType) {
		this.rootNode = null;
		this.dataType = dataType;
		this.size = 0;
	}

	/**
	 * Creates a populated tree of data, using the provided array as the default
	 * data for the tree. Upon adding the data provided, the tree is guaranteed
	 * to be balanced.
	 * 
	 * <h1>Postconditions</h1>
	 * <ol>
	 * <li>The tree is balanced</li>
	 * </ol>
	 * 
	 * @param dataType
	 *            The class of the data being stored in the tree.
	 * @param data
	 *            An array of data to use as the default data for the tree.
	 */
	public BinaryTree (Class<T> dataType, T[] data) {

		// Construct the tree through the default constructor
		this(dataType);

		if (data != null) {
			// Sort the array and add it to the tree
			Arrays.sort(data);
			this.balanceWithStoredArray(data, 0, data.length - 1);
		}
	}

	/**
	 * Creates a populated tree of data, using the provided list as the default
	 * data for the tree.
	 * 
	 * @param dataType
	 *            The class of the data being stored in the tree.
	 * @param data
	 *            A list of data to use as the default data for the tree.
	 */
	public BinaryTree (Class<T> dataType, List<T> data) {

		// Construct the tree through the default constructor
		this(dataType);
	}

	/***************************************************************************
	 * Methods
	 **************************************************************************/

	/**
	 * Returns number of elements currently stored in the tree.
	 * 
	 * @return
	 *         The number of elements currently stored in the tree.
	 */
	public int size () {
		return this.size;
	}

	/**
	 * Obtains the height of the tree.
	 * 
	 * @author Dr. Richard Stansbury, Embry-Riddle Aeronautical University
	 * 
	 * @return
	 *         The height of the tree.
	 */
	public int height () {

		// Calculate the height of the tree starting with the top node
		return this.height(this.rootNode);
	}

	/**
	 * Helper method that obtains the height of a node in the tree.
	 * 
	 * @author Dr. Richard Stansbury, Embry-Riddle Aeronautical University
	 * 
	 * @param rootNode
	 *            The node to obtain the height of.
	 * @return
	 *         The height of the tree, based on the current node.
	 */
	private int height (BinaryTreeNode<T> rootNode) {

		if (rootNode == null) {
			// There is no node at the current location (base case)
			return 0;
		}
		else {
			// Return the maximum height of the left and right subtrees (a 1 is
			// added to the maximum in order to count the current node)
			return 1 + Math.max(this.height(rootNode.getLeftNode()), this.height(rootNode.getRightNode()));
		}
	}

	/**
	 * Checks if the tree is balanced.
	 * 
	 * @author Dr. Richard Stansbury, Embry-Riddle Aeronautical University
	 * 
	 * @return
	 *         True if the tree is balanced, and false if it is not.
	 */
	public boolean isBalanced () {

		// Check if the root of the tree is balanced
		return this.isBalanced(this.rootNode);
	}

	/**
	 * Checks if the subtree provided, with the current node as the root of the
	 * tree, is balanced.
	 * 
	 * @author Dr. Richard Stansbury, Embry-Riddle Aeronautical University
	 * 
	 * @param rootNode
	 *            The root of the subtree to check.
	 * @return
	 *         True if the subtree provided is balanced, and false if it is not.
	 */
	private boolean isBalanced (BinaryTreeNode<T> rootNode) {

		if (rootNode == null) {
			// The tree is empty and is therefore balanced
			return true;
		}
		else if ((rootNode.getLeftNode() == null) && (rootNode.getRightNode() == null)) {
			// This node is leaf and is balanced
			return true;
		}
		else if (Math.abs(this.getBalanceFactor(rootNode)) <= 1) {
			// The balance factor for the current node and the current node is
			// balanced if the left and right subtrees are balanced
			return (this.isBalanced(rootNode.getLeftNode()) && this.isBalanced(rootNode.getRightNode()));
		}
		else {
			// Return false in all other cases
			return false;
		}
	}

	/**
	 * Obtains the balance factor for a subtree, based on the current node as
	 * the root of the tree.
	 * 
	 * @author Dr. Richard Stansbury, Embry-Riddle Aeronautical University
	 * 
	 * @param rootNode
	 *            The root of the subtree.
	 * @return
	 *         The balance factor, or the difference between the height of the
	 *         left and right subtrees.
	 */
	private int getBalanceFactor (BinaryTreeNode<T> rootNode) {

		// Return the balance factor, which is equal to the difference between
		// the left and right subtree
		return (this.height(rootNode.getLeftNode()) - this.height(rootNode.getRightNode()));
	}

	/**
	 * Balances the tree, ensuring that all nodes in the the tree are popularly
	 * balanced.
	 * 
	 * @author Dr. Richard Stansbury, Embry-Riddle Aeronautical University
	 */
	public void rebalance () {

		// Convert the tree into an array
		T[] sortedArray = this.toSortedArray();

		// Clear the tree
		this.rootNode = null;

		// Reinsert the elements of the array into the tree
		this.balanceWithStoredArray(sortedArray, 0, this.size - 1);

	}

	/**
	 * Obtains a sorted array of the data contained within the tree.
	 * 
	 * <h1>Postconditions</h1>
	 * <ol>
	 * <li>Returned array is sorted</li>
	 * </ol>
	 * 
	 * @return
	 *         A sorted array of the data contained within the tree.
	 */
	@SuppressWarnings("unchecked")
	public T[] toSortedArray () {

		// Create a generic array of the data
		T[] arrayToFill = (T[]) Array.newInstance(this.dataType, this.size);

		// Perform in order traversal of the tree to create array
		this.inOrderArrayConversion(this.rootNode, arrayToFill, 0);

		return arrayToFill;
	}

	/**
	 * Obtains a sorted list of the data contained within the tree.
	 * 
	 * <h1>Postconditions</h1>
	 * <ol>
	 * <li>list is sorted</li>
	 * </ol>
	 * 
	 * @return
	 *         A sorted list of the data contained within the tree.
	 */
	public List<T> toSortedList () {

		// Create a list to store the data
		List<T> list = new ArrayList<T>();

		// Obtain the sorted data
		this.inOrderListConversion(this.rootNode, list);

		return list;
	}

	/**
	 * Adds the elements of the subtree provided to the list.
	 * 
	 * <h1>Postconditions</h1>
	 * <ol>
	 * <li>list is sorted</li>
	 * </ol>
	 * 
	 * @param subtree
	 *            The subtree from which to obtain data.
	 * @param list
	 *            The list to insert the data into.
	 */
	private void inOrderListConversion (BinaryTreeNode<T> subtree, List<T> list) {

		if (subtree == null) {
			// This subtree is empty
			return;
		}
		else {
			// Perform in-order insertion into the list
			this.inOrderListConversion(subtree.getLeftNode(), list);
			list.add(subtree.getData());
			this.inOrderListConversion(subtree.getRightNode(), list);
		}
	}

	/**
	 * Adds the elements of the subtree provided into the provided array,
	 * starting with the index provided.
	 * 
	 * <h1>Preconditions</h1>
	 * <ol>
	 * <li>array is large enough to hold the values in the tree</li>
	 * </ol>
	 * 
	 * <h1>Postconditions</h1>
	 * <ol>
	 * <li>array is sorted</li>
	 * </ol>
	 * 
	 * @param subtree
	 *            The subtree to obtain data from.
	 * @param array
	 *            The array to store the data of the subtree.
	 * @param index
	 *            The starting index to insert into the array. For example, if
	 *            an index of 3 is provided, the first data element found in the
	 *            provided subtree will be inserted into the provided array at
	 *            index 3.
	 * @return
	 *         The index of the next available cell in the array.
	 */
	private int inOrderArrayConversion (BinaryTreeNode<T> subtree, T[] array, int index) {

		if (subtree == null) {
			// This subtree is empty
			return index;
		}
		else {
			// Perform in-order traversal of the left subtree
			int leftSubtreeFinalIndex = this.inOrderArrayConversion(subtree.getLeftNode(), array, index);

			// Insert the data at the current node into the array
			array[leftSubtreeFinalIndex] = subtree.getData();

			// Perform in-order traversal of the right subtree (the index is
			// incremented by 1 to skip the element in the array that was just
			// added above)
			int rightSubtreeFinalIndex = this.inOrderArrayConversion(subtree.getRightNode(), array, leftSubtreeFinalIndex + 1);

			return rightSubtreeFinalIndex;
		}
	}

	/**
	 * Balances the tree using a stored array. This balancing does not happen in
	 * place, but rather, the elements in the provided array are inserted into
	 * the tree.
	 * 
	 * <h1>Preconditions</h1>
	 * <ol>
	 * <li>The tree is empty</li>
	 * </ol>
	 * 
	 * <h1>Postconditions</h1>
	 * <ol>
	 * <li>The tree is balanced</li>
	 * </ol>
	 * 
	 * @author Dr. Richard Stansbury, Embry-Riddle Aeronautical University
	 * 
	 * @param data
	 *            An array containing the data to be inserted into the tree.
	 * @param first
	 *            Index of the first element in the array.
	 * @param last
	 *            Index of the last element in the array.
	 */
	private void balanceWithStoredArray (T[] data, int first, int last) {

		// Variable to store the middle index
		int middle = 0;

		if (first <= last) {
			// The first index is less than or equal to the last index

			// Insert the middle element into the tree
			middle = (int) ((first + last) / 2);
			this.insert(data[middle]);

			// Recursively perform the insertions on the left and right portions
			// of the remaining elements in the array
			this.balanceWithStoredArray(data, first, middle - 1);
			this.balanceWithStoredArray(data, middle + 1, last);
		}

	}

	/**
	 * Checks if the target data provided is found within the binary tree.
	 * 
	 * @param target
	 *            The data to search for in the tree.
	 * @return
	 *         True if the target data is found in the tree; false if the target
	 *         data is not found in the tree.
	 */
	public boolean isInTree (T target) {

		// Check if the target is in the tree
		return this.isInTree(this.rootNode, target);
	}

	/**
	 * Helper method that is used to recursively check if a target is in the
	 * binary tree.
	 * 
	 * @param rootNode
	 *            The root node that is being searched for the target data.
	 * @param target
	 *            The data to search for in the tree.
	 * @return
	 *         True if the target data is found in the tree; false if the target
	 *         data is not found in the tree.
	 */
	private boolean isInTree (BinaryTreeNode<T> rootNode, T target) {

		if (rootNode == null) {
			// The current node is null (base case)
			return false;
		}
		else if (target.compareTo(rootNode.getData()) == 0) {
			// The data being searched for is found at the current node
			return true;
		}
		else if (target.compareTo(rootNode.getData()) < 0) {
			// The data being searched for is less than the current data;
			// therefore, recurse down the left side of the binary tree
			return this.isInTree(rootNode.getLeftNode(), target);
		}
		else if (target.compareTo(rootNode.getData()) > 0) {
			// The data being searched for is greater than the current data;
			// therefore, recurse down the right side of the binary tree
			return this.isInTree(rootNode.getRightNode(), target);
		}
		else {
			// In all other cases, return false; this is done to ensure there is
			// a boolean value returned in all cases
			return false;
		}
	}

	/**
	 * Insert data into the binary tree.
	 * 
	 * @param data
	 *            The data to insert into the binary tree.
	 */
	public void insert (T data) {

		// Insert the data into the tree
		this.rootNode = this.insert(this.rootNode, data);

		// Increment the size of the tree
		this.size++;
	}

	/**
	 * Helper method used to insert data into the binary search tree.
	 * 
	 * @param rootNode
	 *            The root node to compare data with the supplied data.
	 * @param data
	 *            The data to add to the binary tree.
	 * @return
	 *         The node that was added, or the current node (supplied as a
	 *         parameter) if no node was added.
	 */
	private BinaryTreeNode<T> insert (BinaryTreeNode<T> rootNode, T data) {

		if (rootNode == null) {
			// The current node should hold the data but has not been created
			rootNode = new BinaryTreeNode<T>(data);
			return rootNode;
		}
		else if (data.compareTo(rootNode.getData()) <= 0) {
			// The data being added is less then or equal to the data at the
			// current node and should be added to the left subtree
			rootNode.setLeftNode(this.insert(rootNode.getLeftNode(), data));
		}
		else if (data.compareTo(rootNode.getData()) > 0) {
			// The data being added is greater than the data at the current node
			// and should be added to the right subtree
			rootNode.setRightNode(this.insert(rootNode.getRightNode(), data));
		}

		// In all other cases, return the current node
		return rootNode;
	}
}
