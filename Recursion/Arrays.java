public class Arrays {
    int[] array;

    public Arrays(int[] array) {
        this.array = array;
    }

    public void print() {
        printRecursive(array, 0, array.length);
    }

    public void reverse() {
        reverseRecursive(array, 0, array.length - 1);
    }

    public void mergeSort() {
        mergeSortHelper(array, 0, array.length - 1);
    }

    public boolean binarySearch(int target) {
        return binarySearchHelper(array, target, 0, array.length - 1);
    }

    private void printRecursive(int[] arr, int index, int end) {
        // base case
        if (index == end) {
            System.out.println();
            return;
        }

        // processing
        System.out.print(arr[index] + " ");

        // Recursive call
        printRecursive(arr, index + 1, end);
    }

    private void reverseRecursive(int[] arr, int start, int end) {
        // base class
        if (start > end) {
            return;
        }

        // process
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;

        // recursive call
        reverseRecursive(arr, start + 1, end - 1);
    }

    private void mergeSortHelper(int[] arr, int start, int end) {
        // base case
        if (start >= end) {
            return;
        }

        int mid = (end - start) / 2 + start;

        // left part
        mergeSortHelper(arr, start, mid);
        // right part
        mergeSortHelper(arr, mid + 1, end);

        // merge
        mergeArray(arr, start, mid, end);
    }

    private void mergeArray(int[] arr, int start, int mid, int end) {
        int len1 = (mid - start) + 1;
        int len2 = (end - mid - 1) + 1;

        int mainArrayIndex = start;

        int[] array1 = new int[len1];
        int[] array2 = new int[len2];

        for (int i = start, j = 0; i <= mid; i++, j++) {
            array1[j] = arr[i];
        }

        for (int i = mid + 1, j = 0; i <= end; i++, j++) {
            array2[j] = arr[i];
        }

        int index1 = 0;
        int index2 = 0;

        while (index1 < len1 && index2 < len2) {
            if (array1[index1] < array2[index2]) {
                arr[mainArrayIndex] = array1[index1];
                mainArrayIndex++;
                index1++;
            } else {
                arr[mainArrayIndex] = array2[index2];
                mainArrayIndex++;
                index2++;
            }
        }

        while (index1 < len1) {
            arr[mainArrayIndex] = array1[index1];
            mainArrayIndex++;
            index1++;
        }

        while (index2 < len2) {
            arr[mainArrayIndex] = array2[index2];
            mainArrayIndex++;
            index2++;
        }
    }

    private boolean binarySearchHelper(int[] arr, int target, int start, int end) {
        if(start > end)
            return false;

        int  mid = start + (end - start) / 2;

        if(target == arr[mid])
            return true;
        else if (target > arr[mid]) {
            return binarySearchHelper(arr, target, mid + 1, end);
        } else {
            return binarySearchHelper(arr, target, start, mid - 1);
        }
    }

    public static void main(String args[]) {
        Arrays arrays = new Arrays(new int[] { 12, 3, 99, 99, 38, 27, 43, 3, 9, -1, 12, 82 });
        arrays.print();
        arrays.mergeSort();
        arrays.print();
        System.out.println(arrays.binarySearch(15));
        System.out.println(arrays.binarySearch(-1));
    }
}
