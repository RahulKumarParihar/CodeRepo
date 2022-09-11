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

    public static void main(String[] args) {
        IncludeExcludePattern pattern = new IncludeExcludePattern();

        String s = "abc";
        String t = "adslkjsdlbsldjdslc";

        System.out.println("Is " + s + " subsequence of " + t + ": " + pattern.isSubsequence(s, t));
    }
}
