package com.joklek.tree.traversal.structure;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
public class Node<T> {

    private final T value;
    private Node parentLeft;
    private Node parentRight;
    private Node childLeft;
    private Node childRight;
    private static final String BAD_NODE_MESSAGE = "Can't set parent, when it's already a child or parent";

    public Node(T value) {
        this.value = value;
    }

    public void setParentLeft(Node parentLeft) {
        if(parentLeft != null && (parentLeft.equals(childLeft) || parentLeft.equals(childRight) || parentLeft.equals(parentRight))) {
            throw new IllegalArgumentException(BAD_NODE_MESSAGE);
        }
        this.parentLeft = parentLeft;
    }

    public void setParentRight(Node parentRight) {
        if(parentRight != null && (parentRight.equals(childLeft) || parentRight.equals(childRight) || parentRight.equals(parentLeft))) {
            throw new IllegalArgumentException(BAD_NODE_MESSAGE);
        }
        this.parentRight = parentRight;
    }

    public void setChildLeft(Node childLeft) {
        if(childLeft != null && (childLeft.equals(childRight) || childLeft.equals(parentLeft) || childLeft.equals(parentRight))) {
            throw new IllegalArgumentException(BAD_NODE_MESSAGE);
        }
        this.childLeft = childLeft;
    }

    public void setChildRight(Node childRight) {
        if(childRight != null && (childRight.equals(childLeft) || childLeft.equals(parentLeft) || childLeft.equals(parentRight))) {
            throw new IllegalArgumentException(BAD_NODE_MESSAGE);
        }
        this.childRight = childRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return new EqualsBuilder()
                .append(parentLeft, node.parentLeft)
                .append(parentRight, node.parentRight)
                .append(childLeft, node.childLeft)
                .append(childRight, node.childRight)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(parentLeft)
                .append(parentRight)
                .append(childLeft)
                .append(childRight)
                .toHashCode();
    }
}