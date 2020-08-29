package laioffer.CrossTrainingI;

public class ArrayDeduplicationII {

    public static void main(String[] args) {

    }

    /**
     * input: array int[]
     * output: int[]
     * 假设：array != null && array.length > 2
     * 如果不符合假设，那么没有需要删除的元素，直接返回input array
     *
     * high level: 使用左右挡板，同向而行解答
     * mid level: 定义双指针slow，fast
     *  1、slow: 在[0，slow - 1]的区间内，都是需要返回的结果
     *  2、fast: fast指针指向的元素就是当前处理的元素
     *  3、init: slow = 2, fast = 2 (前两个元素必然返回，符合物理意义)
     *  4、if (input[fast] == input[slow - 1] && input[fast] == input[slow - 2]) fast++
     *     else                                input[slow++] = input[fast++]
     *     由于input array是sorted，如果input[fast] == input[slow - 2]，
     *     那么input[fast] == input[slow - 1]为必然，可以省略
     *
     * time = O(n)
     * space = O(n)
     */
    public int[] dedup(int[] array) {
        // Write your solution here
        if (array == null || array.length < 3) {
            return array;
        }

        int slow = 2;
        int fast = 2;

        while (fast < array.length) {
            if (array[slow - 2] == array[fast]) {
                fast++;
            } else {
                array[slow++] = array[fast++];
            }
        }

        int[] res = new int[slow];
        for (int i = 0; i < slow; i++) {
            res[i] = array[i];
        }

        return res;
    }
}
