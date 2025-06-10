import java.util.*;
public class test {
    static int countingInversions(int[] arr) {
        if (arr.length < 2) return 0;
        return mscount(arr, 0, arr.length - 1);
    }

    static int mscount(int[] arr, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = left + (right-left)/2;
            count += mscount(arr, left, mid);
            count += mscount(arr, mid+1, right);
            count += mcount(arr, left, mid, right);
        }
        return count;
    }

    static int mcount(int[] arr, int left, int mid, int right) {
        int larr[] = Arrays.copyOfRange(arr, left, mid+1);
        int rarr[] = Arrays.copyOfRange(arr, mid+1, right+1);

        int i=0, j=0, k=left, swaps=0;

        while (i<larr.length && j<rarr.length) {
            if (larr[i] <= rarr[j]) arr[k++] = larr[i++];
            else {
                arr[k++] = rarr[j++];
                swaps += (larr.length-1);
            }
        }

        while (i<larr.length) arr[k++] = larr[i++];
        while (j<rarr.length) arr[k++] = rarr[j++];

        return swaps;
    }

    static int compute(int[] a, int[] b) {
        int n = a.length;
        int[] posinB = new int[n+1];
        for (int i=0; i<n; i++) posinB[b[i]] = i;

        int[] mapped = new int[n];
        for (int i=0; i<n; i++) mapped[i] = posinB[a[i]];

        return countingInversions(mapped);
    }
}