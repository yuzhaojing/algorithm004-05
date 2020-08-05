package laioffer.stringII;

public class DecompressStringII {

    public static void main(String[] args) {
        System.out.println(new DecompressStringII().decompress2("x2y0i0z3"));
    }

    /**
     * 需要考虑一下几点
     * 1.数字的长度大于一位
     * 2.inplace的方法和StringBuilder的方法
     * 3.如果将数字字符转成int(可能是多位数字字符 exam: ['1', '2', '3'] -> 123)
     *
     * time = O(n)
     *
     * space = O(n)
     */
    public String decompress(String input) {
        // Write your solution here
        if (input == null || input.length() <= 1) {
            return input;
        }

        // 没有考虑数字大于个位数的情况
        char[] array = input.toCharArray();
        int newLength = 0;
        for (int i = 0; i < array.length; i++) {
            if (Character.isDigit(array[i])) {
                newLength += getDigit(array[i]);
            }
        }

        char[] result = new char[newLength];
        int slow = newLength - 1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (Character.isDigit(array[i])) {
                int digit = getDigit(array[i]);
                i--;
                for (int j = 0; j < digit; j++) {
                    result[slow--] = array[i];
                }
            }
        }

        return new String(result);
    }

    // StringBuilder版本
    public String decompress1(String input) {
        // Write your solution here
        if (input == null || input.length() <= 1) {
            return input;
        }

        char[] array = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (!Character.isDigit(array[i])) {
                char c = array[i++];
                for (int j = 0; j < getDigit(array[i]); j++) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }

    // 考虑数字大于个位数
    public String decompress2(String input) {
        // Write your solution here
        if (input == null || input.length() <= 1) {
            return input;
        }

        char[] array = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (!Character.isDigit(array[i])) {
                char c = array[i++];
                StringBuilder digits = new StringBuilder();
                while (i < array.length && Character.isDigit(array[i])) {
                    digits.append(array[i++]);
                }
                i--;
                int digit = getDigit(digits.toString().toCharArray());
                for (int j = 0; j < digit; j++) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }

    private int getDigit(char digit) {
        return digit - '0';
    }

    private int getDigit(char[] digits) {
        int digit = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            double a = Math.pow(10, digits.length - 1 - i);
            digit += (getDigit(digits[i]) * (int)(Math.pow(10, digits.length - 1 - i)));
        }
        return digit;
    }
}
