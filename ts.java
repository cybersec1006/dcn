public class ts {

    static int N = 4; // Number of cities
    static int VISITED_ALL = (1 << N) - 1;
    static int[][] dist = {
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 },
    };

    static int[][] dp = new int[1 << N][N];

    // TSP function using DP and Bitmasking
    static int tsp(int mask, int pos) {
        if (mask == VISITED_ALL)
            return dist[pos][0]; // Return to starting city

        if (dp[mask][pos] != -1)
            return dp[mask][pos];

        int ans = Integer.MAX_VALUE;

        // Try to go to any unvisited city
        for (int city = 0; city < N; city++) {
            if ((mask & (1 << city)) == 0) {
                int newAns = dist[pos][city] + tsp(mask | (1 << city), city);
                ans = Math.min(ans, newAns);
            }
        }

        return dp[mask][pos] = ans;
    }

    public static void main(String[] args) {
        // Initialize DP table with -1
        for (int i = 0; i < (1 << N); i++)
            for (int j = 0; j < N; j++)
                dp[i][j] = -1;

        int minCost = tsp(1, 0); // Starting from city 0 with mask 1
        System.out.println("Minimum TSP cost: " + minCost);
    }
}
