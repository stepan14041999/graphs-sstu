package ru.stepan1404.second;

import ru.stepan1404.second.model.Graph;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class App {
    public static void main(String... args) {
        if (args.length > 0) {
            try {
                Set<Graph.Node> nodes = new TreeSet<>();

                for (String arg : args) {
                    Integer[] futures = Arrays.stream(arg.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
                    Graph.Node first = nodes.stream().filter(node -> node.getOrder().equals(futures[0])).findFirst().orElse(new Graph.Node(futures[0]));
                    Graph.Node second = nodes.stream().filter(node -> node.getOrder().equals(futures[1])).findFirst().orElse(new Graph.Node(futures[1]));

                    first.getLinkedNodes().add(second);
                    second.getLinkedNodes().add(first);

                    nodes.add(first);
                    nodes.add(second);
                }

                Graph graph = new Graph(nodes);
                graph.deptFirstTraversal(1);
                System.out.println();

                graph.resetVisits();

                graph.breadthFirstTraversal(1);
                System.out.println();

                graph.resetVisits();

                graph.printNodeOrderOnDistance(1, 1);
                System.out.println();
            } catch (NumberFormatException ignored) {
            }
        } else {
        }
    }
}
