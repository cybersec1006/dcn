import java.util.*;

// Edge class to store graph edges
class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    // Sorting edges by weight
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

// Subset class for Union-Find
class Subset {
    int parent, rank;
}

public class KruskalAlgorithm {

    int vertices;
    List<Edge> edges = new ArrayList<>();

    public KruskalAlgorithm(int vertices) {
        this.vertices = vertices;
    }

    // Add edge to graph
    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    // Find with path compression
    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // Union by rank
    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    public void kruskalMST() {
        // Sort all edges by weight
        Collections.sort(edges);

        // Array for union-find
        Subset[] subsets = new Subset[vertices];
        for (int v = 0; v < vertices; v++) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        List<Edge> result = new ArrayList<>();

        int e = 0; // Number of edges in MST
        int i = 0; // Index for sorted edges

        while (e < vertices - 1 && i < edges.size()) {
            Edge nextEdge = edges.get(i++);

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            // If including this edge doesn't form a cycle
            if (x != y) {
                result.add(nextEdge);
                union(subsets, x, y);
                e++;
            }
        }

        // Print result
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
        }
    }

    public static void main(String[] args) {
        KruskalAlgorithm graph = new KruskalAlgorithm(4);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        graph.kruskalMST();
    }
}
