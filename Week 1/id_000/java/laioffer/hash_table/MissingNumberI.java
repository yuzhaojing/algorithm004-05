package laioffer.hash_table;


import java.util.*;

public class MissingNumberI {

    public static void main(String[] args) {
        int[] combo = {12, 11, 10, 9, 4, 5, 1, 7, 2, 3, 8};
        System.out.println(missing1(combo));
    }

    private static int missing(int[] array) {
        if (array == null || array.length == 0) return 1;

        HashSet<Integer> set = new HashSet<>();

        for (int num : array) {
            set.add(num);
        }

        for (int i = 0; i <= array.length; i++) {
            if (!set.contains(i + 1)) return i + 1;
        }
        return -1;
    }

    private static int missing1(int[] array) {
        if (array == null || array.length == 0) return 1;

        int result = 0;

        for (int num : array) {
            result ^= num;
        }

        for (int i = 0; i <= array.length; i++) {
            result ^= i + 1;
        }

        return result;
    }
}
