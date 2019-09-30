package com.joklek.tree.traversal.structure;

import lombok.Getter;

@Getter
public class Node<T> {

    private final T value;
    private NodePair<T> children = new NodePair<>();
    private static final String BAD_NODE_MESSAGE = "Can't set parent, when it's already a child";

    public Node(T value) {
        this.value = value;
    }

    public void setChildLeft(Node<T> childLeft) {
        if(childLeft != null && childLeft.equals(children.getRight())) {
            throw new IllegalArgumentException(BAD_NODE_MESSAGE);
        }
        children.setLeft(childLeft);
    }

    public void setChildRight(Node<T> childRight) {
        if(childRight != null && childRight.equals(children.getLeft())) {
            throw new IllegalArgumentException(BAD_NODE_MESSAGE);
        }
        children.setRight(childRight);
    }
}