package ru.stepan1404.second.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Graph {
    @Getter
    @Setter
    public static class Node implements Comparable<Node> {
        private Integer order;
        private Set<Node> linkedNodes;
        private Boolean visited;

        public Node(Integer order) {
            this.order = order;
            this.linkedNodes = new HashSet<>();
            this.visited = false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(order, node.order);
        }

        @Override
        public int hashCode() {
            return Objects.hash(order);
        }

        @Override
        public int compareTo(Node o) {
            return order > o.getOrder() ? 1 : order < o.getOrder() ? -1 : 0;
        }
    }

    private Set<Node> nodes;
    private final Queue<Node> traversalQueue = new PriorityQueue<>();

    public void deptFirstTraversal(int order) {
        Node nodePoint = nodes.stream().filter(node -> node.getOrder().equals(order)).findFirst().orElseThrow(RuntimeException::new);

        System.out.print(nodePoint.getOrder());
        nodePoint.setVisited(true);

        nodePoint.getLinkedNodes().forEach(node -> {
            if (!node.getVisited()) {
                deptFirstTraversal(node.getOrder());
            }
        });
    }

    public void resetVisits() {
        nodes.forEach(node -> node.setVisited(false));
    }

    public void breadthFirstTraversal(int order) {
        Node nodePoint = nodes.stream().filter(node -> node.getOrder().equals(order)).findFirst().orElseThrow(RuntimeException::new);

        System.out.print(nodePoint.getOrder());
        nodePoint.setVisited(true);

        traversalQueue.add(nodePoint);

        while (!traversalQueue.isEmpty()) {
            Node nextNode = traversalQueue.remove();

            nextNode.getLinkedNodes().forEach(node -> {
                if (!node.getVisited()) {
                    node.setVisited(true);
                    System.out.print(node.getOrder());
                    traversalQueue.add(node);
                }
            });
        }
    }
}
