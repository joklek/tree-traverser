package com.joklek.tree.traversal;

import com.joklek.tree.traversal.reader.PyramidBuilder;
import com.joklek.tree.traversal.structure.Node;
import com.joklek.tree.traversal.structure.Pyramid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BruteCrawlerTest {

    private BruteCrawler crawler = new BruteCrawler();
    private PyramidBuilder pyramidBuilder = new PyramidBuilder();

    @Test
    void shouldCreateNoWinnersWhenCantReachBottom() {
        List<String> rawPyramid = Arrays.asList("1", "1 1", "1 1 1");
        Pyramid<Integer> pyramid = pyramidBuilder.formPyramid(rawPyramid);
        List<Node<Integer>> nodes = crawler.winningNodes(pyramid);
        assertTrue(nodes.isEmpty());
    }

    private static Stream<Arguments> pyramidsWithExpectedSum() {
        return Stream.of(
                Arguments.of(Arrays.asList("1"), 1),
                Arguments.of(Arrays.asList("1", "8 9", "1 5 9", "4 5 2 3"), 16),
                Arguments.of(Arrays.asList("1", "2 4", "1 5 3"), 10)
        );
    }

    @ParameterizedTest
    @MethodSource("pyramidsWithExpectedSum")
    void shouldCreateNoWinnersWhenCantReachBottom2(List<String> rawPyramid, int expectedSum) {
        Pyramid<Integer> pyramid = pyramidBuilder.formPyramid(rawPyramid);
        List<Node<Integer>> nodes = crawler.winningNodes(pyramid);
        assertFalse(nodes.isEmpty());
        assertEquals(expectedSum, nodes.stream().mapToInt(Node::getValue).sum());
    }

    @Test
    void shouldGetCorrectSumWithOneElement() {
        int actual = 10;
        List<String> rawPyramid = Arrays.asList(Integer.toString(actual));
        Pyramid<Integer> pyramid = pyramidBuilder.formPyramid(rawPyramid);
        List<Node<Integer>> nodes = crawler.winningNodes(pyramid);
        assertFalse(nodes.isEmpty());
        assertEquals(actual, nodes.stream().mapToInt(Node::getValue).sum());
    }
}