public class subset {

    // Function to check if subset with given sum exists
    static boolean isSubsetSum(int[] arr, int n, int sum) {
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // If sum is 0, answer is true (empty subset)
        for (int i = 0; i <= n; i++) dp[i][0] = true;

        // If no items and sum > 0, answer is false
        for (int j = 1; j <= sum; j++) dp[0][j] = false;

        // Fill the table in bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] > j) dp[i][j] = dp[i - 1][j]; // exclude current item
                else dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]]; // include or exclude
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {
        int[] arr = { 3, 34, 4, 12, 5, 2 };
        int sum = 9;
        int n = arr.length;

        if (isSubsetSum(arr, n, sum)) System.out.println(
            "Subset with sum " + sum + " exists."
        );
        else System.out.println("No subset with sum " + sum + " exists.");
    }
}
