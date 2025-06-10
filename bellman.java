import java.util.*;

public class bellman {
    public static int[] bford(int n, int src, int[][] edges) {
        int max = Integer.MAX_VALUE;
        int[] dist = new int[n];
        Arrays.fill(dist, max);
        dist[src] = 0;

        for (int i=0; i< n-1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];

                if (dist[u] + wt < dist[v] && dist[u] != max) dist[v] = dist[u] + wt;
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            if (dist[u] + wt < dist[v] && dist[u] != max) {
                System.out.println("Negative cycle");
                System.exit(0);
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of vertices: ");
        int n = scanner.nextInt();

        System.out.println("Enter number of edges: ");
        int m = scanner.nextInt();

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            System.out.print("Edge " + (i + 1) + ": ");
            edges[i][0] = scanner.nextInt();
            edges[i][1] = scanner.nextInt();
            edges[i][2] = scanner.nextInt();
        }

        System.out.println("Enter src: ");
        int src = scanner.nextInt();

        int[] dist = bford(n,src,edges);

        for (int i = 0; i < n; i++) {
            System.out.print("Distance to vertex " + i + " from source " + src + ": ");
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("Infinity");
            } else {
                System.out.println(dist[i]);
            }
        }

        scanner.close();
    }
}

