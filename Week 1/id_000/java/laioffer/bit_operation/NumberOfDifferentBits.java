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
        // 通过异或将a与b不同的位都置为1，相同的都置为0
        int k = a ^ b;
        int count = 0;

        // k != 0 等价于 k中的bit位还有为1的
        while (k != 0) {
            // 消掉最低位的1
            k = (k & (k - 1));
            count++;
        }

        return count;
    }
}
