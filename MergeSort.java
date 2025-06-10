import java.util.*;

public class MergeSort {
    public static void mergesort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (right-left)/2;
            mergesort(arr, left, mid);
            mergesort(arr, mid+1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] larr = Arrays.copyOfRange(arr, left, mid+1);
        int[] rarr = Arrays.copyOfRange(arr, mid+1, right+1);

        int i=0, j=0, k=left;

        while (i<n1 && j<n2) {
            if (larr[i] <= rarr[j]) arr[k++] = larr[i++];
            else arr[k++] = rarr[j++];
        }

        while (i<n1) arr[k++] = larr[i++];
        while (j<n2) arr[k++] = rarr[j++];
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};

        System.out.println("Original array: " + Arrays.toString(arr));

        mergesort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:   " + Arrays.toString(arr));
    }
}