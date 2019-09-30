package com.joklek.tree.traversal.structure;

import lombok.Getter;

import java.text.MessageFormat;
import java.util.*;

@Getter
public class PyramidImpl implements Pyramid<Integer> {

    private Node root;
    private Map<Integer, List<Node<Integer>>> rows = new HashMap<>();

    public PyramidImpl() {
    }

    @Override
    public int getDepth() {
        return rows.size();
    }

    @Override
    public int getFutureRowLength() {
        return getDepth() + 1;
    }

    @Override
    public void addRow(List<Node<Integer>> bottomRow) {
        int futureRowLength = getFutureRowLength();
        if(bottomRow.size() != futureRowLength) {
            throw new IllegalArgumentException(MessageFormat.format("Expected list of size {0}, but got size {1}", futureRowLength, bottomRow.size()));
        }
        rows.put(getDepth() + 1, bottomRow);
        int depth = getDepth();

        if(root == null && futureRowLength == 1) {
            root = bottomRow.get(0);
        }
        else {
            List<Node<Integer>> parentRow = rows.get(depth-1);
            for (int i = 0; i < futureRowLength; i++) {
                Node<Integer> child = bottomRow.get(i);

                NodePair<Integer> parents = getParents(i, parentRow);
                Optional.ofNullable(parents.getLeft()).ifPresent(leftParent -> leftParent.setChildRight(child));
                Optional.ofNullable(parents.getRight()).ifPresent(rightParent -> rightParent.setChildLeft(child));
            }
        }
    }

    private NodePair<Integer> getParents(int childIndex, List<Node<Integer>> parentRow) {
        Node<Integer> leftParent = (childIndex - 1) >= 0 ? parentRow.get(childIndex - 1) : null;
        Node<Integer> rightParent = childIndex < parentRow.size() ? parentRow.get(childIndex) : null;
        return new NodePair<>(leftParent, rightParent);
    }
}
