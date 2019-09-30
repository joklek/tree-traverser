package com.joklek.tree.traversal.structure;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
public class Node<T> {

    private final T value;
    private NodePair<T> parents = new NodePair<>();
    private NodePair<T> children= new NodePair<>();
    private static final String BAD_NODE_MESSAGE = "Can't set parent, when it's already a child or parent";

    public Node(T value) {
        this.value = value;
    }

    public void setParentLeft(Node<T> parentLeft) {
        if(parentLeft != null && (parentLeft.equals(children.getLeft()) || parentLeft.equals(children.getRight()) || parentLeft.equals(parents.getRight()))) {
            throw new IllegalArgumentException(BAD_NODE_MESSAGE);
        }
        parents.setLeft(parentLeft);
    }

    public void setParentRight(Node<T> parentRight) {
        if(parentRight != null && (parentRight.equals(children.getLeft()) || parentRight.equals(children.getRight()) || parentRight.equals(parents.getLeft()))) {
            throw new IllegalArgumentException(BAD_NODE_MESSAGE);
        }
        parents.setRight(parentRight);
    }

    public void setChildLeft(Node<T> childLeft) {
        if(childLeft != null && (childLeft.equals(children.getRight()) || childLeft.equals(parents.getLeft()) || childLeft.equals(parents.getRight()))) {
            throw new IllegalArgumentException(BAD_NODE_MESSAGE);
        }
        children.setLeft(childLeft);
    }

    public void setChildRight(Node<T> childRight) {
        if(childRight != null && (childRight.equals(children.getLeft()) || childRight.equals(parents.getLeft()) || childRight.equals(parents.getRight()))) {
            throw new IllegalArgumentException(BAD_NODE_MESSAGE);
        }
        children.setRight(childRight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        return new EqualsBuilder()
                .append(value, node.value)
                .append(parents, node.parents)
                .append(children, node.children)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .append(parents)
                .append(children)
                .toHashCode();
    }
}