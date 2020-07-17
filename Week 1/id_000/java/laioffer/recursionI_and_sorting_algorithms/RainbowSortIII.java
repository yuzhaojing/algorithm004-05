package laioffer.recursionI_and_sorting_algorithms;

import java.util.Arrays;

public class RainbowSortIII {

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 5, 2, 1};
        new RainbowSortIII().rainbowSortIII(nums, 5);
        System.out.println(Arrays.toString(nums));
    }

//    public int[] rainbowSortIII(int[] array, int k) {
//        // Write your solution here
//        if (array == null || array.length <= 1) {
//            return array;
//        }
//
//        int[] indics = new int[k];
//        indics[k - 1] = array.length - 1;
//
//        int right = k - 1;
//        int left = right - 1;
//
//        while (indics[left] <= indics[right]) {
//            int currentNum = array[indics[left]];
//            if (currentNum < k - 1) {
//                for (int i = 0; i < k - 1 - currentNum; i++) {
//                    swap(array, indics[left - i], indics[left - i - 1]);
//                    indics[left - i]++;
//                }
//                indics[currentNum - 1]++;
//            } else if (currentNum == k - 1) {
//                indics[left]++;
//            } else {
//                swap(array, indics[left], indics[right]--);
//            }
//        }
//
//        return array;
//    }

    // 定义挡板数组，有多少个不同的类型，数组长度就为多少
    // 由于题意为k个数为1～k，数组为0～k-1，所以每个挡板对应的元素为index + 1
    public int[] rainbowSortIII(int[] array, int k) {
        // Write your solution here
        if (array == null || array.length <= 1) {
            return array;
        }

        int[] indics = new int[k];

        int leftBound = k - 2;
        int rightBound = k - 1;
        indics[rightBound] = array.length - 1;

        while (indics[leftBound] <= indics[rightBound]) {
            int currentNum = array[indics[leftBound]];
            int target = leftBound + 1;
            if (currentNum < target) {
                for (int i = 0; i < target - currentNum; i++) {
                    swap(array, indics[leftBound - i], indics[leftBound - i - 1]);
                    indics[leftBound - i]++;
                }
                indics[currentNum - 1]++;
            } else if (currentNum == target) {
                indics[leftBound]++;
            } else {
                swap(array, indics[leftBound], indics[rightBound]--);
            }
        }

        return array;
    }

    public void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
