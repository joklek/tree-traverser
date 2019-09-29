package com.joklek.tree.traversal;

import com.joklek.tree.traversal.reader.FileReader;
import com.joklek.tree.traversal.structure.BinaryCustomTree;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "source.txt";
        FileReader fileReader = new FileReader();
        List<String> strings;
        try {
            strings = fileReader.readTree(filePath);
        } catch (Exception e) {
            System.out.printf("Can't find file with path %s%n", filePath);
            return;
        }
        BinaryCustomTree binaryCustomTree = fileReader.formTree(strings);
    }
}
