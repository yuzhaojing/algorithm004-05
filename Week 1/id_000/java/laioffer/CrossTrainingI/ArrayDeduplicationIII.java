package laioffer.CrossTrainingI;

public class ArrayDeduplicationIII {

    public static void main(String[] args) {

    }

    /**
     * input: array int[]
     * output: int[]
     * 假设：array != null && array.length > 1
     * 如果不符合假设，那么没有需要删除的元素，直接返回input array
     *
     * high level: 使用左右挡板，同向而行解答
     * mid level: 定义双指针slow，fast
     *  1、slow: 在[0，slow - 1]的区间内，都是需要返回的结果
     *  2、fast: fast指针指向的元素就是当前处理的元素
     *  3、init: slow = 0, fast = 0 (第一个元素也不是必然在结果内)
     *  4、if (fast == array.length - 1)            array[slow++] = array[fast++]
     *     else if (array[fast] == array[fast + 1]) 跳过同一个元素
     *     else if (array[fast] != array[fast + 1]) array[slow++] = array[fast++]
     *
     * time = O(n)
     * space = O(n)
     */
    public int[] dedup(int[] array) {
        // Write your solution here
        if (array == null || array.length < 2) {
            return array;
        }

        int slow = 0;
        int fast = 0;

        while (fast < array.length) {
            // if (fast == array.length - 1)            array[slow++] = array[fast++]
            // else if (array[fast] == array[fast + 1]) 跳过同一个元素
            // else if (array[fast] != array[fast + 1]) array[slow++] = array[fast++]

            if (fast == array.length - 1 || array[fast] != array[fast + 1]) {
                array[slow++] = array[fast++];
            } else {
                while (fast < array.length - 1 && array[fast] == array[fast + 1]) {
                    fast++;
                }
                fast++;
            }
        }

        int[] res = new int[slow];
        for (int i = 0; i < slow; i++) {
            res[i] = array[i];
        }

        return res;
    }
}
