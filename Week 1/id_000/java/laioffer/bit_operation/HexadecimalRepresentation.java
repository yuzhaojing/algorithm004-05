package laioffer.bit_operation;

public class HexadecimalRepresentation {

    public static void main(String[] args) {
        System.out.println(new HexadecimalRepresentation().hex(235));
    }

    /**
     *
     */
    public String hex(int number) {
        // Write your solution here
        String prefix = "0x";
        if (number == 0) {
            return prefix + "0";
        }

        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int k = number % 16;
            if (k < 10) {
                sb.append((char)('0' + k));
            } else {
                sb.append((char)(k - 10 + 'A'));
            }
            number /= 16;
        }

        return prefix + sb.reverse().toString();
    }
}
