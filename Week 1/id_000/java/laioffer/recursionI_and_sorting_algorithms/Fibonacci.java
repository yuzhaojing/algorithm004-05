package laioffer.recursionI_and_sorting_algorithms;

import java.util.HashMap;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(new Fibonacci().fibonacci(6));
    }

    HashMap<Integer, Integer> cache = new HashMap<>();

    /**
     * 时间复杂度
     * 未优化时为O(2^n):每个数都需要计算n-1和n-2，一共n个数,空间复杂度O(1)
     * 优化后为O(n):把计算过的数缓存在hashmap里面，每个数只计算一次，一共只需要计算n次
     * 空间复杂度
     * O(n):使用了hashmap缓存了所有的数列
     */
    public int fibonacci(int K) {
        // Write your solution here
        // Base Case
        if (K <= 0) {
            return 0;
        }

        if (K == 1) {
            return 1;
        }

        if (cache.containsKey(K)) {
            return cache.get(K);
        }

        cache.put(K, fibonacci(K - 1) + fibonacci(K - 2));

        return cache.get(K);
    }
}
