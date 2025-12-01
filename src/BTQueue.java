/**
 * A specialized queue implementation for binary tree node traversal.
 * 
 * This queue provides efficient storage and retrieval of binary tree nodes
 * with error handling and utility methods.
 * 
 * @author Hussein Suleman (Enhanced by AI Assistant)
 * @version 2.0
 * @since 2024-03-28
 */
public class BTQueue {
    /** The first node in the queue. */
    private BTQueueNode head;

    /** The last node in the queue. */
    private BTQueueNode tail;

    /** The current size of the queue. */
    private int size;

    /**
     * Constructs an empty queue with initial size zero.
     */
    public BTQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Retrieves and removes the next node from the queue.
     * 
     * @return The next binary tree node, or null if the queue is empty
     * @throws IllegalStateException if the queue's internal state is corrupted
     */
    public BinaryTreeNode getNext() {
        if (head == null) {
            return null;
        }
        
        try {
            BTQueueNode qnode = head;
            head = head.next;
            
            // Update tail if queue becomes empty
            if (head == null) {
                tail = null;
            }
            
            size--;
            return qnode.node;
        } catch (Exception e) {
            throw new IllegalStateException("Queue traversal failed", e);
        }
    }

    /**
     * Adds a binary tree node to the end of the queue.
     * 
     * @param node The binary tree node to enqueue
     * @throws IllegalArgumentException if the node is null
     */
    public void enQueue(BinaryTreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("Cannot enqueue null node");
        }

        BTQueueNode newNode = new BTQueueNode(node, null);
        
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        
        size++;
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the queue.
     * 
     * @return the current size of the queue
     */
    public int size() {
        return size;
    }

    /**
     * Peeks at the next node in the queue without removing it.
     * 
     * @return the next node, or null if the queue is empty
     */
    public BinaryTreeNode peek() {
        return head != null ? head.node : null;
    }

    /**
     * Clears the entire queue, removing all elements.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}