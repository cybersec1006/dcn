import java.util.*;

// Job class to store job details
class Job {

    int start, end, profit;

    Job(int start, int end, int profit) {
        this.start = start;
        this.end = end;
        this.profit = profit;
    }
}

public class weighted {

    // Custom comparator to sort jobs by end time
    static class EndTimeComparator implements Comparator<Job> {
        public int compare(Job a, Job b) {
            return a.end - b.end;
        }
    }

    // Function to find the last non-conflicting job index using binary search
    static int latestNonConflict(Job[] jobs, int index) {
        int low = 0, high = index - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (jobs[mid].end <= jobs[index].start) {
                if (jobs[mid + 1].end <= jobs[index].start)
                    low = mid + 1;
                else
                    return mid;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Function to compute maximum profit using DP
    static int findMaxProfit(Job[] jobs) {
        Arrays.sort(jobs, new EndTimeComparator());

        int n = jobs.length;
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;

        for (int i = 1; i < n; i++) {
            int inclProfit = jobs[i].profit;
            int l = latestNonConflict(jobs, i);
            if (l != -1)
                inclProfit += dp[l];

            dp[i] = Math.max(inclProfit, dp[i - 1]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        Job[] jobs = {
                new Job(1, 3, 50),
                new Job(3, 5, 20),
                new Job(0, 6, 70),
                new Job(5, 7, 60),
                new Job(5, 9, 80),
                new Job(7, 8, 30),
        };

        System.out.println("Maximum Profit is " + findMaxProfit(jobs));
    }
}
