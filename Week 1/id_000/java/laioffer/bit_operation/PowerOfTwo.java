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
        // 如果number小于等于0，则一定不是2的次方值
        if (number <= 0) {
            return false;
        }

        // 如果number为2的次方值，则说明number的二进制中只有一个1
        // number - 1的话代表将唯一的一个1变为0，之后的所有0变为1
        // number     = 1000 = 8
        // number - 1 = 0111 = 7
        // number & (number - 1)  = 1000 & 0111 = 0000 = 0
        return (number & (number - 1)) == 0;
    }
}
