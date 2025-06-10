import java.util.*;

public class topological {
    static List<Integer> scheduleTasks(int numTasks, List<int[]> dependencies) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] inDegree = new int[numTasks];

        for (int i = 0; i < numTasks; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (int[] dep : dependencies) {
            int from = dep[0];
            int to = dep[1];
            adjList.get(from).add(to);
            inDegree[to]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numTasks; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }

        List<Integer> scheduled = new ArrayList<>();

        while (!queue.isEmpty()) {
            int current = queue.poll();
            scheduled.add(current);

            for (int neighbor : adjList.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }
        return scheduled;
    }

    public static void main(String[] args) {
        int numTasks = 6;
        List<int[]> dependencies = Arrays.asList(
                new int[] { 2, 3 },
                new int[] { 3, 1 },
                new int[] { 4, 0 },
                new int[] { 4, 1 },
                new int[] { 5, 0 },
                new int[] { 5, 2 });

        try {
            List<Integer> schedule = scheduleTasks(numTasks, dependencies);
            System.out.println("Task scheduling order: " + schedule);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}