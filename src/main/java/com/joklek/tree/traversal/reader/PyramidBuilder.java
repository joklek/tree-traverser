package com.joklek.tree.traversal.reader;

import com.joklek.tree.traversal.structure.Node;
import com.joklek.tree.traversal.structure.Pyramid;
import com.joklek.tree.traversal.structure.PyramidImpl;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PyramidBuilder {
    public Pyramid<Integer> formPyramid(List<String> rowsFromFile) {
        PyramidImpl tree = new PyramidImpl();
        for (int i = 0; i < rowsFromFile.size(); i++) {
            String rawRow = rowsFromFile.get(i);
            List<Node<Integer>> nodesFromRawRow = getNodes(i, rawRow);
            tree.addRow(nodesFromRawRow);
        }
        return tree;
    }

    private List<Node<Integer>> getNodes(int index, String rawRow) {

        String[] splitNodes = rawRow.split("\\s+");
        int expectedSize = index + 1;
        if (splitNodes.length != expectedSize) {
            throw new IllegalStateException(MessageFormat.format("Expected list of size {0}, but got size {1}", expectedSize, splitNodes.length));
        }
        return Stream.of(splitNodes)
                .map(Integer::parseInt)
                .map(Node::new)
                .collect(Collectors.toList());
    }
}
