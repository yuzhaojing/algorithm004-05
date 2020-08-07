package laioffer.bit_operation;

public class PowerOfTwo {

    public static void main(String[] args) {
        System.out.println(new PowerOfTwo().isPowerOfTwo(16));
    }

    /**
     *
     */
    public boolean isPowerOfTwo(int number) {
        // Write your solution here
        // 当number <= 0时，必然不是2的指数次方
        // 如果number是2的指数次方的话，则它的二进制数必然只有1个1
        // 所以可以使用number & (number - 1) == 0来进行判断
        return number > 0 && (number & (number - 1)) == 0;
    }
}
