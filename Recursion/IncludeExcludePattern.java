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

    /**
     * @param str1
     * @param str2
     * @return
     */
    public int longestCommonSubsequence(String str1, String str2) {
        return longestCommonSubsequenceHelper(str1, 0, str2, 0);
    }

    private int longestCommonSubsequenceHelper(String str1, int index1, String str2, int index2) {
        if (index1 == str1.length() || index2 == str2.length())
            return 0;

        if (str1.charAt(index1) == str2.charAt(index2)) {
            return 1 + longestCommonSubsequenceHelper(str1, index1 + 1, str2, index2 + 1);
        }

        return Math.max(
                longestCommonSubsequenceHelper(str1, index1 + 1, str2, index2),
                longestCommonSubsequenceHelper(str1, index1, str2, index2 + 1));
    }

    /**
     * @param array
     * @return
     */
    public int longestArithmeticSubsequence(int[] array) {
        int ans = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                ans = Math.max(
                        2 + longestArithmeticSubsequenceHelper(array, i, array[j] - array[i]), //catch here: add 2 for elements rep. by ith and jth index
                        ans);
            }
        }

        return ans;
    }

    private int longestArithmeticSubsequenceHelper(int[] arr, int k, int diff) {
        if (k < 0)
            return 0;

        int result = 0;
        for (int i = k - 1; i >= 0; i--) {
            if (arr[k] - arr[i] == diff) {
                result = Math.max(
                        result,
                        1 + longestArithmeticSubsequenceHelper(arr, i, diff)); //number in AP found
            }
        }

        return result;
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

        // longestCommonSubsequence
        String str1 = "aaaabaaaa";
        String str2 = "aabbbabaaa";
        System.out.println("Longest common subsequence between " + str1 + " and " + str2 + ": "
                + pattern.longestCommonSubsequence(str1, str2));

        // longestArithmeticSubsequence
        int[] longestArithmeticSubsequenceArray = new int[] { 0, 3, 1, 6, 2, 9, 7 };
        System.out.println("Longest Arithmetic Subsequence: "
                + pattern.longestArithmeticSubsequence(longestArithmeticSubsequenceArray));
    }
}
