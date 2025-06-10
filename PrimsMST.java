import java.util.*;

public class PrimsMST {
    static class Edge implements Comparable<Edge> {
        int weight, to;

        Edge(int weight, int to) {
            this.weight = weight;
            this.to = to;
        }

        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static void prim(List<List<Edge>> graph, int V) {
        boolean[] inMST = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int total = 0;

        inMST[0] = true;
        pq.addAll(graph.get(0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (inMST[current.to]) continue;

            inMST[current.to] = true;
            total += current.weight;
            System.out.println("To: " + current.to + " Weight: " + current.weight);

            for (Edge edge : graph.get(current.to)) {
                if (!inMST[edge.to]) pq.add(edge);
            }
        }
        System.out.println("Total: " + total);
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Undirected graph — add both directions

        // Edge between 0 and 1 with weight 2
        graph.get(0).add(new Edge(2, 1));
        graph.get(1).add(new Edge(2, 0));

        // Edge between 0 and 3 with weight 6
        graph.get(0).add(new Edge(6, 3));
        graph.get(3).add(new Edge(6, 0));

        // Edge between 1 and 2 with weight 3
        graph.get(1).add(new Edge(3, 2));
        graph.get(2).add(new Edge(3, 1));

        // Edge between 1 and 3 with weight 8
        graph.get(1).add(new Edge(8, 3));
        graph.get(3).add(new Edge(8, 1));

        // Edge between 1 and 4 with weight 5
        graph.get(1).add(new Edge(5, 4));
        graph.get(4).add(new Edge(5, 1));

        // Edge between 2 and 4 with weight 7
        graph.get(2).add(new Edge(7, 4));
        graph.get(4).add(new Edge(7, 2));

        // Edge between 3 and 4 with weight 9
        graph.get(3).add(new Edge(9, 4));
        graph.get(4).add(new Edge(9, 3));

        // Run Prim's algorithm
        prim(graph, V);
    }
}