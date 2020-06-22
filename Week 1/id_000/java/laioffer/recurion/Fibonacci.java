package laioffer.recurion;

import java.util.HashMap;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fibonacci(6, new HashMap<>()));
    }

    private static int fibonacci(int n, HashMap<Integer, Integer> cache) {
        // Base Case
        if (n < 0) return -1;
        if (n <= 1) return n;

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        cache.put(n, fibonacci(n - 1, cache) + fibonacci(n - 2, cache));

        return cache.get(n);
    }
}
