package laioffer.stringII;

public class DecompressStringII {

    public static void main(String[] args) {
        System.out.println(new DecompressStringII().decompress1("x2y0i0z3"));
    }

    /**
     * 需要考虑一下几点
     * 1.数字的长度大于一位
     * 2.inplace的方法和StringBuilder的方法
     * 3.如果将数字字符转成int(可能是多位数字字符 exam: ['1', '2', '3'] -> 123)
     * <p>
     * time = O(n)
     * <p>
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

    /**
     * StringBuilder版本
     *
     * time = O(n)
     *
     * space = O(n)
     */
    public String decompress1(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }

        StringBuilder sb = new StringBuilder();
        int fast = 0;

        while (fast < input.length()) {
            // 每次将字符连同后面的数字一起处理，如果数据符合要求不会出现等于数字的情况
            // 如果出现了就忽略，此处可以和面试官交流
            if (!Character.isDigit(input.charAt(fast))) {
                // 记录这次需要处理字符
                char c = input.charAt(fast++);
                // 记录数字开始的角标
                int begin = fast;

                while (fast < input.length() && Character.isDigit(input.charAt(fast))) {
                    fast++;
                }

                int digit = getDigit(input, begin, fast - 1);
                for (int i = 0; i < digit; i++) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }

    private int getDigit(String input, int begin, int end) {
        int res = 0;
        for (int i = end; i >= begin; i--) {
            int digit = input.charAt(i) - '0';
            res += (digit * (int) Math.pow(10, end - i));
        }

        return res;
    }

    private int getDigit(char digit) {
        return digit - '0';
    }

    private int getDigit(char[] digits) {
        int digit = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            digit += (getDigit(digits[i]) * (int) (Math.pow(10, digits.length - 1 - i)));
        }
        return digit;
    }
}
