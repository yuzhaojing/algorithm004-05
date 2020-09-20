package laioffer.CrossTrainingIV;

public class MajorityNumberI {

    /**
     * input:  int[] array
     * output: int
     * Assume: array != null && array.length > 0
     *
     * high level: 使用摩尔投票算法解答。假设一共m个元素，里面超过1/2的元素为n个， 那么 n / m > 1 / 2
     *             此时在array中同时删除2个不同的元素，不会使得结果改变。
     *             证明：1、删除的两个元素中没有要找的元素 n / (m - 2) > n / m > 1 / 2
     *                  2、删除的两个元素中有要找的元素 (n - 1) / (m - 2) > (m / 2 - 1) / (m - 2) = 1 / 2
     * detail level:
     *  1、使用一个HashMap，里面只记录一个元素
     *  2、遍历array，每个元素判断一下和map里的元素是否相等。如果相等就count++，否则count--
     *     如果count归0，那么删除这个元素，map内没有元素，当新的元素过来直接put
     *  3、最后map中剩余的元素就是答案
     *
     * time = O(n)
     * space = O(1)
     */
    public int majority(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return Integer.MIN_VALUE;
        }

        int element = array[0];
        int count = 1;

        for (int i = 1; i < array.length; i++) {
            if (array[i] == element) {
                count++;
            } else if (count > 0) {
                count--;
            } else {
                element = array[i];
                count++;
            }
        }

        return element;
    }
}
