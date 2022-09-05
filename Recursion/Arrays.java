public class Arrays<T> {
    T[] array;

    public Arrays(T[] array) {
        this.array = array;
    }

    public void print() {
        printRecursive(array, 0);
    }

    private void printRecursive(T[] arr, int index) {
        // base case
        if (index == arr.length) {
            System.out.println();
            return;
        }

        // processing
        System.out.print(arr[index] + " ");

        // Recursive call
        printRecursive(arr, index + 1);
    }

    public void reverse() {
        reverseRecursive(array, 0, array.length - 1);
    }

    private void reverseRecursive(T[] arr, int start, int end) {
        // base class
        if(start > end) {
            return;
        }

        // process
        T temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        
        // recursive call
        reverseRecursive(arr, start + 1, end - 1);
    }

    public static void main(String args[]) {
        Arrays<Integer> arrays = new Arrays<>(new Integer[] { 1, 2, 3, 4, 5 });
        arrays.print();
        arrays.reverse();
        arrays.print();
    }
}
