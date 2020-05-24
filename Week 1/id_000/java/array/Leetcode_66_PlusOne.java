package array;

import java.util.Arrays;

public class Leetcode_66_PlusOne {

    public static void main(String[] args) {
        int[] digits = {9, 9, 9};
        int[] ints = method2(digits);
        System.out.println("ints.toString() = " + Arrays.toString(ints));
    }

    private static int[] method1(int[] digits) {
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; --i) {
            if (i == digits.length - 1) {
                int a = digits[i] + 1;
                if (a == 10) {
                    carry++;
                    digits[i] = 0;
                    if (i == 0) {
                        int[] newArr = new int[digits.length + 1];
                        newArr[0] = 1;
                        System.arraycopy(newArr, 1, digits, 0, digits.length);
                        return newArr;
                    }
                } else {
                    digits[i] = a;
                    return digits;
                }
                continue;
            }
            if (carry > 0) {
                carry--;
                int a = digits[i] + 1;
                if (i == 0) {
                    int[] newArr = new int[digits.length + 1];
                    newArr[0] = 1;
                    System.arraycopy(newArr, 1, digits, 0, digits.length);
                    return newArr;
                }
                if (a == 10) {
                    carry++;
                    digits[i] = 0;
                } else {
                    digits[i] = a;
                    return digits;
                }
                continue;
            }
        }
        return digits;
    }

    private static int[] method2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; --i) {
            digits[i] = (digits[i] + 1) % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
