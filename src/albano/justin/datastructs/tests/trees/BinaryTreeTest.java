package albano.justin.datastructs.tests.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import albano.justin.datastructs.trees.BinaryTree;

/**
 * TODO Class documentation
 * 
 * @author Justin Albano
 */
public class BinaryTreeTest {

	/***************************************************************************
	 * Setup & Tear Down
	 **************************************************************************/

	@Before
	public void setUp () throws Exception {}

	@After
	public void tearDown () throws Exception {}

	/***************************************************************************
	 * Tests
	 **************************************************************************/

	/**
	 * Tests that the size of an empty tree is zero.
	 */
	@Test
	public void testSizeOfEmptyTree () {

		// Create an empty tree and ensure its size is 0
		BinaryTree<Integer> tree = new BinaryTree<>(Integer.class);
		assertEquals("An empty binary tree size should be 0", 0, tree.size());

		// Create a tree with no data (array) and ensure its size is 0
		Integer[] arrayOfInts = null;
		tree = new BinaryTree<>(Integer.class, arrayOfInts);
		assertEquals("A binary tree with null array data should be of size 0", 0, tree.size());

		// Create a tree with no data (list) and ensure its size is 0
		List<Integer> listOfInts = null;
		tree = new BinaryTree<>(Integer.class, listOfInts);
		assertEquals("A binary tree with null list data should be of size 0", 0, tree.size());
	}

	/**
	 * Tests the insert mechanism for the tree when inserting multiple pieces of
	 * data into the tree. This test checks to ensure that the data is present
	 * in the tree after insertion and that the size of the tree is correct
	 * after insertion.
	 */
	@Test
	public void testInsertMultipleItems () {

		// Create an empty tree
		BinaryTree<Integer> tree = new BinaryTree<>(Integer.class);

		// Insert multiple items into the tree
		tree.insert(10);
		tree.insert(20);
		tree.insert(5);
		tree.insert(25);

		// Ensure that these values are in the tree
		assertTrue("Item of value 10 should be present in tree after insertion", tree.isInTree(10));
		assertTrue("Item of value 20 should be present in tree after insertion", tree.isInTree(20));
		assertTrue("Item of value 5 should be present in tree after insertion", tree.isInTree(5));
		assertTrue("Item of value 25 should be present in tree after insertion", tree.isInTree(25));

		// Ensure the correct size of the tree
		assertEquals("Size is 4 after adding 4 items to the tree", 4, tree.size());

	}

	/**
	 * Tests the height of the tree based on a predefined insertion order.
	 */
	@Test
	public void testHeightOfTree () {

		// Create an empty tree
		BinaryTree<Integer> tree = new BinaryTree<>(Integer.class);

		// Insert multiple items into the tree
		tree.insert(10);
		tree.insert(20);
		tree.insert(5);
		tree.insert(25);

		// Ensure the correct size of the tree
		assertEquals("The height of the tree, based on the insertion order, should be 3", 3, tree.height());
	}

	/**
	 * Tests the balancing of a tree based on predefined insertion orders.
	 */
	@Test
	public void testBalanceOfTree () {

		// Create a balanced tree
		BinaryTree<Integer> balancedTree = new BinaryTree<>(Integer.class);
		balancedTree.insert(10);
		balancedTree.insert(20);
		balancedTree.insert(5);
		balancedTree.insert(25);

		// Ensure the tree is balanced
		assertTrue("Ensure the balanced tree is considered balanced", balancedTree.isBalanced());

		// Create an unbalanced tree
		BinaryTree<Integer> unbalancedTree = new BinaryTree<>(Integer.class);
		unbalancedTree.insert(10);
		unbalancedTree.insert(20);
		unbalancedTree.insert(30);
		unbalancedTree.insert(40);

		// Ensure the tree is unbalanced
		assertTrue("Ensure the unbalanced tree is considered unbalanced", !unbalancedTree.isBalanced());

	}

	/**
	 * Tests that the array conversion of the tree contains all of the elements
	 * in the tree and is properly sorted.
	 */
	@Test
	public void testTreeToSortedArray () {

		// A set of out of order data
		Integer[] arrayOfNumbers = { 10, 15, 6, 12, 90, 14, 55, 0 };

		// Sort the array (to use later to check if the array returned by the
		// tree is actually sorted)
		Integer[] sortedArrayOfNumbers = arrayOfNumbers.clone();
		Arrays.sort(sortedArrayOfNumbers);

		// Create an empty tree
		BinaryTree<Integer> tree = new BinaryTree<>(Integer.class);

		for (int i = 0; i < arrayOfNumbers.length; i++) {
			// Add the numbers to the tree
			tree.insert(arrayOfNumbers[i]);
		}

		// Obtain the sorted array version of the tree
		Integer[] arrayFromTree = tree.toSortedArray();

		// Ensure the size of the converted and original arrays match
		assertEquals("The size of the sorted array matches the size of the original array", arrayOfNumbers.length, arrayFromTree.length);

		for (int i = 0; i < arrayFromTree.length; i++) {
			// Ensure all of the elements are present and sorted
			assertEquals("Element " + i + " matches", sortedArrayOfNumbers[i], arrayFromTree[i]);
		}

		/*
		 * Note: Since the sizes of the array are asserted to be equal, and the
		 * elements in the sorted array are present in the array obtained from
		 * the tree, then the elements in the array obtained from the tree match
		 * the original array element one-to-one. There do not exist any "extra"
		 * elements in the array obtained from the tree that are not present in
		 * the original array.
		 */
	}
	
	/**
	 * Tests that the list conversion of the tree contains all of the elements
	 * in the tree and is properly sorted.
	 */
	@Test
	public void testTreeToSortedList () {

		// A set of out of order data
		Integer[] arrayOfNumbers = { 10, 15, 6, 12, 90, 14, 55, 0 };
		List<Integer> list = Arrays.asList(arrayOfNumbers);

		// Sort the list (to use later to check if the array returned by the
		// tree is actually sorted)
		List<Integer> sortedList = new ArrayList<Integer>();
		Collections.sort(list);

		// Create an empty tree
		BinaryTree<Integer> tree = new BinaryTree<>(Integer.class);

		for (Integer value : list) {
			// Add the numbers to the tree
			tree.insert(value);
		}

		// Obtain the sorted list version of the tree
		List<Integer> listFromTree = tree.toSortedList();

		// Ensure the size of the converted and original listss match
		assertEquals("The size of the sorted array matches the size of the original array", list.size(), listFromTree.size());

		for (int i = 0; i < sortedList.size(); i++) {
			// Ensure all of the elements are present and sorted
			assertEquals("Element " + i + " matches", sortedList.get(i), listFromTree.get(i));
		}

		/*
		 * Note: Since the sizes of the lists are asserted to be equal, and the
		 * elements in the sorted list are present in the array obtained from
		 * the tree, then the elements in the list obtained from the tree match
		 * the original list element one-to-one. There do not exist any "extra"
		 * elements in the list obtained from the tree that are not present in
		 * the original list.
		 */
	}

	/**
	 * Tests that the rebalance() method properly rebalances the tree.
	 */
	@Test
	public void testBalancingTree () {

		// Create an empty tree
		BinaryTree<Integer> tree = new BinaryTree<>(Integer.class);

		// Add some values (to make the tree unbalanced)
		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
		tree.insert(40);

		// Ensure the tree is unbalanced
		assertTrue("Ensure the unbalanced tree is considered unbalanced", !tree.isBalanced());

		// Balance the tree
		tree.rebalance();

		// Ensure the tree is balanced
		assertTrue("Ensure the balanced tree is considered balanced", tree.isBalanced());
	}

	/**
	 * Tests the creation of the tree from an array. This test ensures that all
	 * the data in the array is present in the tree and that upon creation, the
	 * tree is balanced.
	 */
	@Test
	public void testCreateFromArray () {
		
		// An array of data from which to populate the tree
		Integer[] data = {1, 0, 4, 76, 58, 12, 9, 133};

		// Create an empty tree
		BinaryTree<Integer> tree = new BinaryTree<>(Integer.class, data);

		for (int i = 0; i < data.length; i++) {
			// Ensure that each value is present in the tree
			assertTrue("Element of value " + data[i] + " is in tree", tree.isInTree(data[i]));
		}

		// Ensure the tree is balanced
		assertTrue("Ensure the balanced tree is considered balanced", tree.isBalanced());
	}
}
