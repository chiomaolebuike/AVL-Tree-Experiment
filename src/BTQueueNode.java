public class BTQueueNode {
    /** The binary tree node stored in this queue node. */
    BinaryTreeNode node;

    /** Reference to the next queue node. */
    BTQueueNode next;

    /**
     * Constructs a queue node with a binary tree node and next reference.
     * 
     * @param n The binary tree node to store
     * @param nxt Reference to the next queue node
     */
    public BTQueueNode(BinaryTreeNode n, BTQueueNode nxt) {
        node = n;
        next = nxt;
    }
}

/**
 * Represents a node in a binary tree.
 */
class BinaryTreeNode {
    /** The data stored in this node. */
    Object data;

    /** Reference to the left child node. */
    BinaryTreeNode left;

    /** Reference to the right child node. */
    BinaryTreeNode right;

    /**
     * Constructs a binary tree node with the given data.
     * 
     * @param data The data to be stored in the node
     */
    public BinaryTreeNode(Object data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    /**
     * Gets the data stored in this node.
     * 
     * @return The data in the node
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the data for this node.
     * 
     * @param data The data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Gets the left child of this node.
     * 
     * @return The left child node
     */
    public BinaryTreeNode getLeft() {
        return left;
    }

    /**
     * Sets the left child of this node.
     * 
     * @param left The left child node to set
     */
    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    /**
     * Gets the right child of this node.
     * 
     * @return The right child node
     */
    public BinaryTreeNode getRight() {
        return right;
    }

    /**
     * Sets the right child of this node.
     * 
     * @param right The right child node to set
     */
    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}