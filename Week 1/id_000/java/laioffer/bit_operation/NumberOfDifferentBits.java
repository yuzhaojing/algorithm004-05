package laioffer.bit_operation;

public class NumberOfDifferentBits {

    public static void main(String[] args) {
        System.out.println(new NumberOfDifferentBits().diffBits(16, 25));
    }

    /**
     *
     */
    public int diffBits(int a, int b) {
        // Write your solution here

        // 使用a异或b，那么a和b中不一样的bit都会是1，一样的是0
        int k = a ^ b;
        int count = 0;

        // 当k中还有非0的bit的时候
        while (k != 0) {
            // 每次消掉最后面的1
            k = k & (k - 1);
            count++;
        }

        return count;
    }
}
