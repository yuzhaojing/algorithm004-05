import java.util.Arrays;

/**
 * 1、两数之和
 */
public class Leetcode_48_RotateImage {

    public static void main(String[] args) {
//        int[] nums = {186, 419, 83, 408};
//        System.out.println(method1(nums, 6249));
    }

    private static void method1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;

        int pos1 = 0;
        int pos2 = matrix.length - 1;

        while (pos1 < pos2) {
            int add = 0;
            while (add < pos2 - pos1) {
                int temp = matrix[pos1][pos1 + add];
                matrix[pos1][pos1 + add] = matrix[pos2 - add][pos1];
                matrix[pos2 - add][pos1] = matrix[pos2][pos2 - add];
                matrix[pos2][pos2 - add] = matrix[pos1 + add][pos2];
                matrix[pos1 + add][pos2] = temp;

                add++;
            }

            pos1++;
            pos2--;
        }
    }
}
