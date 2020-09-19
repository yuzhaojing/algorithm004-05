package laioffer.CrossTrainingII;

import java.util.HashSet;
import java.util.Set;

public class FourSum {

    public static void main(String[] args) {
        int[] array = {3, 9, 1, 2, 3};
        System.out.println(new FourSum().exist(array, 4));
    }

    /**
     * input: array int[]
     *        target int
     * output: boolean (target是否在array中有两个数之和的解)
     * 假设：array != null && array.length > 2
     * 如果不符合假设，那么array中少于两个元素，必然无解，返回空list
     *
     * high level: 可以使用hashset进行解答
     * mid level:
     *  1、遍历数组，固定一个数字，计算剩余元素的two sum = target - num1 - num2
     *
     * time = O(n^3)
     * space = O(n)
     */
    public boolean exist(int[] array, int target) {
        // Write your solution here
        if (array == null && array.length < 4) {
            return false;
        }

        for (int i = 0; i < array.length - 3; i++) {
            for (int j = i + 1; j < array.length - 2; j++) {
                Set<Integer> set = new HashSet<>();
                int newTarget = target - array[i]- array[j];
                for (int k = j + 1; k < array.length; k++) {
                    if (set.contains(newTarget - array[k])) {
                        return true;
                    }
                    set.add(array[k]);
                }
            }
        }

        return false;
    }
}
