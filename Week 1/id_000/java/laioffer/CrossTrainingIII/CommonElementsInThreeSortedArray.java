package laioffer.CrossTrainingIII;

import java.util.*;

public class CommonElementsInThreeSortedArray {

    public static void main(String[] args) {

    }

    /**
     * input: int[] a
     *        int[] b
     *        int[] c
     * output: List<Integer>
     * Assume: a != null && a.length > 0 &&
     *         b != null && b.length > 0 &&
     *         c != null && c.length > 0
     * 如果不符合假设，没有相同元素返回空list
     *
     * high level: 使用三种方式可以做到，比较优劣势
     * detail level:
     *  1、iterative: 将一个array和另一个array进行比较，求得的公共元素再和第三个比较，依此累推
     *     time = 2n + 2n + ... + 2n = 2kn = O(kn) space = O(n)
     *  2、binary reduction: 将array进行两两分组，分别求得公共元素再重复比较，直到最后一个
     *     time = 2n * k/2 + 2n * k/4 + ... + 2n * 1 = 2nk = O(kn)
     *     space = n * k/2 + n * k/4 + ... + n * 1 = nk = O(kn)
     *  3、k-way: 由于只能pop出最小的元素，并不能同时对比不同数组的最小元素，非常难以实现
     *     time = O(knlogk) space = O(k)
     */
    public List<Integer> common(int[] a, int[] b, int[] c) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (a == null || a.length == 0 || b == null || b.length == 0 || c == null || c.length == 0) {
            return res;
        }

        int indexA = 0;
        int indexB = 0;
        int indexC = 0;

        while (indexA < a.length && indexB < b.length && indexC < c.length) {
            if (a[indexA] == b[indexB] && b[indexB] == c[indexC]) {
                res.add(a[indexA]);
                indexA++;
                indexB++;
                indexC++;
            } else if (a[indexA] <= b[indexB] && a[indexA] <= c[indexC]) {
                indexA++;
            } else if (b[indexB] <= a[indexA] && b[indexB] <= c[indexC]) {
                indexB++;
            } else {
                indexC++;
            }
        }
        return res;
    }
}
