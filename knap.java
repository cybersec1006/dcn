public class knap {

    // Function to solve 0/1 Knapsack problem using DP
    static int knapsack(int[] weights, int[] values, int n, int W) {
        int[][] dp = new int[n + 1][W + 1];

        // Build table dp[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                // Base Case: if no items or weight is 0
                if (i == 0 || w == 0) dp[i][w] = 0;
                else if (weights[i - 1] <= w) dp[i][w] = Math.max(
                    values[i - 1] + dp[i - 1][w - weights[i - 1]],
                    dp[i - 1][w]
                );
                else dp[i][w] = dp[i - 1][w];
            }
        }

        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] weights = { 1, 3, 4, 5 };
        int[] values = { 10, 40, 50, 70 };
        int W = 8;
        int n = values.length;

        int maxProfit = knapsack(weights, values, n, W);
        System.out.println("Maximum value in Knapsack = " + maxProfit);
    }
}
