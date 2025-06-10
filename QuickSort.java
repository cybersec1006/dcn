public class QuickSort {
    static void qs(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            qs(arr,low,pivot-1);
            qs(arr,pivot+1,high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low -1;

        for (int j=low; j<high; j++) {
            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[high];
        arr[high] = arr[i+1];
        arr[i+1] = temp;

        return i+1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};

        System.out.println("Original array: ");
        for (int val : arr) System.out.print(val + " ");

        qs(arr, 0, arr.length - 1);

        System.out.println("Sorted array: ");
        for (int val : arr) System.out.print(val + " ");
    }
}