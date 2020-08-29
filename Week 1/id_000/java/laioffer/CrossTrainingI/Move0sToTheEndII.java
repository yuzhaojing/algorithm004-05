package laioffer.CrossTrainingI;

public class Move0sToTheEndII {

    public static void main(String[] args) {

    }

    /**
     * input: array int[]
     * output: int[]
     * 假设：array != null && array.length > 0
     * 如果不符合假设，那么没有需要移动的元素，直接返回input array
     *
     * high level: 使用左右挡板，同向而行解答 (可以保证原先顺序)
     * mid level: 定义快慢指针slow，fast，在处理完不等于0的数之后，将剩下的数填上0
     *  1、slow: 在[0，slow - 1]的区间内，都是不等于0的数。
     *  2、fast: fast指针指向的元素就是当前处理的元素
     *  3、init: slow = 0, fast = 0 (初始化的时候，没有确定的元素)
     *  4、if (array[fast] != 0)  array[slow++] = array[fast++]
     *     else                   fast++
     *     最后将[slow, array.length - 1]的元素填上0
     *
     * time = O(n)
     * space = O(1)
     */
    public int[] moveZero(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }

        int slow = 0;
        int fast = 0;

        while (fast < array.length) {
            if (array[fast] != 0) {
                array[slow++] = array[fast++];
            } else {
                fast++;
            }
        }

        while (slow < array.length) {
            array[slow++] = 0;
        }

        return array;
    }
}
