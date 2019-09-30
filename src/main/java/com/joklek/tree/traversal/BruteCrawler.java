package com.joklek.tree.traversal;

import com.joklek.tree.traversal.structure.Pyramid;
import com.joklek.tree.traversal.structure.Node;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implements bruteforce crawling from top to bottom while choosing nodes of opposite parity.
 * Should investigate if a bottom up crawler which would merge rows from bottom is more practical in this setup
 */
public class BruteCrawler {

    public List<Node<Integer>> winningNodes(Pyramid<Integer> pyramid) {
        Node<Integer> root = pyramid.getRoot();
        List<Node<Integer>> bestPath = new ArrayList<>(Collections.singletonList(root));
        List<List<Node<Integer>>> listOfPaths = getAllPathsFromNode(root, bestPath);
        return listOfPaths.stream()
                .filter(path -> path.size() == pyramid.getDepth()) // filters out paths that have not reached bottom
                .max(Comparator.comparing(nodes -> nodes.stream().mapToInt(Node::getValue).sum(), Comparator.naturalOrder()))
                .orElse(new ArrayList<>());
    }

    private List<List<Node<Integer>>> getAllPathsFromNode(Node<Integer> root, List<Node<Integer>> bestPath) {
        int value = root.getValue();
        List<Node<Integer>> availableNodes = root.getChildren().getAsList().stream()
                .filter(child -> isDifferentParity(value, child.getValue()))
                .collect(Collectors.toList());

        if(availableNodes.size() == 2) {
            // split paths
            return availableNodes.stream().flatMap(node -> {
                List<Node<Integer>> newPath = new ArrayList<>(bestPath);
                newPath.add(node);
                return getAllPathsFromNode(node, newPath).stream();
            }).collect(Collectors.toList());
        }
        else {
            return availableNodes.stream().findFirst().map(node -> {
                bestPath.add(node);
                return getAllPathsFromNode(node, bestPath);
            }).orElseGet(() -> Collections.singletonList(bestPath));
        }
    }

    private boolean isDifferentParity(int a, int b) {
        return b % 2 != a % 2;
    }
}
