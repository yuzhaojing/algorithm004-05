package laioffer.hash_table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommonNumbers {

    public static void main(String[] args) {
        int[] A = {1, 1, 2, 2, 3};
        int[] B = {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3};
        System.out.println(common(A, B));
    }

    private static List<Integer> common(int[] A, int[] B) {
        List<Integer> res = new ArrayList<>();
        if (A == null || A.length == 0 || B == null || B.length == 0) return res;

        HashMap<Integer, Integer> map = new HashMap<>();
        if (A.length < B.length) {
            for (int a : A) {
                map.put(a, map.getOrDefault(a, 0) + 1);
            }

            for (int b : B) {
                if (map.containsKey(b) && map.get(b) > 0) {
                    map.put(b, map.get(b) - 1);
                    res.add(b);
                }
            }
        } else {
            for (int b : B) {
                map.put(b, map.getOrDefault(b, 0) + 1);
            }

            for (int a : A) {
                if (map.containsKey(a) && map.get(a) > 0) {
                    map.put(a, map.get(a) - 1);
                    res.add(a);
                }
            }
        }

        return res;
    }
}
