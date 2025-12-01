/**
 * A basic implementation of a Binary Tree data structure.
 * 
 * Provides methods for tree traversal, height calculation, 
 * and size determination.
 * 
 * @author Hussein Suleman
 * @version 1.0
 * @since 2017-03-26
 */
public class BinaryTree {
    /** The root node of the binary tree. */
    BinaryTreeNode root;
    
    /**
     * Constructs an empty binary tree.
     */
    public BinaryTree() {
        root = null;
    }
    
    /**
     * Calculates the height of the entire tree.
     * 
     * @return The height of the tree, or -1 if the tree is empty
     */
    public int getHeight() {
        return getHeight(root);
    }   

    /**
     * Recursively calculates the height of a specific node.
     * 
     * @param node The node to calculate height for
     * @return The height of the node, or -1 if the node is null
     */
    public int getHeight(BinaryTreeNode node) {
        if (node == null)
            return -1;
        else
            return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }
    
    /**
     * Calculates the total number of nodes in the tree.
     * 
     * @return The size of the tree
     */
    public int getSize() {
        return getSize(root);
    }   

    /**
     * Recursively calculates the number of nodes in a subtree.
     * 
     * @param node The root of the subtree
     * @return The number of nodes in the subtree
     */
    public int getSize(BinaryTreeNode node) {
        if (node == null)
            return 0;
        else
            return 1 + getSize(node.getLeft()) + getSize(node.getRight());
    }
    
    /**
     * Visits (prints) the data of a given node.
     * 
     * @param node The node to visit
     */
    public void visit(BinaryTreeNode node) {
        System.out.println(node.data);
    }
    
    /** Performs pre-order traversal of the entire tree. */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * Recursively performs pre-order traversal.
     * 
     * @param node The current node to start traversal from
     */
    public void preOrder(BinaryTreeNode node) {
        if (node != null) {
            visit(node);
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }   
    }

    /** Performs post-order traversal of the entire tree. */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * Recursively performs post-order traversal.
     * 
     * @param node The current node to start traversal from
     */
    public void postOrder(BinaryTreeNode node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            visit(node);
        }   
    }

    /** Performs in-order traversal of the entire tree. */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * Recursively performs in-order traversal.
     * 
     * @param node The current node to start traversal from
     */
    public void inOrder(BinaryTreeNode node) {
        if (node != null) {
            inOrder(node.getLeft());
            visit(node);
            inOrder(node.getRight());
        }   
    }

    /** Performs level-order (breadth-first) traversal of the tree. */
    public void levelOrder() {
        if (root == null)
            return;
        BTQueue q = new BTQueue();
        q.enQueue(root);
        BinaryTreeNode node;
        while ((node = q.getNext()) != null) {
            visit(node);
            if (node.getLeft() != null)
                q.enQueue(node.getLeft());
            if (node.getRight() != null)
                q.enQueue(node.getRight());
        }
    }
}