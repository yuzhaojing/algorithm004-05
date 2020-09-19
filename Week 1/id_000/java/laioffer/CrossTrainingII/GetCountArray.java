package laioffer.CrossTrainingII;

import java.util.*;

public class GetCountArray {

    public static void main(String[] args) {
        int[] array = {4, 1, 3, 2};
        System.out.println(Arrays.toString(new GetCountArray().countArray(array)));
    }

    /**
     * input: array int[]
     * output: int[] (统计每个数字右边比该数字小的个数)
     * 假设：array != null && array.length > 0
     * 如果不符合假设，那么array中没有个元素，必然无解，返回空数组
     *
     * high level: 可以使用merge sort进行解答
     * mid level:
     *  1、正常的merge sort流程
     *  2、申请三个数组：indexArr用于存储排序后的数组角标，countArr用于存储对应角标的count，helper用于协助merge排序
     *
     * time = O(nlogn + n)
     * space = O(n)
     */
    public int[] countArray(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return new int[0];
        }

        // 存储角标
        int[] indexArr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            indexArr[i] = i;
        }
        // 存储每个角标对应的count，用于按原顺序返回
        int[] countArr = new int[array.length];
        mergeSort(array, new int[array.length], indexArr, countArr, 0, array.length - 1);

        return countArr;
    }

    private void mergeSort(int[] array, int[] helper, int[] indexArr, int[] countArr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(array, helper, indexArr, countArr, left, mid);
        mergeSort(array, helper, indexArr, countArr, mid + 1, right);
        merge(array, helper, indexArr, countArr, left, mid, right);
    }

    private void merge(int[] array, int[] helper, int[] indexArr, int[] countArr, int left, int mid, int right) {
        // 将indexArr中排好序的角标copy给helper
        for (int i = 0; i < indexArr.length; i++) {
            helper[i] = indexArr[i];
        }

        int leftIndex = left;
        int rightIndex = mid + 1;

        while (leftIndex <= mid && rightIndex <= right) {
            // 如果出现重复数字，需要将左边的先移动，如果先移动右边的，后面就会把这个数字计算成小于的了
            if (array[helper[leftIndex]] <= array[helper[rightIndex]]) {
                countArr[helper[leftIndex]] += rightIndex - mid - 1;
                indexArr[left++] = helper[leftIndex++];
            } else {
                indexArr[left++] = helper[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            countArr[helper[leftIndex]] += rightIndex - mid - 1;
            indexArr[left++] = helper[leftIndex++];
        }

        while (rightIndex <= right) {
            indexArr[left++] = helper[rightIndex++];
        }
    }
}
