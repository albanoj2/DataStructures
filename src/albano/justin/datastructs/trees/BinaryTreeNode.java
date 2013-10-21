package albano.justin.datastructs.trees;

/**
 * TODO Class documentation
 * 
 * @author Justin Albano
 */
public class BinaryTreeNode<T extends Comparable<T>> {

	/***************************************************************************
	 * Attributes
	 **************************************************************************/

	private T data;
	private BinaryTreeNode<T> leftNode;
	private BinaryTreeNode<T> rightNode;

	/***************************************************************************
	 * Constructors
	 **************************************************************************/

	/**
	 * Creates a binary tree node with default data for the node.
	 * 
	 * @param data
	 *            The default data to store within the node.
	 */
	public BinaryTreeNode (T data) {

		// Set the data for this node
		this.data = data;
	}

	/**
	 * Creates a binary tree node with default data, and with set left and right
	 * binary tree nodes.
	 * 
	 * @param data
	 *            The default data to store within in the node.
	 * @param left
	 *            The left binary tree node.
	 * @param right
	 *            The right binary tree node.
	 */
	public BinaryTreeNode (T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {

		// Set the data for this node
		this(data);

		// Set the left and right nodes of the binary tree node
		this.leftNode = left;
		this.rightNode = right;
	}

	/***************************************************************************
	 * Methods
	 **************************************************************************/

	/**
	 * Set the data for this binary tree node.
	 * 
	 * @param data
	 *            The data stored in this binary tree node.
	 */
	public void setData (T data) {
		this.data = data;
	}

	public T getData () {
		return this.data;
	}

	public void setLeftNode (BinaryTreeNode<T> left) {
		this.leftNode = left;
	}

	public BinaryTreeNode<T> getLeftNode () {
		return this.leftNode;
	}

	public void setRightNode (BinaryTreeNode<T> right) {
		this.rightNode = right;
	}

	public BinaryTreeNode<T> getRightNode () {
		return this.rightNode;
	}
}
