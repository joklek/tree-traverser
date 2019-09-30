package com.joklek.tree.traversal.structure;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class NodePair<T> {

    private Node<T> left;
    private Node<T> right;

    public NodePair(Node<T> left, Node<T> right) {
        if(Objects.equals(left, right)) {
            throw new IllegalArgumentException("Node pair can't consist of identical nodes");
        }
        this.left = left;
        this.right = right;
    }

    public void setLeft(Node<T> left) {
        if(Objects.equals(left, this.right)) {
            throw new IllegalArgumentException("Node pair can't consist of identical nodes");
        }
        this.left = left;
    }

    public void setRight(Node<T> right) {
        if(Objects.equals(right, this.left)) {
            throw new IllegalArgumentException("Node pair can't consist of identical nodes");
        }
        this.right = right;
    }

    public List<Node<T>> getAsList() {
        return Stream.of(left, right)
                .filter(Objects::isNull)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        NodePair<?> nodePair = (NodePair<?>) o;

        return new EqualsBuilder()
                .append(left, nodePair.left)
                .append(right, nodePair.right)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(left)
                .append(right)
                .toHashCode();
    }
}
