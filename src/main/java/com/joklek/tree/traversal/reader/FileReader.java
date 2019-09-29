package com.joklek.tree.traversal.reader;

import com.joklek.tree.traversal.structure.BinaryCustomTree;
import com.joklek.tree.traversal.structure.BinaryCustomTreeImpl;
import com.joklek.tree.traversal.structure.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    public List<String> readTree(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("File with name \"%s\" does not exist", path), e);
        }
    }

    public BinaryCustomTree formTree(List<String> rowsFromFile) {
        BinaryCustomTree tree = new BinaryCustomTreeImpl();
        for (int i = 0; i < rowsFromFile.size(); i++) {
            String rawRow = rowsFromFile.get(i);
            List<Node> collect = getNodes(i, rawRow);
            tree.addRow(collect);
        }
        return tree;
    }

    private List<Node> getNodes(int index, String rawRow) {

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
