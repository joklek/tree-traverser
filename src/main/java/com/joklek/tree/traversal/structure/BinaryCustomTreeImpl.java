package com.joklek.tree.traversal.structure;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.text.MessageFormat;
import java.util.*;

@Getter
public class BinaryCustomTreeImpl implements BinaryCustomTree {

    private Node root;
    private Map<Integer, List<Node>> rows = new HashMap<>();

    public BinaryCustomTreeImpl() {
    }

    public BinaryCustomTreeImpl(Node root) {
        this.root = root;
        this.rows.put(1, Collections.singletonList(root));
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
    public void addRow(List<Node> bottomRow) {
        int futureRowLength = getFutureRowLength();
        if(bottomRow.size() != futureRowLength) {
            throw new IllegalArgumentException(MessageFormat.format("Expected list of size {0}, but got size {1}", futureRowLength, bottomRow.size()));
        }
        rows.put(getDepth() + 1, bottomRow);
        int depth = getDepth();

        if(root == null && bottomRow.size() == 1) {
            root = bottomRow.get(0);
        }
        else {
            List<Node> parentRow = rows.get(depth-1);
            for (int i = 0; i < futureRowLength; i++) {
                Node child = bottomRow.get(i);

                Pair<Node, Node> parents = getParents(i, parentRow);
                Optional.ofNullable(parents.getLeft()).ifPresent(leftParent -> {
                    leftParent.setChildRight(child);
                    child.setParentLeft(leftParent);
                });
                Optional.ofNullable(parents.getRight()).ifPresent(rightParent -> {
                    rightParent.setChildLeft(child);
                    child.setParentRight(rightParent);
                });
            }
        }
    }

    private Pair<Node, Node> getParents(int childIndex, List<Node> parentRow) {
        Node leftParent = (childIndex - 1) >= 0 ? parentRow.get(childIndex - 1) : null;
        Node rightParent = childIndex < parentRow.size()  ? parentRow.get(childIndex) : null;
        return Pair.of(leftParent, rightParent);
    }
}
