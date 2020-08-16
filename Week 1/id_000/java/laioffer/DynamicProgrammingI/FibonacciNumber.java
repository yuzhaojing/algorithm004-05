package laioffer.DynamicProgrammingI;

public class FibonacciNumber {

    public static void main(String[] args) {

    }

    /**
     * 使用dp计算fibonacci第K个的值
     *
     * time = O(n)
     *
     * space = O(n)
     * 使用了一个大小为K + 1的数组
     */
    public long fibonacci(int K) {
        // Write your solution here
        // corner case
        if (K <= 0) {
            return 0;
        }
        // 创建dp数组
        long[] M = new long[K + 1];

        // base case
        M[0] = 0;
        M[1] = 1;

        // i的物理意义: 计算第i个数的Fibonacci值
        for (int i = 2; i <= K; i++) {
            // induction rule (递推公式)
            M[i] = M[i - 1] + M[i - 2];
        }

        return M[K];
    }

    /**
     * space = O(1)
     */
    public long fibonacciBetter(int K) {
        // Write your solution here
        // corner case
        if (K <= 0) {
            return 0;
        }

        // 由于只需要回头看前面两个，所以可以使用两个变量记录
        long f1 = 0;
        long f2 = 1;

        // 计算第K个数值的时候，会先将f1 + f2计算出值，然后将其赋值给f2
        // 所以每次循环完后，第K个的值就是f2
        for (int i = 2; i <= K; i++) {
            long f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }

        return f2;
    }
}
