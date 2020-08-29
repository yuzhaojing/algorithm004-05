package laioffer.CrossTrainingI;

public class ArrayDeduplicationIV {

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
     *  1、slow: 在[0，slow - 1]的区间内，都是需要返回的结果。其中slow - 1表示栈顶元素
     *  2、fast: fast指针指向的元素就是当前处理的元素
     *  3、init: slow = 0, fast = 0 (第一个元素也不是必然在结果内)
     *  4、if (slow == 0)                            array[slow++] = array[fast++]
     *     else if (array[slow - 1] == array[fast])  跳过和栈顶元素相同的所有元素,slow--
     *     else if (array[slow - 1] != array[fast])  array[slow++] = array[fast++]
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
            if (slow == 0 || array[slow - 1] != array[fast]) {
                array[slow++] = array[fast++];
            } else {
                while (fast < array.length && array[slow - 1] == array[fast]) {
                    fast++;
                }
                slow--;
            }
        }

        int[] res = new int[slow];
        for (int i = 0; i < slow; i++) {
            res[i] = array[i];
        }

        return res;
    }
}
