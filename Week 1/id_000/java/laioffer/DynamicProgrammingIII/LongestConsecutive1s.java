package laioffer.DynamicProgrammingIII;

public class LongestConsecutive1s {

    public static void main(String[] args) {

    }

    public int longest(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }

        int prev = array[0] == 0 ? 0 : 1;
        int maxLen = prev;

        for (int i = 1; i < array.length; i++) {
            prev = array[i] == 0 ? 0 : prev + 1;
            maxLen = Math.max(maxLen, prev);
        }

        return maxLen;
    }
}
