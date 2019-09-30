package com.joklek.tree.traversal.structure;

import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class NodePair<T> {

    private Node<T> left;
    private Node<T> right;
    private static final String EXCEPTION_MESSAGE = "Node pair can't consist of identical nodes";

    public NodePair() {
    }

    public NodePair(Node<T> left, Node<T> right) {
        if(Objects.equals(left, right)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        this.left = left;
        this.right = right;
    }

    public void setLeft(Node<T> left) {
        if(Objects.equals(left, this.right)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        this.left = left;
    }

    public void setRight(Node<T> right) {
        if(Objects.equals(right, this.left)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        this.right = right;
    }

    public List<Node<T>> getAsList() {
        return Stream.of(left, right)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
