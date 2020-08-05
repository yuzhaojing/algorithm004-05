package laioffer.stringII;

import java.util.Arrays;

public class ReOrderArray {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        System.out.println(Arrays.toString(new ReOrderArray().reorder(array)));
    }

    /**
     * 对数组进行重排序，类似于ABC123 -> A1B2C3
     * 1.首先判断输入数组长度
     * 如果是偶数，则全部作为输入进行reOrder
     * 如果是奇数，则将最后一个元素不进行reOrder计算
     * 2.reOrder中计算三个角标
     * 1|23|4|56
     * lmid: chunk 2的起始位置
     * mid: chunk 3的起始位置
     * rmid: chunk 4的起始位置
     * 3.将chunk 2与chunk 3进度reverse
     * 1|4|23|56
     * 4.递归chunk 1 && chunk 3 | chunk 2 && chunk 4
     * 14  |  2356
     */
    public int[] reorder(int[] array) {
        // Write your solution here
        if (array == null || array.length <= 1) {
            return array;
        }

        if (array.length % 2 == 0) {
            reOrder(array, 0, array.length - 1);
        } else {
            reOrder(array, 0, array.length - 2);
        }

        return array;
    }

    private void reOrder(int[] array, int left, int right) {
        int length = right - left + 1;
        if (length <= 2) {
            return;
        }
        int mid = left + length / 2;
        int lmid = left + length / 4;
        int rmid = left + length * 3 / 4;

        reverse(array, lmid, mid);
        reverse(array, mid, rmid);
        reverse(array, lmid, rmid);

        // 继承左边界，右边界为2 * chunk1 - 1
        reOrder(array, left, left + (lmid - left) * 2 - 1);
        // 继承右边界，左边界为2 * chunk1
        reOrder(array, left + (lmid - left) * 2, right);
    }

    private void reverse(int[] array, int left, int right) {
        // [left, right)
        right = right - 1;
        while (left < right) {
            swap(array, left++, right--);
        }
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
