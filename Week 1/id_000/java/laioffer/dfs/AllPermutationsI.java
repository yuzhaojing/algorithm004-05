package laioffer.dfs;


import java.util.ArrayList;
import java.util.List;

public class AllPermutationsI {

    public static void main(String[] args) {
        System.out.println(permutations("abc"));
    }

    /**
     * 时间复杂度O(2^n)
     * 空间复杂度O(n)
     */
    private static List<String> permutations(String input) {
        List<String> res = new ArrayList<>();
        if (input == null) return res;

        permutationsHelper(input, res, "", 0);
        return res;
    }

    private static void permutationsHelper(String input, List<String> res, String tmp, int index) {
        if (input.length() == index) {
            res.add(tmp);
            return;
        }

        for (int i = 0; i < input.length(); i++) {
            String s = String.valueOf(input.charAt(i));
            if (tmp.contains(s)) {
                continue;
            }
            permutationsHelper(input, res, tmp.concat(s), index + 1);
        }
    }
}
