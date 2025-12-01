/**
 * Represents a node in a binary tree, implementing Comparable interface.
 * 
 * Each node contains data, left and right child references, and a height attribute.
 * 
 * @author Hussein Suleman
 * @version 1.0
 * @since 2017-03-26
 */
public class BinaryTreeNode implements Comparable<BinaryTreeNode> {
    /** The data stored in this node. */
    Object data;
    
    /** Reference to the left child node. */
    BinaryTreeNode left;
    
    /** Reference to the right child node. */
    BinaryTreeNode right;
    
    /** Height of the node in the tree. */
    int height;
    
    /**
     * Constructs a node with given data and null children.
     * 
     * @param d The data to be stored in the node
     */
    public BinaryTreeNode(Object d) {
        data = d;
        left = null;
        right = null;
        height = 0;
    }
    
    /**
     * Constructs a node with given data and child nodes.
     * 
     * @param d The data to be stored in the node
     * @param l The left child node
     * @param r The right child node
     */
    public BinaryTreeNode(Object d, BinaryTreeNode l, BinaryTreeNode r) {
        data = d;
        left = l;
        right = r;
        height = 0;
    }
    
    /**
     * Returns the left child of the node.
     * 
     * @return The left child node
     */
    public BinaryTreeNode getLeft() { 
        return left; 
    }
    
    /**
     * Returns the right child of the node.
     * 
     * @return The right child node
     */
    public BinaryTreeNode getRight() { 
        return right; 
    }
    
    /**
     * Compares this node with another node based on their data.
     * 
     * @param other The node to compare with
     * @return A negative integer, zero, or a positive integer as this node's 
     *         data is less than, equal to, or greater than the other node's data
     * @throws ClassCastException if the data is not comparable
     */
    @Override
    public int compareTo(BinaryTreeNode other) {
        if (this.data instanceof Comparable && other.data instanceof Comparable) {
            return ((Comparable) this.data).compareTo(other.data);
        }
        throw new ClassCastException("Data is not comparable");
    }
}