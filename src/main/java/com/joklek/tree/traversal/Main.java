package com.joklek.tree.traversal;

import com.joklek.tree.traversal.reader.FileReader;
import com.joklek.tree.traversal.reader.PyramidBuilder;
import com.joklek.tree.traversal.structure.Pyramid;
import com.joklek.tree.traversal.structure.Node;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        String filePath = args.length != 0 ? args[0] : "source.txt";
        FileReader fileReader = new FileReader();
        PyramidBuilder pyramidBuilder = new PyramidBuilder();
        BruteCrawler crawler = new BruteCrawler();

        List<String> strings;
        try {
            strings = fileReader.readPyramid(filePath);
        } catch (Exception e) {
            System.out.printf("Can't find file with path %s%n", filePath);
            return;
        }
        Pyramid<Integer> pyramid = pyramidBuilder.formPyramid(strings);
        List<Node<Integer>> winningList = crawler.winningNodes(pyramid);
        int winningSum = winningList.stream().mapToInt(Node::getValue).sum();
        System.out.printf("Max sum: %d%n" +
                          "Path: %s %n", winningSum, winningList.stream()
                .map(integerNode -> integerNode.getValue().toString())
                .collect(Collectors.joining(", ")));
    }
}
