public class Strings {
    String reverse(String str) {
        char[] array = str.toCharArray();
        reverseHelper(array, 0, array.length - 1);

        return String.copyValueOf(array);
    }

    private void reverseHelper(char[] array, int start, int end) {
        // base case
        if (start > end)
            return;

        char temp = array[start];
        array[start] = array[end];
        array[end] = temp;

        // recursive call
        reverseHelper(array, start + 1, end - 1);
    }

    void printSubsequences(String str) {
        System.out.println("Printing Subsequences for " + str);
        printSubsequencesHelper(str.toCharArray(), 0, new StringBuilder());
        System.out.println("Printed Subsequences for " + str);
    }

    private void printSubsequencesHelper(char[] array, int index, StringBuilder result) {
        // base case
        if (index == array.length) {
            System.out.println(result.toString());
            return;
        }

        // include
        printSubsequencesHelper(array, index + 1, result.append(array[index]));
        result.deleteCharAt(result.length() - 1);

        // exclude
        printSubsequencesHelper(array, index + 1, result);
    }

    String addTwoStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        addTwoStringsHelper(num1, num1.length() - 1, num2, num2.length() - 1, 0, sb);
        return sb.toString();
    }

    // "23" "123"
    private void addTwoStringsHelper(String num1, int index1, String num2, int index2, int carrOver,
            StringBuilder sum) {
        // base case
        if (index1 < 0 && index2 < 0 && carrOver == 0)
            return;

        // When sum is greater than max digits
        if (index1 < 0 && index2 < 0 && carrOver == 1) {
            addTwoStringsHelper(num1, index1, num2, index2, 0, sum.insert(0, carrOver));
            return;
        }

        // When second number has greater digits than first number
        if (index1 < 0 && index2 >= 0) {
            int add = Character.getNumericValue(num2.charAt(index2)) + carrOver;
            carrOver = add / 10;
            sum.insert(0, add % 10);
            addTwoStringsHelper(num1, index1, num2, index2 - 1, carrOver, sum);
            return;
        }

        // When first number has greater digits than second number
        if (index1 >= 0 && index2 < 0) {
            int add = Character.getNumericValue(num1.charAt(index1)) + carrOver;
            carrOver = add / 10;
            sum.insert(0, add % 10);
            addTwoStringsHelper(num1, index1 - 1, num2, index2, carrOver, sum);
            return;
        }

        int add = Character.getNumericValue(num1.charAt(index1)) + Character.getNumericValue(num2.charAt(index2))
                + carrOver;
        carrOver = add / 10;
        sum.insert(0, add % 10);
        addTwoStringsHelper(num1, index1 - 1, num2, index2 - 1, carrOver, sum);
    }

    public static void main(String[] args) {
        Strings strings = new Strings();
        System.out.println(strings.reverse("hello world!"));
        strings.printSubsequences("rahul");
        System.out.println(strings.addTwoStrings("9223372036854775807", "9223372036854775807"));
    }
}
