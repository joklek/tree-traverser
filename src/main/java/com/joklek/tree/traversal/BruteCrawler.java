package com.joklek.tree.traversal;

import com.joklek.tree.traversal.structure.Pyramid;
import com.joklek.tree.traversal.structure.Node;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Crawler assumes all numbers positive
 */
public class BruteCrawler {

    public List<Node<Integer>> winningNodes(Pyramid<Integer> pyramid) {
        Node<Integer> root = pyramid.getRoot();
        List<Node<Integer>> bestPath = new ArrayList<>(Collections.singletonList(root));
        List<List<Node<Integer>>> listOfPaths = traversePath(root, bestPath);
        return listOfPaths.stream().max(Comparator.comparing(nodes -> nodes.stream().mapToInt(Node::getValue).sum(), Comparator.naturalOrder()))
                .orElse(new ArrayList<>());
    }

    private List<List<Node<Integer>>> traversePath(Node<Integer> root, List<Node<Integer>> bestPath) {
        int value = root.getValue();
        List<Node<Integer>> availableNodes = root.getChildren().getAsList().stream().filter(child -> isDifferentParity(value, child.getValue())).collect(Collectors.toList());
        if(availableNodes.size() == 2) {
            // split paths
            return availableNodes.stream().flatMap(node -> {
                List<Node<Integer>> newPath = new ArrayList<>(bestPath);
                newPath.add(node);
                return traversePath(node, newPath).stream();
            }).collect(Collectors.toList());
        }
        else {
            return availableNodes.stream().findFirst().map(node -> {
                bestPath.add(node);
                return traversePath(node, bestPath);
            }).orElseGet(() -> Collections.singletonList(bestPath));
        }
    }

    private boolean isDifferentParity(int a, int b) {
        return b % 2 != a % 2;
//        return ((a ^ b) & 1) == 1; // this should be faster?
    }
}
