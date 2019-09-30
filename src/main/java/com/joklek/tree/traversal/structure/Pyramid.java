package com.joklek.tree.traversal.structure;

import java.util.List;

/**
 * A tree data structure where siblings are connected like this
 * Row 1        *
 *             /\
 * Row 2      *  *
 *           /\  /\
 * Row 3    *  *  *
 * Notice that on row 3, the second node has two parents, and the two parents share one of the children vice versa.
 */
public interface Pyramid<T> {

    /**
     * Gets the root node of the tree
     * @return root node
     */
    Node<T> getRoot();

    /**
     * Gets the depth of the tree in number of rows. A tree with only the head node would return 1.
     * @return number of rows in tree
     */
    int getDepth();

    /**
     * Returns the expected number of future bottom row nodes.
     * For example if the tree has 3 rows, the expected number of nodes is 4 (please notice, that it's also the number of the future row)
     * @return number of expected rows in bottom row
     */
    int getFutureRowLength();

    /**
     * Adds a list of nodes to the bottom of the tree
     * @param bottomRow list of nodes. Size of the list is expected to be the same as the result of {@link #getFutureRowLength()}
     */
    void addRow(List<Node<T>> bottomRow);
}
