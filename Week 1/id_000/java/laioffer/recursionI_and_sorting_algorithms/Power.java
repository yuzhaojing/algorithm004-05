package laioffer.recursionI_and_sorting_algorithms;

public class Power {

    public static void main(String[] args) {
        System.out.println(new Power().power(2, -5));
    }

    /**
     * 时间复杂度O(logb)
     * 空间复杂度O(logb):一共logb层，每层使用了O(1)的额外空间
     */
    private long power(int a, int b) {
        // Write your solution here
        return b < 0 ? 1 / myPower(a, Math.abs(b)) : myPower(a, b);
    }

    private long myPower(int a, int b) {
        // Base Case
        if (b == 0) {
            return 1L;
        }

        if (a == 0) {
            return 0L;
        }

        // recursion rule
        long half = myPower(a, b / 2);
        if (b % 2 == 0) {
            return half * half;
        } else {
            return half * half * a;
        }
    }
}
