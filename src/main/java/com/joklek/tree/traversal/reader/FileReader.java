package com.joklek.tree.traversal.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {

    public List<String> readPyramid(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("File with name \"%s\" does not exist", path), e);
        }
    }
}
