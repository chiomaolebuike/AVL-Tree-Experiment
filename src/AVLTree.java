/**
 * An implementation of a self-balancing AVL Tree data structure.
 * 
 * AVL Tree is a binary search tree that maintains balance through 
 * automatic rotations, ensuring that the height difference between 
 * left and right subtrees of any node is at most 1.
 * 
 * This implementation extends the basic Binary Tree and provides:
 * <ul>
 *   <li>Efficient insertion with O(log n) time complexity</li>
 *   <li>Self-balancing mechanism using rotations</li>
 *   <li>Performance metrics tracking</li>
 * </ul>
 * 
 * @author Hussein Suleman
 * @version 1.0
 * @since 2017-03-26
 */
public class AVLTree extends BinaryTree { 
    /** Number of comparisons made during insert operations. */
    private int insertComparisons = 0;
    
    /** Number of comparisons made during search operations. */
    private int searchComparisons = 0;
    
    /** Number of single rotations performed during tree balancing. */
    private int singleRotations = 0;
    
    /** Number of double rotations performed during tree balancing. */
    private int doubleRotations = 0;
    
    /** Total time spent on insertion operations in milliseconds. */
    private long insertionTime = 0;
    
    /** Total time spent on search operations in milliseconds. */
    private long searchTime = 0;
    
    /** Maximum height reached by the tree. */
    private int maxHeight = 0;

    /**
     * Calculates the height of a given node.
     * 
     * @param node The node to calculate height for
     * @return Height of the node, or -1 if node is null
     */
    public int height(BinaryTreeNode node) {
        return (node != null) ? node.height : -1;
    }

    /**
     * Calculates the balance factor of a node.
     * 
     * Balance factor is the height difference between right and left subtrees.
     * 
     * @param node The node to calculate balance factor for
     * @return The balance factor (right subtree height - left subtree height)
     */
    public int balanceFactor(BinaryTreeNode node) {
        return height(node.right) - height(node.left);
    }

    /**
     * Updates the height of a node based on its children's heights.
     * 
     * @param node The node whose height needs to be fixed
     */
    public void fixHeight(BinaryTreeNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * Performs a right rotation on the given node.
     * 
     * @param p The node to rotate
     * @return The new root of the rotated subtree
     */
    public BinaryTreeNode rotateRight(BinaryTreeNode p) {
        singleRotations++;  // Increment rotation counter
        BinaryTreeNode q = p.left;
        p.left = q.right;
        q.right = p;
        fixHeight(p);
        fixHeight(q);
        return q;
    }

    /**
     * Performs a left rotation on the given node.
     * 
     * @param q The node to rotate
     * @return The new root of the rotated subtree
     */
    public BinaryTreeNode rotateLeft(BinaryTreeNode q) {
        singleRotations++;  // Increment rotation counter
        BinaryTreeNode p = q.right;
        q.right = p.left;
        p.left = q;
        fixHeight(q);
        fixHeight(p);
        return p;
    }

    /**
     * Balances the tree at a given node using rotations.
     * 
     * Checks the balance factor and performs necessary rotations
     * to maintain the AVL tree property.
     * 
     * @param p The node to balance
     * @return The new root of the balanced subtree
     */
    public BinaryTreeNode balance(BinaryTreeNode p) {
        fixHeight(p);
        if (balanceFactor(p) == 2) {
            if (balanceFactor(p.right) < 0) {
                doubleRotations++; // Double rotation case
                p.right = rotateRight(p.right);
            }
            return rotateLeft(p);
        }
        if (balanceFactor(p) == -2) {
            if (balanceFactor(p.left) > 0) {
                doubleRotations++; // Double rotation case
                p.left = rotateLeft(p.left);
            }
            return rotateRight(p);
        }
        return p;
    }

    /**
     * Inserts an object into the AVL tree.
     * 
     * Performs the insertion and ensures tree balance is maintained.
     * 
     * @param d The object to insert
     */
    public void insert(Object d) {
        long startTime = System.nanoTime();
        root = insert(d, root);
        long endTime = System.nanoTime();
        insertionTime += (endTime - startTime) / 1_000_000;
        maxHeight = height(root);
    }

    /**
     * Recursive helper method for inserting an object.
     * 
     * @param d The object to insert
     * @param node The current node in the recursive traversal
     * @return The new root of the subtree after insertion and balancing
     */
    public BinaryTreeNode insert(Object d, BinaryTreeNode node) {
        if (node == null) return new BinaryTreeNode(d, null, null);

        insertComparisons++;
        if (((Comparable) d).compareTo(node.data) <= 0) {
            node.left = insert(d, node.left);
        } else {
            node.right = insert(d, node.right);
        }
        return balance(node);
    }

    /**
     * Finds a node with a given key in the tree.
     * 
     * @param key The key to search for
     * @return The node containing the key, or null if not found
     */
    public BinaryTreeNode find(String key) {
        long startTime = System.nanoTime();
        BinaryTreeNode result = find(root, key);
        long endTime = System.nanoTime();
        searchTime += (endTime - startTime) / 1_000_000; // Convert to milliseconds
        return result;
    }

    /**
     * Recursive helper method for finding a node with a given key.
     * 
     * @param node The current node in the recursive search
     * @param key The key to search for
     * @return The node containing the key, or null if not found
     */
    public BinaryTreeNode find(BinaryTreeNode node, String key) { 
        searchComparisons++;  // Count comparisons
        if (node == null) return null;

        Statement currentStatement = (Statement) node.data;
        int compareResult = key.toLowerCase().compareTo(currentStatement.getTerm().toLowerCase());

        if (compareResult == 0) {
            return node;
        } else if (compareResult < 0) {
            return find(node.left, key);
        } else {
            return find(node.right, key);
        }
    }

    /**
     * Prints performance metrics of the AVL tree operations.
     * 
     * Outputs details about:
     * <ul>
     *   <li>Total comparisons</li>
     *   <li>Search and insert comparisons</li>
     *   <li>Single and double rotations</li>
     *   <li>Insertion and search times</li>
     *   <li>Maximum tree height</li>
     * </ul>
     */
    public void printMetrics() {
        System.out.println("Total comparisons: " + (searchComparisons + insertComparisons));
        System.out.println("Search comparisons: " + searchComparisons);
        System.out.println("Insert comparisons: " + insertComparisons);
        System.out.println("Single rotations: " + singleRotations);
        System.out.println("Double rotations: " + doubleRotations);
        System.out.println("Total insertion time (ms): " + insertionTime);
        System.out.println("Total search time (ms): " + searchTime);
        System.out.println("Max tree height: " + maxHeight);
    }
}