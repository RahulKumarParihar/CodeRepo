public class IncludeExcludePattern {
    /**
     * Complexity:-
     * Time: O(m + n) where m and n are length of two strings
     * Space: O(m + n) where m and n are length of two strings
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        return isSubsequenceHelper(s, s.length(), 0, t, t.length(), 0);
    }

    private boolean isSubsequenceHelper(String s, int sLen, int indexS, String t, int tLen, int indexT) {
        if (sLen == indexS && tLen == indexT)
            return true;

        if (sLen == indexS && tLen > indexT)
            return true;

        if (tLen == indexT && sLen > indexS)
            return false;

        if (s.charAt(indexS) == t.charAt(indexT)) {
            return isSubsequenceHelper(s, sLen, indexS + 1, t, tLen, indexT + 1);
        }
        return isSubsequenceHelper(s, sLen, indexS, t, tLen, indexT + 1);
    }

    /**
     * @param array
     * @return
     */
    public int longestSubsequence(int[] array) {
        return longestSubsequenceHelper(array, 0, Integer.MIN_VALUE);
    }

    private int longestSubsequenceHelper(int[] array, int index, int lastMaxElement) {
        // base case
        if (array.length == index)
            return 0;

        // include
        int include = 0;
        if (lastMaxElement == Integer.MIN_VALUE || lastMaxElement < array[index]) {
            include = 1 + longestSubsequenceHelper(array, index + 1, array[index]);
        }

        // exclude
        int exclude = longestSubsequenceHelper(array, index + 1, lastMaxElement);

        return Math.max(include, exclude);
    }

    public static void main(String[] args) {
        IncludeExcludePattern pattern = new IncludeExcludePattern();

        // isSubsequence
        String s = "abc";
        String t = "adslkjsdlbsldjdslc";
        System.out.println("Is " + s + " subsequence of " + t + ": " + pattern.isSubsequence(s, t));

        // longestSubsequence
        int[] longestSubsequenceArray = new int[] { 0, 3, 1, 6, 2, 2, 7 };
        System.out.println("Longest Subsequence: " + pattern.longestSubsequence(longestSubsequenceArray));
    }
}
