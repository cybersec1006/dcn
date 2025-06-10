import java.util.*;

public class CountingInversions {

    // Merge sort based function to count inversions
    public static int countInversions(int[] arr) {
        if (arr.length < 2) return 0;
        return mergeSortAndCount(arr, 0, arr.length - 1);
    }

    private static int mergeSortAndCount(int[] arr, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            count += mergeSortAndCount(arr, left, mid);
            count += mergeSortAndCount(arr, mid + 1, right);
            count += mergeAndCount(arr, left, mid, right);
        }
        return count;
    }

    private static int mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left, swaps = 0;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
                swaps += (leftArr.length - i);
            }
        }

        while (i < leftArr.length) arr[k++] = leftArr[i++];
        while (j < rightArr.length) arr[k++] = rightArr[j++];

        return swaps;
    }

    // Function to compute inversion count between two playlists
    public static int computeInversionBetweenPlaylists(int[] a, int[] b) {
        int n = a.length;
        int[] positionInB = new int[n + 1];
        for (int i = 0; i < n; i++) {
            positionInB[b[i]] = i;
        }

        int[] mappedOrder = new int[n];
        for (int i = 0; i < n; i++) {
            mappedOrder[i] = positionInB[a[i]];
        }

        return countInversions(mappedOrder);
    }

    public static void main(String[] args) {
        int[][] playlists = {
            {1, 2, 3, 4, 5, 6, 7, 8},
            {3, 1, 2, 5, 4, 7, 8, 6},
            {2, 3, 1, 4, 6, 5, 8, 7}
        };

        int n = playlists.length;

        for (int i = 0; i < n; i++) {
            int minInversions = Integer.MAX_VALUE;
            int recommendedUser = -1;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int inversions = computeInversionBetweenPlaylists(playlists[i], playlists[j]);
                    if (inversions < minInversions) {
                        minInversions = inversions;
                        recommendedUser = j;
                    }
                }
            }

            System.out.println("User " + (i + 1) + " should be recommended User " + (recommendedUser + 1) + "'s playlist (Inversions: " + minInversions + ")");
        }
    }
}
